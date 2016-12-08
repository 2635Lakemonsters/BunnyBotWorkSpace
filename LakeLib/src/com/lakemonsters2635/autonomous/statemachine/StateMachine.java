package com.lakemonsters2635.autonomous.statemachine;

/**
 * A state machine. Give it a starting state that is linked with other states and it will run until hitting and ending state.
 * @author Tristan Thompson
 *
 */
public class StateMachine
{
	
	IState state;
	IState finishedState;
	/**
	 * 
	 * @param startingState The beginning state
	 * @param endingState The ending state
	 */
	public StateMachine(IState startingState, IState endingState)
	{
		state = startingState;
		finishedState = endingState;
	}
	/**
	 * Run the state machine with the current state. States are guaranteed to run at least once.
	 * @return True if finished, false if not.
	 */
	public boolean run()
	{
		state.act();
		state = state.getState();
		if(state == finishedState)
		{
			return true;
		}
		
		return false;
	}
	
}
