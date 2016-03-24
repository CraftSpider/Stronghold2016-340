package org.usfirst.frc.team340.robot.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shoot extends Command {

	private double speedOverride = 100;
	
	Logger logger = Robot.getLogger(Shoot.class);
    public Shoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);    	
    	requires(Robot.harvesterRollers);
    }
    
    public Shoot(double speed) {
    	requires(Robot.harvesterRollers);
    	speedOverride = speed;
    }
    
    Timer t = new Timer();
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	t.start();
    	logger.info("[Initializing: Shooter]");
    	if(speedOverride == 100) {
    		Robot.harvesterRollers.setShooter(Robot.harvesterRollers.SHOOTER_SHOOT_V_BUS);
    	} else {
    		Robot.harvesterRollers.setShooter(speedOverride);
    	}
    }
    
    // Called repeatedly when this Command is scheduled to run
    //Allows the shooter wheel to speed up before the ball is shot
    //Hold Back on controller 1 to keep the command going and roll the shooter wheel
    //Hold Start on controller 1 to begin rolling the ball control wheel
    
    protected void execute() {
    	if(speedOverride == 100) {
    		Robot.harvesterRollers.setShooter(Robot.harvesterRollers.SHOOTER_SHOOT_V_BUS);
    	} else {
    		Robot.harvesterRollers.setShooter(speedOverride);
    	}
//    	logger.info("current: " + Robot.harvesterRollers.harvesterCurrent());
    	Robot.harvesterRollers.harvesterCurrent();;
    	double desiredBallControlSpeed = 0;
    	//if we have the ball stop
    	if(Robot.harvesterRollers.hasBall()){
    		desiredBallControlSpeed = 0;
    	}else{
    		desiredBallControlSpeed = Robot.harvesterRollers.HARVESTER_HARVEST_V_BUS;
    	}
    	
    	if(t.get() > Robot.harvesterRollers.SHOOTER_SHOOT_SPINUP_TIME) {
    		desiredBallControlSpeed = Robot.harvesterRollers.HARVESTER_RELEASE_BALL_V_BUS;
    	}
    	Robot.harvesterRollers.setBallControl(desiredBallControlSpeed);
//    	if(Robot.oi.getBackState()) {
//    		Robot.harvester.setShooter(Robot.harvester.SHOOTER_SHOOT_V_BUS);
//    	}
//    	
//    	if(Robot.oi.getStartState()) {
//    		Robot.harvester.setBallControl(Robot.harvester.HARVESTER_RELEASE_BALL_V_BUS);
//    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	logger.info("[Ending]");
    	Robot.harvesterRollers.setShooter(0);
    	Robot.harvesterRollers.setBallControl(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	logger.info("[Interrupted]");
    	end();
    }
}
