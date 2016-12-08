package com.lakemonsters2635.sensor.modules;

import com.lakemonsters2635.sensor.interfaces.IOperator;

public class OperatorOneShot<IOType, ModifierType> implements IOperator<IOType, ModifierType>
{
	IOType previousValue;
	IOType lowValue;
	public OperatorOneShot (IOType lowValue)
	{
		this.lowValue = lowValue;
		this.previousValue = lowValue;
	}
	
	public IOType operate(IOType currentValue, ModifierType unused)
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
