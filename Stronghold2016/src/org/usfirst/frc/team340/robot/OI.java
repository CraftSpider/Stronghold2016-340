package org.usfirst.frc.team340.robot;

import org.usfirst.frc.team340.robot.commands.ArmStop;
import org.usfirst.frc.team340.robot.commands.CG_Shoot;
import org.usfirst.frc.team340.robot.commands.DischargeBall;
import org.usfirst.frc.team340.robot.commands.HarvestBall;
import org.usfirst.frc.team340.robot.commands.MoveArm;
import org.usfirst.frc.team340.robot.commands.Shoot;
import org.usfirst.frc.team340.robot.commands.StopShooter;
import org.usfirst.frc.team340.robot.commands.overrides.MO_ArmDown;
import org.usfirst.frc.team340.robot.commands.overrides.MO_ArmUp;
import org.usfirst.frc.team340.robot.commands.overrides.MO_ManualShooting;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
@SuppressWarnings("unused")
public class OI {
	
    public OI() {
    	// commented out so we dont mess stuff up
//    	X1.whenPressed(new ReleaseLatch());
//    	Y1.whenPressed(new Climb());
//    	Y1.whenReleased(new DriveWithXbox());
//    	
//    	A1.whileActive(new Shoot());
//    	B1.whenPressed(new DischargeBall());
//    	
//    	
    	
//    	X1.whenPressed(new MO_BallControlIn());
//    	X1.whenReleased(new BallControlOff());
//    	
//    	Y1.whenPressed(new MO_BallControlOut());
//    	Y1.whenReleased(new BallControlOff());
    	
//    	RB1.whenPressed(new MO_ShooterIn());
//    	RB1.whenReleased(new StopShooter());
    	
//    	LB1.whenPressed(new MO_ShooterOut());
//    	LB1.whenReleased(new StopShooter());
    	
//    	X1.whenPressed(new ManualShooting());
//    	Y1.whenPressed(new ManualShooting());
//    	RB1.whenPressed(new ManualShooting());
//    	LB1.whenPressed(new ManualShooting());
//    	
    	// DRIVER
    	
    	Y1.whenPressed(new DischargeBall());
    	Y1.whenReleased(new StopShooter());
    	
    	LB1.whenPressed(new HarvestBall());
    	LB1.whenReleased(new StopShooter());
    	
    	RB1.whenPressed(new Shoot());
    	RB1.whenReleased(new StopShooter());
    	
    	A1.whenPressed(new MoveArm(0.80));
    	A1.whenReleased(new ArmStop());
    	B1.whenPressed(new MoveArm(-0.75));
    	B1.whenReleased(new ArmStop());
    	
    	// CO DRIVER
    	
    	Y2.whenPressed(new DischargeBall());
    	Y2.whenReleased(new StopShooter());
    	
    	A2.whenPressed(new MoveArm(0.80));
    	A2.whenReleased(new ArmStop());
    	B2.whenPressed(new MoveArm(-0.75));
    	B2.whenReleased(new ArmStop());
    	
    	Start2.whenPressed(new MO_ArmUp());
    	Start2.whenReleased(new ArmStop());
    	Back2.whenPressed(new MO_ArmDown());
    	Back2.whenReleased(new ArmStop());
    	
    	LB2.whenPressed(new HarvestBall());
    	LB2.whenReleased(new StopShooter());
    	
    	RB2.whenPressed(new Shoot());
    	RB2.whenReleased(new StopShooter());
    }
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	//Init & Construct driver controller
	Joystick xBoxDriver = new Joystick(0);
	
	//Init & Construct driver controller buttons
	Button A1 = new JoystickButton(xBoxDriver, 1);
	Button B1 = new JoystickButton(xBoxDriver, 2);
	Button X1 = new JoystickButton(xBoxDriver, 3);
	Button Y1 = new JoystickButton(xBoxDriver, 4);
	Button LB1 = new JoystickButton(xBoxDriver, 5);
	Button RB1 = new JoystickButton(xBoxDriver, 6);
	Button Back1 = new JoystickButton(xBoxDriver, 7);
	Button Start1 = new JoystickButton(xBoxDriver, 8);
	Button LeftStick1 = new JoystickButton(xBoxDriver, 9);
	
	//Turn driver triggers to buttons
//	public class RightTrig1 extends Button {
//		public boolean get() {
//			return xBoxDriver.getRawAxis(3) > .5;
//		}
//	}
//	RightTrig1 rightTrig1 = new RightTrig1();
//	
//	public class LeftTrig1 extends Button {
//		public boolean get() {
//			return xBoxDriver.getRawAxis(2) > .5;
//		}
//	}
//	LeftTrig1 leftTrig1 = new LeftTrig1();
//	
	//Init & construct co-driver controller
	Joystick xBoxCoDriver = new Joystick(1);
	
	//Init and construct co-driver controller buttons
	Button A2 = new JoystickButton(xBoxCoDriver, 1);
	Button B2 = new JoystickButton(xBoxCoDriver, 2);
	Button X2 = new JoystickButton(xBoxCoDriver, 3);
	Button Y2 = new JoystickButton(xBoxCoDriver, 4);
	Button LB2 = new JoystickButton(xBoxCoDriver, 5);
	Button RB2 = new JoystickButton(xBoxCoDriver, 6);
	Button Back2 = new JoystickButton(xBoxCoDriver, 7);
	Button Start2 = new JoystickButton(xBoxCoDriver, 8);
	Button LeftStick2 = new JoystickButton(xBoxCoDriver, 9);
	
	//Turn co-driver triggers to buttons
//	public class RightTrig2 extends Button {
//		public boolean get() {
//			return xBoxCoDriver.getRawAxis(3) > .5;
//		}
//	}
//	RightTrig2 rightTrig2 = new RightTrig2();
//	
//	public class LeftTrig2 extends Button {
//		public boolean get() {
//			return xBoxCoDriver.getRawAxis(2) > .5;
//		}
//	}
//	LeftTrig2 leftTrig2 = new LeftTrig2();
//	
	
	
	/**
	 * Get throttle for GTA (trigger-based) drive
	 * @return double throttle
	 */
	public double getDriverSummedTriggers() {
		//only return for the right trigger if it above 0.05 (dead zone)
		if(xBoxDriver.getRawAxis(3) > 0.05) {
			return xBoxDriver.getRawAxis(3);
		//even though the right trigger isn't above 0.05, make sure the left is to avoid running the
		//motors really slowly
		} else if(xBoxDriver.getRawAxis(2) > 0.05) {
			return -xBoxDriver.getRawAxis(2);
		}
		//in case neither trigger is held down
		return 0;
		
		//todo: add a dead zone variable perhaps in robotmap
	}
	
	public double getDriverLeftY() {
		return -xBoxDriver.getRawAxis(1);
	}
	
	public double getDriverLeftX() {
		return xBoxDriver.getRawAxis(0);
	}
	public double getDriverRightY() {
		return -xBoxDriver.getRawAxis(5);
	}
	
	public double getDriverRightX() {
		return xBoxDriver.getRawAxis(4);
	}
	
	/**
	 * returns 0 if LB1 is pressed
	 * returns 2 if RB1 is pressed
	 * returns 1 if neither are pressed
	 * @return
	 */
	public int getDriverBumperState() {
		if(RB1.get()) {
			return 2;
		} else if(LB1.get()) {
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * returns 2 if X1 is pressed
	 * returns 0 if Y1 is pressed
	 * returns 1 if neither
	 * @return
	 */
	public int getXYButtonState() {
		if(X1.get()) {
			return 2;
		} else if(Y1.get()) {
			return 0;
		} else {
			return 1;
		}
	}
}
