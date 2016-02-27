package org.usfirst.frc.team340.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team340.robot.Robot;

/**
 *
 * @deprecated
 */
@SuppressWarnings("unused")
public class AutoPortcullis extends Command {
	
	private double armLength = 28;
	private double potFlatDifference = 18;
	private double tgtDist;
	private double tgtVelocity;
	

    public AutoPortcullis() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	requires(Robot.harvester);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//X = 28*cos(θ - 18 + (-10/115*θ + 5.8696)), θ goes from 10 to 125
    	//	derivative X = (588/23)*sin(0.913043 (-13.2857+θ))
    	//θ = 13.2857-1.09524 cos^(-1)(X/28), x from -28 to 28
    	//	derivative θ = 1.09524/(sqrt(28-x) * sqrt(28+x))
    	tgtDist = -(armLength * Math.cos(Robot.harvester.getLeftAimPot() - potFlatDifference));
    	tgtVelocity = (588/23) * (Math.sin(0.913043 * (-13.2857 + Robot.harvester.getLeftAimPot())));
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
