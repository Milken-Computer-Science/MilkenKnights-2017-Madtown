package org.usfirst.frc.team1836.robot.util;

import org.usfirst.frc.team1836.robot.Constants;

import com.ctre.CANTalon;

public class MkCANTalon extends CANTalon {

	protected final double wheelDiameter;
	protected final int codesPerRev;
	protected final int conversionFactor;
	protected boolean setPrint;

	public MkCANTalon(int deviceNumber, double wheelDiameter, boolean rotation) {
		super(deviceNumber);
		this.codesPerRev = Constants.PID.codesPerRev;
		configEncoderCodesPerRev(codesPerRev);
		this.wheelDiameter = wheelDiameter;
		setPrint = true;
		conversionFactor = rotation ? 360 : 1;
	}

	public double getPosition() {
		return nativeToUser(getEncPosition());
	}

	public double getVelocity() {
		return nativeToUser(getEncVelocity());
	}

	public void setEncoderPosition(double pos) {
		setEncPosition(userToNative(pos));
	}

	public void set(double val) {
		if (getControlMode() == TalonControlMode.Speed || getControlMode() == TalonControlMode.MotionMagic
				|| getControlMode() == TalonControlMode.Position) {
			set(userToNative(val));
		} else {
			set(val);
		}
		System.out.println("Mode: " + getControlMode().toString() + "Value: ");
	}

	public int userToNative(double val) {
		return ((int) ((val / (wheelDiameter * Math.PI)) * codesPerRev));
	}

	public double nativeToUser(int val) {
		return (val / codesPerRev) * (wheelDiameter * Math.PI);
	}
	
	public void setPrint(boolean val) {
		this.setPrint = val;
	}

}
