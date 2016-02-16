package org.usfirst.frc.team340.robot.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 */
public class TiltArm extends Command {
	
	//Logger
	Logger logger = Robot.getLogger(TiltArm.class);
	
	//Variables
	double endPos;
	double dist;

	/**
	 * Tilts the harvester
	 * @param dist
	 */
    public TiltArm(double endPos) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.harvester);
    	
    	this.endPos = endPos;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	logger.info("[Initializing]");
    	dist = endPos - Robot.harvester.getLeftAimPot();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(dist > 0) {
    		Robot.harvester.setTilt(1);
    	} else {
    		Robot.harvester.setTilt(-1);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(dist == 0) {
        	return true;
        } else {
        	return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	logger.info("[Ending]");
    	Robot.harvester.setTilt(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	logger.info("[Interrupted]");
    	end();
    }
}
