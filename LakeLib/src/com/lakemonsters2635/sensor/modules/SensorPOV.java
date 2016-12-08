package com.lakemonsters2635.sensor.modules;

import com.lakemonsters2635.sensor.interfaces.BaseSensor;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDSourceType;

public class SensorPOV extends BaseSensor<Integer>
{
	int POV;
	Joystick joystick;
	public SensorPOV(int POV, Joystick joystick)
	{
		super();
		this.POV = POV;
		this.joystick = joystick;
	}
	@Override
	public Integer sense(Object unused)
	{
		return joystick.getPOV(POV);
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
