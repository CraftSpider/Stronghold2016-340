package org.usfirst.frc.team340.robot.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BallControlOff extends Command {
	
	Logger logger = Robot.getLogger(BallControlOff.class);
	
	/**
	 * Set requirements for ball control off command
	 * Requires harvester subsystem
	 * Turns off the ball control
	 */
    public BallControlOff() {
    	
    	requires(Robot.harvesterRollers);
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	logger.info("[Initializing]");
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     * Turns off the ball control
     */
    protected void execute() {
    	Robot.harvesterRollers.setBallControl(0);
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	logger.info("Interrupted");
    }
}
