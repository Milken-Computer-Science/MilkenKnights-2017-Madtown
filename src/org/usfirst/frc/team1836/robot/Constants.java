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
        public static final int GEAR_PICKUP_TALON_ID = 4;

        public static final boolean GEAR_ARM_TALON_SENSOR_REVERSE = false;
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
        public static final int GEAR_PICKUP_BUTTON = 2;
        public static final int GEAR_STOW_BUTTON = 3;
        public static final int GEAR_PLACE_BUTTON = 4;
    }


    public static class DRIVE {

        public static final double DRIVE_A = 0;
        public static final double DRIVE_V = 0;
        public static final int DRIVE_I_ZONE = 0;
        public static final double DRIVE_P = 0;
        public static final double DRIVE_I = 0;
        public static final double DRIVE_D = 0;
        public static final double DRIVE_F = 0;

        public static final double DRIVE_FOLLOWER_P = 0;
        public static final double DRIVE_FOLLOWER_D = 0;
        public static final double DRIVE_FOLLOWER_ANG = 0;

        public static final double TRAJ_TOL = 0;

        public static final int CODES_PER_REV = 4096;

        public static final double DELTA_T = 0;
        public static final double M_ACCEL = 0;
        public static final double M_JERK = 0;
        public static final double M_VEL = 0;

        public static final double DRIVE_MAGIC_MOTION_TOL = 1;
        public static final double WHEEL_DIAMETER = 4;
    }


    public static class GearMech {
        //Degrees - Zeroed on Stow
        public static final double GEAR_PICKUP = 120;
        public static final double GEAR_PLACE = 60;
        public static final double GEAR_STOW = 0;

        public static final double MAX_ROTATIONS = 1.5; //Per sec
        public static final double GEAR_A = 0.75 * MAX_ROTATIONS;
        public static final double GEAR_V = 0.75 * MAX_ROTATIONS;
        public static final int GEAR_I_ZONE = 400;
        public static final double GEAR_P = (((0.1 * 1023) / 8000) * 16);
        public static final double GEAR_I = 0.001;
        public static final double GEAR_D = ((0.1 * 1023) / 8000) * 160;
        public static final double GEAR_F = 1.2487792969;
    }


    public static class Auto {
        public static final double CENTER_AUTO_DISTANCE = 90;
    }

}
