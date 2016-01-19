package org.usfirst.frc.team340.robot.commands;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmToPosition extends Command {
	
	int position;
	double speed;
	
    public ArmToPosition(int position, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.clawArm);
    	this.position = position;
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.clawArm.sendArmToPosition(position, speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.clawArm.armAtBottom() || Robot.clawArm.armAtTop()) {
    		return true;
    	} else if (Robot.clawArm.armPosition() == position) {
    		return true;
    	}
    	return false;
    	//This would do about the same thing, but would be quite a pain to read:
    	//return ((Robot.clawArm.armPosition() > position || Robot.clawArm.armPosition() < position) && !(Robot.clawArm.armAtBottom() || Robot.clawArm.armAtTop())? false : true);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.clawArm.armStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
