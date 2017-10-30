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

    @Override public double getPosition() {
        return nativeToUser(getEncPosition());
    }

    public double getVelocity() {
        return nativeToUser(getEncVelocity());
    }

    public void setEncoderPosition(double pos) {
        setEncPosition(userToNative(pos));
    }

    public void set(double val) {
        super.set(userToNative(val));
        if (setPrint)
            System.out.println("Mode: " + getControlMode().toString() + "Value: ");
    }

    private int userToNative(double val) {
        if (rotation) {
            return (int) Math.round((val / 360) * codesPerRev);
        } else {
            return (int) Math.round((val / (wheelDiameter * Math.PI)) * codesPerRev);
        }
    }

    private double nativeToUser(int val) {
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
