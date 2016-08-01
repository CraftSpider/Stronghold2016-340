package org.usfirst.frc.team340.robot.commands.auto;

import org.usfirst.frc.team340.robot.commands.ArmToMax;
import org.usfirst.frc.team340.robot.commands.ArmToZero;
import org.usfirst.frc.team340.robot.commands.DriveTime;
import org.usfirst.frc.team340.robot.commands.Shoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_DefenseAndShoot extends CommandGroup {
    
    public  CG_DefenseAndShoot() {
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
    	addSequential(new ArmToZero(), 3);
//    	addSequential(new DriveTime(3, 0.5));
    	addSequential(new ArmToMax(), 1.3);
    	addSequential(new Shoot(), 5);
    }
}
