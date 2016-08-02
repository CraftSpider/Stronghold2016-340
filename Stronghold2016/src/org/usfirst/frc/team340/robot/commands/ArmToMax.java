package org.usfirst.frc.team340.robot.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 */
public class ArmToMax extends Command {
	
	Logger logger = Robot.getLogger(ArmToMax.class);
	
//	private static final double differentiate = .025;
//	private static final double minSpd = .1;
//	private static double lSpd = .5, rSpd = .5;

	/**
	 * Automagically puts the harvester at its farthest back angle
	 */
    public ArmToMax() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.harvester);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	logger.info("[Initializing]");
    }
    
    protected void execute() {
    	double leftSpeed = 0.85;
    	double rightSpeed = 0.85;
    	
    	System.out.println(Robot.harvester.getTopLeftLimit() + " " + Robot.harvester.getTopRightLimit());
    	
    	
    	if(Robot.harvester.getTopLeftLimit()) {
    		rightSpeed *= 0.7;
    	} else if(Robot.harvester.getTopRightLimit()) {
    		leftSpeed *= 0.7;
    	}
    	
    	System.out.println(leftSpeed + " " + rightSpeed);
    	
    	Robot.harvester.setLeftTilt(leftSpeed);
    	Robot.harvester.setRightTilt(rightSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.harvester.getTopLeftLimit() && Robot.harvester.getTopRightLimit());
    }

    // Called once after isFinished returns true
    protected void end() {
    	logger.info("[Ending]");
    	Robot.harvester.setTilt(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	logger.info("[Interrupted]");
    	end();
    }
}
