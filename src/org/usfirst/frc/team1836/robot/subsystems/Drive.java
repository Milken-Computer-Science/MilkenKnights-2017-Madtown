package org.usfirst.frc.team1836.robot.subsystems;

import org.usfirst.frc.team1836.robot.Constants;
import org.usfirst.frc.team1836.robot.Inputs;
import org.usfirst.frc.team1836.robot.util.Subsystem;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class Drive extends Subsystem {

  private static Drive drive;

  private final RobotDrive robotDr;
  final CANTalon leftfwdtalon = new CANTalon(Constants.Hardware.LEFT_FWD_TALON_ID);
  final CANTalon leftbacktalon = new CANTalon(Constants.Hardware.LEFT_BACK_TALON_ID);
  final CANTalon rightfwdtalon = new CANTalon(Constants.Hardware.RIGHT_FWD_TALON_ID);
  final CANTalon rightbacktalon = new CANTalon(Constants.Hardware.RIGHT_BACK_TALON_ID);

  public static Drive getInstance() {
    if (drive == null)
      drive = new Drive();
    return drive;
  }

  public Drive() {
    super("Drive");
    robotDr = new RobotDrive(leftfwdtalon, leftbacktalon, rightfwdtalon, rightbacktalon);
  }


  @Override
  public void initTeleop() {
    leftfwdtalon.changeControlMode(TalonControlMode.PercentVbus);
    leftbacktalon.changeControlMode(TalonControlMode.PercentVbus);
    rightfwdtalon.changeControlMode(TalonControlMode.PercentVbus);
    rightbacktalon.changeControlMode(TalonControlMode.PercentVbus);
    leftfwdtalon.setEncPosition(0);
    rightfwdtalon.setEncPosition(0);
  }

  @Override
  public void updateTeleop() {
    robotDr.arcadeDrive(Inputs.driverJoystick.getRawAxis(1), Inputs.driverJoystick.getRawAxis(2));
  }

  @Override
  public void initAuto() {
    leftfwdtalon.changeControlMode(TalonControlMode.MotionMagic);
    leftbacktalon.changeControlMode(TalonControlMode.Follower);
    leftbacktalon.set(Constants.Hardware.LEFT_FWD_TALON_ID);
    rightfwdtalon.changeControlMode(TalonControlMode.MotionMagic);
    rightbacktalon.changeControlMode(TalonControlMode.Follower);
    rightbacktalon.set(Constants.Hardware.RIGHT_BACK_TALON_ID);
    leftfwdtalon.setEncPosition(0);
    rightfwdtalon.setEncPosition(0);
  }

  @Override
  public void updateAuto() {

  }


  @Override
  public void sendToSmartDash() {
    SmartDashboard.putNumber("Left Encoder Position", leftfwdtalon.getEncPosition());
    SmartDashboard.putNumber("Right Encoder Position", rightfwdtalon.getEncPosition());
    SmartDashboard.putNumber("Left Encoder Velocity", leftfwdtalon.getEncVelocity());
    SmartDashboard.putNumber("Right Encoder Velocity", rightfwdtalon.getEncVelocity());
  }

  public void setMotionMagicRelPosition(double pos) {
    leftfwdtalon.setEncPosition(0);
    rightfwdtalon.setEncPosition(0);
    leftfwdtalon.set(pos);
    rightfwdtalon.set(pos);
  }

  public void setMotionMagicAbsPosition(double pos) {
    leftfwdtalon.set(pos);
    rightfwdtalon.set(pos);
  }

  public boolean getMotionMagicOnTarget() {
    if (Math.abs(leftfwdtalon.getClosedLoopError()) <= Constants.Drive.DriveMagicMotionTol
        && Math.abs(rightfwdtalon.getClosedLoopError()) <= Constants.Drive.DriveMagicMotionTol) {
      return true;
    } else {
      return false;
    }

  }


}
