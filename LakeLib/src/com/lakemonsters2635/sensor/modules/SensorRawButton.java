package com.lakemonsters2635.sensor.modules;

import com.lakemonsters2635.sensor.interfaces.BaseSensor;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDSourceType;

public class SensorRawButton extends BaseSensor<Boolean>
{
	int button;
	Joystick joystick;
	public SensorRawButton(int button, Joystick joystick)
	{
		super();
		this.button = button;
		this.joystick = joystick;
	}
	@Override
	public Boolean sense(Object unused)
	{
		return joystick.getRawButton(button);
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
		return null;
	}
	@Override
	public double pidGet()
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
