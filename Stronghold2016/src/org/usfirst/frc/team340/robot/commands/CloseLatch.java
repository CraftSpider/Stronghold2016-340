package org.usfirst.frc.team340.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

/**
 * Closes the latch/clutch to change the drive control
 * from the drive train to pulling the climber
 * @deprecated no climber, no point
 */
@Deprecated
public class CloseLatch extends Command {
	
	Logger logger = Robot.getLogger(CloseLatch.class);
	/**
	 * Set requirements for latch closing command.
	 * Requires climber subsystem.
	 * Closes the latch over the arm.
	 */
    public CloseLatch() {
    	
    	requires(Robot.climber);
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	logger.info("[Initializing]");
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     * Closes the latch
     */
    protected void execute() {
    	Robot.climber.closeLatch();
    }

    // Make this return true when this Command no longer needs to run execute()
    /**
     * Sets command to completed
     * @return boolean true
     */
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	logger.info("[Ending]");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	logger.info("[Interrupted]");
    	end();
    }
}
