package com.lakemonsters2635.actuator.modules;

import com.lakemonsters2635.actuator.interfaces.BaseActuator;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * A boolean actuator. Good for pistons. Actuating true will set forward, actuating false will set reverse.
 * @author Tristan
 *
 */
public class ActuatorSimpleDoubleSolenoid extends BaseActuator<Boolean>
{	
	DoubleSolenoid solenoid;
	
	public ActuatorSimpleDoubleSolenoid(DoubleSolenoid solenoid)
	{
		super();
		this.solenoid = solenoid;
	}

	@Override
	public boolean actuate(Boolean magnitude)
	{
		//Set solenoid forward if true, reverse if false.
		solenoid.set(magnitude?Value.kForward:Value.kReverse);
		return true;
	}

}
