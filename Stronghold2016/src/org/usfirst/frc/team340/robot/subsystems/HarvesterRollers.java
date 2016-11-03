package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class HarvesterRollers extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public final double SHOOTER_SHOOT_V_BUS = -1.0;
	public final double SHOOTER_HARVEST_V_BUS = 1.0;
	public final double SHOOTER_DISCHARGE_BALL_V_BUS = -0.75;
	public final double SHOOTER_SHOOT_SPINUP_TIME = .2;
	
	// Important ball control constants
	public final double HARVESTER_RELEASE_BALL_V_BUS = .6;
	public final double HARVESTER_DISCHARGE_BALL_V_BUS = 0.75;
	public final int HARVESTER_CONTROL_STALL_CURRENT = 42;
	public final double HARVESTER_HARVEST_V_BUS = -1.0;
	
	private CANTalon shooterWheelA;
	private CANTalon shooterWheelB;
	
	// Roller closest to the robot
	private CANTalon harvesterBallControl;
	

	//Sensors determine whether or not we have the ball
	private DigitalInput ballSensorLeft;
	private DigitalInput ballSensorRight;
	
	private PowerDistributionPanel pdp;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public HarvesterRollers() {
    	shooterWheelA = new CANTalon(RobotMap.HARVESTER_SHOOTER_WHEEL_A);//Construct shooter A as CANTalon, this should have encoder into it
		shooterWheelB = new CANTalon(RobotMap.HARVESTER_SHOOTER_WHEEL_B);//Construct shooter B as CANTalon
//		shooterWheelB.changeControlMode(CANTalon.TalonControlMode.Follower);//turn shooter motor B to a slave
//		shooterWheelB.set(shooterWheelA.get());//slave shooter motor B to shooter motor A
		
		harvesterBallControl = new CANTalon(RobotMap.HARVESTER_BALL_CONTROL);

		ballSensorLeft = new DigitalInput(RobotMap.BALL_SENSOR_LEFT);
		ballSensorRight = new DigitalInput(RobotMap.BALL_SENSOR_RIGHT);
		pdp = new PowerDistributionPanel();
    }
    
    /**
     * Drives the roller farthest from the robot
     * @param value speed
     */
    public void setShooter(double value) {
    	shooterWheelA.set(value);
    	shooterWheelB.set(value);
    }
   
    /**
     * all methods begin by disabling the wheels. changes controlling mode to voltage %,
     * absolute voltage (voltage compensation), and encoder speed, respectively. latter also reenables
     */
    public void setPercentVolt() {
    	shooterWheelA.disable();
    	shooterWheelA.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    }
    
    /**
     * Set the shooter to absolute voltage mode. In this mode all set values 
     * should be in volts from 12V to -12V. This will compensate for voltage 
     * variation from the battery over the course of the match. Closed loop 
     * control will be disabled before mode shifting
     */
    public void setAbsVolt() {
    	shooterWheelA.disable();
    	shooterWheelA.changeControlMode(CANTalon.TalonControlMode.Voltage);
    }
    
    /**
     * Set the shooter to closed loop encoder control. Does not enable the closed loop.
     */
    public void setEncSpd() {
    	shooterWheelA.disable();
    	shooterWheelA.changeControlMode(CANTalon.TalonControlMode.Speed);
    }
    
    public void enableClosedLoop(){
    	shooterWheelA.enableControl();
    }
    
    public void disableClosedLoop(){
    	shooterWheelA.disable();
    }
    
    /**
     * Drives the roller closest to the robot
     * at a specific speed.
     * @param speed
     */
    public void setBallControl(double speed) {
    	harvesterBallControl.set(speed);
    }
    
    public double getControlCurrent() {
    	return harvesterBallControl.getOutputCurrent();
    }
    
    /**
     * Had to invert these because sensor outputs true if ball is not there.
     * this is not a javadoc, this should be a comment
     */
    public boolean hasBall() {
    	return !ballSensorLeft.get() && !ballSensorRight.get();
    }
    
    public boolean hasBallLeft() {
    	return !ballSensorLeft.get();
    }
    public boolean hasBallRight() {
    	return !ballSensorRight.get();
    }
    public void harvesterCurrent(){
    	double current = pdp.getCurrent(0);
    	double current2 = pdp.getCurrent(1);
    	System.out.println(current + ": port 14" + current2 + ": port 15");
    	
    	//return current;
    }
    
    public double getShooterSpeed() {
    	return shooterWheelB.getSpeed();
    }
}

