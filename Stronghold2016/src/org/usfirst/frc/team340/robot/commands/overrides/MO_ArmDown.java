package org.usfirst.frc.team340.robot.commands.overrides;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MO_ArmDown extends Command {
	private static final Logger logger = Robot.getLogger(MO_ArmDown.class); 
	
	/**
	 * Set requirements for arm down operation
	 * Requires harvester subsystem
	 * Will cause the harvester arm to move downwards
	 */
    public MO_ArmDown() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.harvester);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	logger.info("[initializing]");
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     * Moves the arm down
     */
    protected void execute() {
    	logger.info("left bump: " + Robot.harvester.getLeftLimit() + 
    			" right bump: " + Robot.harvester.getRightLimit() +
    			" left pot: " + Robot.harvester.getLeftAimPot() +  
    			" right pot: " + Robot.harvester.getRightAimPot());
    	Robot.harvester.setLeftTilt(-0.75);
    	Robot.harvester.setRightTilt(-0.75);
    }

    // Make this return true when this Command no longer needs to run execute()
    /**
     * Sets command to completed
     * @return boolean false
     */
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    /**
     * Stops the movement of the arm
     */
    protected void end() {
    	logger.info("[ending]");
    	Robot.harvester.setLeftTilt(0);
    	Robot.harvester.setRightTilt(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    /**
     * Ends
     */
    protected void interrupted() {
    	logger.info("[interrupted]");
    	end();
    }
}
