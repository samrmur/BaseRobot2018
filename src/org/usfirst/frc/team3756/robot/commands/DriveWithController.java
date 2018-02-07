package org.usfirst.frc.team3756.robot.commands;

import org.usfirst.frc.team3756.robot.Constants;
import org.usfirst.frc.team3756.robot.OI;
import org.usfirst.frc.team3756.robot.RobotMap;
import org.usfirst.frc.team3756.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives the robot with an Xbox Controller using its triggers for forward and reverse
 * @author Samer Alabi
 */
public class DriveWithController extends Command {
	// Declare subsystems
	private DriveTrain train;
	private OI oi;
	
	/**
	 * Creates Command object
	 */
	public DriveWithController() {
		// Get required subsystems
		train = DriveTrain.getInstance();
		oi = OI.getInstance();
	} // End of constructor
	
	@Override
	protected void execute() {
		train.controllerDrive(oi.getController(), RobotMap.LEFT_TRIGGER, RobotMap.RIGHT_TRIGGER, RobotMap.RIGHT_STICK, Constants.TUNE_STRAIGHT_DRIVE);
	} // End of method
	
	@Override
	protected boolean isFinished() {
		return true;
	} // End of method
} // End of class
