package org.usfirst.frc.team340.robot.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmToNicerPosition extends Command {
	
	private final double KP = 0.05;
	private final double NKP = 0.02;
	private final double tolPos = 2; //tolerance position
	private final double balancePos = 75; //balanced arm position, where no feedforward is needed.
	private final double maxPos = 150; //max arm position
	private final double negFFwdCmd = -0.4; //max feedforward amount for negative direction moving
	private final double posFFwdCmd = 0.5; //max feedforward amount for positive direction moving
	private double tgtPos;
	public double minPosCmdSpd = 0.15; //positive direction command will be at least this much
	public double maxNegCmdSpd = -0.1; //negative direction command will be at liast this much
	private Logger logger = Robot.getLogger("ArmToNicerPosition");
	double currPos =0;

    public ArmToNicerPosition(double tgt) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.harvester);
    	this.tgtPos = tgt;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	logger.info("initializing");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	logger.info("executing");
    	currPos = Robot.harvester.getRightAimPot();//(Robot.harvester.getLeftAimPot() + Robot.harvester.getRightAimPot()) / 2;
    	double deltaPos = this.tgtPos - currPos;
    	double cmdSpd = 0;
    	double cmdFF;
    	
    	if(deltaPos >= 0 ) {
    		cmdSpd += deltaPos * KP;
    	} else {
    		cmdSpd += deltaPos * NKP;
    	}
    	
    	
    	logger.info("Command speed is: " + cmdSpd);
    	//ramp? or something else?
    	//desiredSpd  += ??
    	
    	if(deltaPos > 0 && currPos >= 0 && currPos <= balancePos) { //checking positive direction lifting
    		logger.info("Is positive lifting");
    		cmdFF = ((-posFFwdCmd/balancePos) * currPos) + posFFwdCmd;
    	} else if(deltaPos < 0 && currPos >= balancePos && currPos <= maxPos) { //checking negative direction lifting
    		logger.info("Is negative lifting");
    		cmdFF = ((negFFwdCmd * currPos) - (negFFwdCmd * balancePos)) / (maxPos - balancePos);
    	} else {
    		logger.info("No lifting");
    		cmdFF = 0;
    	}
    	
    	cmdSpd += cmdFF;
    	
    	//clamp command speed
    	
    	if(cmdSpd > 1) {
    		cmdSpd = 1;
    	} else if(cmdSpd < -1) {
    		cmdSpd = -1;
    	}
    	
    	if(cmdSpd > 0 && cmdSpd < minPosCmdSpd) {
    		cmdSpd = minPosCmdSpd;
    	} else if(cmdSpd < 0 && cmdSpd > maxNegCmdSpd) {
    		cmdSpd = maxNegCmdSpd;
    	}
    	logger.info("commandFF:" + cmdFF + " command speed:" + cmdSpd + " currPos:" + currPos + " Target:" + tgtPos);
    	Robot.harvester.setTilt(cmdSpd);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//we're done when we're 2 away from our target
        return Math.abs(tgtPos - currPos) < tolPos;
    }

    // Called once after isFinished returns true
    protected void end() {
    	logger.info("Ending ArmToNicerPosition");
    	Robot.harvester.setTilt(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
