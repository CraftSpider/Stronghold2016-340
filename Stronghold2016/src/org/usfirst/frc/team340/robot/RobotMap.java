package org.usfirst.frc.team340.robot;

import org.usfirst.frc.team340.robot.subsystems.Harvester;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//
	// Motor Ports
	//
	
	public static int DriveLeftMotor = 0;
	public static int DriveRightMotor = 1;
	
	public static int HarvesterOuterMotor = 2;
	public static int HarvesterInnerMotor = 3;
	public static int HarvesterAimingMotor = 4;
	
	public static int ClimberMovementMotor = 5;
	public static int ClimberWinchMotor = 6;
	
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
	
	//
	// Analog I/O Ports
	//
	
}
