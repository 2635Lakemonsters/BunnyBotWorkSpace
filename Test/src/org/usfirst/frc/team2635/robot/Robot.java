
package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
//import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	RobotDrive myRobot;
	Joystick stick;
	CANTalon motor1, motor2; 
	Relay spike1; 
	int autoLoopCounter;
	int autoLoop;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	motor1 = new CANTalon(12);
    	motor2 = new CANTalon(6);
    	spike1 = new Relay(0);
    	
    	myRobot = new RobotDrive(motor1,motor2);
    	stick = new Joystick(0);
    }
    
    /**
     * This function is run once each time the robot enters autonomous mode
     */
    public void autonomousInit() {
    	autoLoopCounter = 0;
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	if (autoLoop < 25)
    	{
    		myRobot.setLeftRightMotorOutputs(0.0, 0.5);
    		autoLoop++;
    	}
    	else if (autoLoop < 100 && autoLoop >= 25) {
    		myRobot.setLeftRightMotorOutputs(0.5, 0.5);
    		autoLoop++;
    	}
    	else if (autoLoop >= 100 && autoLoop < 125) {
    		
    		myRobot.setLeftRightMotorOutputs(0.75,0.0);
    				autoLoop++;
    	}
    	else if (autoLoop < 300 && autoLoop >= 125) {
    		myRobot.setLeftRightMotorOutputs(0.75, 0.75);
    		autoLoop++;
    	}
    		else if (autoLoop >= 300 && autoLoop < 425) {
    			myRobot.setLeftRightMotorOutputs(0.4, 0.85);
    			autoLoop++;
    		}
    		else if (autoLoop >= 425 && autoLoop < 600) {
    			myRobot.setLeftRightMotorOutputs(0.75, 0.75);
    			autoLoop++;
    		}
    		else if (autoLoop >= 600 && autoLoop < 725){
    			myRobot.setLeftRightMotorOutputs(0.85, 0.4);
    			autoLoop++;
    		}
    	}
    	/*if(autoLoopCounter < 250) //Check if we've completed 100 loops (approximately 2 seconds)
		{
			myRobot.drive(-0.75, 0.0); 	// drive forwards half speed
			autoLoopCounter++;
			} else {
			myRobot.drive(0.0, 0.0); 	// stop robot
		}
		*/
    
    
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
    public void teleopInit(){
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        myRobot.arcadeDrive(stick);
        
        spike1.set(null);
        }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }
    
}
