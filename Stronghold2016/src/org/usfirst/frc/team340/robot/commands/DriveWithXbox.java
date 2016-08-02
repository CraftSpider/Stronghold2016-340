package org.usfirst.frc.team340.robot.commands;

import org.usfirst.frc.team340.robot.Robot;
import java.util.logging.Logger;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithXbox extends Command {
	
	Logger logger = Robot.getLogger(DriveWithXbox.class);
	
    public DriveWithXbox() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.drive);
    }
	
    // Called just before this Command runs the first time
    protected void initialize() {
    	logger.info("[Initializing]Drive?");
    }
	
    // Called repeatedly when this Command is scheduled to run
    private double moveSlowScale = 0.6;
    private double rotateSlowScale = 0.7;
    
    public double throttleThrottling(double input) {
    	if(input > 0.1) {
    		return 	-Math.sqrt(-Math.pow((input-1),2)+1);
    	} else if (input < -0.1){
    		return Math.sqrt(-Math.pow((-input-1),2)+1);
    	}
    	return 0;
    }
    
    protected void execute() { 
    	
    	if(Robot.drive.PTOState) {
    		Robot.drive.engagePTO();
    	} else {
    		Robot.drive.disengagePTO();
    	}
    	
    	//Allow driver to drive in any of three modes.
    	// 1. Use triggers for speed, joystick for turning
    	// 2. Use right joystick for slow motion
    	// 3. Use left joystick for normal arcadeDrive
    	if(Math.abs(Robot.oi.getDriverSummedTriggers()) > .1) {
    		Robot.drive.arcadeDrive((Robot.oi.getDriverSummedTriggers()), (Robot.oi.getDriverLeftX()));
    	} else if(Math.abs(Robot.oi.getDriverRightY()) > 0.1 
    			|| Math.abs(Robot.oi.getDriverRightX()) > 0.1) {
    		Robot.drive.arcadeDrive((Robot.oi.getDriverRightY())*moveSlowScale, (Robot.oi.getDriverRightX())*rotateSlowScale);
    	} else {
    		Robot.drive.arcadeDrive((Robot.oi.getDriverLeftY()),(Robot.oi.getDriverLeftX()));
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	logger.info("[Ending]Drive?");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	logger.info("[Interrupted]Drive?");
    	end();
    }
}
