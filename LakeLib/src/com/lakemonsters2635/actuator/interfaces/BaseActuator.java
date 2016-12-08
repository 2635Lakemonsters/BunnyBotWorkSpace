package com.lakemonsters2635.actuator.interfaces;

/**
 * Use this interface for anything non-drive related that moves. Examples of implementation include: <br>
 * An arm with limit switches <br>
 * One motor <br>
 * Two motors <br>
 * A motor with closed loop control <br>
 * A grabber that is controlled by a motor that pulls a string, and is stopped by a combination of an encoder and limit switch <br>
 * A car engine
 * @author Tristan
 *
 * @param <T> Magnitude input type
 */
public abstract class BaseActuator<T>
{
	/**
	 * Define how your actuator actuates. 
	 * @param magnitude 
	 * @return Most commonly true for no errors, false for errors.
	 */
	public abstract boolean actuate(T magnitude);
	/**
	 * Define a test mode for your actuator. Any sort of logging and motor and sensor testing should go in here. 
	 * @param magnitude
	 * @return 
	 */
	public boolean test(T magnitude)
	{
		System.out.println("Magnitude: " + magnitude);
		return true;
	}
}
