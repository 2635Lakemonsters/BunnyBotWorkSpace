package com.lakemonsters2635.actuator.modules;

import com.lakemonsters2635.actuator.interfaces.BaseActuator;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * A simple actuator. Initializing the speed controller should be done outside this class. Actuating simply sets the actuator to a value. 
 * @author Tristan
 *
 */
public class ActuatorSimple extends BaseActuator<Double>
{
	SpeedController actuator;
	
	public ActuatorSimple(SpeedController actuator)
	{
		super();
		this.actuator = actuator;
	}

	@Override
	public boolean actuate(Double magnitude)
	{
		actuator.set(magnitude);
		return true;
	}


}
