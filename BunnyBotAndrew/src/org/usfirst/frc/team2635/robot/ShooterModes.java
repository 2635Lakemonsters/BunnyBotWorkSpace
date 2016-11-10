package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.Timer;

public class ShooterModes {
	private static final double SHOOTER_OFF_TIME = 0.1;
	private static final double SHOOT_TIME = 100.0E-3;
	int num = 0;
	double motorSpeed;
	ShooterEnabled  shooterEnabled;
	public double getMotorSpeed() {
		return motorSpeed;
	}
	Timer shooterTimer;
	enum singleShooterEnum {INIT,SHOOTING,STOP,RESET}
	singleShooterEnum singleShooterState = singleShooterEnum.INIT ;
	enum burstShooterEnum {INIT,SHOOTING,STOP,RESET}
	burstShooterEnum burstShooterState = burstShooterEnum.INIT;
	public ShooterModes(ShooterEnabled shEnabled) {
		super();
		shooterEnabled.isEnabled = shEnabled;
		shooterTimer = new Timer();
	}
	
	public void singleUpdate (boolean button) {
		switch (singleShooterState){
		case INIT:
			if (button) {
				motorSpeed = 1.0;
				singleShooterState = singleShooterEnum.SHOOTING;
				shooterTimer.start();
			}
			break;
		case SHOOTING:
			if (shooterTimer.hasPeriodPassed(SHOOT_TIME)){
				motorSpeed = 0.0;
				singleShooterState = singleShooterEnum.STOP;
				shooterTimer.stop();
				shooterTimer.reset();
				shooterTimer.start();
			}
			break;
		case STOP:
			if (shooterTimer.hasPeriodPassed(SHOOTER_OFF_TIME)){
				singleShooterState = singleShooterEnum.RESET;
				shooterTimer.stop();
				shooterTimer.reset();
			}
			break;
		case RESET:
			if (!button) {
				singleShooterState = singleShooterEnum.INIT;
				shooterEnabled.isEnabled = false;
			}
		
		default:
			break;
		}
		}
		public void burstUpdate (boolean button) {
			switch (burstShooterState){
			case INIT:
				if (button) {
					motorSpeed = 1.0;
					burstShooterState = burstShooterEnum.SHOOTING;
					shooterTimer.start();
				}
				break;
			case SHOOTING:
				if (shooterTimer.hasPeriodPassed(SHOOT_TIME)){
					motorSpeed = 0.0;
					burstShooterState = burstShooterEnum.STOP;
					shooterTimer.stop();
					shooterTimer.reset();
					shooterTimer.start();
				}
				break;
			case STOP:
				if (shooterTimer.hasPeriodPassed(SHOOTER_OFF_TIME)){
					burstShooterState = burstShooterEnum.RESET;
					shooterTimer.stop();
					shooterTimer.reset();
				}
				break;
			case RESET:
				num++;
				if(num<3){burstShooterState = burstShooterEnum.INIT;}
				else{
					if(!button){
						num=0;
					burstShooterState = burstShooterEnum.INIT;
				}
				}
				
			default:
				break;
			
			}
	}
	
}
