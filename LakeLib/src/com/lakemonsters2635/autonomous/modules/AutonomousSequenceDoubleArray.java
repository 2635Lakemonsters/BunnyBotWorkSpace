package com.lakemonsters2635.autonomous.modules;

import com.lakemonsters2635.autonomous.interfaces.IAutonomousSequence;

public class AutonomousSequenceDoubleArray implements IAutonomousSequence<Double, Integer>
{
	Integer index = -1;
	Double[] values;
	
	@Override
	/**
	 * Advance index and return value.
	 */
	public Double nextInSequence()
	{
		++index;
		if(index > values.length)
		{
			return null;
		}
		return values[index];
	}

	@Override
	public void skipTo(Integer sequenceIndex)
	{
		index = sequenceIndex;
		if(index > values.length)
		{
			System.err.println("Index: " + index + " is too high!");
		}
	}

	@Override
	public void restart()
	{
		index = -1;
		
	}

}
