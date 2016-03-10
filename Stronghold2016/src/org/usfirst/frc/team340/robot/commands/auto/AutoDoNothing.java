package org.usfirst.frc.team340.robot.commands.auto;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDoNothing extends Command {

    public AutoDoNothing() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive); // require something just because i think one has to require something
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// literally do nothing
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// literally still do nothing
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	// wait for a time out
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	// theres nothing to end
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	// who cares
    }
}
