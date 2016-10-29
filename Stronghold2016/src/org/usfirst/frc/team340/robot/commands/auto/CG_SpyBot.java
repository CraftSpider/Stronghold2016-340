package org.usfirst.frc.team340.robot.commands.auto;

import org.usfirst.frc.team340.robot.commands.ArmToMax;
import org.usfirst.frc.team340.robot.commands.DriveTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_SpyBot extends CommandGroup {
	public CG_SpyBot() {
		addParallel(new ArmToMax());
		addSequential(new DriveTime(3.2, .25, .3));
		addSequential(new DriveTime(.2, .8, -.2));
//		addSequential(new RollersSpeedOut(), 4);
//		addSequential(new RollersShootFire());
	}
}
