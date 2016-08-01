package org.usfirst.frc.team340.robot.subsystems;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;
import org.usfirst.frc.team340.robot.RobotMap;
import org.usfirst.frc.team340.robot.commands.DriveWithXbox;

//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;

/**
 * drive code including motors and encoders.
 * 
 * @version 1.0
 */
public class Drive extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public final double CLIMB_DRIVE_SPEED = 0.5;

	// Drive motors
	public static Talon leftDrive;
	public static Talon rightDrive;

	// Drive speed variables
	private double leftMotorSpeed;
	private double rightMotorSpeed;
	
	// Drive Gyro Sensor
//	private AnalogGyro driveGyro;

	private Encoder leftDriveEncoder;
	//private Encoder rightDriveEncoder;
 
	// Clutch servo
	private Servo PTOMotor;

	// Is the clutch on or off?
	public boolean PTOState = false;

	// Logger
	Logger logger = Robot.getLogger("drive");

	private Accelerometer accel;
	private AnalogGyro gyro;
	/**
	 * Code for driving robot
	 */
	public Drive() {
		leftDrive = new Talon(RobotMap.DriveLeftMotor);
		rightDrive = new Talon(RobotMap.DriveRightMotor);
		
//		driveGyro = new AnalogGyro(RobotMap.DriveGyroPort);

//		leftDriveEncoder = new Encoder(RobotMap.LeftDriveEncoderPortA, RobotMap.LeftDriveEnocderPortB);
//		rightDriveEncoder = new Encoder(RobotMap.RightDriveEncoderPortA, RobotMap.RightDriveEncoderPortB);

		leftDriveEncoder = new Encoder(10, 11);
		
//		PTOMotor = new PWM(RobotMap.DrivePTO);
		PTOMotor = new Servo(RobotMap.DrivePTO);
				
		accel = new BuiltInAccelerometer();
		// clutchMotor.setRaw(126);
		gyro = new AnalogGyro(0);
	}

	/**
	 * Sets the default command to DriveWithXbox
	 */
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DriveWithXbox());
	}

	/**
	 * Sets left driverail motor to a given speed
	 * 
	 * @param speed
	 */
	public void setLeftDrive(double speed) {
		if (speed > 1) {
			speed = 1;
		} else if (speed < -1) {
			speed = -1;
		}
		leftDrive.set(-speed);
	}

	/**
	 * Sets right driverail motor to a given speed
	 * 
	 * @param speed
	 */
	public void setRightDrive(double speed) {
		if (speed > 1) {
			speed =	1;
		} else if (speed < -1) {
			speed = -1;
		}
		rightDrive.set(speed);
	}

	/**
	 * Sets both driverail motors to given speeds
	 * 
	 * @param leftOutput
	 * @param rightOutput
	 */
	public void setBothDrive(double leftOutput, double rightOutput) {
		setLeftDrive(leftOutput);
		setRightDrive(rightOutput);
	}

	/**
	 * Stops both driverail motors
	 */
	public void stopBothDrive() {
		setLeftDrive(0);
		setRightDrive(0);
	}
	
//	public double getGyroAngle() {
//		return driveGyro.getAngle();
//	}
//	
//	public void calibrateGyro() {
//		driveGyro.calibrate();
//	}
//	
//	public void resetGyro() {
//		driveGyro.reset();
//	}

	/**
	 * Finds state of left driverail encoder
	 * 
	 * @return double left driverail encoder count
	 */
	public double getLeftEncoder() {
//		return leftDriveEncoder.get();
		return 0;
	}

	/**
	 * Finds state of right driverail encoder
	 * 
	 * @return double right driverail encoder count
	 */
	public double getRightEncoder() {
//		return rightDriveEncoder.get();
		return 0;
	}

	/**
	 * Resets the count of the left driverail encoder
	 */
	public void resetLeftEncoder() {
//		leftDriveEncoder.reset();
		// logger.fine("Left encoder reset. Value: " + leftDriveEncoder.get());
	}

	/**
	 * Resets the count of the right driverail encoder
	 */
	public void resetRightEncoder() {
//		rightDriveEncoder.reset();
		// logger.fine("Right encoder reset. Value: " +
		// rightDriveEncoder.get());
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
	public void engagePTO() {
//		PTOMotor.setRaw(256);
		PTOMotor.set(1);
		PTOState = true;
	}

	/**
	 * Releases clutch
	 */
	public void disengagePTO() {
		PTOMotor.set(0);
		PTOState = false;
	}

	/**
	 * One joystick drive mode.
u	 * 
	 * @param moveValue
	 * @param rotateValue
	 */
	public void arcadeDrive(double moveValue, double rotateValue) {
//		System.out.println("left encoder " + leftDriveEncoder.get());
		/*if (moveValue >= 0.0) {
			moveValue = (moveValue * moveValue);

		} else {
			moveValue = -(moveValue * moveValue);

		}
		if (rotateValue >= 0.0) {
			rotateValue = (rotateValue * rotateValue);
		} else {
			rotateValue = -(rotateValue * rotateValue);
		}*/
		/*if(rotateValue >= 0.02) {
			rotateValue = Math.sqrt(rotateValue);
		} else if (rotateValue <= -0.02){
			rotateValue = -Math.sqrt(-rotateValue);
		} else  {
			rotateValue = 0;
		}*/
		
		if (moveValue > 0.0) {

			if (rotateValue > 0.0) {
				leftMotorSpeed = moveValue - rotateValue;
				rightMotorSpeed = Math.max(moveValue, rotateValue);
			}

			else {
				leftMotorSpeed = Math.max(moveValue, -rotateValue);
				rightMotorSpeed = moveValue + rotateValue;
			}
		}

		else {
			if (rotateValue > 0.0) {
				leftMotorSpeed = -Math.max(-moveValue, rotateValue);
				rightMotorSpeed = moveValue + rotateValue;
			} else {
				leftMotorSpeed = moveValue - rotateValue;
				rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
			}
		}
		setBothDrive(leftMotorSpeed, rightMotorSpeed);
	}
	
	public double getGyroAngle() {
		return gyro.getAngle();
	}
}
