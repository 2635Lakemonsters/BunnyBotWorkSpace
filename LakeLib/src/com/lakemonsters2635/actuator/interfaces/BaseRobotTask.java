package com.lakemonsters2635.actuator.interfaces;

import java.util.TimerTask;
/**
 * An extension off of TimerTask that allows the programmer to find out if the task has run. 
 * Classes extending off this class should run this class's run after implementing their logic.
 * @author Tristan Thompson
 *
 */
public class BaseRobotTask extends TimerTask
{
	public boolean hasRun = false;

	@Override
	public void run() 
	{
		hasRun = true;
	}
	
}
