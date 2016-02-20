package org.usfirst.frc.team340.robot.commands.auto;

import org.usfirst.frc.team340.robot.commands.ArmToPosition;
import org.usfirst.frc.team340.robot.commands.ArmToZero;
import org.usfirst.frc.team340.robot.commands.DriveDistance;
import org.usfirst.frc.team340.robot.commands.DriveTime;
import org.usfirst.frc.team340.robot.commands.Shoot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;

/**
 *
 */
public class CG_AutoLowBar extends CommandGroup {
    
    public  CG_AutoLowBar() {
         /*Add Commands here:
         e.g. addSequential(new Command1());
              addSequential(new Command2());
         these will run in order.

         To run multiple commands at the same time,
         use addParallel()
         e.g. addParallel(new Command1());
              addSequential(new Command2());
         Command1 and Command2 will run in parallel.

         A command group will require all of the subsystems that each member
         would require.
         e.g. if Command1 requires chassis, and Command2 requires arm,
         a CommandGroup containing them would require both the chassis and the
         arm.*/
    	
    	addSequential(new ArmToZero());
    	addSequential(new PrintCommand("Autonomous finished"));
    	addSequential(new DriveTime(3, .5));
    	addSequential(new Shoot(), 3.0);
    	//addSequential(new DriveDistance(10, 500));
    }
}
