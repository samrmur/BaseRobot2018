/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3756.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// Speed Controllers Ports
	public static final int SPD_CNTRL_LEFT_FRONT = 0;
	public static final int SPD_CNTRL_LEFT_BACK = 1;
	public static final int SPD_CNTRL_RIGHT_FRONT = 3;
	public static final int SPD_CNTRL_RIGHT_BACK = 4;
	
	// Encoder Ports
	public static final int ENCODER_LEFT_CHNL_A = 0;
	public static final int ENCODER_LEFT_CHNL_B = 1;
	public static final int ENCODER_RIGHT_CHNL_A = 3;
	public static final int ENCODER_RIGHT_CHNL_B = 4;
	public static final boolean LEFT_ENCODER_REVERSE_DIR = false;
	public static final boolean RIGHT_ENCODER_REVERSE_DIR = true;
	
	// Xbox Controller Ports
	public static final int XBOX_CONTROLLER_PORT = 0;
	public static final int LEFT_TRIGGER = 2;
	public static final int RIGHT_TRIGGER = 3;
	public static final int LEFT_STICK = 12;
	public static final int RIGHT_STICK = 13;
	public static final int A_BUTTON = 2;
} // End of class
