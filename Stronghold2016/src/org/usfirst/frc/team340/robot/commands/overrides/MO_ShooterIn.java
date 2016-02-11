package org.usfirst.frc.team340.robot.commands.overrides;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MO_ShooterIn extends Command {
	private static final Logger logger = Robot.getLogger(MO_ShooterIn.class); 

	/**
	 * Set requirements for shooter in operation
	 * Requires harvester subsystem
	 * Will cause the shooter roller to rotate inwards
	 */
    public MO_ShooterIn() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.harvester);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	logger.info("[initializing]");
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     * Sets the rotation for the shooter in
     */
    protected void execute() {
    	Robot.harvester.setShooter(-1);
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
     * Stops the shooter in
     */
    protected void end() {
    	logger.info("[ending]");
    	Robot.harvester.setShooter(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    /**
     * Ends
     */
    protected void interrupted() {
    	logger.info("[interrupted]");
    	end();
    }
}
