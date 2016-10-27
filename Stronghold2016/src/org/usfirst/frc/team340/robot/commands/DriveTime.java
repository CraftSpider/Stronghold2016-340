package org.usfirst.frc.team340.robot.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTime extends Command {
	
	private Logger logger = Robot.getLogger(DriveTime.class);
	
	private double lSpeed;
	private double rSpeed;

	/**
	 * Code to drive robot based on time
	 * @param timeout time before stop
	 * @param speed speed to move (both rails)
	 */
    public DriveTime(double timeout, double speed) {
        this(timeout, speed, speed);
    }
    
    /**
     * Drive based on time
     * @param timeout time before stop
     * @param leftSpeed speed of left rail
     * @param rightSpeed speed of right rail
     */
    public DriveTime(double timeout, double leftSpeed, double rightSpeed) {
    	requires(Robot.drive);
    	setTimeout(timeout);
    	lSpeed = leftSpeed;
    	rSpeed = rightSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	logger.info("[Initializing]");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drive.setBothDrive(lSpeed, rSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
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
