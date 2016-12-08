package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Turntable{
	
PIDController PID;
Encoder encoder;
CANTalon talon;

	public Turntable(int encoderChannelA, int encoderChannelB, int motorChannel){
		encoder = new Encoder(encoderChannelA, encoderChannelB);
		talon = new CANTalon(motorChannel);
		PID = new PIDController(0, 0, 0, encoder, talon);
	}

	public void update(double axis, double angle) {
		double setpoint = axis*1000;
		PID.setSetpoint(setpoint);
	}
	
	public void setPID(double p, double i, double d) {
		PID.setPID(p, i, d);
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
}
