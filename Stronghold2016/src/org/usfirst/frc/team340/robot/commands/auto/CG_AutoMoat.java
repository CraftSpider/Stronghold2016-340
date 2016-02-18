package org.usfirst.frc.team340.robot.commands.auto;

import org.usfirst.frc.team340.robot.Robot;
import org.usfirst.frc.team340.robot.commands.ArmToPosition;
import org.usfirst.frc.team340.robot.commands.DriveDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoMoat extends CommandGroup {
    
    public  CG_AutoMoat() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	addSequential(new ArmToPosition(Robot.harvester.ArmClear));
    	addSequential(new DriveDistance(10, 500));
    }
}
