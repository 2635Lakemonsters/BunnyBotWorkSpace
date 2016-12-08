package com.lakemonsters2635.composites;

import com.lakemonsters2635.actuator.interfaces.BaseActuator;
import com.lakemonsters2635.actuator.interfaces.BaseDrive;
import com.lakemonsters2635.sensor.interfaces.BaseSensor;

import edu.wpi.first.wpilibj.PIDController;

/**
 * Uses PID to drive the main drive assembly when the middle wheel is down.
 * @author localuser
 *
 */
public class HDrivePneumaticClosedLoop extends HDrivePneumatic
{
	public PIDController pid;
	public BaseSensor<Double> setPointGet;
	/**
	 * 
	 * @param drive Drive module.
	 * @param middleWheel The turny part of the middle wheel.
	 * @param piston The thing that pushes down the middle wheel.
	 * @param setPointGetter Thing to get the set point. Value is sent to the PIDController. Sensor may be shared with the PIDController.
	 * @param pid The PIDController. Is given a set point by the setPointGetter.
	 * @param depressionTolerance Determines the value at which the middle wheel will be depressed. 
	 */
	public HDrivePneumaticClosedLoop(BaseDrive drive,
			BaseActuator<Double> middleWheel, BaseActuator<Boolean> piston, BaseSensor<Double> setPointGetter, PIDController pid,
			double depressionTolerance)
	{
		super(drive, middleWheel, piston, depressionTolerance);
		this.setPointGet = setPointGetter;
		this.pid = pid;
	}
	/**
	 * Depresses the middle wheel when X goes above a certain tolerance. When the middle wheel is depressed the drive is driven by pid rather than the IDrive implementation.
	 */
	@Override
	public void drive(double X, double Y, double rotation)
	{
		if(Math.abs(X) > depressionTolerance)
		{
			piston.actuate(true);
			middleWheel.actuate(X);
			//Drive from pid rather than from drive
			pid.setSetpoint(setPointGet.sense(null));
			pid.enable();
			
		}
		else
		{
			pid.disable();
			piston.actuate(false);
			middleWheel.actuate(0.0);
			drive.drive(rotation, Y);
		}

	}

}
