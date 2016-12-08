package com.lakemonsters2635.actuator.interfaces;

/**
 * An actuator that is programmatically pushed forward and backwards. Implementation examples include: <br><br>
 * A piston that locks itself one way, and must be unlocked to go the other way <br>
 * A motor that must switch into a forward or reverse gear when going forwards or backwards <br>
 * A simple car. 
 * @author Tristan
 *
 * @param <T>
 */
public abstract class BaseTwoStateActuator<T>
{
	public abstract boolean actuateForward(T magnitude);
	public abstract boolean actuateBackwards(T magnitude);
	public boolean testForward(T magnitude)
	{
		System.out.println("magnitude: " + magnitude);
		return true;
	}
	public boolean testBackwards(T magnitude)
	{
		System.out.println("magnitude:" + magnitude);
		return true;
	}
}
