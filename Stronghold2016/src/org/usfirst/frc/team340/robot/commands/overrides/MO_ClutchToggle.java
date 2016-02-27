package org.usfirst.frc.team340.robot.commands.overrides;

import edu.wpi.first.wpilibj.command.Command;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;
/**
 *
 */
public class MO_ClutchToggle extends Command {
	
	Logger logger = Robot.getLogger(MO_ClutchToggle.class);
	
	/**
	 * Set requirements for clutch operation
	 * Requires driver subsystem
	 * Engages the clutch that drives the climbing arm mechanism
	 */
    public MO_ClutchToggle() {
    	
    	requires(Robot.drive);
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	logger.info("[Initializing: MO_ClutchOn]");
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     * Engages the clutch
     */
    protected void execute() {
    	//latchState default is true which means the climber arm is down
    	//clutchState default is false which means the clutch is disengaged
    	if(!Robot.climber.latchState && Robot.drive.PTOState) {
    		Robot.drive.disengagePTO();
    	}
    	else {
    		Robot.drive.engagePTO();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    /**
     * Sets command to completed
     * @return boolean true
     */
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
