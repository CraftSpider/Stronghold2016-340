package org.usfirst.frc.team340.robot.subsystems;

import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RightDrive extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    private TalonSRX rightDrive;
    
    public RightDrive() {
    	rightDrive = new TalonSRX(1);
    }
    
    public void setRight(double rightOut) {
    	rightDrive.set(-rightOut);
    }
    
    public void stopRight() {
    	rightDrive.set(0);
    }
}
