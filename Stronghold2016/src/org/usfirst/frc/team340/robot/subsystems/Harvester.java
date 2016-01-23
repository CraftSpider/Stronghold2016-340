package org.usfirst.frc.team340.robot.subsystems;

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
	public CANTalon outside = new CANTalon(0);
	// Roller closest to the robot
	public CANTalon inside = new CANTalon(1);
	
	// not sure what type of motor this is gonna be
	public CANTalon aiming = new CANTalon(2);
	
	public Encoder aimPosition = new Encoder(0, 1);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void driveOutside(double speed) {
    	outside.set(speed);
    }
    public void driveInside(double speed) {
    	inside.set(speed);
    }
    
    public void driveAiming(double speed) {
    	aiming.set(speed);
    }
    public int getAim() {
    	return aimPosition.get();
    }
}
