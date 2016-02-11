package org.usfirst.frc.team340.robot.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveArm extends Command {

	private double speed = 0.0;
	
	Logger logger = Robot.getLogger(MoveArm.class);
    public MoveArm(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.speed = speed;
    	requires(Robot.harvester);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	logger.info("[Initializing]");
    }
    private double leftSpeed = speed;
	private double rightSpeed = speed;
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
//    	if(Math.abs(leftPot-rightPot) < 5) {
//    		Robot.harvester.setLeftTilt(speed);
//    		Robot.harvester.setRightTilt(speed);
//    	} else 
    	
    	double leftPot = Robot.harvester.getLeftAimPot();
    	double rightPot = Robot.harvester.getRightAimPot();
    	
//    	if((leftPot < rightPot && speed > 0) 
//    			|| (leftPot > rightPot && speed < 0)) {
////    		Robot.harvester.setLeftTilt(speed);
//    			leftSpeed = speed;
//    			rightSpeed = speed/2.5;
////    		Robot.harvester.setRightTilt(speed/2.5);
//    		
//    	} else if ((rightPot < leftPot && speed > 0)
//    			|| (rightPot > leftPot && speed < 0)){
////    		Robot.harvester.setLeftTilt(speed/2.5);
//    		leftSpeed = speed/2.5;
//    		rightSpeed = speed;
////    		Robot.harvester.setRightTilt(speed);
//    	}
    	leftSpeed = speed;
    	rightSpeed = speed;
    	
		if(Robot.harvester.getLeftLimit() /*|| leftPot > 50*/) {
			leftSpeed = 0;
			rightSpeed /= 2;
		}
		if(Robot.harvester.getRightLimit() /*|| rightPot > 50*/) {
			rightSpeed = 0;
			leftSpeed /= 2;
		}
		
		Robot.harvester.setLeftTilt(leftSpeed);
		Robot.harvester.setRightTilt(rightSpeed);
		
//		logger.info("Execute: leftSpeed: " + leftSpeed + " rightSpeed: " + rightSpeed);
		
		logger.info("      ");
		logger.info("left pot: " + leftPot + " right pot: " + rightPot);
		logger.info("left Pot: " + Robot.harvester.getLeftLimit()  + " right pot: " + Robot.harvester.getRightLimit());
		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return leftSpeed == 0 && rightSpeed == 0;
    }

    // Called once after isFinished returns true
    protected void end() {
//    	logger.info("[Ending]");
    	Robot.harvester.setLeftTilt(0);
    	Robot.harvester.setRightTilt(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
//    	logger.info("[Interrupted]");
    	end();
    }
}
