package org.usfirst.frc.team3756.robot.subsystems;

import org.usfirst.frc.team3756.robot.Constants;
import org.usfirst.frc.team3756.robot.RobotMap;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * Implementation of a 4-Victor and 2-Encoder drive train
 * @author Samer Alabi
 */
public class DriveTrain extends Subsystem {	
	// Declare instance
	private static DriveTrain instance;
	
	// Declare attributes
	private DifferentialDrive driveTrain;
	private SpeedControllerGroup leftGroup, rightGroup;
	private Encoder leftEncoder, rightEncoder;
	
	/**
	 * Creates single instance of a class
	 * @return
	 */
	public static DriveTrain getInstance() {
		// If instance hasn't been defined, create it
		if (instance == null)
			instance = new DriveTrain(RobotMap.SPD_CNTRL_LEFT_FRONT, RobotMap.SPD_CNTRL_LEFT_BACK,
					RobotMap.SPD_CNTRL_RIGHT_FRONT, RobotMap.SPD_CNTRL_RIGHT_BACK, RobotMap.ENCODER_LEFT_CHNL_A,
					RobotMap.ENCODER_LEFT_CHNL_B, RobotMap.ENCODER_RIGHT_CHNL_A, RobotMap.ENCODER_RIGHT_CHNL_B,
					RobotMap.LEFT_ENCODER_REVERSE_DIR, RobotMap.RIGHT_ENCODER_REVERSE_DIR, EncodingType.k4X, 
					EncodingType.k4X);
		
		// Return instance
		return instance;
	} // End of method
	
	/**
	 * Creates the robot drive train using four victor controllers and two encoders
	 * @param cimLeftFront is the port for the left-front Victor
	 * @param cimLeftBack is the port for the left-back Victor
	 * @param cimRightFront is the port for the right-front Victor
	 * @param cimRightBack is the port for the right-back Victor
	 */
	private DriveTrain(int cimLeftFront, int cimLeftBack, int cimLightFront, int cimRightBack, 
			int encoderLeftChnlOne, int encoderLeftChnlTwo, int encoderRightChnlOne, 
			int encoderRightChnlTwo, boolean reverseEncoderOne, boolean reverseEncoderTwo,
			EncodingType encoderLeftType, EncodingType encoderRightType) {
		// Initialize speed controller groups
		this.leftGroup = new SpeedControllerGroup(new Victor(cimLeftFront), new Victor(cimLeftBack));
		this.rightGroup = new SpeedControllerGroup(new Victor(cimLightFront), new Victor(cimRightBack));
		
		// Initialize encoders
		leftEncoder = new Encoder(encoderLeftChnlOne, encoderLeftChnlTwo, reverseEncoderOne, encoderLeftType);
		rightEncoder = new Encoder(encoderRightChnlOne, encoderRightChnlTwo, reverseEncoderTwo, encoderRightType);
		
		// Initialize the drive of the robot
		this.driveTrain = new DifferentialDrive(this.leftGroup, this.rightGroup);
	} // End of method
	
	/**
	 * Drives the drive train using an Xbox Controller
	 * @param controller is an XboxController object
	 * @param forwardTrigger is the port number for the forwards trigger
	 * @param backwardTrigger is the port number for the backwards trigger
	 * @param rotateAxis is the port number for the rotate stick
	 * @param straight is a boolean that determines whether the robot needs to be tuned
	 */
	public void controllerDrive(XboxController controller, int forwardTrigger, int backwardTrigger, int rotateAxis, double tuneValue) {
		// Get value of turn axis
		double rotateValue = controller.getRawAxis(rotateAxis);
		
		if (rotateValue == 0)
			this.driveTrain.arcadeDrive(controller.getRawAxis(forwardTrigger) - controller.getRawAxis(backwardTrigger), rotateValue, true);
		else 
			this.driveTrain.arcadeDrive(controller.getRawAxis(forwardTrigger) - controller.getRawAxis(backwardTrigger), tuneValue + rotateValue, true);
	} // End of method
	
	/**
	 * Drives the robot with the same of different speeds for the left and right sides
	 * of the robot
	 * @param leftSpeed is the speed of the left side of the robot
	 * @param rightSpeed is the speed of the right side of the robot
	 */
	public void autonomousDrive(double leftSpeed, double rightSpeed) {
		this.driveTrain.tankDrive(leftSpeed, rightSpeed);
	} // End of method
	
	 /**
     * Gets the current angle the robot is traveling at
     * @return double
     */
    public double getCurrentAngle() {
    	// Get current encoder value
    	int currentVal = (Math.abs(getLeftRaw()) + Math.abs(getRightRaw())) / 2;
    	
    	// Return angle
    	return (currentVal / Constants.PULSES_PER_REVOLUTION) * (Constants.WHEEL_DIAMETER / Constants.WIDTH) * 360;
    } // End of method
	
	/**
	 * Stops the robot
	 */
	public void stop() {
		this.driveTrain.stopMotor();
	} // End of method
	
	/**
	 * Inverts the direction the left controllers move in
	 */
	public void invertLeftController() {
		this.leftGroup.setInverted(true);
	} // End of method
	
	/**
	 * Inverts the direction the right controllers move in
	 */
	public void invertRightController() {
		this.rightGroup.setInverted(true);
	} // End of method
	
	/**
	 * Checks if the left controller is inverted
	 * @return boolean
	 */
	public boolean isLeftControllerInverted() {
		return this.leftGroup.getInverted();
	} // End of method
	
	/**
	 * Checks if the right controller is inverted
	 * @return boolean
	 */
	public boolean isRightControllerInverted() {
		return this.rightGroup.getInverted();
	} // End of method
	
	/**
	 * Gets the speed controllers for the left side of the robot
	 * @return SpeedController object
	 */
	public SpeedController getLeftController() {
		return this.leftGroup;
	} // End of method
	
	/**
	 * Gets the speed controllers for the right side of the robot
	 * @return SpeedController object
	 */
	public SpeedController getRightController() {
		return this.rightGroup;
	} // End of method
	
	/**
	 * Reset the encoder values
	 */
	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	} // End of method
	
	/**
	 * Sets distance per pulse for both encoders
	 * @param distancePerPulse is a double
	 */
	public void setDistancePerPulse(double distancePerPulse) {
		leftEncoder.setDistancePerPulse(distancePerPulse);
		rightEncoder.setDistancePerPulse(distancePerPulse);
	} // End of method
	
	/**
	 * Gets the distance of the left encoder
	 * @return double
	 */
	public double getLeftDistance() {
		return leftEncoder.getDistance();
	} // End of method
	
	/**
	 * Gets the distance of the left encoder
	 * @return double
	 */
	public double getRightDistance() {
		return rightEncoder.getDistance();
	} // End of method
	
	/**
	 * Gets raw output of left encoder
	 * @return int 
	 */
	public int getLeftRaw() {
		return leftEncoder.getRaw();
	} // End of method
	
	/**
	 * Gets raw output of right encoder
	 * @return int
	 */
	public int getRightRaw() {
		return rightEncoder.getRaw();
	} // End of method
	
	@Override
	protected void initDefaultCommand() {
	} // End of method
} // End of class
