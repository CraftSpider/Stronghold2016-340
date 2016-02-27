package org.usfirst.frc.team340.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team340.robot.Robot;

/**
 *
 */
public class StopDrive extends Command {

    public StopDrive() {
        
    	requires(Robot.drive);
    	
    	// Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drive.setBothDrive(0, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
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
