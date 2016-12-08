package com.lakemonsters2635.actuator.modules;

import com.lakemonsters2635.actuator.interfaces.BaseDrive;

import edu.wpi.first.wpilibj.RobotDrive;
/**
 * Arcade drive drive module. Drives a robot drive using its arcadeDrive method.
 * @author Tristan Thompson
 *
 */

public class DriveArcade extends BaseDrive
{

	RobotDrive drive;
	public DriveArcade(RobotDrive drive)
	{
		super();
		this.drive = drive;
	}
	@Override

	public boolean drive(double X, double Y)
	{
		drive.arcadeDrive(Y, X);
		return true;
	}
	@Override
	public boolean drive(double X, double Y, double rotation)
	{
		drive(X, Y);
		return true;
	}

}
