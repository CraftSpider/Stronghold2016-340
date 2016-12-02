package org.usfirst.frc.team340.robot.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 */
public class Climb extends Command {
	
	Logger logger = Robot.getLogger(Climb.class);
	
//	private boolean dartStage = false;
//	private boolean ptoStage = false;
	
	private double speed = 0;
	
	/**
	 * Set requirements for climb command.
	 * Requires climber and drive.
	 * Pulls down the arm to raise the robot
	 */
    public Climb(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.speed = speed;
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
    
//    private double dartExtendSpeed = 0.85;
//    private double dartInSpeed = -1;
    
    @SuppressWarnings("deprecation")
	protected void execute() {
    	if(Robot.climber.getDartLimit() && this.speed > 0) {
    		Robot.climber.runDart(0);
    	} else {
    		Robot.climber.runDart(this.speed);
    	}
    	
    	System.out.println("Limit: " + Robot.climber.getDartLimit()  + " and Banner: " + Robot.climber.getWinchBanner());
//    	if(!dartStage) {
//    		Robot.climber.runDart(dartExtendSpeed);
//    		if(Robot.climber.getDartLimit()) {
//    			Robot.climber.runDart(0);
//    			dartStage = true;
//    		}
//    	} else if(!ptoStage) {
//    		Robot.drive.engagePTO();
//    		Robot.drive.setBothDrive(-1,-1);
//    		if(Robot.climber.getWinchBanner()) {
//    			Robot.drive.setBothDrive(0, 0);
//    			ptoStage = true;
//    		}
//    	} else {
//    		Robot.climber.runDart(dartInSpeed);
//    	}
//    	Robot.drive.engagePTO();
//    	if(!Robot.climber.isAtBottom() && Robot.drive.clutchState) {
//    		Robot.drive.setBothDrive(Robot.drive.CLIMB_DRIVE_SPEED, Robot.drive.CLIMB_DRIVE_SPEED);
//    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    /**
     * Checks to see if command is completed 
     * @return boolean is climber arm at bottom
     */
    protected boolean isFinished() {
    	return false;
//        return Robot.climber.isAtBottom();
    }

    // Called once after isFinished returns true
    /**
     * Stops driverail motors when completed
     * @return void
     */
    @SuppressWarnings("deprecation")
	protected void end() {
    	logger.info("[Ending]");
    	Robot.drive.disengagePTO();
    	Robot.climber.runDart(0);
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
