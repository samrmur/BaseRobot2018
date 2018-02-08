package org.usfirst.frc.team3756.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class UsbVision extends Subsystem {
	// Declare instance
	private static UsbVision instance;
	
	// Declare and initialize constants
	private final int DEFAULT_FRAMES = 30;
	private final int DEFAULT_RES_WIDTH = 640;
	private final int DEFAULT_RES_HEIGHT = 480;
	
	// Declare attributes
	private UsbCamera camera;
	
	/**
	 * Gets single instance of this class
	 * @return
	 */
	public static UsbVision getInstance() {
		// If instance doesn't exist, create it
		if (instance == null)
			instance = new UsbVision();
		
		// Return instance
		return instance;
	} // End of method
	
	/**
	 * Constructs Vision system with default settings
	 */
	private UsbVision() {
		// Instantiate camera
    	camera = CameraServer.getInstance().startAutomaticCapture();
    	
    	// Set camera settings
    	camera.setFPS(DEFAULT_FRAMES);
    	camera.setResolution(DEFAULT_RES_WIDTH, DEFAULT_RES_HEIGHT);
    } // End of constructor	
	
	/**
	 * Constructs Vision system with custom settings
	 * @param frames is the FPS the camera should display
	 * @param resWidth is the resolution output width of the camera
	 * @param resHeight is the resolution output height of the camera
	 */
	private UsbVision(int frames, int resWidth, int resHeight) {
		// Instantiate camera
    	this.camera = CameraServer.getInstance().startAutomaticCapture();
    	
    	// Set camera settings
    	camera.setFPS(frames);
    	camera.setResolution(resWidth, resHeight);
    } // End of constructor	
	
	/**
	 * Sets the amount of frames the camera outputs
	 * @param frames is an integer
	 */
	public void setFrames(int frames) {
		camera.setFPS(frames);
	} // End of method
	
	/**
	 * Sets the output resolution of the camera
	 * @param width is an integer
	 * @param height is an integer
	 */
	public void setResolution(int width, int height) {
		camera.setResolution(width, height);
	} // End of method
	
	@Override
	public void initDefaultCommand() {
    	
    } // End of method
} // End of constructor
