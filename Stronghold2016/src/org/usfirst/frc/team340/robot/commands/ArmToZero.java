package org.usfirst.frc.team340.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import static org.usfirst.frc.team340.robot.commands.ArmToPosition.vBound;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

/**
 *
 */
public class ArmToZero extends Command {

	//This is bound to 1/3 the max speed for armToPosition.
	private double maxV = vBound/3;
	//May need to be changed later to be bound to ArmToPosition maxAcc
	private double maxA = 0.05;
	private double cmdV = 0;
	private double deltaV;
	
	private double leftSpeed = 0,
			       rightSpeed = 0;
	private static Logger log = Robot.getLogger("ArmToZero");
    public ArmToZero() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.harvester);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	cmdV = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//this ramps up cmdV to the max v which is half of the max speed we use in the arm to pos command
    	deltaV = maxV - cmdV;
    	if (cmdV < maxV) {
    		if (deltaV < maxA) {
    			cmdV = maxV;
    		} else {
    			cmdV += maxA;
    		}
    	}
    	
    	leftSpeed = cmdV;
    	rightSpeed = cmdV;
    	//stop the left side if we hit the lft limit
    	if (Robot.harvester.getLeftLimit()) {
    		leftSpeed = 0;
    		rightSpeed = rightSpeed/2;
    	}
    	
    	//stop the right side if the right limit is hit
    	if (Robot.harvester.getRightLimit()) {
    		leftSpeed = leftSpeed/2;
    		rightSpeed = 0;
    	}
    	log.info("lft speed " + leftSpeed + " rgt speed " + rightSpeed);
    	Robot.harvester.setRightTilt(-rightSpeed);
    	Robot.harvester.setLeftTilt(-leftSpeed);
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
