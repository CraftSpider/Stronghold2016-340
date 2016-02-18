
package org.usfirst.frc.team340.robot;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.subsystems.Climber;
import org.usfirst.frc.team340.robot.subsystems.Drive;
import org.usfirst.frc.team340.robot.subsystems.Harvester;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	public static Level logLevel = Level.FINE;
	public static ConsoleHandler logHandler;
	
	public static Climber climber;
	public static Drive drive;
	public static Harvester harvester;
	public static OI oi;

    Command autonomousCommand;
    Command belowLowBar;
    SendableChooser chooser;
    
    public Robot() {
    	super();
    	if(logHandler == null) {
    		logHandler = new ConsoleHandler();
            logHandler.setLevel(logLevel);
            logHandler.setFormatter(new Formatter() {
    			@Override
    			public String format(LogRecord record) {
    				String t = record.getLoggerName()+ "[" + record.getLevel() + "]" + record.getMessage(); 
    				return t;
    			}
    		});
    	}
    }

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		
    	harvester = new Harvester();
        drive = new Drive();
        climber = new Climber();
        SmartDashboard.putData(harvester);
        SmartDashboard.putData(drive);
        SmartDashboard.putData(climber);
        logHandler = new ConsoleHandler();
        logHandler.setLevel(logLevel);
        logHandler.setFormatter(new Formatter() {
			@Override
			public String format(LogRecord record) {
				String t = record.getLoggerName()+ "[" + record.getLevel() + "]" + record.getMessage(); 
				return t;
			}
		});
        oi = new OI();

        chooser = new SendableChooser();
//        chooser.addDefault("Default Auto", new AutoDefault());
//        chooser.addObject("My Auto", new MyAutoCommand());
        SmartDashboard.putData("AutoSelect", chooser);
        
        
    }
    
    public static Logger getLogger(String name) {
    	if(logHandler == null) {
    		logHandler = new ConsoleHandler();
    		logHandler.setFormatter(new Formatter() {
				
				@Override
				public String format(LogRecord record) {
					//record.getLoggerName();
					String t = record.getLoggerName()+ "[" + record.getLevel() + "]" + record.getMessage(); 
					return t;
				}
			});
            logHandler.setLevel(logLevel);
    	}
    	Logger logger = Logger.getLogger(name);
    	logger.addHandler(logHandler);
    	return logger;
    }
    
    public static Logger getLogger(Class<?> _class) {
    	Logger logger = getLogger(_class.getName());
    	
    	return logger;
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
        
        belowLowBar.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
