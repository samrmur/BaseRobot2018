/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3756.robot;

import org.usfirst.frc.team3756.robot.commands.DriveWithController;
import org.usfirst.frc.team3756.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3756.robot.subsystems.ScissorLift;
import org.usfirst.frc.team3756.robot.subsystems.UsbVision;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	// Declare dash-board elements
	private Command defaultAutonomousCommand;
	private SendableChooser<Command> chooser = new SendableChooser<>();
	
	// Declare subsystems
	private DriveWithController drive;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		// Create instances at beginning of program
		DriveTrain.getInstance();
		ScissorLift.getInstance();
		UsbVision.getInstance();		
	} // End of method

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
	} // End of method

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	} // End of method

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		// Get selected autonomous command
		defaultAutonomousCommand = chooser.getSelected();

		// Start autonomous command if one has been selected in SmartDashboard
		if (defaultAutonomousCommand != null) {
			defaultAutonomousCommand.start();
		} // End of if statement
	} // End of method

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	} // End of method

	@Override
	public void teleopInit() {
		// Cancel autonomous command if running
		if (defaultAutonomousCommand != null) {
			defaultAutonomousCommand.cancel();
		} // End of method
		
		// Run teleop control command
		drive = new DriveWithController();
		drive.start();
	} // End of method

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		// Set the tune value
		drive.setTune(SmartDashboard.getNumber("Tune Value", 0));
	} // End of method

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	} // End of method
} // End of class
