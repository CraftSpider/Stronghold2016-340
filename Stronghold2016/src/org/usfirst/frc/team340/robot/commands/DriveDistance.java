package org.usfirst.frc.team340.robot.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Basically PID
 * <!-- DON'T USE THIS CODE -->
 * @author wundrweapon
 *
 */
public class DriveDistance extends Command {
	
	//Logger
	Logger logger = Robot.getLogger(DriveDistance.class);
	
	//Value inits
	double tolerance;
	double targetDist;
	double delta = 0;
	double prevDelta = 0;
	double changeInDelta = 0;

	/**
	 * Allows the robot to travel to a given distance
	 * @param tolerance
	 * @param targetDist
	 */
    public DriveDistance(double tolerance, double targetDist) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	
    	this.targetDist = targetDist;
    	this.tolerance = tolerance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	logger.info("[Initializing]");
    	Robot.drive.resetLeftEncoder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drive.getLeftEncoder() == (targetDist - tolerance);
    }

    // Called once after isFinished returns true
    protected void end() {
    	logger.info("[Ending]");
    	Robot.drive.stopBothDrive();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	logger.info("[Interrupted]");
    	end();
    }
}
