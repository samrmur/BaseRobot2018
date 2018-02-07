/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3756.robot;

import edu.wpi.first.wpilibj.XboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * @author Samer Alabi
 */
public class OI {
	// Declare instance
	private static OI instance;
	
	// Declare attributes
	private XboxController controller;
	
	/**
	 * Gets the instance of this class
	 * @return OI
	 */
	public static OI getInstance() {
		// If instance is null, create one
		if (instance == null)
			instance = new OI(RobotMap.XBOX_CONTROLLER_PORT);
		
		// Return instance
		return instance;
	} // End of method
	
	/**
	 * Creates an OI object with the port for the Xbox Controller
	 * @param xboxPort is an integer
	 */
	private OI(int xboxPort) {
		// Instantiate Xbox controller
		controller = new XboxController(xboxPort);
	} // End of class
	
	public XboxController getController() {
		return controller;
	} // End of class
} // End of class
