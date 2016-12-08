package com.lakemonsters2635.actuator.modules;

import com.lakemonsters2635.actuator.interfaces.BaseActuator;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * A class that implements a two limit actuator. Examples of this include arms that sweep within a specific range,
 * and are stopped by one of two limit switches. If a limit is hit, magnitudes in the direction of that switch will be ignored.
 * @author Tristan
 *
 */
public class ActuatorTwoLimit extends BaseActuator<Double>
{
	SpeedController actuator;
	DigitalInput upperLimit;
	DigitalInput lowerLimit;
	public ActuatorTwoLimit(SpeedController actuator, DigitalInput upperLimit, DigitalInput lowerLimit)
	{
		super();
		this.actuator = actuator;
		this.upperLimit = upperLimit;
		this.lowerLimit = lowerLimit;
	}

	@Override
	public boolean actuate(Double magnitude)
	{
		if(upperLimit.get())
		{
			if(magnitude <= 0)
			{
				actuator.set(magnitude);
			}
			else
			{
				actuator.set(0);
			}
		}
		else if(lowerLimit.get())
		{
			if(magnitude >= 0)
			{
				actuator.set(magnitude);
			}
			else
			{
				actuator.set(0);
			}
			
		}
		else
		{
			actuator.set(magnitude);
		}
		return true;
	}
	@Override
	public boolean test(Double magnitude)
	{
		super.test(magnitude);
		System.out.println("Upper limit: " + upperLimit.get());
		System.out.println("Lower limit: " + lowerLimit.get());
		return true;
	}
}
