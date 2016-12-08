package com.lakemonsters2635.sensor.modules;

import com.lakemonsters2635.sensor.interfaces.BaseSensor;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDSourceType;

public class SensorRawJoystickAxis extends BaseSensor<Double>
{
	int axis;
	Joystick joystick;
	public SensorRawJoystickAxis(int axis, Joystick joystick)
	{
		super();
		this.axis = axis;
		this.joystick = joystick;
	}
	@Override
	public Double sense(Object unused)
	{
		return joystick.getRawAxis(axis);
	}
	@Override
	public void setPIDSourceType(PIDSourceType pidSource)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public PIDSourceType getPIDSourceType()
	{
		// TODO Auto-generated method stub
		return PIDSourceType.kDisplacement;
	}
	@Override
	public double pidGet()
	{
		// TODO Auto-generated method stub
		return sense(null);
	}
	
}
