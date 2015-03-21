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


public class Robot extends SampleRobot {
	
    RobotDrive robotDrive;
    
    Joystick stick;
    
    //********SPEED CONTROLLERS*****
    SpeedController elevatorMotor;
    SpeedController FrontRight;
    SpeedController BackRight;
    SpeedController FrontLeft;
    SpeedController BackLeft;
    
    // Channels for the wheels
    final int frontLeftChannel	= 3;       
    final int rearLeftChannel	= 4;       
    final int frontRightChannel	= 2;       
    final int rearRightChannel	= 1;       
    final int elevator = 6;
    
    //******BUTTONS FOR LIFTERS********
    private static final int LIFT_BUTTON=2; //Button to raise elevator
    private static final int LOWER_BUTTON=1; //Button to lower elevator

    
    // The channel on the driver station that the joystick is connected to
    final int joystickChannel	= 0;

    public Robot() {
        robotDrive = new RobotDrive(frontLeftChannel, rearLeftChannel, frontRightChannel, rearRightChannel);
        robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);	
    	robotDrive.setInvertedMotor(MotorType.kFrontRight, true);
        robotDrive.setExpiration(0.1);

       stick = new Joystick(joystickChannel);
       elevatorMotor=new Jaguar(elevator);
    }
    
    //***************START OF AUTONOMOUS****************
    public void autonomous(){
    	
    	for(int i = 0; i < 400; i++){
    		if(isAutonomous() && isEnabled() ){
    		
    			
    			robotDrive.setInvertedMotor(MotorType.kFrontRight, false);
    			robotDrive.setInvertedMotor(MotorType.kRearLeft, true);
    			
    			robotDrive.mecanumDrive_Cartesian(0, 0.5, 0, 1);
    			Timer.delay(.005);
    			robotDrive.mecanumDrive_Cartesian(0, 0, 0, 1);} 
    			robotDrive.setInvertedMotor(MotorType.kRearLeft, false);
    			robotDrive.setInvertedMotor(MotorType.kFrontRight,  true);
    	}
    	
    }
    //***************************************************
    
    public void operatorControl() {
        robotDrive.setSafetyEnabled(true);
        while (isOperatorControl() && isEnabled()) {
      
   
        	// Use the joystick X axis for lateral movement, Y axis for forward movement, and Z axis for rotation.
        	// This sample does not use field-oriented drive, so the gyro input is set to zero.
        	
           
           
          //******USED FOR DEBUG (CHECK RIOLOG*******
            System.out.println(-stick.getRawAxis(2));
            System.out.println(stick.getRawAxis(3));
          //*****************************************
            
        double RotateStuff = 0;  //we know that the code is run continuously on the robot, does that mean
        //that this line ^ get run every iteration?  If so, it will always be reset to 0 even after the
        //if statements below change the value. 
        		
         //****************START OF ROTATION CODE***********************
        
        //*****************RIGHT TRIGGER*********************
         if (-stick.getRawAxis(2) + stick.getRawAxis(3)>0 && RotateStuff == 0){
          robotDrive.setInvertedMotor(MotorType.kFrontRight, true);  
       	  robotDrive.setInvertedMotor(MotorType.kFrontLeft, false);
       	  robotDrive.setInvertedMotor(MotorType.kRearLeft, false);	
       	robotDrive.setInvertedMotor(MotorType.kRearRight, false); //***IF RIGHT TWIST NOT WORKING TRY, CHANGING TO TRUE***
            RotateStuff= 1;
          }
          
         //*******************LEFT TRIGGER*********************
        if (-stick.getRawAxis(2) + stick.getRawAxis(3)<0 && RotateStuff==0){
        	  	// invert the left side motors
        	  robotDrive.setInvertedMotor(MotorType.kFrontRight, false);  
        	  robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);
        	  robotDrive.setInvertedMotor(MotorType.kRearLeft, true);
        	  robotDrive.setInvertedMotor(MotorType.kRearRight, true); //***IF LEFT TWIST NOT WORKING, TRY CHANGING TO FALSE***
        	  RotateStuff = 2;
          }
        /* Basic Movement Code */robotDrive.mecanumDrive_Cartesian(-0.8 * stick.getY(), -0.8 * stick.getX(), -0.8 * stick.getRawAxis(2) + 0.8 * stick.getRawAxis(3), 1);
        
        /*  if (RotateStuff == 1){
          	RotateStuff = 0;
          	robotDrive.setInvertedMotor(MotorType.kFrontLeft,  false);
          	robotDrive.setInvertedMotor(MotorType.kFrontRight, true);	
          }
          else if (RotateStuff == 2){
        	 robotDrive.setInvertedMotor(MotorType.kFrontRight, true);
        	 robotDrive.setInvertedMotor(MotorType.kFrontLeft, false);
        	 
        	  RotateStuff = 0;
          }*/
        
        /*^^^^^^^^FOR LINES 112-122^^^^^^^^^^^ 
         I'm not sure what that code is for, however, if the normal drive is now 
         malfunctioning (Strafe, drive forward/backwards, i think we need to insert a default
         "else" statement that simply resets the motors to be normal when the triggers are added
         together give a value of 0 (AKA Neither/both are pressed).  This will ensure that when the
         triggers are not being used, the motors are given the default attributes as if we never 
         twisted in the first place.*/

    	  
         //***************START OF ELEVATOR CODE********************** 
            if(stick.getRawButton(LIFT_BUTTON)) {
                elevatorMotor.set(0.5);
           } else if(stick.getRawButton(LOWER_BUTTON)) {
                elevatorMotor.set(-0.5);
           } else {
                elevatorMotor.set(0);
           }
          //**********************************************************   
            
            Timer.delay(0.010);	// wait 5ms to avoid hogging CPU cycles
        }
    }


	
		// TODO Auto-generated method stub
		
	}