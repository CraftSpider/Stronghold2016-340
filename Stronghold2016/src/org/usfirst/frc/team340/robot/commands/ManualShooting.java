package org.usfirst.frc.team340.robot.commands;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualShooting extends Command {

    public ManualShooting() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.harvester);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.getDriverBumperState() == 2) {
    		Robot.harvester.setShooter(1);
    	} else if(Robot.oi.getDriverBumperState() == 0) {
    		Robot.harvester.setShooter(-1);
    	} else {
    		Robot.harvester.setShooter(0);
    	}
    	
    	if(Robot.oi.getXYButtonState() == 2) {
    		Robot.harvester.setBallControl(1);;
    	} else if(Robot.oi.getXYButtonState() == 0) {
    		Robot.harvester.setBallControl(-1);
    	} else {
    		Robot.harvester.setBallControl(0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.oi.getXYButtonState() == 1 && Robot.oi.getDriverBumperState() == 1;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.harvester.setBallControl(0);
    	Robot.harvester.setBallControl(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}