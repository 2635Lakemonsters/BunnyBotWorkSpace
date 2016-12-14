package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;

public class PIDDrive implements PIDOutput{
	RobotDrive drive;
	double forward;
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		drive.arcadeDrive(forward, output);
	}
	public PIDDrive(RobotDrive drive) {
		super();
		this.drive = drive;
		forward = 0;
	}
	public double getForward() {
		return forward;
	}
	public void setForward(double forward) {
		this.forward = forward;
	}
	
	
}
