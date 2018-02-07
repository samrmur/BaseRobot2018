package org.usfirst.frc.team3756.robot;

public class Constants {
	// Encoder DPP Values
	public static final double WHEEL_DIAMETER = 0.1524;
	public static final double WIDTH = 0.685;
	public static final double ENCODING_TYPE = 4;
	public static final double PULSES_PER_REVOLUTION = 128;
	public static final double DISTANCE_PER_PULSE = (WHEEL_DIAMETER * Math.PI) / (PULSES_PER_REVOLUTION / ENCODING_TYPE);
	
	// Drive Tuning Values
	public static final double TUNE_STRAIGHT_DRIVE = 0.15;
} // End of class
