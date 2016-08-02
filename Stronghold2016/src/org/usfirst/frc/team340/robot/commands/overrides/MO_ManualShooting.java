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
    
    private boolean ended = false;
    
    Timer t = new Timer();
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	leftTrigPressed = false;
    	leftTrigReleased = false;
        
    	ended = false;
        
        t.reset();
        t.stop();
        log.fine("[MO_ManualShooting] Initializing!");
    }
    
    public boolean rumble = false;
    

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	System.out.println("trig pressed : " + leftTrigPressed + " trig released: "+ leftTrigReleased);
    	
    	// if left trig is pressed
    	if(Robot.oi.getCoDriverSummedTriggers() < -0.5) {
    		System.out.println("trig pressed");
    		// if it has not already been pressed nor released
    		if(!leftTrigPressed && !leftTrigReleased) {
    			System.out.println("setting shooter full");
    			// set left trig to true (it has been pressed)
    			leftTrigPressed = true;
    			rumble = true;
    			// shoot spin up
    			Robot.harvesterRollers.setShooter(Robot.harvesterRollers.SHOOTER_SHOOT_V_BUS);
    		// if we have already released
    		} else if (leftTrigReleased) {
    			System.out.println("trig pressed : " + leftTrigPressed + " trig released: "+ leftTrigReleased);
    			
    			System.out.println("setting shooter off");
    			// turn shooter off
    			Robot.harvesterRollers.setShooter(0);
    			// set left trig to true (it has been pressed)
    			leftTrigPressed = true;
    			// we have not released yet
    			leftTrigReleased = false;
    		}
    	// if left and right trigs are not pressed
    	} else if(Math.abs(Robot.oi.getCoDriverSummedTriggers()) < 0.1 ){
    		// if we have pressed but not released yet
    		if(leftTrigPressed && !leftTrigReleased) {
    			// we have released
    			leftTrigReleased = true;
    		}
			leftTrigPressed = false;
    	} else if(Robot.oi.getCoDriverSummedTriggers() > 0.5) {
    		System.out.println("right trig");
    		Robot.harvesterRollers.setBallControl(Robot.harvesterRollers.HARVESTER_DISCHARGE_BALL_V_BUS);
    		t.start();
    	}
    	if(t.get() > 2) {
    		ended = true;
    	}
    	if(rumble) {
    		Robot.oi.coDriverRumbleLeft((float) 0.5);
    		Robot.oi.coDriverRumbleRight((float) 0.5);
    		
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return ended;
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