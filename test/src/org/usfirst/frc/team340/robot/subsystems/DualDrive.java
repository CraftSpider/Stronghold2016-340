package org.usfirst.frc.team340.robot.subsystems;

import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DualDrive extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    private TalonSRX leftDrive;
    private TalonSRX rightDrive;
    
    public DualDrive() {
    	leftDrive = new TalonSRX(0);
    	rightDrive = new TalonSRX(1);
    }
    
    public void setLeft(double leftOut) {
    	leftDrive.set(leftOut);
    }
    
    public void setRight(double rightOut) {
    	rightDrive.set(-rightOut);
    }
    
    public void stopLeft() {
    	leftDrive.set(0);
    }
    
    public void stopRight() {
    	rightDrive.set(0);
    }
}
