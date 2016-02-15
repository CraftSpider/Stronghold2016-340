package org.usfirst.frc.team340.robot.commands.auto;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoChevalDeFrise extends Command {
	
	Logger logger = Robot.getLogger(AutoChevalDeFrise.class);
	
	double potAverage;
	double encAverage;

    public AutoChevalDeFrise() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	requires(Robot.harvester);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	logger.info("[Initializing]");
    	
    	Robot.drive.resetBothEncoders();
    	Robot.harvester.resetBothPots();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	potAverage = (Robot.harvester.getLeftAimPot() + Robot.harvester.getRightAimPot()) / 2;
    	encAverage = (Robot.drive.getLeftEncoder() + Robot.drive.getRightEncoder()) / 2;
    	
    	if(potAverage <= 30) {
    		Robot.harvester.setTilt(0.5);
    		Robot.drive.setBothDrive(0, 0);
    	} else if(encAverage <= 500) {
    		Robot.drive.setBothDrive(0.2, 0.2);
    	} else if(potAverage > 5) {
    		Robot.harvester.setTilt(-0.5);
    	} else {
    		Robot.drive.setBothDrive(1, 1);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return encAverage > 2400;
    }

    // Called once after isFinished returns true
    protected void end() {
    	logger.info("[Ending]");
    	Robot.harvester.setTilt(-0.1);
    	Robot.drive.setBothDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	logger.info("[Interrupted]");
    	Robot.drive.setBothDrive(-1, -1);
    	Robot.harvester.setTilt(-1);
    }
}
