package org.usfirst.frc.team340.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import static org.usfirst.frc.team340.robot.commands.ArmToPosition.vBound;

import org.usfirst.frc.team340.robot.Robot;

/**
 *
 */
public class ArmToZero extends Command {

	//This is bound to half the max speed for armToPosition.
	private double maxV = vBound/2;
	//May need to be changed later to be bound to ArmToPosition maxAcc
	private double maxA = 0.05;
	private double cmdV = 0;
	private double deltaV;
	
	private double leftSpeed = 0,
			       rightSpeed = 0;
	
    public ArmToZero() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.harvester);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	deltaV = maxV - cmdV;
    	if (cmdV < maxV) {
    		if (deltaV < maxA) {
    			cmdV = maxV;
    		} else {
    			cmdV += maxA;
    		}
    	}
    	if (Robot.harvester.getLeftLimit()) {
    		leftSpeed = 0;
    		rightSpeed = cmdV/2;
    	}
    	if (Robot.harvester.getRightLimit()) {
    		leftSpeed = cmdV/2;
    		rightSpeed = 0;
    	}
    	Robot.harvester.setRightTilt(rightSpeed);
    	Robot.harvester.setLeftTilt(leftSpeed);
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
    }
}
