package org.usfirst.frc.team340.robot.commands;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightGyro extends Command {
	
	private double angle;
	private double Kp = .03; //TODO Figure out what this should be.
							 //And what it means?
    public DriveStraightGyro() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	Robot.drive.resetGyro();
    	setTimeout(2);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	angle = Robot.drive.getGyroAngle();
//    	Robot.drive.arcadeDrive(.75, angle*Kp);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.stopBothDrive();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
