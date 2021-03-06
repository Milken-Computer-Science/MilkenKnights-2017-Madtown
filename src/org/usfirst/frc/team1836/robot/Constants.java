package org.usfirst.frc.team1836.robot;

/**
 * Stores all Hard-coded numbers subject to change
 */
public final class Constants {

  public static class Hardware {
    public static final int LEFT_FWD_TALON_ID = 1;
    public static final int LEFT_BACK_TALON_ID = 2;
    public static final int RIGHT_FWD_TALON_ID = 8;
    public static final int RIGHT_BACK_TALON_ID = 7;
    public static final int GEAR_PICKUP_TALON_ID = 5;
    public static final int ROLLER_TALON_ID = 3;
    public static final int CLIMBER_TALON_ID = 6;

    public static final boolean GEAR_ARM_TALON_SENSOR_REVERSE = true;
    public static final boolean GEAR_ARM_TALON_REVERSE = false;

    public static final boolean LEFT_FWD_TALON_SENSOR_REVERSE = true;
    public static final boolean RIGHT_FWD_TALON_SENSOR_REVERSE = false;

    public static final boolean LEFT_FWD_TALON_REVERSE = false;
    public static final boolean LEFT_BACK_TALON_REVERSE = false;
    public static final boolean RIGHT_FWD_TALON_REVERSE = false;
    public static final boolean RIGHT_BACK_TALON_REVERSE = false;
  }


  public static class Input {
    public static final int DRIVE_STICK = 0;
    public static final int OPERATOR_STICK = 1;
    public static final int REVERSE_BUTTON = 1;
    public static final int CHEESE_BUTTON = 2;
    public static final int STRAIGHT_BUTTON = 3;

    public static final int GEAR_PICKUP_BUTTON = 2;
    public static final int GEAR_STOW_BUTTON = 3;
    public static final int GEAR_PLACE_BUTTON = 4;
    public static final int ROLLER_IN_BUTTON = 10;
    public static final int ROLLER_OUT_BUTTON = 5;
    public static final int CLIMBER_FWD_BUTTON = 11;
    public static final int GEAR_MANUAL_BUTTON = 1;
    public static final int GEAR_RESET_BUTTON = 6;
  }


  public static class DRIVE {
    public static final double WHEEL_DIAMETER = 4;
    public static final double MAX_VEL = ((30) / (WHEEL_DIAMETER * Math.PI)) * 60; // RPM
    public static final double DRIVE_A = MAX_VEL * 0.75;
    public static final double DRIVE_V = MAX_VEL * 0.75;
    public static final int DRIVE_I_ZONE = 0;
    public static final double DRIVE_P = 0;
    public static final double DRIVE_I = 0;
    public static final double DRIVE_D = 0;
    public static final double DRIVE_F = 1.0461748973;
    public static final double DRIVE_MAGIC_MOTION_TOL = 1;

    public static final double DRIVE_FOLLOWER_P = 0;
    public static final double DRIVE_FOLLOWER_D = 0;
    public static final double DRIVE_FOLLOWER_ANG = 0;
    public static final double TRAJ_TOL = 0;

    public static final int CODES_PER_REV = 4096;

    public static final double DELTA_T = 0;
    public static final double M_ACCEL = 0;
    public static final double M_JERK = 0;
    public static final double M_VEL = 0;

  }


  public static class GearMech {
    // Degrees - Zeroed on Stow
    public static final double GEAR_PICKUP = -178;
    public static final double GEAR_PLACE = -110;
    public static final double GEAR_STOW = 0;

    public static final double MAX_VEL = 120; // RPM
    public static final double GEAR_A = 0.9 * MAX_VEL;
    public static final double GEAR_V = 0.9 * MAX_VEL;
    public static final int GEAR_I_ZONE = 50;
    public static final double GEAR_P = 0.06709 * 80;
    public static final double GEAR_I = 0.004;
    public static final double GEAR_D = 0.06709 * 80 * 70;
    public static final double GEAR_F = (1023) / (0.2 * 4096);
    public static final double ROLLER_SPEED = -0.375;
    public static final double ROLLER_DEFAULT_SPEED = -0.375 / 10;
    public static final double STOW_CURRENT_LIMIT = 8.0;
    public static final double RESET_BACK_POWER = 0.25;
    public static final double PLACE_GEAR_ROLLER_SPEED = -0.1875;
  }


  public static class Auto {
    public static final double CENTER_AUTO_DISTANCE = 90;
    public static final double CENTER_AUTO_SPEED = 0.6;
    public static final double CENTER_AUTO_TIME = 3.75;
    public static final double BASELINE_TIME = 4;
  }


  public static class Climb {
    public static final double CLIMBER_SPEED = 0.95;
  }

}
