package org.usfirst.frc.team340.robot.commands.auto;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoPortcullis extends Command {

	Logger logger = Robot.getLogger(AutoPortcullis.class);
	
	double potAverage;
	double encAverage;
	
    public AutoPortcullis() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	requires(Robot.harvester);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	logger.info("[Initializing]");
    	Robot.harvester.resetBothPots();
    	Robot.drive.resetBothEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	encAverage = (Robot.drive.getLeftEncoder() + Robot.drive.getRightEncoder()) / 2;
    	potAverage = (Robot.harvester.getLeftAimPot() + Robot.harvester.getRightAimPot()) / 2;
    	
    	Robot.drive.setBothDrive(0.5, 0.5);
    	
    	if(encAverage > 10) {
    		Robot.harvester.setTilt(0.5);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return potAverage >= 110;
    }

    // Called once after isFinished returns true
    protected void end() {
    	logger.info("[Ending]");
    	Robot.harvester.setTilt(-1);
    	Robot.drive.setBothDrive(1, 1);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	logger.info("[Interrupted]");
    	Robot.drive.setBothDrive(-1, -1);
    	Robot.harvester.setTilt(-1);
    }
}
