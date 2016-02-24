package org.usfirst.frc.team340.robot.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * What John calls ingest
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
    	Robot.oi.driverRumbleRight(1);
    	Robot.oi.driverRumbleLeft(1);
    	Robot.oi.coDriverRumbleLeft(1);
    	Robot.oi.coDriverRumbleRight(1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double desiredShooterWheelHarvestSpeed = Robot.harvester.SHOOTER_HARVEST_V_BUS;
    	
    	if(Robot.harvester.hasBallLeft()) {
    		Robot.oi.driverRumbleLeft(0);
    		Robot.oi.coDriverRumbleLeft(0);
    		desiredShooterWheelHarvestSpeed /= 2;
    	} else {
    		Robot.oi.driverRumbleLeft(1);
    		Robot.oi.coDriverRumbleLeft(1);
    	}
    	
    	if(Robot.harvester.hasBallRight()) {
    		Robot.oi.driverRumbleRight(0);
    		Robot.oi.coDriverRumbleRight(0);
    		desiredShooterWheelHarvestSpeed /= 2;
    	} else {
    		Robot.oi.driverRumbleRight(1);
    		Robot.oi.coDriverRumbleRight(1);
    	}
    	
    	Robot.harvester.setBallControl(Robot.harvester.HARVESTER_HARVEST_V_BUS);
    	Robot.harvester.setShooter(desiredShooterWheelHarvestSpeed);
    	
    	if(Robot.harvester.hasBall()) {
    		logger.info("Both sensors tripped; ball harvested");
    	} else {
    		logger.info("Left sensor: " + Robot.harvester.hasBallLeft() + "; right sensor: " + Robot.harvester.hasBallRight());
    	}
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
    	
    	Robot.oi.driverRumbleLeft(0);
    	Robot.oi.driverRumbleRight(0);
    	
    	Robot.oi.coDriverRumbleLeft(0);
    	Robot.oi.coDriverRumbleRight(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	logger.info("[Interrupted]");
    	end();
    }
}
