package org.usfirst.frc.team340.robot.commands.overrides;

import edu.wpi.first.wpilibj.command.Command;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;
/**
 *
 */
public class MO_ClutchOff extends Command {

	Logger logger = Robot.getLogger(MO_ClutchOff.class);
	
	/**
	 * Set requirements for clutch operation
	 * Requires driver subsystem
	 * Disengages the clutch that drives the climbing arm mechanism
	 */
    public MO_ClutchOff() {
    	
    	requires(Robot.drive);
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	logger.info("[Initializing: MO_ClutchOff]");
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     * Disengages the clutch
     */
    protected void execute() {
    	Robot.drive.disengageClutch();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
