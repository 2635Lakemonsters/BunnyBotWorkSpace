package com.lakemonsters2635.sensor.modules;

import com.lakemonsters2635.sensor.interfaces.BaseSensor;

import edu.wpi.first.wpilibj.PIDSourceType;

public class SensorScaler extends BaseSensor<Double>
{
	double scaler;
	public SensorScaler(double scaler)
	{
		this.scaler = scaler;
	}
	@Override
	public Double sense(Object input)
	{
		Double castInput = 0.0;
		try
		{
			castInput = (Double) input;
		}
		catch(ClassCastException ex)
		{
			ex.printStackTrace();
			System.err.println("SensorScaler: invalid input type. Must be the same as OutputType.");
		}
		return castInput * scaler;
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
