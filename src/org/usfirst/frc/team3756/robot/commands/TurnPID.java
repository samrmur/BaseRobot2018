package org.usfirst.frc.team3756.robot.commands;

import org.usfirst.frc.team3756.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Turns the robot a given amount of degrees
 * @author Samer Alabi
 */
public class TurnPID extends Command {
	// Declare required subsystems
	private DriveTrain train;
	
	// Declare attributes
	private final int P = 1;
	private final int I = 1;
	private double integral, setpoint, speed, leftSpeed, rightSpeed; 
	
	/**
	 * Creates object with the amount of degrees the robot will be turning
	 */
    public TurnPID(int angle) {
    	// Get required subsystems
    	train = DriveTrain.getInstance();
        
        // Initialize attributes
        this.setpoint = angle;
    } // End of constructor
    
    /**
	 * PID controller for the robot, slows it down as it gets to the set-point
	 */
	private void PID() {
		// Get error and integral from error
		double error = Math.abs(setpoint) - train.getCurrentAngle();
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
		if (setpoint >= 0)
			train.autonomousDrive(leftSpeed, -rightSpeed);
		else
			train.autonomousDrive(-leftSpeed, rightSpeed);
    } // End of method

    @Override
    protected boolean isFinished() {
    	return (Math.abs(setpoint) - Math.abs(train.getLeftDistance())) < 0.2 && (Math.abs(setpoint) - Math.abs(train.getRightDistance()) < 0.2);
    } // End of method

    @Override
    protected void end() {
    	train.stop();
    } // End of method

    @Override
    protected void interrupted() {
    } // End of method
} // End of class