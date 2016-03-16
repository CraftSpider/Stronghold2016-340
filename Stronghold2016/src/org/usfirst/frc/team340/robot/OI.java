package org.usfirst.frc.team340.robot;

import org.usfirst.frc.team340.robot.commands.ArmStop;
import org.usfirst.frc.team340.robot.commands.ArmToMax;
import org.usfirst.frc.team340.robot.commands.ArmToNicerPosition;
import org.usfirst.frc.team340.robot.commands.ArmToZero;
import org.usfirst.frc.team340.robot.commands.BallControlOff;
import org.usfirst.frc.team340.robot.commands.Climb;
import org.usfirst.frc.team340.robot.commands.DischargeBall;
import org.usfirst.frc.team340.robot.commands.DriveTime;
import org.usfirst.frc.team340.robot.commands.DriveWithXbox;
import org.usfirst.frc.team340.robot.commands.HarvestBall;
import org.usfirst.frc.team340.robot.commands.MoveArm;
import org.usfirst.frc.team340.robot.commands.MoveArmVariable;
import org.usfirst.frc.team340.robot.commands.ReleaseLatch;
import org.usfirst.frc.team340.robot.commands.Shoot;
import org.usfirst.frc.team340.robot.commands.StopBallControl;
import org.usfirst.frc.team340.robot.commands.StopDrive;
import org.usfirst.frc.team340.robot.commands.StopShooter;
import org.usfirst.frc.team340.robot.commands.StopShooterWheels;
import org.usfirst.frc.team340.robot.commands.auto.CG_SpyBot;
import org.usfirst.frc.team340.robot.commands.overrides.MO_ArmDown;
import org.usfirst.frc.team340.robot.commands.overrides.MO_ArmStop;
import org.usfirst.frc.team340.robot.commands.overrides.MO_ArmUp;
import org.usfirst.frc.team340.robot.commands.overrides.MO_BallControlIn;
import org.usfirst.frc.team340.robot.commands.overrides.MO_BallControlOut;
import org.usfirst.frc.team340.robot.commands.overrides.MO_ClutchOff;
import org.usfirst.frc.team340.robot.commands.overrides.MO_ClutchOn;
import org.usfirst.frc.team340.robot.commands.overrides.MO_ClutchToggle;
import org.usfirst.frc.team340.robot.commands.overrides.MO_ManualShooting;
import org.usfirst.frc.team340.robot.commands.overrides.MO_ShooterIn;
import org.usfirst.frc.team340.robot.commands.overrides.MO_ShooterOut;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
@SuppressWarnings("unused")
public class OI {
	
    public OI() {
    	// commented out so we dont mess stuff up
    	//X1.whenPressed(new ReleaseLatch());
    	//Y1.whenPressed(new Climb());
    	//Y1.whenReleased(new DriveWithXbox());
    	
    	//A1.whileActive(new Shoot());
    	//B1.whenPressed(new DischargeBall());
    	
    	
    	
    	//X1.whenPressed(new MO_BallControlIn());
    	//X1.whenReleased(new BallControlOff());
    	
    	//Y1.whenPressed(new MO_BallControlOut());
    	//Y1.whenReleased(new BallControlOff());
    	
    	//RB1.whenPressed(new MO_ShooterIn());
    	//RB1.whenReleased(new StopShooter());
    	
    	//LB1.whenPressed(new MO_ShooterOut());
    	//LB1.whenReleased(new StopShooter());
    	
    	//X1.whenPressed(new ManualShooting());
    	//Y1.whenPressed(new ManualShooting());
    	//RB1.whenPressed(new ManualShooting());
    	//LB1.whenPressed(new ManualShooting());
    	
    	// DRIVER
    	
//    	Y1.whenPressed(new DischargeBall());
//    	Y1.whenReleased(new StopShooter());
    	
    	//X1.whenPressed(new DriveTime(1, .5));
    	
    	A2.whenPressed(new ArmToZero());
    	A2.whenReleased(new ArmStop());
    	
    	B2.whenPressed(new ArmToMax());
    	B2.whenReleased(new ArmStop());

//    	Start1.whenPressed(new MO_ManualShooting());
    	Start1.whenPressed(new Climb(0.7));
    	Start1.whenReleased(new DriveWithXbox());
    	Back1.whenPressed(new Climb(-1));
    	Back1.whenReleased(new DriveWithXbox());
    	
    	
    	LB1.whenPressed(new HarvestBall());
    	LB1.whenReleased(new StopShooter());
    	
    	RB1.whenPressed(new Shoot());
    	RB1.whenReleased(new StopShooter());
//    	Back1.whenPressed(new MO_ShooterOut());
//    	Back1.whenReleased(new StopShooter());
    	//RB1.whenPressed(new DischargeBall());
    	//RB1.whenReleased(new StopShooter());
    	
//    	Start1.whenPressed(new ArmToZero());
//    	Start1.whenReleased(new ArmStop());
    	
    	//Back1.whenPressed(new ArmToNicerPosition(20) );
    	
    	dPadUp1.whenPressed(new MoveArm(0.80));
    	dPadUp1.whenReleased(new ArmStop());
    	dPadDown1.whenPressed(new MoveArm(-0.75));
    	dPadDown1.whenReleased(new ArmStop());
    	
    	
//    	Back1.whenPressed(new ArmToMax());
//    	Back1.whenReleased(new ArmStop());
    	
//    	dPadUp1.whenPressed(new CG_SpyBot());
//    	X1.whenPressed(new Climb());
//    	X1.whenReleased(new DriveWithXbox());
    	
    	
    	// CO DRIVER
    	
    	Y2.whenPressed(new DischargeBall());
    	Y2.whenReleased(new StopShooter());
    	
    	dPadUp2.whenPressed(new MoveArm(0.90));
    	dPadUp2.whenReleased(new ArmStop());
    	dPadDown2.whenPressed(new MoveArm(-0.8));
    	dPadDown2.whenReleased(new ArmStop());
    	
//    	Start2.whenPressed(new MO_ArmUp());
//    	Start2.whenReleased(new ArmStop());
    	//Start2.whenPressed(new ArmToNicerPosition(12));
    	Back2.whenPressed(new MO_ArmDown());
    	Back2.whenReleased(new ArmStop());
    	
    	LB2.whenPressed(new HarvestBall());
    	LB2.whenReleased(new StopShooter());
    	
    	RB2.whenPressed(new Shoot());
    	RB2.whenReleased(new StopShooter());
    	
    	//rightTrig2.whenPressed(new MO_ClutchToggle());
    	
    	//rightTrig2.whenPressed(new MO_ClutchOn());
    	
    	//leftTrig2.whenPressed(new MO_ClutchOff());
    	
//    	X2.whenPressed(new Climb());
//    	X2.whenReleased(new StopDrive());
    	
    	coDriverLeftStick.whenPressed(new MoveArmVariable());
    	coDriverLeftStick.whenReleased(new StopDrive());
    	
    	coDriverTrigs.whenPressed(new MO_ManualShooting());
    	//A1.whenPressed(new DriveDistance(5,20));
    	//B1.whenPressed(new DriveDistance(5,-20));
    	
    	//////////////////////////////////////////
    	//			  MANUAL BOARD				//
    	//////////////////////////////////////////
    	
    	HarvesterUp.whenPressed(new MO_ArmUp());
    	HarvesterUp.whenReleased(new MO_ArmStop());
    	HarvesterDown.whenPressed(new MO_ArmDown());
    	HarvesterDown.whenReleased(new MO_ArmStop());
    	
    	RollerIn.whenPressed(new MO_BallControlIn());
    	RollerIn.whenReleased(new StopBallControl());
    	RollerOut.whenPressed(new MO_BallControlOut());
    	RollerOut.whenReleased(new StopBallControl());
    	
    	ShooterIn.whenPressed(new MO_ShooterIn());
    	ShooterIn.whenReleased(new StopShooterWheels());
    	ShooterOut.whenPressed(new MO_ShooterOut());
    	ShooterOut.whenReleased(new StopShooterWheels());
    	
    	ClimberEngage.whenPressed(new MO_ClutchOn());
    	ClimberEngage.whenReleased(new DriveWithXbox());
    	ClimberDisengage.whenPressed(new MO_ClutchOff());
    	ClimberDisengage.whenPressed(new DriveWithXbox());
    	
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
	
    Joystick ManualBoard = new Joystick(2);
    
    Button HarvesterUp = new JoystickButton(ManualBoard, 2);
    Button HarvesterDown = new JoystickButton(ManualBoard, 1);
    Button RollerIn = new JoystickButton(ManualBoard, 10);
    Button RollerOut = new JoystickButton(ManualBoard, 13);
    Button ShooterIn = new JoystickButton(ManualBoard, 5);
    Button ShooterOut = new JoystickButton(ManualBoard, 6);
    Button ClimberUp = new JoystickButton(ManualBoard, 4);
    Button ClimberDown = new JoystickButton(ManualBoard, 3);
    Button ClimberEngage = new JoystickButton(ManualBoard, 9);
    
    public class climberDisengage extends Button {
		public boolean get() {
//			System.out.println(xBoxDriver.getPOV());
			return ManualBoard.getPOV() == 0;
		}
	}
    	
    climberDisengage ClimberDisengage = new climberDisengage();
    
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
	
	public class DPadUp1 extends Button {
		public boolean get() {
//			System.out.println(xBoxDriver.getPOV());
			return xBoxDriver.getPOV() == 0;
		}
	}
	
	DPadUp1 dPadUp1 = new DPadUp1();
	
	public class DPadDown1 extends Button {
		public boolean get() {
//			System.out.println(xBoxDriver.getPOV());
			return xBoxDriver.getPOV() == 180;
		}
	}
	
	DPadDown1 dPadDown1 = new DPadDown1();
	
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
	public class RightTrig2 extends Button {
		public boolean get() {
			return xBoxCoDriver.getRawAxis(3) > .5;
		}
	}
	RightTrig2 rightTrig2 = new RightTrig2();
	
	public class LeftTrig2 extends Button {
		public boolean get() {
			return xBoxCoDriver.getRawAxis(2) > .5;
		}
	}
	LeftTrig2 leftTrig2 = new LeftTrig2();
	
	public class DPadUp2 extends Button {
		public boolean get() {
//			System.out.println(xBoxDriver.getPOV());
			return xBoxCoDriver.getPOV() == 0;
		}
	}
	
	DPadUp2 dPadUp2 = new DPadUp2();
	
	public class DPadDown2 extends Button {
		public boolean get() {
//			System.out.println(xBoxDriver.getPOV());
			return xBoxCoDriver.getPOV() == 180;
		}
	}
	
	DPadDown2 dPadDown2 = new DPadDown2();
	
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
	
	public double getCoDriverSummedTriggers() {
		//only return for the right trigger if it above 0.05 (dead zone)
		if(xBoxCoDriver.getRawAxis(3) > 0.05) {
			return xBoxCoDriver.getRawAxis(3);
		//even though the right trigger isn't above 0.05, make sure the left is to avoid running the
		//motors really slowly
		} else if(xBoxCoDriver.getRawAxis(2) > 0.05) {
			return -xBoxCoDriver.getRawAxis(2);
		}
		//in case neither trigger is held down
		return 0;
		
		//todo: add a dead zone variable perhaps in robotmap
	}
	
	public class CoDriverTrigs extends Button {
		public boolean get() {
//			System.out.println(xBoxDriver.getPOV());
			return Math.abs(getCoDriverSummedTriggers()) < 0.5;
		}
	}
	
	CoDriverTrigs coDriverTrigs = new CoDriverTrigs();
	
	public double getDriverLeftY() {
		return -xBoxDriver.getRawAxis(1);
	}
	
	public double getDriverLeftX() {
		return -xBoxDriver.getRawAxis(0);
	}
	public double getDriverRightY() {
		return -xBoxDriver.getRawAxis(5);
	}
	
	public double getDriverRightX() {
		return -xBoxDriver.getRawAxis(4);
	}
	
	public double getCoDriverLeftY() {
		return -xBoxCoDriver.getRawAxis(1);
	}
	
	public class CoDriverLeftStick extends Button {
		public boolean get() {
//			System.out.println(xBoxDriver.getPOV());
			return Math.abs(getCoDriverLeftY()) > 0.1;
		}
	}
	
	CoDriverLeftStick coDriverLeftStick = new CoDriverLeftStick();
	
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
	
	/**
	 * For button-based shooter/ball control desync.
	 * Allows Shoot.java to test whether or not Back is pressed.
	 * See the note there for extra explanation.
	 * TODO: make button-based shooter/ball control desync better
	 * @return state of Back button
	 */
	public boolean getBackState() {
		return Back1.get();
	}
	
	/**
	 * For button-based shooter/ball control desync.
	 * Allows Shoot.java to test whether or not Start is pressed.
	 * See the note there for extra explanation.
	 * TODO: make button-based shooter/ball control desync better
	 * @return state of Start button
	 */
	public boolean getStartState() {
		return Start1.get();
	}
	
	public boolean getDPadUpState2() {
		return dPadUp2.get();
	}
	public boolean getDPadDownState2() {
		return dPadDown2.get();
	}
	
	/**
	 * Rumbles the right side of the driver controller at given speed
	 */
	public void driverRumbleRight(float strength) {
		xBoxDriver.setRumble(RumbleType.kRightRumble, strength);
	}
	
	/**
	 * Rumbles the right side of the driver controller at given speed
	 */
	public void driverRumbleLeft(float strength) {
		xBoxDriver.setRumble(RumbleType.kLeftRumble, strength);
	}
	
	/**
	 * Rumbles the right side of the driver controller at given speed
	 */
	public void coDriverRumbleRight(float strength) {
		xBoxCoDriver.setRumble(RumbleType.kRightRumble, strength);
	}
	
	/**
	 * Rumbles the right side of the driver controller at given speed
	 */
	public void coDriverRumbleLeft(float strength) {
		xBoxCoDriver.setRumble(RumbleType.kLeftRumble, strength);
	}
}
