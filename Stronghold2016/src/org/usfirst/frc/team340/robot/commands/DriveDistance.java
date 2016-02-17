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
	double tolerance; //Distance from tgtPos that the robot can stop at without any trouble
	double speed; //Speed to set motors to
	double currPos = 0; //Current position. Starts at 0 to simply everything and make it all work
	double tgtPos; //Target position
	double ThV = 0; //Theoretical velocity. Starts at 0 to simply everything and make it all work
	double maxAcc = 0.05; //Increases speed every tick. Currently a magic number
	double vBound = 1; //Velocity boundary

	/**
	 * Allows the robot to travel to a given distance.
	 * Put negative tgtPos values for reverse travel
	 * @param tolerance
	 * @param targetDist
	 */
    public DriveDistance(double tolerance, double targetPos) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	
    	this.tgtPos = targetPos;
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
    	if(currPos < (tgtPos - tolerance)) {
    		direction = 2;
    		
    		//Deals with variance in our velocity
    		if(currPos < (tgtPos / 2)) {
    			ThV += maxAcc;
    		} else if(currPos < tgtPos) {
    			ThV -= maxAcc;
    		} else {
    			ThV = 0;
    		}
    		
    		//Sets velocity
    		if(ThV < vBound) {
    			speed = ThV;
    		} else {
    			speed = vBound;
    		}
    	} else {
    		//This if/else just sets the direction value to simplify isFinished()
    		if(currPos > (tgtPos + tolerance)) {
    			direction = 1;
    		} else {
    			direction = 0;
    		}
    		
    		//The actual reversing code is here, essentially the opposite of forwards code
    		if(currPos > (tgtPos / 2)) {
    			ThV -= maxAcc;
    		} else if(currPos > tgtPos) {
    			ThV += maxAcc;
    		} else {
    			ThV = 0;
    		}
    		
    		if(ThV > -vBound) {
    			speed = ThV;
    		} else {
    			speed = -vBound;
    		}
    	}
    	
    	currPos = (Robot.drive.getLeftEncoder() + Robot.drive.getRightEncoder()) / 2; //Sets our current position to the average of the encoders for the next run of execute()
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
