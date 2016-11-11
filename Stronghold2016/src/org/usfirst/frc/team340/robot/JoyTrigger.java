package org.usfirst.frc.team340.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class JoyTrigger extends Button {
	private Joystick joystick;
	private int axis;
	private double percent;
	
	/**
	 * Class for dealing with the six axes
	 * @param joy the joystick
	 * @param axis the number of the axis
	 */
	public JoyTrigger(Joystick joy, int axis) {
		joystick = joy;	
		this.axis = axis;
		this.percent = 0.5;
	}
	
	/**
	 * Class for dealing with the six axes
	 * @param joy the joystick
	 * @param axis the number of the axis
	 * @param percent the percentage the axis must
	 * return to return true
	 */
	public JoyTrigger(Joystick joy, int axis, double percent) {
		joystick = joy;	
		this.axis = axis;
		this.percent = percent;
	}
	
	/**
	 * Tells whether or not the axis in question has
	 * been pressed greater than or equal to the
	 * defined percent
	 * @return true if the axis is pressed enough,
	 * false otherwise
	 */
	public boolean get() {
		return joystick.getRawAxis(axis) >= percent;
	}
}
