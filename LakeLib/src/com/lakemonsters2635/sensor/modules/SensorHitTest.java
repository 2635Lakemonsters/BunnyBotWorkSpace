package com.lakemonsters2635.sensor.modules;

import com.lakemonsters2635.sensor.interfaces.BaseSensor;

import edu.wpi.first.wpilibj.PIDSourceType;
/**
 * Converts a Double returning ISensor into a Boolean returning ISensor
 * <br><br>
 * Sense returns true if sensed value is less than or equal to the upper limit 
 * and greater than or equal to the lower limit. Use cases include:<br>
 * Detecting if the robot is within a distance to a wall<br>
 * Detecting if a line follower is on a line or not<br>
 * Deciding whether or not to display the "low fuel" light in a car
 * @author Tristan Thompson
 */
public class SensorHitTest extends BaseSensor<Boolean>
{

	BaseSensor<Double> sensor;
	Double upperLimit;
	Double lowerLimit;
	
	
	
	public SensorHitTest(BaseSensor<Double> sensor, Double upperLimit,
			Double lowerLimit)
	{
		super();
		this.sensor = sensor;
		this.upperLimit = upperLimit;
		this.lowerLimit = lowerLimit;
	}


	@Override
	public Boolean sense(Object passThrough)
	{
		Double sensedValue = sensor.sense(passThrough);		
		return sensedValue <= upperLimit && sensedValue >= lowerLimit; 
	}


	@Override
	public void setPIDSourceType(PIDSourceType pidSource)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public PIDSourceType getPIDSourceType()
	{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public double pidGet()
	{
		// TODO Auto-generated method stub
		return 0;
	}
 
}
