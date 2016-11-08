package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.Timer;

public class ShooterModes {
	private static final double SHOOTER_OFF_TIME = 0.1;
	private static final double SHOOT_TIME = 100.0E-3;
	int num = 0;
	double motorSpeed;
	public double getMotorSpeed() {
		return motorSpeed;
	}
	Timer shooterTimer;
	enum singleShooterEnum {INIT,SHOOTING,STOP,RESET}
	singleShooterEnum singleShooterState = singleShooterEnum.INIT ;
	enum burstShooterEnum {INIT,SHOOTING,STOP,RESET}
	burstShooterEnum burstShooterState = burstShooterEnum.INIT;
	enum autoShooterEnum {INIT,SHOOTING,STOP,RESET}
	autoShooterEnum autoShooterState = autoShooterEnum.INIT;
	enum modesEnum {SINGLE,BURST,AUTO}
	modesEnum modesState = modesEnum.SINGLE;
	boolean nerfSwitch;
	
	public ShooterModes() {
		super();
		shooterTimer = new Timer();
	}
	
	public void setSwitch(boolean nerfS) {
		nerfS = nerfSwitch;
		if(nerfSwitch) {
			System.out.println("TRUE");
		}
	}
	public void modeChange (boolean singleButton,boolean burstButton,boolean autoButton){
		if(singleButton && !burstButton && !autoButton){
			modesState = modesEnum.SINGLE;
		} 
		else if(burstButton && !singleButton && !autoButton){
			modesState = modesEnum.BURST;
		}
		else if(autoButton && !singleButton && !burstButton) {
			modesState = modesEnum.AUTO;
			System.out.println("Switched mode to auto");
		}
	}
	
	public void update (boolean button){
		switch(modesState) {
		case SINGLE:
			this.singleUpdate(button);
			break;
		case BURST:
			this.burstUpdate(button);
			break;
		case AUTO:
			this.autoUpdate(button);
			break;
		default:
			break;
		}
	}
	
	public void singleUpdate (boolean button) {
		/*if(button && !nerfSwitch) {
			motorSpeed = 1.0;
		} else if(!button) {
			motorSpeed = 0.0;
		} else if (nerfSwitch) {
			motorSpeed = 0.0;
		}*/
		
		switch (singleShooterState){
		case INIT:
			if (button) {
				motorSpeed = 1.0;
				singleShooterState = singleShooterEnum.SHOOTING;
			}
			break;
		case SHOOTING:
			
			if (!nerfSwitch){
				motorSpeed = 0.0;
				singleShooterState = singleShooterEnum.RESET;
				System.out.println("Stopped");
			}
			
			else if(!button){singleShooterState=singleShooterEnum.INIT;}
			
			break;
		case STOP:
			if (!nerfSwitch){
				singleShooterState = singleShooterEnum.RESET;
			}
			break;
		case RESET:
			if (!button) {
				singleShooterState = singleShooterEnum.INIT;
			}
		
		default:
			break;
		}
		
		/*switch (singleShooterState){
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
			
			else if(!button){singleShooterState=singleShooterEnum.INIT;}
			
			break;
		case STOP:
			if(button){
			if (shooterTimer.hasPeriodPassed(SHOOTER_OFF_TIME)){
				singleShooterState = singleShooterEnum.RESET;
				shooterTimer.stop();
				shooterTimer.reset();
			}
			}
			break;
		case RESET:
			if (!button) {
				singleShooterState = singleShooterEnum.INIT;
			}
		
		default:
			break;
		}*/
		}
		public void burstUpdate (boolean button) {
			switch (burstShooterState){
			case INIT:
				if (button) {
					motorSpeed = 1.0;
					burstShooterState = burstShooterEnum.SHOOTING;
					shooterTimer.start();
				}
				else if(!button){
					burstShooterState=burstShooterEnum.INIT;
					motorSpeed=0.0;
					num=0;
				}
				break;
			case SHOOTING:
				if(button){
				if (shooterTimer.hasPeriodPassed(SHOOT_TIME)){
					motorSpeed = 0.0;
					burstShooterState = burstShooterEnum.STOP;
					shooterTimer.stop();
					shooterTimer.reset();
					shooterTimer.start();
				}
				}
				else if(!button){burstShooterState=burstShooterEnum.INIT;num=0;}
				else{
					//What Magic have you done???
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
		public void autoUpdate (boolean button) {
			
			if(button){
				motorSpeed=1.0;
			}else{
				motorSpeed=0.0;
			}
			
			
			
			
			/*switch (autoShooterState){
			case INIT:
				if (button) {
					motorSpeed = 1.0;
					autoShooterState = autoShooterEnum.SHOOTING;
					shooterTimer.start();
				} 
				else {
					motorSpeed = 0.0;
				}
				break;
			case SHOOTING:
				
				if (shooterTimer.hasPeriodPassed(SHOOT_TIME)){
					motorSpeed = 0.0;
					autoShooterState = autoShooterEnum.STOP;
					shooterTimer.stop();
					shooterTimer.reset();
					shooterTimer.start();
				}
				
				else if(!button){autoShooterState=autoShooterEnum.INIT;}
				
				break;
			case STOP:
				if(button){
				if (shooterTimer.hasPeriodPassed(SHOOTER_OFF_TIME)){
					autoShooterState = autoShooterEnum.INIT;
					shooterTimer.stop();
					shooterTimer.reset();
				}
				else if(!button) {
					autoShooterState = autoShooterEnum.INIT;
					shooterTimer.stop();
					shooterTimer.reset();
				}
				}
				break;
			case RESET:
					if (!button) {
						autoShooterState = autoShooterEnum.INIT;
					}
				break;
				}*/
	
			}
		}
