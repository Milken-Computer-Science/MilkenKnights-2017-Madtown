package org.usfirst.frc.team1836.robot;

public final class Constants {

	public static class Hardware {
		public static final int LEFT_FWD_TALON_ID = 1;
		public static final int LEFT_BACK_TALON_ID = 2;
		public static final int RIGHT_FWD_TALON_ID = 3;
		public static final int RIGHT_BACK_TALON_ID = 4;
		public static final int GEAR_PICKUP_TALON_ID = 5;
	}

	public static class Input {
		public static final int DRIVE_STICK = 0;
		public static final int OPERATOR_STICK = 1;
		public static final int REVERSE_BUTTON = 1;
		public static final int GEAR_PICKUP_BUTTON = 2;
		public static final int GEAR_STOW_BUTTON = 3;
		public static final int GEAR_PLACE_BUTTON = 4;

	}

	public static class PID {
		public static final double GearA = 0.75 * 4400;
		public static final double GearV = 0.75 * 4400;
		public static final int GearIZone = 400;
		public static final double GearP = (((0.1 * 1023) / 8000) * 16);
		public static final double GearI = 0.001;
		public static final double GearD = ((0.1 * 1023) / 8000) * 160;
		public static final double GearF = (1.0 * 1023) / (4400 / 60 / 10 * 4096);

		public static final double DriveA = 0;
		public static final double DriveV = 0;
		public static final int DriveIZone = 0;
		public static final double DriveP = 0;
		public static final double DriveI = 0;
		public static final double DriveD = 0;
		public static final double DriveF = 0;
		public static final double DriveFollowerP = 0;
		
		public static final int codesPerRev = 1023;
	}

	public static class GearMech {
		// From 0 to 10
		public static final double GearPickup = 10;
		public static final double GearStow = 5;
		public static final double GearPlace = 0;
	}

	public static class Drive {
		public static final double DriveMagicMotionTol = 1;
		public static final double WheelDiameter = 6;
	}

}
//Cloud ;)
