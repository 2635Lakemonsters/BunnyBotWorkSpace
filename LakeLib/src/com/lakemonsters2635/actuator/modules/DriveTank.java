package com.lakemonsters2635.actuator.modules;

import com.lakemonsters2635.actuator.interfaces.BaseDrive;

import edu.wpi.first.wpilibj.RobotDrive;
/**
 * A tank drive module. Drives a robot drive using its tankDrive method.
 * @author Tristan Thompson
 *
 */
public class DriveTank extends BaseDrive
{

	RobotDrive drive;
	public DriveTank(RobotDrive drive)
	{
		super();
		this.drive = drive;
	}
	@Override
	public boolean drive(double X, double Y)
	{
		drive.tankDrive(X,Y);
		return true;
	}
	@Override
	public boolean drive(double X, double Y, double rotation)
	{
		drive(X, Y);
		return false;
	}

}
