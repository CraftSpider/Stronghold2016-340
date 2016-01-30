package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * this is a dumb name because this mechanism will also shoot
 * HarvestShooter?
 * HaversterAndShooter?
 * 
 * todo: robotmap this and write commands
 * todo: modify as design changes (updated as of 1/20/16)
 */
public class Harvester extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	// Roller furthest from the robot
	private CANTalon outside = new CANTalon(RobotMap.HarvesterOuterMotor);
	// Roller closest to the robot
	private CANTalon inside = new CANTalon(RobotMap.HarvesterInnerMotor);
	
	// not sure what type of motor this is gonna be
	private CANTalon aiming = new CANTalon(RobotMap.HarvesterAimingMotor);
	
	private Encoder aimPosition = new Encoder(RobotMap.HarvesterEncoderPortA, RobotMap.HarvesterEncoderPortB);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * Drives the robot farthest from the robot
     * @param speed
     */
    public void driveOutside(double speed) {
    	outside.set(speed);
    }
    
    /**
     * Drives the robot closest to the robot
     * @param speed
     */
    public void driveInside(double speed) {
    	inside.set(speed);
    }
    
    /**
     * Drives the motor that actuates the harvester/shooter
     * @param speed
     */
    public void driveAiming(double speed) {
    	aiming.set(speed);
    }
    /**
     * Gets the encoder on the harvester
     * @return
     */
    public int getAim() {
    	return aimPosition.get();
    }
}
