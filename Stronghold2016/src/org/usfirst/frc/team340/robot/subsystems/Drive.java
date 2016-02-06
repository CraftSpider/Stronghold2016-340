package org.usfirst.frc.team340.robot.subsystems;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;
import org.usfirst.frc.team340.robot.RobotMap;
import org.usfirst.frc.team340.robot.commands.DriveWithXbox;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *drive code including motors and encoders.
 *@version 1.0
 */
public class Drive extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	// Drive motors
	public static Talon leftDrive;
	public static Talon rightDrive;
	
	// Drive speed variables
	private double leftMotorSpeed;
	private double rightMotorSpeed;
	
	private Encoder leftDriveEncoder;
	private Encoder rightDriveEncoder;
	
	//Clutch servo
	private Servo clutch;
	
	//Is the clutch on or off?
	public boolean clutchState;
	
	//Logger
	Logger logger = Robot.getLogger(Drive.class);
	
	/**
	 * Code for driving robot
	 */
	public Drive() {
		leftDrive = new Talon(RobotMap.DriveLeftMotor);
		rightDrive = new Talon(RobotMap.DriveRightMotor);
		
		leftDriveEncoder = new Encoder(RobotMap.LeftDriveEncoderPortA, RobotMap.LeftDriveEnocderPortB);
		rightDriveEncoder = new Encoder(RobotMap.RightDriveEncoderPortA, RobotMap.RightDriveEncoderPortB);
		
		clutch = new Servo(RobotMap.DriveClutch);
	}
	
	/**
	 * Sets the default command to DriveWithXbox
	 */
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveWithXbox());
    }
    
    /**
     * Sets left driverail motor to a given speed
     * @param speed
     */
    public void setLeftDrive(double speed) {
    	if(speed > 1) {
    		speed = 1;
    	} else if(speed < -1) {
    		speed = -1;
    	}
    	leftDrive.set(speed);
    }
    
    /**
     * Sets right driverail motor to a given speed
     * @param speed
     */
    public void setRightDrive(double speed) {
    	if(speed > 1) {
    		speed = 1;
    	} else if(speed < -1) {
    		speed = -1;
    	}
    	rightDrive.set(speed);
    }
    
    /**
     * Sets both driverail motors to given speeds
     * @param leftOutput
     * @param rightOutput
     */
    public void setBothDrive(double leftOutput, double rightOutput){
    	setLeftDrive(leftOutput);
    	setRightDrive(-rightOutput);
    }
    
    /**
     * Stops both driverail motors
     */
    public void stopBothDrive() {
    	leftDrive.set(0);
    	rightDrive.set(0);
    }
    
    /**
     * Finds state of left driverail encoder
     * @return double left driverail encoder count
     */
    public double getLeftEncoder() {
    	return leftDriveEncoder.get();
    }
    
    /**
     * Finds state of right driverail encoder
     * @return double right driverail encoder count
     */
    public double getRightEncoder() {
    	return rightDriveEncoder.get();
    }
    
    /**
     * Resets the count of the left driverail encoder
     */
    public void resetLeftEncoder() {
    	leftDriveEncoder.reset();
    	logger.fine("Left encoder reset. Value: " + leftDriveEncoder.get());
    }
    
    /**
     * Resets the count of the right driverail encoder
     */
    public void resetRightEncoder() {
    	rightDriveEncoder.reset();
    	logger.fine("Right encoder reset. Value: " + rightDriveEncoder.get());
    }
    
    /**
     * Resets the count of both driverail encoders
     */
    public void resetBothEncoders() {
    	resetLeftEncoder();
    	resetRightEncoder();
    }
    
    /**
     * Enables the clutch servo, enabling the arm to pull up the robot
     */
    public void engageClutch() {
    	clutch.set(1);
    	clutchState = true;
    }
    
    /**
     * Releases clutch
     */
    public void disengageClutch() {
    	clutch.set(0);
    	clutchState = false;
    }
    
    /**
     * One joystick drive mode.
     * @param moveValue
     * @param rotateValue
     */
    public void arcadeDrive(double moveValue, double rotateValue){
    	if (moveValue >= 0.0){
    		moveValue = (moveValue * moveValue);	
   		} else {
   			moveValue = -(moveValue * moveValue);
   			
   		}
    	if (rotateValue >= 0.0){
    		rotateValue = (rotateValue * rotateValue);
    	} else {
    		rotateValue = -(rotateValue *rotateValue);
    	}
    	if (moveValue > 0.0) 
        {
        
            if (rotateValue > 0.0) 
            {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = Math.max(moveValue, rotateValue);
            } 
        
            else 
            {
                leftMotorSpeed = Math.max(moveValue, -rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            }
        }
        
        else 
        {
            if (rotateValue > 0.0) 
            {
                leftMotorSpeed = -Math.max(-moveValue, rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            }
            else {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
            }
        }
    	setBothDrive(leftMotorSpeed, rightMotorSpeed);
   	}
}

