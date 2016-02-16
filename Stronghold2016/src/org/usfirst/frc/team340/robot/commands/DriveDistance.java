package org.usfirst.frc.team340.robot.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Basically PID
 * <!-- DON'T USE THIS CODE -->
 * @author wundrweapon
 *
 */
public class DriveDistance extends Command {
	
	//Logger
	Logger logger = Robot.getLogger(DriveDistance.class);
	
	//Value inits
	double tolerance;
	/*double targetDist;
	double delta = 0;
	double prevDelta = 0;
	double changeInDelta = 0;*/
	
	double cmdSpeed;
	double currPos;
	double tgtPos;
	double potAverage = (Robot.harvester.getLeftAimPot() + Robot.harvester.getRightAimPot()) / 2;
	double ThV = 0;
	double maxAcc = 0.05;  //Increases speed every tick. This will be changed possibly, not sure.
	double vBound = 1;

	/**
	 * Allows the robot to travel to a given distance
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
    	Robot.drive.resetLeftEncoder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(currPos < (tgtPos / 2)) {
    		ThV += maxAcc;
    	} else if(currPos < tgtPos) {
    		ThV -= maxAcc;
    	} else {
    		ThV = 0;
    	}
    	
    	if(ThV < vBound) {
    		cmdSpeed = ThV;
    	} else {
    		cmdSpeed = vBound;
    	}
    	
    	if(currPos > tgtPos) {
    		Robot.drive.setBothDrive(-cmdSpeed, -cmdSpeed);
    	} else if(currPos < tgtPos) {
    		Robot.drive.setBothDrive(cmdSpeed, cmdSpeed);
    	} else {
    		Robot.drive.setBothDrive(0, 0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drive.getLeftEncoder() == (tgtPos - tolerance);
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
