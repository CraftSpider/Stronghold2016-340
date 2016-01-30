package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;
import org.usfirst.frc.team340.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *drive code including motors and encoders.
 *@version 1.0
 */
public class Drive extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	// Drive motors
	private Talon leftDrive;
	private Talon rightDrive;
	
	// Drive speed variables
	private double leftMotorSpeed;
	private double rightMotorSpeed;
	
	private Encoder leftDriveEncoder;
	private Encoder rightDriveEncoder;
	
	public Drive() {
		leftDrive = new Talon(RobotMap.DriveLeftMotor);
		rightDrive = new Talon(RobotMap.DriveRightMotor);
		
		leftDriveEncoder = new Encoder(RobotMap.LeftDriveEncoderPortA, RobotMap.LeftDriveEnocderPortB);
		rightDriveEncoder = new Encoder(RobotMap.RightDriveEncoderPortA, RobotMap.RightDriveEncoderPortB);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveWithJoysticks());
    }
    
    public void setLeftDrive(double speed) {
    	if(speed > 1) {
    		speed = 1;
    	} else if(speed < -1) {
    		speed = -1;
    	}
    	leftDrive.set(speed);
    }
    
    public void setRightDrive(double speed) {
    	if(speed > 1) {
    		speed = 1;
    	} else if(speed < -1) {
    		speed = -1;
    	}
    	rightDrive.set(speed);
    }
    
    public void setBothDrive(double leftOutput, double rightOutput){
    	setLeftDrive(leftOutput);
    	setRightDrive(-rightOutput);
    }
    
    public void stopBothDrive() {
    	leftDrive.set(0);
    	rightDrive.set(0);
    }
    
    public double getLeftEncoder() {
    	return leftDriveEncoder.get();
    }
    
    public double getRightEncoder() {
    	return rightDriveEncoder.get();
    }
    
    public void resetLeftEncoder() {
    	leftDriveEncoder.reset();
    }
    
    public void resetRightEncoder() {
    	rightDriveEncoder.reset();
    }
    
    public void resetBothEncoders() {
    	resetLeftEncoder();
    	resetRightEncoder();
    }
    
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

