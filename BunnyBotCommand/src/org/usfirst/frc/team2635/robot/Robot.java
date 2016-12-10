package org.usfirst.frc.team2635.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;                                
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SerialPort;
import com.lakemonsters2635.sensor.modules.SensorUnwrapper;

public class Robot extends IterativeRobot{
	
	Button button;
	Command autonomousCommand;
	Relay relay;
	CANTalon motor;
	CANTalon flywheel;
	CANTalon frontLeftMotor;
	CANTalon backLeftMotor;
	CANTalon frontRightMotor;
	CANTalon backRightMotor;
	Joystick leftJoystick;
	Joystick rightJoystick;
	Joystick shootJoystick;
	RobotDrive drive;
	ShooterModes shooter;
	DigitalInput nerfSwitch;
	Encoder turntableEncoder;
	ShooterEnabled shooterEnabled;
	ShooterEnabled ShooterEnabled;
    SendableChooser chooser; 
    AHRS navx;
    SensorUnwrapper angleUnwrapper;
    CANTalon turntable;
    Turntable table;
    double wheel;
    boolean nerfSw;
	public static OI oi;
	private static final int NERF_MOTOR_CHANNEL = 7;
	private static final int NERF_FLYWHEEL_CHANNEL = 1;
	private static final int NERF_SWITCH_CHANNEL = 0;
	private static final int LEFT_JOYSTICK_CHANNEL = 1;
	private static final int RIGHT_JOYSTICK_CHANNEL = 0;
	private static final int SHOOT_JOYSTICK_CHANNEL = 2;
	private static final int TURNTABLE_MOTOR_CHANNEL = 15;
	private static final int FRONT_RIGHT_MOTOR_CHANNEL = 12;
	private static final int FRONT_LEFT_MOTOR_CHANNEL = 6;
	private static final int BACK_RIGHT_MOTOR_CHANNEL = 4;
	private static final int BACK_LEFT_MOTOR_CHANNEL = 9;
	private static final int ENCODER_CHANNEL_A = 1;
	private static final int ENCODER_CHANNEL_B = 2;

    public void robotInit(){
    	shooterEnabled = new ShooterEnabled();
    	shooterEnabled.isEnabled = false;
        motor = new CANTalon(NERF_MOTOR_CHANNEL);
        flywheel = new CANTalon(NERF_FLYWHEEL_CHANNEL); 
        leftJoystick = new Joystick(LEFT_JOYSTICK_CHANNEL);
        rightJoystick = new Joystick(RIGHT_JOYSTICK_CHANNEL);
        shootJoystick =  new Joystick(SHOOT_JOYSTICK_CHANNEL);
        button = new JoystickButton(leftJoystick,1);
        relay = new Relay(0);
        shooter = new ShooterModes(shooterEnabled);
        nerfSwitch = new DigitalInput(NERF_SWITCH_CHANNEL);
        frontLeftMotor = new CANTalon(FRONT_LEFT_MOTOR_CHANNEL);
        backLeftMotor = new CANTalon(BACK_LEFT_MOTOR_CHANNEL);
        frontRightMotor = new CANTalon(FRONT_RIGHT_MOTOR_CHANNEL);	
        backRightMotor = new CANTalon(BACK_RIGHT_MOTOR_CHANNEL);
        drive = new RobotDrive(frontLeftMotor,backLeftMotor,frontRightMotor,backRightMotor);
        navx = new AHRS(SerialPort.Port.kMXP);
        angleUnwrapper = new SensorUnwrapper(180.0, new SensorNavxAngle(navx));
        //turntable = new CANTalon(TURNTABLE_MOTOR_CHANNEL);
        //turntableEncoder = new Encoder(ENCODER_CHANNEL_A, ENCODER_CHANNEL_B);
        table = new Turntable(ENCODER_CHANNEL_A, ENCODER_CHANNEL_B, TURNTABLE_MOTOR_CHANNEL, angleUnwrapper.sense(null));
        
        SmartDashboard.putNumber("Set P", 0.011);
        SmartDashboard.putNumber("Set I", 0.001);
        SmartDashboard.putNumber("Set D", 0);
    }

    public void disabledInit(){
    	System.out.println("Why did you do that? I just wanted to be your friend!");
    }

	public void disabledPeriodic(){
		Scheduler.getInstance().run();
	}

    public void autonomousInit(){
        autonomousCommand = (Command) chooser.getSelected();
        if (autonomousCommand != null) autonomousCommand.start();
    }

    public void autonomousPeriodic(){
        Scheduler.getInstance().run();
    }

    public void teleopInit(){
        if (autonomousCommand != null) autonomousCommand.cancel();
    } 

    public void teleopPeriodic(){
        Scheduler.getInstance().run();
        nerfSw=false;
        nerfSw = nerfSwitch.get();
        wheel=leftJoystick.getRawAxis(2);
        wheel=Math.abs(wheel);     
        shooter.setSwitch(nerfSwitch.get());
        shooter.modeChange(leftJoystick.getRawButton(4),leftJoystick.getRawButton(3),leftJoystick.getRawButton(5));
        shooter.update(leftJoystick.getRawButton(1));
        shooterEnabled.isEnabled=true;
        motor.set(shooter.getMotorSpeed()/2);
        /*if(joystick.getRawButton(2)){
        flywheel.set(0.4);
        }
        else if(joystick.getRawButton(8)){
        	flywheel.set(0.0);
        }
        else if(joystick.getRawButton(6)){*/
        	flywheel.set(0.3);
        /*}
        else if(joystick.getRawButton(7)){
        	flywheel.set(0.5);
        }
        else{
        	flywheel.set(wheel/2);
        }*/
        System.out.println(wheel);
        drive.tankDrive(rightJoystick, leftJoystick );	
        //turntable.set(shootJoystick.getRawAxis(0));
        
        table.setPID(SmartDashboard.getNumber("Set P"), SmartDashboard.getNumber("Set I"), SmartDashboard.getNumber("Set D"));
        table.update(shootJoystick.getRawAxis(0), angleUnwrapper.sense(null));
                
        SmartDashboard.putNumber("Raw Angle", navx.getAngle());
        SmartDashboard.putNumber("Angle", angleUnwrapper.sense(null));
        //SmartDashboard.putNumber("Encoder Position", turntableEncoder.getDistance());
        //SmartDashboard.putNumber("Encoder Rate", turntableEncoder.getRate());
    }
    
    public void testPeriodic(){
        LiveWindow.run();
    }
}