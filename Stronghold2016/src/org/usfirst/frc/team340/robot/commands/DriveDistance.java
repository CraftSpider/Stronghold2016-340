package org.usfirst.frc.team340.robot.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 */
public class DriveDistance extends Command {
	
	//Logger
	Logger logger = Robot.getLogger(DriveDistance.class);
	
	//Value inits
	int direction; //0 means no movement, 1 means reverse, 2 means forward. I did this because I code weirdly
	double tolerance; //Distance from targetPosition that the robot can stop at without any trouble
	double speed; //Speed to set motors to
	double currentPosition = 0; //Starts at 0 to simply everything and make it all work
	double targetPosition; //Target position
	double theoreticalVelocity = 0; //Starts at 0 to simply everything and make it all work
	double maxAccelleration = 0.05; //Increases speed every tick. Currently a magic number
	double velocityBound = 1; //Sets a boundary for velocity

	/**
	 * Allows the robot to travel to a given distance.
	 * Put negative targetPosition values for reverse travel
	 * @param tolerance
	 * @param targetDist
	 */
    public DriveDistance(double tolerance, double targetPos) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	
    	this.targetPosition = targetPos;
    	this.tolerance = tolerance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	logger.info("[Initializing]");
    	Robot.drive.resetBothEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    //Someone, at some point, needs to optimize this
    protected void execute() {
    	currentPosition = (Robot.drive.getLeftEncoder() + Robot.drive.getRightEncoder()) / 2; //Sets our current position to the average of the encoders for the next run of execute()
    	
    	if(currentPosition < (targetPosition - tolerance)) {
    		direction = 2;
    		
    		//Deals with variance in our velocity
    		if(currentPosition < (targetPosition / 2)) {
    			theoreticalVelocity += maxAccelleration;
    		} else if(currentPosition < targetPosition) {
    			theoreticalVelocity -= maxAccelleration;
    		} else {
    			theoreticalVelocity = 0;
    		}
    		
    		//Sets velocity
    		if(theoreticalVelocity < velocityBound) {
    			speed = theoreticalVelocity;
    		} else {
    			speed = velocityBound;
    		}
    	} else if(currentPosition > (targetPosition + tolerance)) {
    		direction = 1;
    		
    		//The actual reversing code is here, essentially the opposite of forwards code
    		if(currentPosition > (targetPosition / 2)) {
    			theoreticalVelocity -= maxAccelleration;
    		} else if(currentPosition > targetPosition) {
    			theoreticalVelocity += maxAccelleration;
    		} else {
    			theoreticalVelocity = 0;
    		}
    		
    		if(theoreticalVelocity > -velocityBound) {
    			speed = theoreticalVelocity;
    		} else {
    			speed = -velocityBound;
    		}
    	} else {
    		direction = 0;
    		speed = 0;
    	}
    	
    	Robot.drive.setBothDrive(speed, speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return direction == 0;
    }

    // Called once after isFinished returns true
    protected void end() {
    	logger.info("[Ending]");
    	Robot.drive.stopBothDrive();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	logger.info("[Interrupted]");
    	end();
    }
}
