package com.lakemonsters2635.sensor.modules;

import com.lakemonsters2635.sensor.interfaces.BaseSensor;

import edu.wpi.first.wpilibj.PIDSourceType;

public class SensorDummy<OutputType> extends BaseSensor<OutputType>
{
	OutputType constant;
	public SensorDummy(OutputType constant)
	{
		this.constant = constant;
	}
	@Override
	public OutputType sense(Object constant)
	{
		
		if(constant != null)
		{
			try
			{
				return (OutputType) constant;
			}
			catch(ClassCastException ex)
			{
				ex.printStackTrace();
			}
		}
		return this.constant;
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
