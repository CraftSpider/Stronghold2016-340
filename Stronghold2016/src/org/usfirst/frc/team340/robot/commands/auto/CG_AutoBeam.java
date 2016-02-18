package org.usfirst.frc.team340.robot.commands.auto;

import org.usfirst.frc.team340.robot.Robot;
import org.usfirst.frc.team340.robot.commands.ArmToPosition;
import org.usfirst.frc.team340.robot.commands.DriveDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoBeam extends CommandGroup {
    
    public  CG_AutoBeam() {
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
    	
    	addSequential(new ArmToPosition(Robot.harvester.ArmClear * 2));
    	addSequential(new DriveDistance(10, 100));
    	addParallel(new DriveDistance(10, 500));
    	addSequential(new ArmToPosition(0));
    }
}
