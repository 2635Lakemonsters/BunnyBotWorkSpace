package org.usfirst.frc.team2635.robot;

//import edu.wpi.first.wpilibj.CANTalon;
import com.ctre.*; 
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Turntable{
	
PIDController PID;
Encoder encoder;
CANTalon talon;
double deltaanglescaled;
double prevanglescaled;
private static final double TurntableGearRatio=770.0/360.0;	//counts per revolution divided by degrees per revolution

	public Turntable(int encoderChannelA, int encoderChannelB, int motorChannel, double angle){
		encoder = new Encoder(encoderChannelA, encoderChannelB);
		encoder.setPIDSourceType(PIDSourceType.kDisplacement);
		talon = new CANTalon(motorChannel);
		PID = new PIDController(0, 0, 0, encoder, talon);
		PID.enable();
		prevanglescaled=angle*TurntableGearRatio;
	}

	public void update(double axis, double angle) {
		double axisscaled = axis;
		
		if(axisscaled <0.07 && axisscaled > -.07)
		{
			axisscaled=0;
		}
		
		axisscaled=axisscaled*10;
		deltaanglescaled=angle*TurntableGearRatio-prevanglescaled;
		PID.setSetpoint(PID.getSetpoint()+axisscaled+deltaanglescaled);
		prevanglescaled=angle*TurntableGearRatio;
		SmartDashboard.putNumber("setpoint", PID.getSetpoint());
		SmartDashboard.putNumber("error", PID.getError());
		SmartDashboard.putNumber("sensor position", encoder.getDistance());
		SmartDashboard.putNumber("controller", axis);
	}
	
	public void setPID(double p, double i, double d) {
		PID.setPID(p, i, d);
		SmartDashboard.putNumber("get p", PID.getP());

	}

}
