package org.usfirst.frc.team340.robot.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * <!-- OLD CODE, USE ArmToNicerPoition UNLESS NECESSARY -->
 */
public class ArmToPosition extends Command {

	// Variables
	public double cmdSpeed;
	public double currPos;
	public double tgtPos;
	public double potAverage;
	public double ThV = 0;
	private double initPos;
	public double maxAcc = 0.05; // Increases speed every tick. This will be changed possibly.
	boolean rightIsAtBottom = false, leftIsAtBottom = false;
	public static double vBound = 1;
	double midPoint;


	// Logger
	Logger logger = Robot.getLogger(ArmToPosition.class);

	public ArmToPosition(double tgtPos) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);

		requires(Robot.harvester);
		this.tgtPos = tgtPos < 0 ? 0 : tgtPos;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		logger.info("[Initializing]");
		Robot.harvester.setTilt(0);
		potAverage = (Robot.harvester.getLeftAimPot() + Robot.harvester.getRightAimPot()) / 2;
		currPos = potAverage;
		initPos = potAverage;
		midPoint = initPos + ((tgtPos - initPos) / 2);
		logger.info("init:" + initPos + "; midPoint: " + midPoint + "; curr: " + currPos);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.harvester.getLeftLimit() && tgtPos < currPos) {
			Robot.harvester.resetLeftPot();
			leftIsAtBottom = true;
		}
		
		if (Robot.harvester.getRightLimit() && tgtPos < currPos) {
			Robot.harvester.resetRightPot();
			rightIsAtBottom = true;
		}

		if(currPos <= midPoint && currPos < tgtPos) {
			logger.info("moving up, increase speed");
			ThV += maxAcc;			
		} else if (currPos > midPoint && currPos < tgtPos) {
			ThV -= maxAcc;
			logger.info("moving up, decrease speed");
		} else if( currPos >= midPoint && currPos > tgtPos) {
			ThV -= maxAcc;
			logger.info("moving down, increase speed");
		} else if( currPos < midPoint && currPos > tgtPos) {
			ThV += maxAcc;
			logger.info("moving down, decrease speed");
		} else {
			ThV = 0;
		}

		if(ThV < vBound && ThV > -vBound) {
			logger.info("ThV is ok");
			cmdSpeed = ThV;
		} else if(ThV >= vBound) {
			logger.info("ThV is too high");
			cmdSpeed = vBound;
		} else if(ThV <= -vBound) {
			logger.info("ThV is too low");
			cmdSpeed = -vBound;
		} else {
			cmdSpeed = 0;
		}
		logger.info("cmdSpeed: " + cmdSpeed);

		if (currPos > tgtPos -1) {
			Robot.harvester.setLeftTilt(leftIsAtBottom ? 0 : -cmdSpeed);
			Robot.harvester.setRightTilt(rightIsAtBottom ? 0 : -cmdSpeed);
			logger.info("tilt: " + -cmdSpeed + "; ThV: " + ThV);
		} else if (currPos < tgtPos + 1) {
			Robot.harvester.setLeftTilt(cmdSpeed);
			Robot.harvester.setRightTilt(cmdSpeed);
			logger.info("tilt: " + cmdSpeed + "; ThV: " + ThV);
		} else {
			Robot.harvester.setTilt(0);
		}
		
		logger.info("target: " + tgtPos + "; current: " + currPos);
		potAverage = (Robot.harvester.getLeftAimPot() + Robot.harvester.getRightAimPot()) / 2;
		currPos = potAverage;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return  !Robot.harvester.hasReset()
				|| (currPos >= tgtPos - 1 && currPos <= tgtPos + 1) 
				|| (Robot.harvester.getLeftLimit() && Robot.harvester.getRightLimit() && tgtPos < currPos)
				|| (Robot.harvester.getLeftAimPot() >= 110 && Robot.harvester.getRightAimPot() >= 110 && tgtPos > currPos);
	}

	// Called once after isFinished returns true
	protected void end() {
		logger.info("[Ending]");
		Robot.harvester.setTilt(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		logger.info("[Interrupted]");
		end();
	}
}
