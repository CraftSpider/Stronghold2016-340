package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * this is a dumb name because this mechanism will also shoot
 * HarvestShooter?
 * HaversterAndShooter?
 * 
 * TODO: robotmap this and write commands
 * TODO: modify as design changes (updated as of 1/25/16)
 */
public class Harvester extends Subsystem {
	
	// Max harvester angle
	public static final double HARVESTER_MAX_ANGLE = 160;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public final double ArmClear = 30; //Pot val to clear harvester of obstacle
	
	// Important shooter constants
	
	//Tilting
	private CANTalon tiltLeft;
	private CANTalon tiltRight;
	
	//limit switches
	private DigitalInput limitLeft;
	private DigitalInput limitRight;
	private DigitalInput limitLeftTop;
	private DigitalInput limitRightTop;
	
	public class ZeroablePotentiometer extends AnalogPotentiometer {
		
		private double offset = 0.0;
		private int invert = 1;
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
			return (super.get() - offset) * invert;
		}
		
		private boolean hasReset = false;
		
		public void reset() {
			offset = super.get();
			hasReset = true;
		}
		public boolean isReset() {
			return hasReset;
		}
		
		public void setInverted(boolean invert) {
			if(invert) {
				this.invert = -1;
			} else {
				this.invert = 1;
			}
		}
		
		public boolean isInverted() {
			return this.invert == -1;
		}
	}   
	
	//potentiometers
	private ZeroablePotentiometer leftPot;
	private ZeroablePotentiometer rightPot;
	
	public Harvester() {		
		
		//TODO: sync left/right motors
		tiltRight = new CANTalon(RobotMap.HARVESTER_AIMING_RIGHT);
		tiltLeft = new CANTalon(RobotMap.HARVESTER_AIMING_LEFT);
		
		//tiltLeft.setVoltageRampRate(5); // this might be a good way to solve our ramp rate issue IE smooth out the jerkiness
		//tiltRight.setVoltageRampRate(5); // this might be a good way to solve our ramp rate issue IE smooth out the jerkiness
		
		limitLeft = new DigitalInput(RobotMap.HARVESTER_LEFT_BUMP_BOTTOM);
		limitRight = new DigitalInput(RobotMap.HARVESTER_RIGHT_BUMP_BOTTOM);
		limitLeftTop = new DigitalInput(RobotMap.HARVESTER_LEFT_BUMP_TOP);
		limitRightTop = new DigitalInput(RobotMap.HARVESTER_RIGHT_BUMP_TOP);
		
		leftPot = new ZeroablePotentiometer(RobotMap.LEFT_AIM_POT, 250);
		rightPot = new ZeroablePotentiometer(RobotMap.RIGHT_AIM_POT, 250);
		leftPot.setInverted(true);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
    
//    /**
//     * Drives the motor that actuates the harvester/shooter
//     * @param speed
//     */
//    public void setTiltSpeed(double speed) {
//    	tiltLeft.set(-speed);
//    	tiltRight.set(speed);
//    }
    
    /**
     * Access right limit switch state
     * @return boolean limit switch state
     */
    public boolean getRightLimit() {
    	return !limitRight.get();
    }
    
    /**
     * Access left limit switch state
     * @return boolean limit switch state
     */
    public boolean getLeftLimit() {
    	return !limitLeft.get();
    }
    
    /**
     * Access top left limit switch state
     * @return boolean top left limit switch state
     */
    public boolean getTopLeftLimit() {
    	return !limitLeftTop.get();
    }
    
    /**
     * Access top right limit switch state
     * @return boolean top right limit switch state
     */
    public boolean getTopRightLimit() {
    	return !limitRightTop.get();
    }
    
    public double getLeftAimPot() {
    	return leftPot.get();
    }
    
    public double getRightAimPot() {
    	return rightPot.get();
    }
    
    public void resetLeftPot() {
    	leftPot.reset();
    }
    public void resetRightPot() {
    	rightPot.reset();
    }
    
    public void resetBothPots() {
    	resetLeftPot();
    	resetRightPot();
    }
    
    public boolean hasReset() {
    	return leftPot.isReset() && rightPot.isReset();
    }
    
    public void setLeftTilt(double speed) {
    	tiltLeft.set(speed);
    	SmartDashboard.putNumber("LeftPotValue", leftPot.get());
    }
    
    public void setRightTilt(double speed) {
    	tiltRight.set(-speed);
    }
    
    public void setTilt(double speed) {
    	this.setRightTilt(speed);
    	this.setLeftTilt(speed);
    	
    }
    
    public double getLeftCurrent() {
    	return tiltLeft.getOutputCurrent();
    }
    
    public double getRightCurrent() {
    	return tiltRight.getOutputCurrent();
    }
}
