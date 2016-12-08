package com.lakemonsters2635.sensor.modules;

import com.lakemonsters2635.sensor.interfaces.BaseSensor;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDSourceType;

public class SensorDigitalInput extends BaseSensor<Boolean>
{
	DigitalInput digitalInput;
	SensorDigitalInput(DigitalInput digitalInput)
	{
		this.digitalInput = digitalInput;
	}

	@Override
	public Boolean sense(Object unused)
	{
		return digitalInput.get();
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
		return 0;
	}
	
	
}
