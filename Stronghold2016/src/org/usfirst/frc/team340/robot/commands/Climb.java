package org.usfirst.frc.team340.robot.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 */
public class Climb extends Command {
	
	Logger logger = Robot.getLogger(Climb.class);
	/**
	 * Set requirements for climb command.
	 * Requires climber and drive.
	 * Pulls down the arm to raise the robot
	 */
    public Climb() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	requires(Robot.climber);
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	logger.info("[Initializing]");
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     * Engages clutch
     * If the arm is not at the bottom AND the clutch is engaged AND the latch is open, set driverail motors to full speed
     */
    protected void execute() {
    	Robot.drive.engageClutch();
    	if(!Robot.climber.isAtBottom() && Robot.drive.clutchState) {
    		Robot.drive.setBothDrive(1, 1);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    /**
     * Checks to see if command is completed 
     * @return boolean is climber arm at bottom
     */
    protected boolean isFinished() {
        return Robot.climber.isAtBottom();
    }

    // Called once after isFinished returns true
    /**
     * Stops driverail motors when completed
     * @return void
     */
    protected void end() {
    	logger.info("[Ending]");
    	Robot.drive.setBothDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    /**
     * When command interrupted, call end
     * @return void
     */
    protected void interrupted() {
    	logger.info("[Interrupted]");
    	end();
    }
}
