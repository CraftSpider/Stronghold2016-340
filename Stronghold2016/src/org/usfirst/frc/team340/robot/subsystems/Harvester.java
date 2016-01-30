package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * this is a dumb name because this mechanism will also shoot
 * HarvestShooter?
 * HaversterAndShooter?
 * 
 * todo: robotmap this and write commands
 * todo: modify as design changes (updated as of 1/25/16)
 */
public class Harvester extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	// Roller farthest from the robot, it is the shooter
	
	private CANTalon shooterWheelA;
	private CANTalon shooterWheelB;
	// Roller closest to the robot
	private CANTalon harvesterBallControl;
	
	// not sure what type of motor this is gonna be
	private Victor tiltLeft;
	private Victor tiltRight;
	
	//limit switches
	private DigitalInput limitLeft;
	private DigitalInput limitRight;
	
	public class ZeroablePotentiometer extends AnalogPotentiometer {
		
		private double offset = 0.0;
		
		public ZeroablePotentiometer(int channel) {
			super(channel);
		}
		
		public ZeroablePotentiometer(int channel, double scale) {
			super(channel, scale);
		}
		
		public ZeroablePotentiometer(int channel, double scale, double offset) {	
			super(channel, scale);
			this.offset = offset;
		}
		
		public double get() {
			return super.get() - offset;
		}
		
		public void reset() {
			offset = super.get();
		}
	}   
	
	//potentiometers
	@SuppressWarnings("unused")
	private ZeroablePotentiometer leftPot;
	@SuppressWarnings("unused")
	private ZeroablePotentiometer rightPot;
	
	public Harvester() {		
		shooterWheelA = new CANTalon(RobotMap.HarvesterShooterWheelA);
		shooterWheelB = new CANTalon(RobotMap.HarvesterShooterWheelB);
		shooterWheelB.changeControlMode(CANTalon.TalonControlMode.Follower);
		shooterWheelB.set(shooterWheelA.getDeviceID());
		
		harvesterBallControl = new CANTalon(RobotMap.HarvesterBallControl);
		
		tiltLeft = new Victor(RobotMap.HarvesterAimingMotorA);
		tiltRight = new Victor(RobotMap.HarvesterAimingMotorB);
		
		limitLeft = new DigitalInput(0);
		limitRight = new DigitalInput(1);
		
		leftPot = new ZeroablePotentiometer(0, 250);
		rightPot = new ZeroablePotentiometer(1, 250);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * Drives the roller farthest from the robot
     * @param value (this is percent voltage, absolute voltage, or encoder speed)
     */
    public void setShooter(double value) {
    	shooterWheelA.set(value);
    }
    
    /**
     * all methods begin by disabling the wheels. changes controlling mode to voltage %,
     * absolute voltage (voltage compensation), and encoder speed, respectively. latter also reenables
     */
    public void setPercentVolt() {
    	shooterWheelA.disable();
    	shooterWheelA.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    }
    
    public void setAbsVolt() {
    	shooterWheelA.disable();
    	shooterWheelA.changeControlMode(CANTalon.TalonControlMode.Voltage);
    }
    
    /**
     * 
     */
    public void setEncSpd() {
    	shooterWheelA.disable();
    	shooterWheelA.changeControlMode(CANTalon.TalonControlMode.Speed);
    	shooterWheelA.enable();
    	
    }
    
    /**
     * Drives the roller closest to the robot
     * at a specific speed.
     * @param speed
     */
    public void setBallControl(double speed) {
    	harvesterBallControl.set(speed);
    }
    
    /**
     * Drives the motor that actuates the harvester/shooter
     * @param speed
     */
    public void setTiltSpeed(double speed) {
    	tiltLeft.set(-speed);
    	tiltRight.set(speed);
    }
    
    /**
     * Access right limit switch state
     * @return boolean limit switch state
     */
    public boolean getRightLimit() {
    	return limitRight.get();
    }
    
    /**
     * Access left limit switch state
     * @return boolean limit switch state
     */
    public boolean getLeftLimit() {
    	return limitLeft.get();
    }
}
