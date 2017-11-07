package com.ctre.phoenix.Drive;

import com.ctre.phoenix.Mechanical.*;
import com.ctre.phoenix.MotorControl.*;
import com.ctre.phoenix.Drive.Styles;

public class Tank implements IDrivetrain
{
	Gearbox _left;
	Gearbox _right;
	
	public Tank(Gearbox left, Gearbox right, boolean leftInvert, boolean rightInvert)
	{
		GroupMotorControllers.Register(left.getMaster());
		_left = left;
		_right = right;
		_left.setInverted(leftInvert);
		_right.setInverted(rightInvert);
	}
	
	public Tank(IMotorController m1, IMotorController m2, boolean leftInvert, boolean rightInvert)
    {
        GroupMotorControllers.Register(m1);
        /* Create 2 single motor gearboxes */
        Gearbox temp1 = new Gearbox(m1);
        Gearbox temp2 = new Gearbox(m2);

        _left = temp1;
        _right = temp2;

        _left.setInverted(leftInvert);
        _right.setInverted(rightInvert);
    } 
	
	/** Inherited from IDrivetrain */
    public void set(Styles.Basic mode, float forward, float turn)
    {
        float l, r;
        l = forward + turn;
        r = forward - turn;

        Drive(mode, l, r);
    }
    
    public void SetVoltageRampRate(float rampRate)
    {
        _left.SetVoltageRampRate(rampRate);
        _right.SetVoltageRampRate(rampRate);
    }

    public void SetVoltageCompensationRampRate(float rampRate)
    {
        _left.SetVoltageCompensationRampRate(rampRate);
        _right.SetVoltageCompensationRampRate(rampRate);
    }

    public void ConfigPeakPercentOutputVoltage(float forwardVoltage, float reverseVoltage)
    {
        _left.ConfigPeakOutputVoltage(forwardVoltage, reverseVoltage);
        _right.ConfigPeakOutputVoltage(forwardVoltage, reverseVoltage);
    }

    public void ConfigNominalPercentOutputVoltage(float forwardVoltage, float reverseVoltage)
    {
        _left.ConfigNominalOutputVoltage(forwardVoltage, reverseVoltage);
        _right.ConfigNominalOutputVoltage(forwardVoltage, reverseVoltage);
    }

    private void Drive(Styles.Basic mode, float left, float right)
    {
        if (mode == Styles.Basic.Voltage)
        {
            _left.SetControlMode(ControlMode.BasicControlMode.kVoltage);
            _right.SetControlMode(ControlMode.BasicControlMode.kVoltage);
        }
        else if (mode == Styles.Basic.PercentOutput)
        {
            _left.SetControlMode(ControlMode.BasicControlMode.kPercentVbus);
            _right.SetControlMode(ControlMode.BasicControlMode.kPercentVbus);
        }

        _left.set(left);
        _right.set(right);
    }
}