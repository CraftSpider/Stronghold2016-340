package org.usfirst.frc.team340.robot;
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
	// =)!!!Hi there nerds!!!(=
	
	public static int DriveLeftMotor = 0;
	public static int DriveRightMotor = 1;
	
	public static int ClawArmMotor = 2;
	public static int ClawTopMotor = 3;
	
	//
	// Solenoid Ports
	//
	
	public static int ClawPiston = 0;
	
	//
	// Digital I/O Ports
	//
	
	public static int ClawBottomSwitch = 4;
	public static int ClawTopSwitch = 5;
	
	//
	// Analog I/O Ports
	//
	
	public static int ClawArmAngleSensor = 0;
}
