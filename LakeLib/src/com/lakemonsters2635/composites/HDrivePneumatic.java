package com.lakemonsters2635.composites;

import com.lakemonsters2635.actuator.interfaces.BaseActuator;
import com.lakemonsters2635.actuator.interfaces.BaseDrive;

/**
 * Like HDrive, but uses a pneumatic piston to depress the middle wheel.
 * @author localuser
 *
 */
public class HDrivePneumatic extends HDrive
{
	public BaseActuator<Boolean> piston;
	public double depressionTolerance;
	/**
	 * 
	 * @param drive The drive module.
	 * @param middleWheel Turny part of middle wheel.
	 * @param piston Depressor of the middle wheel.
	 * @param depressionTolerance Determines what value will depress the middle wheel.
	 */
	public HDrivePneumatic(BaseDrive drive, BaseActuator<Double> middleWheel,
			BaseActuator<Boolean> piston, double depressionTolerance ) 
	{
		super(drive, middleWheel);
		this.piston = piston;
		this.depressionTolerance = depressionTolerance;
	}
	/**
	 * Depresses the middle wheel if X goes above depressionTolerance. 
	 */
	@Override
	public void drive(double X, double Y, double rotation)
	{
		if(Math.abs(X) > depressionTolerance)
		{
			piston.actuate(true);
			middleWheel.actuate(X);
		}
		else
		{
			piston.actuate(false);
			middleWheel.actuate(0.0);
		}
		drive.drive(rotation, Y);

	}
}
