package org.usfirst.frc.team1836.robot;

import org.usfirst.frc.team1836.robot.util.MkButton;
import org.usfirst.frc.team1836.robot.util.MkJoystick;

public class Inputs {
  public static final MkJoystick driverJoystick = new MkJoystick(Constants.Input.DRIVE_STICK);
  public static final MkJoystick operatorJoystick = new MkJoystick(Constants.Input.OPERATOR_STICK);
  public static final MkButton reverseButton = driverJoystick.getButton(Constants.Input.REVERSE_BUTTON);
  public static final MkButton gearPickupButton = driverJoystick.getButton(Constants.Input.GEAR_PICKUP_BUTTON);
  public static final MkButton gearStowButton = driverJoystick.getButton(Constants.Input.GEAR_STOW_BUTTON);
  public static final MkButton gearPlaceButton = driverJoystick.getButton(Constants.Input.GEAR_PLACE_BUTTON);
}

