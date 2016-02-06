package org.usfirst.frc.team340.robot.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shoot extends Command {

	Logger logger = Robot.getLogger(Shoot.class);
    public Shoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	requires(Robot.harvester);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	logger.info("[Initializing: Shooter]");
    	Robot.harvester.setShooter(1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.harvester.setBallControl(.3);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	logger.info("[Ending]");
    	Robot.harvester.setShooter(0);
    	Robot.harvester.setBallControl(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	logger.info("[Interrupted]");
    	end();
    }
}
