package org.usfirst.frc.team1836.robot;


import org.usfirst.frc.team1836.robot.subsystems.Drive;

public class Sensors {
  public static final Drive DRIVE;
    /**
     * Initialize the robot hardware.
     */
    static {
      
      System.out.println("Starting HardwareAdapter init");

      DRIVE = new Drive();

    }

  
}
