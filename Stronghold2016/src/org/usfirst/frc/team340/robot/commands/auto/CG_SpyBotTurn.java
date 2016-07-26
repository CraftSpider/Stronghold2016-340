package org.usfirst.frc.team340.robot.commands.auto;

import org.usfirst.frc.team340.robot.commands.ArmToMax;
import org.usfirst.frc.team340.robot.commands.DriveTurn;
import org.usfirst.frc.team340.robot.commands.ArmMove;
import org.usfirst.frc.team340.robot.commands.Shoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_SpyBotTurn extends CommandGroup {
    
    public  CG_SpyBotTurn() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	addSequential(new ArmToMax(), 5);
    	addSequential(new Shoot(-0.67), 7);
    	addSequential(new ArmMove(-1.0), 1);
    	addSequential(new DriveTurn(-0.55, -0.3), 3);
    	
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
    }
}
