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
	
	private double leftSpeed = 0;
	private double rightSpeed = 0;
	
    public MoveArm(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.speed = speed;
//    	logger.info("construct left speed: " + leftSpeed + " right speed: " + rightSpeed);
		
    	
    	requires(Robot.harvester);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	logger.info("[Initializing]");
    	
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftPot = Robot.harvester.getLeftAimPot();
    	double rightPot = Robot.harvester.getRightAimPot();
    	
    	this.leftSpeed = this.speed;
    	this.rightSpeed = this.speed;
    	
//    	if(Math.abs(leftPot-rightPot) < 1) {
//    		Robot.harvester.setLeftTilt(speed);
//    		Robot.harvester.setRightTilt(speed);
//    	} else if((leftPot < rightPot && speed > 0) 
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
//    	leftSpeed = speed;
//    	rightSpeed = speed;
    	
    	if(!Robot.harvester.hasReset()) {
    		leftSpeed *= 0.5;
    		rightSpeed *= 0.5;
    	}
    	
    	double max = 110;
    	
    	// slow down as we get higher
    	//
    	if(speed > 0 && Robot.harvester.hasReset()) {
    		leftSpeed *= (-(leftPot+rightPot)/2/225+1);
    		rightSpeed *= (-(leftPot+rightPot)/2/225+1);
    	}
    	if(speed < 0 && Robot.harvester.hasReset()) {
    		leftSpeed = leftSpeed * ((1/150.0)*((leftPot+rightPot)/2.0-max)+1);
    		rightSpeed = rightSpeed * ((1/150.0)*((leftPot+rightPot)/2.0-max)+1);
//    		logger.info("formula: " + ((1/150.0)*((leftPot+rightPot)/2.0-max)+1));
    	}
    	
    	// stop left if bump slow right
		if((Robot.harvester.getLeftLimit() && speed < 0)) {
			leftSpeed = 0;
			rightSpeed /= 2;
			Robot.harvester.resetLeftPot();
		}
		// stop left slow right if pot is too high and we have reset
		if((leftPot > max && speed > 0 && Robot.harvester.hasReset())) {
			leftSpeed = 0;
			rightSpeed /= 2;
		}
		// stop right if bump slow left
		if((Robot.harvester.getRightLimit() && speed < 0)) {
			rightSpeed = 0;
			leftSpeed /= 2;
			Robot.harvester.resetRightPot();
		}
		// stop right slow left if pot is too high and we have reset
		if((rightPot > max && speed > 0 && Robot.harvester.hasReset())) {
			rightSpeed = 0;
			leftSpeed /= 2;
		}
		
//		logger.info("left speed: " + leftSpeed + " right speed: " + rightSpeed);
		
		Robot.harvester.setLeftTilt(leftSpeed);
		Robot.harvester.setRightTilt(rightSpeed);
		
//		logger.info("Execute: leftSpeed: " + leftSpeed + " rightSpeed: " + rightSpeed);
		
//		logger.info("      ");
		logger.info("left pot: " + leftPot + " right pot: " + rightPot + 
				" left limit: " + Robot.harvester.getLeftLimit()  + " right limit: " + Robot.harvester.getRightLimit());
//		logger.info();
		
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
