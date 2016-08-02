package org.usfirst.frc.team340.robot.commands.auto;

import org.usfirst.frc.team340.robot.commands.ArmToZero;
import org.usfirst.frc.team340.robot.commands.DriveTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @author Austin
 */
public class CG_LowBarAndShoot extends CommandGroup {
    
    public  CG_LowBarAndShoot() {
    			addSequential(new DriveTime(1, -.65));
    			addSequential(new ArmToZero(), 3);
                addSequential(new DriveTime(3.75, -.65));
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require allf the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
