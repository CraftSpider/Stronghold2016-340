package org.usfirst.frc.team340.robot.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

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
    	requires(Robot.drive);
    }
    
    public Shoot(double speed) {
    	requires(Robot.harvesterRollers);
    	requires(Robot.drive);
    	speedOverride = speed;
    }
    
    Timer t = new Timer();
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	speed = false;
//    	t.start();
    	t.stop();
    	t.reset();
    	t.start();
    	logger.info("[Initializing: Shooter]");
    	liningUp = false;
    	align = false;
    	
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
    
    //private boolean speed = false;
    //private boolean align = false;
    private boolean speed = true;
    private boolean align = true;
    private boolean liningUp = false;
    
    private double checkDelay = 0.6;
    private double width = 320;
    private double tolerance = 2;
    private double offset = 18;
    private double angleOff = 0;
    private double initAngle = 0;
    

	NetworkTable table = NetworkTable.getTable("GRIP");
    
    protected void execute() {
//    	System.out.println(Robot.harvesterRollers.getShooterSpeed());
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
    	
//    	if(t.get() > Robot.harvesterRollers.SHOOTER_SHOOT_SPINUP_TIME) {
//    		desiredBallControlSpeed = Robot.harvesterRollers.HARVESTER_RELEASE_BALL_V_BUS;
//    	}
    	if(Robot.harvesterRollers.getShooterSpeed() < -55000 || t.get() > Robot.harvesterRollers.SHOOTER_SHOOT_SPINUP_TIME) {
    		speed = true;
    	}
    	if(speed) {
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
    	try {
    	double[] defaultValue = new double[0];
    	
    	double[] areas = table.getNumberArray("contours/area", defaultValue);
//    	System.out.println(table.getSubTables());
//    	System.out.println(areas.length);
    	int which = 0;
//    	if(areas.length > 1) {
    		double record = 0;
    		for(int i = 0; i< areas.length; i++) {
    			if(areas[i] > record && areas[i] > 200) {
    				record = areas[i];
    				which = i;
    			}
    		}
//    	}
    	double[] centerX = table.getNumberArray("contours/centerX", defaultValue);
//    	System.out.println(centerX.length);
    	double startspeed = 0.25;
    	double maxModifier = 0.2;
    	if(!liningUp) {
    		angleOff = ((width/2-offset)-centerX[which])*0.157;
    		initAngle = Robot.drive.getGyroAngle();
    		liningUp = true;
    		t.stop();
    		t.reset();
    	}
    	System.out.println("angle: " + angleOff);
    	align = true;
    	if(angleOff > 0 && Math.abs(Robot.drive.getGyroAngle() - initAngle) < Math.abs(angleOff) && liningUp) {
    		System.out.println("change: " + Math.abs(Robot.drive.getGyroAngle() - initAngle));
    		Robot.drive.setLeftDrive(0.5);
    		Robot.drive.setRightDrive(-0.5);
    	} else if(angleOff < 0 && Math.abs(Robot.drive.getGyroAngle() - initAngle) < Math.abs(angleOff) && liningUp) {
    		Robot.drive.setLeftDrive(-0.5);
    		Robot.drive.setRightDrive(0.5);
    	} else {
    		Robot.drive.setLeftDrive(0);
    		Robot.drive.setRightDrive(0);
    		t.start();
    		if(Math.abs((width/2+offset)-centerX[which]) < tolerance) {
    			System.out.println("lined up");
    			align = true;
    			liningUp = true;
    		} else {
    			if(t.get() > checkDelay) {
//	    			align = false;
	    			liningUp = false;
    			}
    		}
    	}
    	

		} catch (Exception e) {
			// do nothing
			Robot.drive.setRightDrive(0);
			Robot.drive.setLeftDrive(0);

			if (t.get() > Robot.harvesterRollers.SHOOTER_SHOOT_SPINUP_TIME) {
				desiredBallControlSpeed = Robot.harvesterRollers.HARVESTER_RELEASE_BALL_V_BUS;
			}
    	}
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
