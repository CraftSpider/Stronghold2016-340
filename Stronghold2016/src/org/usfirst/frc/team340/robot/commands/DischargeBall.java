package org.usfirst.frc.team340.robot.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DischargeBall extends Command {

	//Logger
	Logger logger = Robot.getLogger(DischargeBall.class);
	
    public DischargeBall() {
    	requires(Robot.harvesterRollers);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	logger.info("[Initializing]");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.harvesterRollers.setBallControl(Robot.harvesterRollers.HARVESTER_DISCHARGE_BALL_V_BUS);
    	Robot.harvesterRollers.setShooter(Robot.harvesterRollers.SHOOTER_DISCHARGE_BALL_V_BUS);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	logger.info("[Ending]");
    	Robot.harvesterRollers.setBallControl(0);
    	Robot.harvesterRollers.setShooter(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	logger.info("[Interrupted]");
    	end();
    }
}
