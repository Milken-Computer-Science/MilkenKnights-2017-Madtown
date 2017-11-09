package org.usfirst.frc.team1836.robot.util;

/**
 * Helper class to implement "Cheesy Drive". "Cheesy Drive" simply means that the "turning" stick
 * controls the curvature of the robot's path rather than its rate of heading change. This helps
 * make the robot more controllable at high speeds. Also handles the robot's quick turn
 * functionality - "quick turn" overrides constant-curvature turning for turn-in-place maneuvers.
 */
public class CheesyDriveHelper {

  private static final double kThrottleDeadband = 0;
  private static final double kWheelDeadband = 0;

  // These factor determine how fast the wheel traverses the "non linear" sine curve.
  private static final double kHighWheelNonLinearity = 0.65;
  private static final double kLowWheelNonLinearity = 0.5;

  private static final double kHighNegInertiaScalar = 4.0;

  private static final double kLowNegInertiaThreshold = 0.65;
  private static final double kLowNegInertiaTurnScalar = 3.5;
  private static final double kLowNegInertiaCloseScalar = 4.0;
  private static final double kLowNegInertiaFarScalar = 5.0;

  private static final double kHighSensitivity = 0.95;
  private static final double kLowSensitiity = 1.3;

  private static final double kQuickStopDeadband = 0.2;
  private static final double kQuickStopWeight = 0.1;
  private static final double kQuickStopScalar = 5.0;

  private double mOldWheel = 0.0;
  private double mQuickStopAccumlator = 0.0;
  private double mNegInertiaAccumlator = 0.0;

  public DriveSignal cheesyDrive(double throttle, double wheel, boolean isQuickTurn) {

    double leftMotorSpeed;
    double rightMotorSpeed;

    double moveValue = limit(throttle);
    double rotateValue = limit(wheel);

    if (isQuickTurn) {
      // square the inputs (while preserving the sign) to increase fine control
      // while permitting full power

      moveValue = moveValue / 2;
      rotateValue = rotateValue / 2;

    }

    if (moveValue > 0.0) {
      if (rotateValue > 0.0) {
        leftMotorSpeed = moveValue - rotateValue;
        rightMotorSpeed = Math.max(moveValue, rotateValue);
      } else {
        leftMotorSpeed = Math.max(moveValue, -rotateValue);
        rightMotorSpeed = moveValue + rotateValue;
      }
    } else {
      if (rotateValue > 0.0) {
        leftMotorSpeed = -Math.max(-moveValue, rotateValue);
        rightMotorSpeed = moveValue + rotateValue;
      } else {
        leftMotorSpeed = moveValue - rotateValue;
        rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
      }
    }
    return new DriveSignal(leftMotorSpeed, rightMotorSpeed);
  }

  public double handleDeadband(double val, double deadband) {
    return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
  }

  protected static double limit(double num) {
    if (num > 1.0) {
      return 1.0;
    }
    if (num < -1.0) {
      return -1.0;
    }
    return num;
  }
}
