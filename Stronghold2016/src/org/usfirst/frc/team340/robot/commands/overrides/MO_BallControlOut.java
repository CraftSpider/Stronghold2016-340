package org.usfirst.frc.team340.robot.commands.overrides;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MO_BallControlOut extends Command {
	private static final Logger logger = Robot.getLogger(MO_BallControlOut.class); 
	
	/**
	 * Set requirements for ball control out operation
	 * Requires harvester subsystem
	 * Will cause the harvester roller to rotate outwards
	 */
    public MO_BallControlOut() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.harvesterRollers);	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	logger.info("[initializing");
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     * Sets the rotation of the ball control out
     */
    protected void execute() {
    	Robot.harvesterRollers.setBallControl(1);
    }

    // Make this return true when this Command no longer needs to run execute()
    /**
     * Sets command to completed
     * @return boolean false
     */
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    /**
     * Stops the ball control out
     */
    protected void end() {
    	logger.info("[ending]");
    	Robot.harvesterRollers.setBallControl(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	logger.info("[interrupted]");
    	end();
    }
}
