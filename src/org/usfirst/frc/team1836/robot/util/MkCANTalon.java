package org.usfirst.frc.team1836.robot.util;

import org.usfirst.frc.team1836.robot.Constants;

import com.ctre.CANTalon;

public class MkCANTalon extends CANTalon {

	protected final double wheelDiameter;
	protected final int ticksPerRev;
	protected final int conversionFactor;

	public MkCANTalon(int deviceNumber, double wheelDiameter) {
		super(deviceNumber);
		this.ticksPerRev = Constants.PID.codesPerRev;
		configEncoderCodesPerRev(ticksPerRev);
		this.wheelDiameter = wheelDiameter;
		this.conversionFactor = 1;
	}

	public MkCANTalon(int deviceNumber, double wheelDiameter, int conversionFactor) {
		super(deviceNumber);
		this.ticksPerRev = Constants.PID.codesPerRev;
		configEncoderCodesPerRev(ticksPerRev);
		this.wheelDiameter = wheelDiameter;
		this.conversionFactor = conversionFactor;
	}

	public double getPosition() {
		return (getEncPosition() / ticksPerRev) * (wheelDiameter * Math.PI);
	}

	public double getVelocity() {
		return (getEncVelocity() / ticksPerRev) * (wheelDiameter * Math.PI);
	}

	public void setEncoderPosition(double pos) {
		setEncPosition((int) ((pos / (wheelDiameter * Math.PI)) * ticksPerRev));
	}

	public void set(double val) {
		if (getControlMode() == TalonControlMode.Speed || getControlMode() == TalonControlMode.MotionMagic
				|| getControlMode() == TalonControlMode.Position) {
			set(((val / (wheelDiameter * Math.PI)) * ticksPerRev));
		} else {
			set(val);
		}

	}

}
