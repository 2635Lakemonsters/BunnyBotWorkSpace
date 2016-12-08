package com.lakemonsters2635.autonomous.statemachine;

/**
 * A state that does nothing. Useful for an end state.
 * @author Tristan Thompson
 *
 */
public class StateFinished implements IState
{

	@Override
	public void act()
	{
		
	}

	@Override
	public IState getState()
	{
		
		return this;
	}

}
