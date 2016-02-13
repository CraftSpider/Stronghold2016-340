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
	
	//Variables
	double speed;
	double stallVoltage = 42; //This will be replaced with the actual stall voltage
	
    public HarvestBall(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.harvester);
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	logger.info("[Initializing]");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.harvester.setBallControl(speed);
    	Robot.harvester.setShooter(-speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(Robot.harvester.getVoltage() == stallVoltage) {
        	return true;
        } else {
        	return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	logger.info("[Ending]");
//    	Robot.harvester.setBallControl(0);
//    	Robot.harvester.setShooter(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	logger.info("[Interrupted]");
    	end();
    }
}
