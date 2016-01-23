package org.usfirst.frc.team340.robot.commands;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LeftSide extends Command {

    public LeftSide() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.dDrive);
    	setTimeout(5);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.dDrive.setLeft(.2);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.dDrive.stopLeft();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
