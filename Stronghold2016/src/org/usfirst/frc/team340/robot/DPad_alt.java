package org.usfirst.frc.team340.robot;

import edu.wpi.first.wpilibj.Joystick;

public class DPad_alt {
	private Joystick joystick;
	
	public DPad_alt(Joystick joystick) {
		this.joystick = joystick;
	}
	
	public boolean get(int direction) {
		return joystick.getPOV() == direction;
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