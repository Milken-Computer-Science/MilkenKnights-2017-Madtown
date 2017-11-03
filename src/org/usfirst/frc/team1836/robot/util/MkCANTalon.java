package org.usfirst.frc.team1836.robot.util;

import com.ctre.CANTalon;
import org.usfirst.frc.team1836.robot.Constants;

public class MkCANTalon extends CANTalon {

  private final double wheelDiameter;
  private final int codesPerRev = Constants.DRIVE.CODES_PER_REV;
  private final boolean rotation;
  private boolean setPrint = true;



  public MkCANTalon(int deviceNumber, double wheelDiameter) {
    super(deviceNumber);
    configEncoderCodesPerRev(codesPerRev);
    this.wheelDiameter = wheelDiameter;
    this.rotation = false;
  }

  public MkCANTalon(int deviceNumber, boolean rotation) {
    super(deviceNumber);
    configEncoderCodesPerRev(codesPerRev);
    this.rotation = rotation;
    this.wheelDiameter = 0;
  }

 
  public double getMkPosition() {
    return getPosition() * 4096;
  }

  public double getMkSetpoint() {
    return getSetpoint();
  }

  /*
   * @return User Unit Velocity In Seconds
   */
  public double getVelocity() {
    return nativeToUser(getEncVelocity()) * 10;
  }

  public void setEncoderPosition(double pos) {
    setEncPosition((int) userToNative(pos));
  }

  @Override
  public void set(double val) {
    super.set(modeValue(val));
    if (setPrint)
      System.out.println("Mode: " + getControlMode().toString() + " Value: " + modeValue(val));
  }

  public double modeValue(double val) {
    if (getControlMode().equals(TalonControlMode.Speed)) {
      return userToNative(val) / 100;
    } else if (getControlMode().equals(TalonControlMode.MotionMagic)) {
      return val;
    }
    return val;
  }

  private double userToNative(double val) {
    if (rotation) {
      return Math.round((val / 360) * ((double) codesPerRev));
    } else {
      return Math.round((val / (wheelDiameter * Math.PI)) * codesPerRev);
    }
  }

  private double nativeToUser(double val) {
    if (rotation) {
      return (val / ((double) codesPerRev)) * 360.0;
    } else {
      return (val / codesPerRev) * (wheelDiameter * Math.PI);
    }
  }

  public void setPrint(boolean val) {
    this.setPrint = val;
  }

}
