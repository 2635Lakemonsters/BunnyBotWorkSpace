package com.lakemonsters2635.sensor.modules;

import com.lakemonsters2635.sensor.interfaces.BaseSensor;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Unwrap oscillating sensor values into continuous values
 * @author LakeM
 *
 */
public class SensorUnwrapper extends BaseSensor<Double>
{
	Double accumulator;
	Double previousAngle;
	Double jumpTolerance;
	BaseSensor<Double> angleGetter;
	
	public SensorUnwrapper(Double jumpTolerance, BaseSensor<Double> angleGetter) {
		super();
		accumulator = 0.0;
		previousAngle = null;
		this.jumpTolerance = jumpTolerance;
		this.angleGetter = angleGetter;
	}
	public void reset()
	{
		accumulator = 0.0;
		previousAngle = null;
	}
	@Override
	public Double sense(Object passThrough) {
		if(previousAngle == null)
		{
			//Initialization
			previousAngle = angleGetter.sense(passThrough);
			return accumulator;
		}
		Double currentAngle = angleGetter.sense(passThrough);
		Double delta = previousAngle - currentAngle;
		SmartDashboard.putNumber("Delta", delta);
		if(delta > jumpTolerance)
		{
			accumulator -= delta - jumpTolerance*2;
		}
		else if (delta < -jumpTolerance)
		{
			accumulator -= delta + jumpTolerance*2;
		}
		else
		{
			accumulator -= delta;
		}
		previousAngle = currentAngle;

		return accumulator;
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource)
	{
	}

	@Override
	public PIDSourceType getPIDSourceType()
	{
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet()
	{
		return this.sense(null);
	}
	
}
