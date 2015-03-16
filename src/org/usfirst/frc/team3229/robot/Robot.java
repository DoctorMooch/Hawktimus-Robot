package org.usfirst.frc.team3229.robot;


import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.ButtonType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

/**
 * This is a demo program showing how to use Mecanum control with the RobotDrive class.
 */
public class Robot extends SampleRobot {
	
    RobotDrive robotDrive;
    Joystick stick;
    SpeedController elevatorMotor;
    
    // Channels for the wheels
    final int frontLeftChannel	= 3;       //originally 3
    final int rearLeftChannel	= 4;       //originally 4
    final int frontRightChannel	= 2;       //originally 2
    final int rearRightChannel	= 1;       //originally 1
    final int elevator = 6;
    private static final int LIFT_BUTTON=2; //or whatever button raises
    private static final int LOWER_BUTTON=1; //or whatever button lowers
    private static final int ROTATE_LEFT=4; 
    private static final int ROTATE_RIGHT=5;
    
    // The channel on the driver station that the joystick is connected to
    final int joystickChannel	= 0;
    int rotationDirection;

    public Robot() {
        robotDrive = new RobotDrive(frontLeftChannel, rearLeftChannel, frontRightChannel, rearRightChannel);
        robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);	// invert the left side motors
    	robotDrive.setInvertedMotor(MotorType.kFrontRight, true);
        robotDrive.setExpiration(0.1);

       stick = new Joystick(joystickChannel);
       elevatorMotor=new Jaguar(elevator);
   
       
       
    }
        

    /**
     * Runs the motors with Mecanum drive.
     */
    public void operatorControl() {
        robotDrive.setSafetyEnabled(true);
        while (isOperatorControl() && isEnabled()) {
        	if(stick.getRawButton(ROTATE_RIGHT)) {
        			rotationDirection = 1;
        		}
        	else{
        		
        	}
        	if(stick.getRawButton(ROTATE_LEFT)) {
        			rotationDirection = -1;
        	}
        	else{
        		rotationDirection=0;
        	}
        	
        	
        	// Use the joystick X axis for lateral movement, Y axis for forward movement, and Z axis for rotation.
        	// This sample does not use field-oriented drive, so the gyro input is set to zero.
            robotDrive.mecanumDrive_Cartesian(-stick.getY(), -stick.getX(), rotationDirection, 1);
            
            if(stick.getRawButton(LIFT_BUTTON)) {
                elevatorMotor.set(0.5);
           } else if(stick.getRawButton(LOWER_BUTTON)) {
                elevatorMotor.set(-0.5);
           } else {
                elevatorMotor.set(0);
           }
            
            Timer.delay(0.010);	// wait 5ms to avoid hogging CPU cycles
        }
    }


	
		// TODO Auto-generated method stub
		
	}
    	
    

