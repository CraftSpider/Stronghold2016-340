package org.usfirst.frc.team340.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * 
 * @author Tech
 *
 */
public class DPad extends Button {
	private Joystick joy;
	private int direction;
	
	/**
	 * Class for dealing with the D-Pad
	 * aka PoV hat
	 * @param joy the joystick
	 * @param direction angle the hat
	 * must be equal to
	 */
	public DPad(Joystick joy, int direction) {
		this.joy = joy;
		this.direction = direction;
	}
	
	/**
	 * Gets whether or not the hat's angle
	 * is equal to the given angle
	 * @return true if the PoV hat is
	 * equal to the given angle, false
	 * otherwise
	 */
	public boolean get() {
		return joy.getPOV() == direction;		
	}
	
}
