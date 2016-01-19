
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
		armMotor = new TalonSRX(RobotMap.ClawArmMotor);
		
		clawPiston = new Solenoid(RobotMap.ClawPiston);
		
		armAngleSensor = new AnalogPotentiometer(RobotMap.ClawArmAngleSensor,270);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void openClaw() {
    	clawPiston.set(true);
    }
    
    public void closeClaw() {
    	clawPiston.set(false);
    }
    
    public void armDown(double speed) {
		armMotor.set(-speed);
    }
    
    public void armUp(double speed) {
    	armMotor.set(speed);
    }
    
    public void armStop() {
    	armMotor.set(0);
    }
    
    public boolean armAtBottom() {
    	return ((armAngleSensor.get() < 1) || bottomSwitch.get());
    }
    
    public boolean armAtTop() {
    	return ((armAngleSensor.get() > 179) || topSwitch.get());
    }
    
    public double armPosition() {
    	return armAngleSensor.get();
    }
}