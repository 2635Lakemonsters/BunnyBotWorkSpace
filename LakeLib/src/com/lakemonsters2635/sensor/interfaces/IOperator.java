package com.lakemonsters2635.sensor.interfaces;

/**
 * Interface for performing an operation on an input, with modification. Implementation examples include:<br><br>
 * Math<br>
 * Transforming a number based upon sensor input<br>
 * Giving a safety rating based upon information about a car
 * @author Tristan Thompson
 *
 * @param <IOType>
 * @param <ModifierType>
 */
public interface IOperator<IOType, ModifierType>
{
	public IOType operate(IOType input, ModifierType modifier);
}
