package org.usfirst.frc.team340.robot.commands;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmToNicerPosition extends Command {
	
	private final double KP = 0.05;
	private double tgtPos;
	public double minPosCmdSpd = 0.1;
	public double maxNegCmdSpd = -0.1;

    public ArmToNicerPosition(double tgt) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.harvester);
    	this.tgtPos = tgt;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double currPos = (Robot.harvester.getLeftAimPot() + Robot.harvester.getRightAimPot()) / 2;
    	double deltaPos = this.tgtPos - currPos;
    	double desiredSpd = 0;
    	desiredSpd += deltaPos * KP;
    	
    	//ramp? or something else?
    	//desiredSpd  += ??
    	
    	if(desiredSpd > 1) {
    		desiredSpd = 1;
    	} else if(desiredSpd < -1) {
    		desiredSpd = -1;
    	}
    	
    	if(desiredSpd > 0 && desiredSpd < minPosCmdSpd) {
    		desiredSpd = minPosCmdSpd;
    	} else if(desiredSpd < 0 && desiredSpd > maxNegCmdSpd) {
    		desiredSpd = maxNegCmdSpd;
    	}
    	
    	Robot.harvester.setTilt(desiredSpd);
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
    }
}
