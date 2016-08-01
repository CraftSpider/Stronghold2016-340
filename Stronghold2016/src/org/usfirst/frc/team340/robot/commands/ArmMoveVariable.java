package org.usfirst.frc.team340.robot.commands;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmMoveVariable extends Command {

    public ArmMoveVariable() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.harvester);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    private double speed = 0;
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	speed = Robot.oi.getCoDriverLeftY();
    	double leftSpeed = speed;
    	double rightSpeed = speed;
    	
//    	Robot.harvester.setLeftTilt(Robot.oi.getCoDriverLeftY());
//    	Robot.harvester.setRightTilt(Robot.oi.getCoDriverLeftY());
    	
    	if(Robot.harvester.getLeftLimit() && speed < 0) {
			leftSpeed = 0;
			rightSpeed /= 2;
			Robot.harvester.resetLeftPot();
		}
		
		if(Robot.harvester.getTopLeftLimit() && speed > 0) {
			leftSpeed = 0;
			rightSpeed /= 2;
		}
		// stop left slow right if pot is too high and we have reset
		/*if(leftPot > Robot.harvester.HARVESTER_MAX_ANGLE && speed > 0 && Robot.harvester.hasReset()) {
			leftSpeed = 0;
			rightSpeed /= 2;
		}*/
		// stop right if bump slow left
		if(Robot.harvester.getRightLimit() && speed < 0) {
			rightSpeed = 0;
			leftSpeed /= 2;
			Robot.harvester.resetRightPot();
		}
		
		if(Robot.harvester.getTopRightLimit() && speed > 0) {
			rightSpeed = 0;
			leftSpeed /= 2;
		}
		// stop right slow left if pot is too high and we have reset
		/*if(rightPot > Robot.harvester.HARVESTER_MAX_ANGLE && speed > 0 && Robot.harvester.hasReset()) {
			rightSpeed = 0;
			leftSpeed /= 2;
		}*/
		
		Robot.harvester.setRightTilt(rightSpeed);
		Robot.harvester.setLeftTilt(leftSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.harvester.getLeftLimit() && Robot.harvester.getRightLimit() && speed < 0) || 
        		(Robot.harvester.getTopLeftLimit() && Robot.harvester.getTopRightLimit() && speed > 0);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.harvester.setLeftTilt(0);
    	Robot.harvester.setRightTilt(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
