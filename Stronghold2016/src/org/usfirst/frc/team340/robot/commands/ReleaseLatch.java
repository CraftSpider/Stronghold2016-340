package org.usfirst.frc.team340.robot.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReleaseLatch extends Command {

	private static boolean latchReleased = false;
	
	/**
	 * Set requirements for latch releasing command.
	 * Requires climber subsystem.
	 * Releases the latch which lets the arm raise
	 */
    public ReleaseLatch() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     * Releases the latch
     */
    protected void execute() {
    	if(Robot.isEndGame()) {
    		Robot.climber.releaseLatch();
    		latchReleased = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    /**
     * Sets command to completed
     * @return boolean is the latch released
     */
    protected boolean isFinished() {
        return latchReleased ? true:false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
