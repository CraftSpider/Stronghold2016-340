package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Flashlight extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Relay spike;
	public boolean enabled = false;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public Flashlight() {
    	spike = new Relay(RobotMap.FLASHLIGHT);
    }
    public void enableFlashLight() {
    	spike.set(Relay.Value.kForward);
    	enabled = true;
    }
    public void disableFlashLight() {
    	spike.set(Relay.Value.kOff);
    	enabled = false;
    }
}

