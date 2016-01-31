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
	public static int HarvesterBallControl = 3;
	public static int ClimberMovementMotor = 5;
	public static int ClimberWinchMotor = 6;
	
	public static int Latch = 0;
	public static int Clutch = 4;
	
	//
	// CAN Talon Ports
 	//
	
	public static int HarvesterShooterWheelA = 1;
	public static int HarvesterShooterWheelB = 2;
	
	public static int HarvesterAimingMotorLeft = 3;
	public static int HarvesterAimingMotorRight = 4;	
	//
	// Solenoid Ports
	//
	
	public static int ClimberPinPuller = 0;
	
	//
	// Digital I/O Ports
	//
	
	public static int HarvesterEncoderPortA = 0;
	public static int HarvesterEncoderPortB = 1;
	
	public static int LeftDriveEncoderPortA = 6;
	public static int LeftDriveEnocderPortB = 7;
	public static int RightDriveEncoderPortA = 8;
	public static int RightDriveEncoderPortB = 9;
	
	public static int BottomSensor = 2;
	
	public static int HarvesterLeftBump = 3;
	public static int HarvesterRightBump = 4;
	
	//
	// Analog I/O Ports
	//
	public static int LeftAimPot = 0;
	public static int RightAimPot = 1;
}
