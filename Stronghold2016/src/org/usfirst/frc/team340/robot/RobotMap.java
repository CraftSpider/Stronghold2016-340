package org.usfirst.frc.team340.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//
	// PWM Motor Ports
	//
	
	public static int DriveLeftMotor = 1;
	public static int DriveRightMotor = 2;
//	public static int HarvesterBallControl = 3;
	public static int ClimberMovementMotor = 5;
	public static int ClimberWinchMotor = 6;
	
	public static int ClimberLatch = 0;
	public static int DriveClutch = 9;
	
	//
	// CAN Talon Ports
 	//
	
	public static int HarvesterShooterWheelA = 3;
	public static int HarvesterShooterWheelB = 4;
	public static int HarvesterBallControl = 2;
	
	public static int HarvesterAimingMotorLeft = 1;
	public static int HarvesterAimingMotorRight = 5;	
	
	//
	// Solenoid Ports
	//
	
	public static int ClimberPinPuller = 0;
	
	//
	// Digital I/O Ports
	//
	
//	public static int HarvesterEncoderPortA = 3;
//	public static int HarvesterEncoderPortB = 4;
	
//	public static int LeftDriveEncoderPortA = 123,456,789;
//	public static int LeftDriveEnocderPortB = 987654321;
//	public static int RightDriveEncoderPortA = 8;
//	public static int RightDriveEncoderPortB = 9;
	
	public static int ClimberBottomSensor = 5;
	
	public static int HarvesterBottomLeftBump = 1;
	public static int HarvesterBottomRightBump = 0;
	
	public static int HarvesterTopLeftBump = 6;
	public static int HarvesterTopRightBump = 7;
	
	public static int BallSensorLeftPort = 2;
	public static int BallSensorRightPort = 3;
	
	//
	// Analog I/O Ports
	//
	
	public static int LeftAimPot = 0;
	public static int RightAimPot = 1;
	
	//
	// SPI Ports
	//
	
	public static int DriveGyroPort = 0;
	
	//
	// Constants
	//
	
	public static int ENCODER_TICKS_PER_REV = 100;
}
