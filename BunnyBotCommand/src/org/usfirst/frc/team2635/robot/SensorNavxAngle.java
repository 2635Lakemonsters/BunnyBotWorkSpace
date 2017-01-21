package org.usfirst.frc.team2635.robot;

import com.kauailabs.navx.frc.AHRS;
import com.lakemonsters2635.sensor.interfaces.BaseSensor;

import edu.wpi.first.wpilibj.PIDSourceType;

/**
 * returns the yaw sensed by the navx, from 0 to 360
 * @author LakeM
 *
 */


public class SensorNavxAngle extends BaseSensor<Double>
{
	AHRS navx;
	public SensorNavxAngle(AHRS navx) 
	{
		super();
		this.navx = navx;
	}
	@Override
	public Double sense(Object unused) 
	{
		return (double) navx.getAngle();
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
		return sense(null);
	}

}
