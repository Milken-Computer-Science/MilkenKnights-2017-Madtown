package org.usfirst.frc.team1836.robot.util;

import org.usfirst.frc.team1836.robot.Constants;

import com.ctre.CANTalon;

public class MkCANTalon extends CANTalon {

  protected final double wheelDiameter;
  protected final int codesPerRev;
  protected final boolean rotation;
  protected boolean setPrint;

  public MkCANTalon(int deviceNumber, double wheelDiameter, boolean rotation) {
    super(deviceNumber);
    this.codesPerRev = Constants.PID.codesPerRev;
    configEncoderCodesPerRev(this.codesPerRev);
    this.wheelDiameter = wheelDiameter;
    setPrint = true;
    this.rotation = rotation;
  }

  @Override
  public double getPosition() {
    return nativeToUser(getEncPosition());
  }

  public double getVelocity() {
    return nativeToUser(getEncVelocity());
  }

  public void setEncoderPosition(double pos) {
    setEncPosition(userToNative(pos));
  }

  public void set(double val, boolean test) {
    set(userToNative(val));
    if (setPrint) {
      System.out.println("Mode: " + getControlMode().toString() + "Value: ");
    }
  }

  public int userToNative(double val) {
    if (rotation) {
      return (int) Math.round((val / 360) * codesPerRev);
    } else {
      return (int) Math.round((val / (wheelDiameter * Math.PI)) * codesPerRev);
    }
  }

  public double nativeToUser(int val) {
    if (rotation) {
      return (val / codesPerRev) * 360;
    } else {
      return (val / codesPerRev) * (wheelDiameter * Math.PI);
    }
  }

  public void setPrint(boolean val) {
    this.setPrint = val;
  }

}
