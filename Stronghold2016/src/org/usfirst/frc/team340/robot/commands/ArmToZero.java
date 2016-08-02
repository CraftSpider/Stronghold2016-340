package org.usfirst.frc.team340.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

/**
 *
 */
public class ArmToZero extends Command {
	 BuiltInAccelerometer accel = new BuiltInAccelerometer();

	private static Logger log = Robot.getLogger("ArmToZero");
    public ArmToZero() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.harvester);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	log.fine("[ArmToZero] Initializing!");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double leftSpeed = -0.85;
    	double rightSpeed = -0.85;
    	
    	if(Robot.harvester.getLeftLimit()) {
    		rightSpeed *= 0.7;
    	} else if(Robot.harvester.getRightLimit()) {
    		leftSpeed *= 0.7;
    	}
    	
    	Robot.harvester.setLeftTilt(leftSpeed);
    	Robot.harvester.setRightTilt(rightSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.harvester.getRightLimit() && Robot.harvester.getLeftLimit());
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Just in case it's still moving
    	Robot.harvester.setTilt(0);
    	Robot.harvester.resetBothPots();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
