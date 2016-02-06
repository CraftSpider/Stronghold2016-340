package org.usfirst.frc.team340.robot.subsystems;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;
import org.usfirst.frc.team340.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands
	
	Logger logger = Robot.getLogger(Climber.class);
	private Servo armLatch;
	private DigitalInput atBottom;
	
	public boolean latchState = true;
	
	/**
	 * Instantiate latch and sensor
	 */
	public Climber() {
		armLatch = new Servo(RobotMap.Latch);
		atBottom = new DigitalInput(RobotMap.BottomSensor);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * Releases latch to raise arm
     */
    public void releaseLatch() {  
    	armLatch.set(0);
    	latchState = false;
    	logger.fine("Latch Released: " + "ArmLatch=" + armLatch.get());
    }
    /**
     * Closes latch over the arm
     */
    public void closeLatch() {
    	armLatch.set(1);
    	latchState = true;
    	logger.fine("Latch Closed: " + "ArmLatch=" + armLatch.get());
    }
    
    /**
     * Checks if arm is at the bottom
     * @return boolean is the arm at bottom
     */
    public boolean isAtBottom() {
    	return atBottom.get();
    }
}