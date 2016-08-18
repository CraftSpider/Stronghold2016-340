package org.usfirst.frc.team340.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class DPad extends Button {
	private Joystick m_joystick;
	private int direction;
	
	/**
	 * Class for dealing with the D-Pad
	 * aka PoV hat
	 * @param joy the joystick
	 */
	public DPad(Joystick joy) {
		this.m_joystick = joy;
	}
	
	/**
	 * Class for dealing with the D-Pad
	 * aka PoV hat
	 * @param joy the joystick
	 * @param direction angle the hat
	 * must be equal to
	 */
	public DPad(Joystick joy, int direction) {
		this.m_joystick = joy;
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
		return m_joystick.getPOV() == direction;		
	}
	
	/**
	 * Gets whether or not the hat's angle
	 * is equal to the given angle
	 * @param direction the angle of the
	 * PoV hat. Use <code>Direction.dPad___</code>
	 * @return true if the PoV hat is
	 * equal to the given angle, false
	 * otherwise
	 */
	public boolean get(int direction) {
		return m_joystick.getPOV() == direction;
	}
	
	public enum Direction {
		dPadRight(0),
		dPadUpRight(45),
		dPadUp(90),
		dPadUpLeft(135),
		dPadLeft(180),
		dPadDownLeft(225),
		dPadDown(270),
		dPadDownRight(315);
		
		public final int direction;
		
		private Direction(int direction) {
			this.direction = direction;
		}
	}
}
