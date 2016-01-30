package org.usfirst.frc.team340.robot.commands;

import org.usfirst.frc.team340.robot.Robot;
import org.usfirst.frc.team340.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithJoysticks extends Command {

	public boolean GTADrive = true;
	
    public DriveWithJoysticks() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(GTADrive) {
    		Robot.drive.arcadeDrive(Robot.oi.getGTADriveMove(), Robot.oi.getDriveRotate());
    	} else {
    		Robot.drive.arcadeDrive(Robot.oi.getArcadeDriveMove(), Robot.oi.getDriveRotate());
    	}
    	
    	Robot.drive.disengageClutch();
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
