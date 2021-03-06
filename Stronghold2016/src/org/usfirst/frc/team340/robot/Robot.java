package org.usfirst.frc.team340.robot;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.usfirst.frc.team340.robot.commands.DriveTime;
import org.usfirst.frc.team340.robot.commands.auto.AutoDoNothing;
import org.usfirst.frc.team340.robot.commands.auto.CG_DefenseAndShoot;
import org.usfirst.frc.team340.robot.commands.auto.CG_LowBarAndShoot;
import org.usfirst.frc.team340.robot.commands.auto.CG_SpyBot;
import org.usfirst.frc.team340.robot.commands.auto.CG_SpyBotTurn;
import org.usfirst.frc.team340.robot.subsystems.Climber;
import org.usfirst.frc.team340.robot.subsystems.Drive;
import org.usfirst.frc.team340.robot.subsystems.Flashlight;
import org.usfirst.frc.team340.robot.subsystems.Harvester;
import org.usfirst.frc.team340.robot.subsystems.HarvesterRollers;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
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
@SuppressWarnings("deprecation")
public class Robot extends IterativeRobot {
	
	public static Level logLevel = Level.FINE;
	public static ConsoleHandler logHandler;
	
	public static Climber climber;
	public static Drive drive;
	public static Harvester harvester;
	public static HarvesterRollers harvesterRollers;
	public static Flashlight flashlight;
	public static OI oi;
	
//	public static Timer climberFailsafe;

    Command autonomousCommand;
    CommandGroup belowLowBar;
    SendableChooser chooser;
    
    CameraServer server;
    
    public Robot() {
    	super(); // the following newline was from joey gmitter dropping a cup off his head and hitting the laptop
    	
    	if(logHandler == null) {
    		logHandler = new ConsoleHandler();
            logHandler.setLevel(logLevel);
            logHandler.setFormatter(new Formatter() {
    			@Override
    			public String format(LogRecord record) {
    				String nameLvl = record.getLoggerName()+ "[" + record.getLevel() + "]" + record.getMessage(); 
    				return nameLvl;
    			}
    		});
    	}
//    	USBCamera camera = new USBCamera("cam0");
////    	camera.
//    	camera.setExposureManual(0);
//        server = CameraServer.getInstance();
//        server.setQuality(	50);
//    	
////        //the camera name (ex "cam0") can be found through the roborio web interface
//        server.startAutomaticCapture(camera);
    }

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		
    	harvester = new Harvester();
        drive = new Drive();
        climber = new Climber();
        harvesterRollers = new HarvesterRollers();
        flashlight = new Flashlight();
        SmartDashboard.putData(harvester);
        SmartDashboard.putData(drive);
        SmartDashboard.putData(climber);
        SmartDashboard.putData(harvesterRollers);
        oi = new OI();
//ppim.pm.ip.mip.mi.p.miipmmip.ip.m.pimmmpim.m.ipm..ipmmpimipmipm.mmipipm.pipmi.pmipmmpimipmpi..lmmmplip.mimp.im..p.ppn.p.pp.p.ppp.p
        chooser = new SendableChooser();
        chooser.addDefault("Do nothing", new AutoDoNothing());
        chooser.addObject("Spy Bot", new CG_SpyBot());
        chooser.addObject("Spy Bot and Turn", new CG_SpyBotTurn());
        chooser.addObject("Drive straight 2.5 seconds", new DriveTime(2.5, 1));
        chooser.addObject("Low bar", new CG_LowBarAndShoot());
        chooser.addObject("Shoot", new CG_DefenseAndShoot());
//        chooser.addObject("Middle to Low Bar", new MyAutoCommand());
        SmartDashboard.putData("AutoSelect", chooser);
    }
    
    public static Logger getLogger(String name) {
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
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    	
    	//Calibrates Gyro at beginning of auto.
//        drive.calibrateGyro();
    	
//    	belowLowBar = new CG_AutoLowBar();
//        belowLowBar.start();
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
        
//        climberFailsafe.start();
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
    
    /**
     * Basic failsafe to prevent climber from being raised at the wrong time.
     * I can't stop the timer from here, so I did it in the command
     * @return boolean are we at the end of the match
     */
//    public static boolean isEndGame() {
//    	if(climberFailsafe.get() >= 110) {
//    		return true;
//    	} else {
//    		return false;
//    	}
//    }
}
