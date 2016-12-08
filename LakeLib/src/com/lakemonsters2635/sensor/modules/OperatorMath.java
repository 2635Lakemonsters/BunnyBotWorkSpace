package com.lakemonsters2635.sensor.modules;

import com.lakemonsters2635.sensor.interfaces.IOperator;

/**
 * An operator that does math.
 * @author Tristan Thompson
 *
 */
public class OperatorMath implements IOperator<Double, Double>
{
	public enum Operator
	{
		ADD,
		SUBTRACT,
		MULTIPLY,
		DIVIDE,
		EXPONENT,
	}
	Operator operator;
	public OperatorMath(Operator operator)
	{
		this.operator = operator;
	}
	/**
	 * Performs the operation selected in the constructor. Always input [operator] modifier.
	 * Returns null if operator is invalid.
	 */
	@Override
	public Double operate(Double input, Double modifier)
	{
		switch(operator)
		{
		case ADD:
			return input + modifier;
		case DIVIDE:
			return input / modifier;
		case EXPONENT:
			return Math.pow(input, modifier);
		case MULTIPLY:
			return input * modifier;
		case SUBTRACT:
			return input - modifier;
		default:
			break;
		
		}
		return null;
	}

}
