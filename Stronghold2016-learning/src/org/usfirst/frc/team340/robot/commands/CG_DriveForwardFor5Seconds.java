package org.usfirst.frc.team340.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_DriveForwardFor5Seconds extends CommandGroup {
    
    public  CG_DriveForwardFor5Seconds() {
       addSequential(new DriveForward(1,1));
       addSequential(new DriveForward(2,-1));
       addSequential(new DriveForward(2,1));
       
    }
}
