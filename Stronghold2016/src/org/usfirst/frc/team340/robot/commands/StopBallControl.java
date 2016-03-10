package org.usfirst.frc.team340.robot.commands;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopBallControl extends Command {

    public StopBallControl() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.harvesterRollers);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.harvesterRollers.setBallControl(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.harvesterRollers.setBallControl(0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.harvesterRollers.setBallControl(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.harvesterRollers.setBallControl(0);
    }
}
