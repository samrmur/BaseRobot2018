package org.usfirst.frc.team3756.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Scissor Lift system for lifting blocks and robots at the end of the game
 * @author Samer Alabi
 */
public class ScissorLift extends Subsystem {
    // Declare instance
	private static ScissorLift instance;
	
	/**
	 * Gets single instance of this class
	 * @return
	 */
	public static ScissorLift getInstance() {
		// If instance doesn't exist, create it
		if (instance == null)
			instance = new ScissorLift();
		
		// Return instance
		return instance;
	} // End of method
	
	/**
	 * Constructs Scissor Lift
	 */
	private ScissorLift() {
    	
    } // End of constructor	
	
	@Override
	public void initDefaultCommand() {
    	
    } // End of method
} // End of class