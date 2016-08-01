package org.usfirst.frc.team340.robot.commands.overrides;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
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

    private boolean leftTrigPressed = false;
    private boolean leftTrigReleased = false;
    
    private boolean rightTrigPressed = false;
    private boolean rightTrigReleased = false;
    
    private boolean backPressed = false;
    private boolean backReleased = false;
    private boolean ended = false;
    
    Timer t = new Timer();
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	leftTrigPressed = false;
    	leftTrigReleased = false;
        
    	rightTrigPressed = false;
    	rightTrigPressed = false;
    	
        backPressed = false;
        backReleased = false;
        
        ended = false;
        
        t.reset();
        t.stop();
    }
    
    public boolean rumble = false;
    

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
//    	
    	System.out.println(/*arg0*/);
    	System.out.println("trig pressed : " + leftTrigPressed + " trig released: "+ leftTrigReleased);
    	
    	// if left trig is pressed
//    	if(Robot.oi.getCoDriverSummedTriggers() < -0.5) {
//    		System.out.println("trig pressed");
//    		// if it has not already been pressed nor released
//    		if(!leftTrigPressed && !leftTrigReleased) {
//    			System.out.println("setting shooter full");
//    			// set left trig to true (it has been pressed)
//    			leftTrigPressed = true;
//    			rumble = true;
//    			// shoot spin up
//    			Robot.harvesterRollers.setShooter(Robot.harvesterRollers.SHOOTER_SHOOT_V_BUS);
//    		// if we have already released
//    		} else if (leftTrigReleased) {
//    			System.out.println("trig pressed : " + leftTrigPressed + " trig released: "+ leftTrigReleased);
//    			
//    			System.out.println("setting shooter off");
//    			// turn shooter off
//    			Robot.harvesterRollers.setShooter(0);
//    			// set left trig to true (it has been pressed)
//    			leftTrigPressed = true;
//    			// we have not released yet
//    			leftTrigReleased = false;
//    		}
//    	// if left and right trigs are not pressed
//    	} else if(Math.abs(Robot.oi.getCoDriverSummedTriggers()) < 0.1 ){
//    		// if we have pressed but not released yet
//    		if(leftTrigPressed && !leftTrigReleased) {
//    			// we have released
//    			leftTrigReleased = true;
//    		}
//    		
////    		if(rightTrigPressed && !rightTrigReleased) {
//    			
////    			rightTrigReleased = true;
////    		}
//    		// we aren't pressed any more
////    		rightTrigPressed = false;
//			leftTrigPressed = false;
//    	} else if(Robot.oi.getCoDriverSummedTriggers() > 0.5) {
//    		System.out.println("right trig");
//    		Robot.harvesterRollers.setBallControl(Robot.harvesterRollers.HARVESTER_DISCHARGE_BALL_V_BUS);
//    		t.start();
//    	}
//    	if(t.get() > 2) {
//    		ended = true;
//    	}
//    	if(rumble) {
//    		Robot.oi.coDriverRumbleLeft((float) 0.5);
//    		Robot.oi.coDriverRumbleRight((float) 0.5);
//    		
//    	}
//    	
//    	
////    	log.info("Arm Potentiometers left: " + Robot.harvester.getLeftAimPot() + " right: " + Robot.harvester.getRightAimPot() + " left switch:" + Robot.harvester.getLeftLimit()  + " right switch:" + Robot.harvester.getRightLimit());
//    	
//    	//SET SHOOTER SPEED
////    	if(Robot.oi.getDriverBumperState() == 2) { //if RB1 is pressed
////    		// Feed in
////    		log.info("RB1 pressed Shooter Speed is 1");
////    		Robot.harvester.setShooter(1);
////    	} else if(Robot.oi.getDriverBumperState() == 0) { // if LB1 is pressed
////    		// Feeds out
////    		log.info("LB1 pressed Shooter Speed is -1");
////    		Robot.harvester.setShooter(-1);
////    	} else {
////    		Robot.harvester.setShooter(0);
////    		log.info("RB1 and LB1 not pressed Stop Shooter");
////    	}
//    	
//    	//SET HARVESTER SPEED
////    	if(Robot.oi.getXYButtonState() == 2) { //if X1 is pressed
////    		log.info("X 1 pressed Ball Control is 1");
////    		Robot.harvester.setBallControl(1);
////    	} else if(Robot.oi.getXYButtonState() == 0) { //if Y1 is pressed
////    		log.info("Y 1 pressed Ball Control is -1");
////    		Robot.harvester.setBallControl(-1);
////    	} else {
////    		Robot.harvester.setBallControl(0);
////    		log.info("X 1 and Y 1 not pressed Ball Control Stop");
//    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return ended;
//        return Robot.oi.getXYButtonState() == 1 && Robot.oi.getDriverBumperState() == 1;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("ending");
    	
    	Robot.oi.coDriverRumbleLeft((float) 0);
		Robot.oi.coDriverRumbleRight((float) 0);
    	
    	Robot.harvesterRollers.setBallControl(0);
    	Robot.harvesterRollers.setShooter(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("interrupt");
    	end();
    }
}