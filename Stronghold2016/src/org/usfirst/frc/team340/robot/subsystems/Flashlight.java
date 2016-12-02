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
	
	private Relay spike;
	public boolean isOn = false;

    public void initDefaultCommand() {
    	
    }
    
    public Flashlight() {
    	spike = new Relay(RobotMap.FLASHLIGHT);
    }
    
    public void enableFlashLight() {
    	spike.set(Relay.Value.kForward);
    	isOn = true;
    }
    
    public void disableFlashLight() {
    	spike.set(Relay.Value.kOff);
    	isOn = false;
    }
}

