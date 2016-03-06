package org.usfirst.frc.team340.robot.commands.overrides;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MO_ManualShooting extends Command {

	private final static Logger log = Robot.getLogger(MO_ManualShooting.class);
			
    public MO_ManualShooting() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.harvesterRollers);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }
    
    private boolean startPressed = false;
    private boolean startReleased = false;
    
    private boolean backPressed = false;
    private boolean backReleased = false;

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(Robot.oi.getStartState()) {
    		System.out.println("start pressed");
    		if(!startPressed && !startReleased) {
    			System.out.println("setting shooter full");
    			startPressed = true;
    			Robot.harvesterRollers.setShooter(Robot.harvesterRollers.SHOOTER_SHOOT_V_BUS);
    		} else if (startReleased) {
    			System.out.println("setting shooter off");
    			Robot.harvesterRollers.setShooter(0);
    			startReleased = false;
    		}
    	} else {
    		if(startPressed && !startReleased) {
    			startReleased = true;
    			startPressed = false;
    		}
    	}
    	
//    	log.info("Arm Potentiometers left: " + Robot.harvester.getLeftAimPot() + " right: " + Robot.harvester.getRightAimPot() + " left switch:" + Robot.harvester.getLeftLimit()  + " right switch:" + Robot.harvester.getRightLimit());
    	
    	//SET SHOOTER SPEED
//    	if(Robot.oi.getDriverBumperState() == 2) { //if RB1 is pressed
//    		// Feed in
//    		log.info("RB1 pressed Shooter Speed is 1");
//    		Robot.harvester.setShooter(1);
//    	} else if(Robot.oi.getDriverBumperState() == 0) { // if LB1 is pressed
//    		// Feeds out
//    		log.info("LB1 pressed Shooter Speed is -1");
//    		Robot.harvester.setShooter(-1);
//    	} else {
//    		Robot.harvester.setShooter(0);
//    		log.info("RB1 and LB1 not pressed Stop Shooter");
//    	}
    	
    	//SET HARVESTER SPEED
//    	if(Robot.oi.getXYButtonState() == 2) { //if X1 is pressed
//    		log.info("X 1 pressed Ball Control is 1");
//    		Robot.harvester.setBallControl(1);
//    	} else if(Robot.oi.getXYButtonState() == 0) { //if Y1 is pressed
//    		log.info("Y 1 pressed Ball Control is -1");
//    		Robot.harvester.setBallControl(-1);
//    	} else {
//    		Robot.harvester.setBallControl(0);
//    		log.info("X 1 and Y 1 not pressed Ball Control Stop");
//    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
//        return Robot.oi.getXYButtonState() == 1 && Robot.oi.getDriverBumperState() == 1;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("ending");
    	
//    	Robot.harvester.setBallControl(0);
//    	Robot.harvester.setShooter(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("interrupt");
    	end();
    }
}