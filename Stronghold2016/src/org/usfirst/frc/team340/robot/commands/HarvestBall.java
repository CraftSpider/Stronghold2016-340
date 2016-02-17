package org.usfirst.frc.team340.robot.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HarvestBall extends Command {


	//Logger
	Logger logger = Robot.getLogger(HarvestBall.class);
	
    public HarvestBall() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.harvester);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	logger.info("[Initializing]");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.harvester.setBallControl(Robot.harvester.HARVESTER_HARVEST_V_BUS);
    	Robot.harvester.setShooter(Robot.harvester.SHOOTER_HARVEST_V_BUS);
    	logger.info("Harvester Ball Sensor: " + Robot.harvester.hasBall());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.harvester.hasBall();
    }

    // Called once after isFinished returns true
    protected void end() {
    	logger.info("[Ending]");
    	Robot.harvester.setBallControl(0);
    	Robot.harvester.setShooter(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	logger.info("[Interrupted]");
    	end();
    }
}
