package com.lakemonsters2635.autonomous.interfaces;

/**
 * A single action. Implementation examples include:<br><br>
 * Moving a motor<br>
 * Driving a car
 * @author Tristan Thompson
 *
 * @param <ModifierType>
 */
public interface IAction<ModifierType>
{
	public ModifierType act();
}
