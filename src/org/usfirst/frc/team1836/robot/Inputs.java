package org.usfirst.frc.team1836.robot;

import org.usfirst.frc.team1836.robot.util.MkButton;
import org.usfirst.frc.team1836.robot.util.MkJoystick;

public class Inputs {
    public static final MkJoystick driverJoystick = new MkJoystick(Constants.Input.DRIVE_STICK);
    public static final MkJoystick operatorJoystick =
        new MkJoystick(Constants.Input.OPERATOR_STICK);
    public static final MkButton reverseButton =
        driverJoystick.getButton(Constants.Input.REVERSE_BUTTON);
    public static final MkButton gearPickupButton =
        operatorJoystick.getButton(Constants.Input.GEAR_PICKUP_BUTTON);
    public static final MkButton gearStowButton =
        operatorJoystick.getButton(Constants.Input.GEAR_STOW_BUTTON);
    public static final MkButton gearPlaceButton =
        operatorJoystick.getButton(Constants.Input.GEAR_PLACE_BUTTON);
    public static final MkButton rollerInButton =
        operatorJoystick.getButton(Constants.Input.ROLLER_IN_BUTTON);
    public static final MkButton rollerOutButton =
        operatorJoystick.getButton(Constants.Input.ROLLER_OUT_BUTTON);
    public static final MkButton climberButton =
        operatorJoystick.getButton(Constants.Input.CLIMBER_BUTTON);
    public static final MkButton climberReverseButton =
        operatorJoystick.getButton(Constants.Input.CLIMBER_REVERSE_BUTTON);
}
