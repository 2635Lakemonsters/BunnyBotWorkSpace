package com.lakemonsters2635.autonomous.interfaces;

/**
 * Autonomous that returns a series of values calculated on command. Implemenation examples include:<br><br>
 * An autonomous sequence that commands a robot to turn in a series of steps given by an array.<br>
 * A state machine that advances externally.<br>
 * A car that moves in a series of steps.
 * @author Tristan Thompson
 *
 * @param <OutputType>
 * @param <IndexerType>
 */
public interface IAutonomousSequence<OutputType, IndexerType>
{
	/**
	 * 
	 * @return The next value in the sequence
	 */
	public OutputType nextInSequence();
	
	/**
	 * 
	 * @param sequenceIndex A specific point of the sequence.
	 */
	public void skipTo(IndexerType sequenceIndex);
	/**
	 * Reset the indexer.
	 */
	public void restart();
}
