package org.usfirst.frc.team3756.robot.commands;

import org.usfirst.frc.team3756.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives the robot straight using encoders
 * @author Samer Alabi
 */
public class DriveStraightPID extends Command {
	// Declare subsystems
	private DriveTrain train;
	
	// Declare attributes
	private final int P = 1;
	private final int I = 1;
	private double integral, setpoint, speed, leftSpeed, rightSpeed; 
	private boolean direction;
	
	/**
	 * Creates command with a robot (containing subsystems) and the speed 
	 * the robot will travel at.
	 * @param robot is a Robot object
	 * @param speed is the speed the robot will drive at 
	 * @param reverese is the direction the robot will be traveling in
	 */
	public DriveStraightPID(double distance, boolean reverse) {
		// Get required subsystems
		train = DriveTrain.getInstance();
		
		// Initialize attributes
		this.setpoint = distance;
		this.integral = 0;
		this.direction = reverse;
	} // End of constructor
	
	/**
	 * PID controller for the robot, slows it down as it gets to the setpoint
	 */
	private void PID() {
		// Get error and integral from error
		double error = setpoint - (Math.abs(train.getLeftDistance() + train.getRightDistance()) / 2);
		this.integral += (error * 0.02);
		
		// Get speed for robot to travel at
		this.speed = P*error * I*this.integral;
	} // End of method
	
	/**
	 * Adjusts the speed of the robot to match encoder values
	 */
	private void adjust() {
		// Get encoder difference
		int encDiff = Math.abs(train.getLeftRaw()) - Math.abs(train.getRightRaw());
		
		// Check the difference between the encoders
		if (encDiff > 75) {
			leftSpeed = speed * 0.9;
			rightSpeed = speed;
		}
		else if (encDiff < -75) {
			leftSpeed = speed;
			rightSpeed = speed * 0.9;
		} // End of if statement
	} // End of method
	
	@Override
	protected void initialize() {
		train.resetEncoders();
	} // End of method
	
	@Override
	protected void execute() {
		// Get adjusted turning speed
		PID();
		adjust();
		
		// Drive based on direction
    	if (direction)
    		train.autonomousDrive(-leftSpeed, -rightSpeed);
    	else
    		train.autonomousDrive(leftSpeed, rightSpeed);
	} // End of method
	
	@Override
	protected boolean isFinished() {
		return (Math.abs(setpoint) - Math.abs(train.getLeftDistance())) < 0.2 && (Math.abs(setpoint) - Math.abs(train.getRightDistance()) < 0.2);
	} // End of method
	
	
	@Override
	protected void end() {
		train.stop();
	} // End of method
} // End of class
