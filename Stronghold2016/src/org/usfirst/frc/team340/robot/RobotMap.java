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
	
	public static final int LEFT_DRIVE_TALON = 1;
	public static final int RIGHT_DRIVE_TALON = 2;
//	public static int HarvesterBallControl = 3;
	public static final int CLIMBER_MOVEMENT = 5;
	public static final int CLIMBER_WINCH = 6;
	
	public static final int CLIMBER_LATCH = 3;
	
	public static final int DRIVE_PTO = 9;
	
	//
	// CAN Talon Ports
 	//
	
	public static final int HARVESTER_SHOOTER_WHEEL_A = 3;
	public static final int HARVESTER_SHOOTER_WHEEL_B = 4;
	public static final int HARVESTER_BALL_CONTROL = 2;
	
	public static final int HARVESTER_AIMING_LEFT = 1;
	public static final int HARVESTER_AIMING_RIGHT = 5;	
	
	//
	// Solenoid Ports
	//
	
	public static final int CLIMBER_PIN_PULL = 0;
	
	//
	// Digital I/O Ports
	//
	
//	public static int HarvesterEncoderPortA = 3;
//	public static int HarvesterEncoderPortB = 4;
	
//	public static int LeftDriveEncoderPortA = 123456789;
//	public static int LeftDriveEnocderPortB = 987654321;
//	public static int RightDriveEncoderPortA = 8;
//	public static int RightDriveEncoderPortB = 9;
	
	public static final int LEFT_DRIVE_ENCODER = 10;
	
	public static final int CLIMBER_BANNER = 8;
	public static final int CLIMBER_DART_LIMIT = 9;
	
	public static final int HARVESTER_LEFT_BUMP_BOTTOM = 1;
	public static final int HARVESTER_RIGHT_BUMP_BOTTOM = 0;
	
	public static final int HARVESTER_LEFT_BUMP_TOP = 6;
	public static final int HARVESTER_RIGHT_BUMP_TOP = 7;
	
	public static final int BALL_SENSOR_LEFT = 2;
	public static final int BALL_SENSOR_RIGHT = 3;
	
	//
	// Analog I/O Ports
	//
	
	public static final int LEFT_AIM_POT = 2;
	public static final int RIGHT_AIM_POT = 3;
	//
	// SPI Ports
	//
	
	public static final int DRIVE_GYRO = 2;
	
	//
	// Constants
	//
	
	public static final int ENCODER_TICKS_PER_REV = 100;
	
	//
	// Relay ports
	//
	
	public static final int FLASHLIGHT = 2;
}
