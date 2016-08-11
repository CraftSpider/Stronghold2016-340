package org.usfirst.frc.team340.robot.commands;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.ControllerPower;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RollersSpeedOut extends Command {

    public RollersSpeedOut() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.harvesterRollers);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(this.timeSinceInitialized()<Robot.harvesterRollers.SHOOTER_SHOOT_SPINUP_TIME){
    		Robot.harvesterRollers.setBallControl(Robot.harvesterRollers.HARVESTER_HARVEST_V_BUS*12/ControllerPower.getInputVoltage());
    	}else{
    		Robot.harvesterRollers.setBallControl(0);
    	}
    	Robot.harvesterRollers.setShooter(-10/ControllerPower.getInputVoltage());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.harvesterRollers.setBallControl(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}
