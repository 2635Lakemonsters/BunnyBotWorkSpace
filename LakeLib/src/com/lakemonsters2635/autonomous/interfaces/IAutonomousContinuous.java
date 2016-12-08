package com.lakemonsters2635.autonomous.interfaces;

/**
 * Autonomous routine that returns a continuous value, as opposed to a series of steps. Implementation examples include:<br><br>
 * A robot that drives based on sensor values<br>
 * A self driving car!
 * @author Tristan Thompson
 *
 * @param <OutputType>
 */
public interface IAutonomousContinuous<OutputType>
{
	public OutputType getMoveValue();
}
