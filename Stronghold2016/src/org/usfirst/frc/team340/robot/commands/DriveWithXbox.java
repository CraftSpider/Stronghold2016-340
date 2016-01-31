package org.usfirst.frc.team340.robot.commands;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithXbox extends Command {
	
    public DriveWithXbox() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    private double moveSlowScale = 0.6;
    private double rotateSlowScale = 0.7;
    protected void execute() {
    	if(Math.abs(Robot.oi.getDriverSummedTriggers()) > .1) {
    		Robot.drive.arcadeDrive(Robot.oi.getDriverSummedTriggers(), 
    				Robot.oi.getDriverLeftX());
    	} else if(Math.abs(Robot.oi.getDriverRightY()) > 0.1 
    			|| Math.abs(Robot.oi.getDriverRightX()) > 0.1) {
    		Robot.drive.arcadeDrive(Robot.oi.getDriverRightY()*moveSlowScale, 
    				Robot.oi.getDriverRightX()*rotateSlowScale);
    	} else {
    		Robot.drive.arcadeDrive(Robot.oi.getDriverLeftY(), Robot.oi.getDriverLeftX());
    	}
    	
//    	Robot.drive.disengageClutch();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
