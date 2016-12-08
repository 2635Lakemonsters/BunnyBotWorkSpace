package com.lakemonsters2635.sensor.modules;

import com.lakemonsters2635.sensor.interfaces.BaseSensor;

import edu.wpi.first.wpilibj.PIDSourceType;

/**
 * One shot output. Same as OneShot in utils.
 * @author Tristan Thompson
 *
 */
public class SensorOneShot extends BaseSensor<Object>
{

	Object previousValue;
	Object lowValue;
	
	public SensorOneShot(Object lowValue)
	{
		this.lowValue = lowValue;
		this.previousValue = lowValue;
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
	@Override
	public Object sense(Object currentValue)
	{
		//If T is Integer, Double, etc., .equals will compare by value, not by object
				if(!currentValue.equals(previousValue))
				{
					previousValue = currentValue;
					return currentValue;
				}
				return lowValue;	
	}
}
