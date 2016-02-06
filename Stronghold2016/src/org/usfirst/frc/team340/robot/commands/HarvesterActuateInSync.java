package org.usfirst.frc.team340.robot.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HarvesterActuateInSync extends Command {

	private double speed = 0.0;
	
	Logger logger = Robot.getLogger(HarvesterActuateInSync.class);
    public HarvesterActuateInSync(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.speed = speed;
    	requires(Robot.harvester);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	logger.info("[Initializing]");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Math.abs(Robot.harvester.getLeftAimPot()-Robot.harvester.getRightAimPot()) < 5) {
    		Robot.harvester.setLeftTilt(speed);
    		Robot.harvester.setRightTilt(speed);
    	} else if((Robot.harvester.getLeftAimPot() < Robot.harvester.getRightAimPot() &&
    			speed > 0) || (
    			Robot.harvester.getLeftAimPot() > Robot.harvester.getRightAimPot() &&
    			speed < 0)) {
    		Robot.harvester.setLeftTilt(speed);
    		Robot.harvester.setRightTilt(speed/2.5);
    	} else {
    		Robot.harvester.setLeftTilt(speed/2.5);
    		Robot.harvester.setRightTilt(speed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
