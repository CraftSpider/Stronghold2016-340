package org.usfirst.frc.team340.robot.commands;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.ControllerPower;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RollersShootFire extends Command {

    public RollersShootFire() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.harvesterRollers);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	Robot.harvesterRollers.getShooterSpeed();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.harvesterRollers.setShooter(-8/ControllerPower.getInputVoltage());
    	Robot.harvesterRollers.setBallControl(Robot.harvesterRollers.HARVESTER_DISCHARGE_BALL_V_BUS);
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
