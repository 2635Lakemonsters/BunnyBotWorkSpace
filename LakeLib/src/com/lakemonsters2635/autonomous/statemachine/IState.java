package com.lakemonsters2635.autonomous.statemachine;

/**
 * A state for the state machine. Implementation examples include:<br><br>
 * A state that moves a motor forward until it reaches a certain limit, where it then returns a new state.<br>
 * A robot that heats coffee until it reaches a certain temperature.<br>
 * A car that drives until it senses that it has hit a wall<br>
 * @author Tristan Thompson
 *
 */
public interface IState
{
	/**
	 * A routine that runs for the state, like moving a motor.
	 */
	public void act();
	/**
	 * Check sensed values and return a new state if the values reach the requirements for a new state, otherwise return this .
	 * @return
	 */
	public IState getState();
}
