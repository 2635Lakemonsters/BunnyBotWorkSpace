package com.lakemonsters2635.actuator.modules;

import com.lakemonsters2635.actuator.interfaces.BaseActuator;

/**
 * An actuator that does nothing. Good for testing.
 * @author Tristan Thompson
 *
 * @param <InputType>
 */
public class ActuatorDummy<InputType> extends BaseActuator<InputType>
{

	@Override
	public boolean actuate(InputType magnitude)
	{
		
		return true;
	}

}
