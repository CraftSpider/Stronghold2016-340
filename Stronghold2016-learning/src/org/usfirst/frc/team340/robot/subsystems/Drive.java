package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;
import org.usfirst.frc.team340.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogGyro;

/**
 *Dummy subsystem for simple drive code, including motors and encoders.
 *@version 1.0
 */
public class Drive extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	// Drive motors
	private TalonSRX leftDrive;
	private TalonSRX rightDrive;
	
	private AnalogGyro gyro;
	
	// Drive speed variables
	public double leftMotorSpeed;
	public double rightMotorSpeed;
	
	public Drive() {
		leftDrive = new TalonSRX(RobotMap.DriveLeftMotor);
		rightDrive = new TalonSRX(RobotMap.DriveRightMotor);
		
		gyro = new AnalogGyro(RobotMap.DriveGyro);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveWithJoysticks());
    }
    
    public double getAngle() {
    	return gyro.getAngle();
    }
    
    public double getRate() {
    	return gyro.getRate();
    }
    
    public void resetGyro() {
    	gyro.calibrate();
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

