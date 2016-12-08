package com.lakemonsters2635.actuator.interfaces;


public class ActuateTask<T> extends BaseRobotTask
{
	BaseActuator<T> actuator;
	T magnitude;
	public ActuateTask(BaseActuator<T> actuator, T magnitude) {
		super();
		this.actuator = actuator;
		this.magnitude = magnitude;
	}
	@Override
	public void run() 
	{
		super.run();
		actuator.actuate(magnitude);
	}
	
}
