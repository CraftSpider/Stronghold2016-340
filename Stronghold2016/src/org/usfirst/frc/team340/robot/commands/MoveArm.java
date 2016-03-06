package org.usfirst.frc.team340.robot.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveArm extends Command {

	Logger logger = Robot.getLogger(MoveArm.class);
	private double speed = 0.0;
	
	private double leftSpeed = 0;
	private double rightSpeed = 0;
	
    public MoveArm(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.speed = speed;
    	requires(Robot.harvester);
    }

    // Called just before this Command runs the first time
    protected void initialize() {    	
    }
    
	// Called repeatedly when this Command is scheduled to run
	@SuppressWarnings("static-access")
	protected void execute() {

		double leftPot = Robot.harvester.getLeftAimPot();
		double rightPot = Robot.harvester.getRightAimPot();

		this.leftSpeed = this.speed;
		this.rightSpeed = this.speed;

		if (!Robot.harvester.hasReset()) {
			leftSpeed *= 0.75;
			rightSpeed *= 0.75;
		}/* else if(Robot.harvester.hasReset() && Math.abs(Robot.harvester.getLeftAimPot()-Robot.harvester.getRightAimPot()) < 1) {
			// do nothing we have arms in sync and speeds are already set
		}else {
			if ((leftPot < rightPot && speed > 0) || (leftPot > rightPot && speed < 0)) {
				// Robot.harvester.setLeftTilt(speed);
				leftSpeed = speed;
				rightSpeed = speed / 2.5;
				// Robot.harvester.setRightTilt(speed/2.5);

			} else if ((rightPot < leftPot && speed > 0) || (rightPot > leftPot && speed < 0)) {
				// Robot.harvester.setLeftTilt(speed/2.5);
				leftSpeed = speed / 2.5;
				rightSpeed = speed;
				// Robot.harvester.setRightTilt(speed);
			}
		}*/
		// leftSpeed = speed;
		// rightSpeed = speed;

    	// slow down as we get higher
    	// will not slow down below 20% of start speed
//    	if(speed > 0 && Robot.harvester.hasReset()) {
//    		leftSpeed *= (-(leftPot+rightPot)/2.0/225.0+1);
//    		rightSpeed *= (-(leftPot+rightPot)/2.0/225.0+1);
//    		logger.info("Formula: " + (-(leftPot+rightPot)/2.0/225.0+1));
//    	}
    	
    	//Slow down as we get lower
    	//logger.info("reset:" + Robot.harvester.hasReset());
//    	if(speed < 0 && Robot.harvester.hasReset()) {
//    		//what happens when we get negative values passed in here?
//    		//  need to figure out how to deal with this...
//    		//  Maybe special case for negativ pot values
//    		leftSpeed = leftSpeed * ((1/150.0)*((leftPot+rightPot)/2.0-Robot.harvester.HARVESTER_MAX_ANGLE)+1);
//    		rightSpeed = rightSpeed * ((1/150.0)*((leftPot+rightPot)/2.0-Robot.harvester.HARVESTER_MAX_ANGLE)+1);
//    	}
    	
    	// stop left if bump slow right
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
		if(leftPot > Robot.harvester.HARVESTER_MAX_ANGLE && speed > 0 && Robot.harvester.hasReset()) {
			leftSpeed = 0;
			rightSpeed /= 2;
		}
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
		if(rightPot > Robot.harvester.HARVESTER_MAX_ANGLE && speed > 0 && Robot.harvester.hasReset()) {
			rightSpeed = 0;
			leftSpeed /= 2;
		}
		
		Robot.harvester.setRightTilt(rightSpeed);
		Robot.harvester.setLeftTilt(leftSpeed);
		
		logger.info("left pot: " + leftPot + " right pot: " + rightPot + " hasReset" + Robot.harvester.hasReset());//+ 
				//" left limit: " + Robot.harvester.getLeftLimit()  + " right limit: " + Robot.harvester.getRightLimit());
		logger.info("getTopLeftLimit: " + Robot.harvester.getTopLeftLimit() + " getTopRightLimit: " + Robot.harvester.getTopRightLimit());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return leftSpeed == 0 && rightSpeed == 0;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.harvester.setTilt(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}//EOF