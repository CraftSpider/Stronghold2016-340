
package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *Dummy subsystem for a simple Arm with a claw at the end. Includes motors, solenoids, and digital sensors.
 *@version 1.0
 */
public class ClawArm extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private TalonSRX armMotor;
	
	private Solenoid clawPiston;
	
	private AnalogPotentiometer armAngleSensor;
	private DigitalInput bottomSwitch;
	private DigitalInput topSwitch;
	
	public ClawArm() {
		System.out.println("Claw arm constructor method called");
		armMotor = new TalonSRX(RobotMap.ClawArmMotor);
		
		clawPiston = new Solenoid(RobotMap.ClawPiston);
		
		armAngleSensor = new AnalogPotentiometer(RobotMap.ClawArmAngleSensor,270);
		bottomSwitch = new DigitalInput(RobotMap.ClawBottomSwitch);
		topSwitch = new DigitalInput(RobotMap.ClawTopSwitch);
		System.out.println("Claw arm constructor method complete.");
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void openClaw() {
    	System.out.println("Opening Claw");
    	clawPiston.set(true);
    }
    
    public void closeClaw() {
    	System.out.println("Closing Claw");
    	clawPiston.set(false);
    }
    
    public void armDown(double speed) {
    	System.out.println("Sending arm down at " + speed);
		armMotor.set(-speed);
    }
    
    public void armUp(double speed) {
    	System.out.println("Sending arm up at " + speed);
    	armMotor.set(speed);
    }
    
    public void armStop() {
    	System.out.println("Arm Stopped");
    	armMotor.set(0);
    }
    
    public boolean armAtBottom() {
    	System.out.println("Arm at bottom?");
    	return ((armAngleSensor.get() < 1) || bottomSwitch.get());
    }
    
    public boolean armAtTop() {
    	System.out.println("Arm at top?");
    	return ((armAngleSensor.get() > 179) || topSwitch.get());
    }
    
    public double armPosition() {
    	System.out.println("Arm at position " + armAngleSensor.get());
    	return armAngleSensor.get();
    }
}