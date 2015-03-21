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
    SpeedController elevatorMotor;
    SpeedController FrontRight;
    SpeedController BackRight;
    SpeedController FrontLeft;
    SpeedController BackLeft;
    // Channels for the wheels
    final int frontLeftChannel	= 3;       //originally 3
    final int rearLeftChannel	= 4;       //originally 4
    final int frontRightChannel	= 2;       //originally 2
    final int rearRightChannel	= 1;       //originally 1
    final int elevator = 6;
    private static final int LIFT_BUTTON=2; //or whatever button raises
    private static final int LOWER_BUTTON=1; //or whatever button lowers

    
    // The channel on the driver station that the joystick is connected to
    final int joystickChannel	= 0;
    double rotationDirection;

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
    
    public void operatorControl() {
        robotDrive.setSafetyEnabled(true);
        while (isOperatorControl() && isEnabled()) {
      
   
        	// Use the joystick X axis for lateral movement, Y axis for forward movement, and Z axis for rotation.
        	// This sample does not use field-oriented drive, so the gyro input is set to zero.
        	
           
           
          //******USED FOR DEBUG (CHECK RIOLOG*******
            System.out.println(-stick.getRawAxis(2));
            System.out.println(stick.getRawAxis(3));
          //*****************************************
            
        double RotateStuff = 0;
        		
         //
         if (-stick.getRawAxis(2) + stick.getRawAxis(3)>0 & RotateStuff == 0){
          	  robotDrive.setInvertedMotor(MotorType.kFrontLeft, false);
          	  robotDrive.setInvertedMotor(MotorType.kFrontRight, false);																// invert the left side motors
            RotateStuff= 1;
          }
          
        if (-stick.getRawAxis(2) + stick.getRawAxis(3)<0 & RotateStuff==0){
        	  	// invert the left side motors
        	  robotDrive.setInvertedMotor(MotorType.kFrontRight, false);
        	  robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);
        	  robotDrive.setInvertedMotor(MotorType.kRearLeft, true);
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

          robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);	// invert the left side motors
      	  robotDrive.setInvertedMotor(MotorType.kFrontRight, true);
      	  robotDrive.setInvertedMotor(MotorType.kRearLeft, false);	// invert the left side motors
    	  robotDrive.setInvertedMotor(MotorType.kRearRight, false);
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