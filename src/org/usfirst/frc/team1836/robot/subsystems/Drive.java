package org.usfirst.frc.team1836.robot.subsystems;

import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;
import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.TrajectoryFollower;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1836.robot.Inputs;
import org.usfirst.frc.team1836.robot.util.CheesyDriveHelper;
import org.usfirst.frc.team1836.robot.util.DriveSignal;
import org.usfirst.frc.team1836.robot.util.MkCANTalon;
import org.usfirst.frc.team1836.robot.util.MkGyro;
import org.usfirst.frc.team1836.robot.util.Subsystem;

import static org.usfirst.frc.team1836.robot.Constants.DRIVE;
import static org.usfirst.frc.team1836.robot.Constants.Hardware;

public class Drive extends Subsystem {

  private static Drive drive;
  private final MkCANTalon leftfwdtalon;
  private final MkCANTalon leftbacktalon;
  private final MkCANTalon rightfwdtalon;
  private final MkCANTalon rightbacktalon;
  private MkGyro navX;
  private TrajectoryFollower trajFollower;
  private double trajectoryDist;
  private CheesyDriveHelper mCheesyDriveHelper = new CheesyDriveHelper();
  private int reverseState = -1;
  private RobotDrive robotDr;

  private Drive() {
    super("DRIVE");
    navX = new MkGyro(new AHRS(SPI.Port.kMXP));
    leftfwdtalon = new MkCANTalon(Hardware.LEFT_FWD_TALON_ID, DRIVE.WHEEL_DIAMETER);
    leftbacktalon = new MkCANTalon(Hardware.LEFT_BACK_TALON_ID, DRIVE.WHEEL_DIAMETER);
    rightbacktalon = new MkCANTalon(Hardware.RIGHT_BACK_TALON_ID, DRIVE.WHEEL_DIAMETER);
    rightfwdtalon = new MkCANTalon(Hardware.RIGHT_FWD_TALON_ID, DRIVE.WHEEL_DIAMETER);

    leftfwdtalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    leftfwdtalon.reverseSensor(Hardware.LEFT_FWD_TALON_SENSOR_REVERSE);
    leftfwdtalon.configNominalOutputVoltage(+0.0f, -0.0f);
    leftfwdtalon.configPeakOutputVoltage(+12.0f, -12.0f);
    leftfwdtalon.setProfile(0);
    leftfwdtalon.setF(DRIVE.DRIVE_F);
    leftfwdtalon.setP(DRIVE.DRIVE_P);
    leftfwdtalon.setI(DRIVE.DRIVE_I);
    leftfwdtalon.setD(DRIVE.DRIVE_D);
    leftfwdtalon.setIZone(DRIVE.DRIVE_I_ZONE);
    leftfwdtalon.setMotionMagicCruiseVelocity(DRIVE.DRIVE_V);
    leftfwdtalon.setMotionMagicAcceleration(DRIVE.DRIVE_A);



    rightfwdtalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    rightfwdtalon.reverseSensor(Hardware.RIGHT_FWD_TALON_SENSOR_REVERSE);
    rightfwdtalon.configNominalOutputVoltage(+0.0f, -0.0f);
    rightfwdtalon.configPeakOutputVoltage(+12.0f, -12.0f);
    rightfwdtalon.setProfile(0);
    rightfwdtalon.setF(DRIVE.DRIVE_F);
    rightfwdtalon.setP(DRIVE.DRIVE_P);
    rightfwdtalon.setI(DRIVE.DRIVE_I);
    rightfwdtalon.setD(DRIVE.DRIVE_D);
    rightfwdtalon.setIZone(DRIVE.DRIVE_I_ZONE);
    rightfwdtalon.setMotionMagicCruiseVelocity(DRIVE.DRIVE_V);
    rightfwdtalon.setMotionMagicAcceleration(DRIVE.DRIVE_A);

    leftfwdtalon.reverseOutput(Hardware.LEFT_FWD_TALON_REVERSE);
    leftbacktalon.reverseOutput(Hardware.LEFT_BACK_TALON_REVERSE);
    rightfwdtalon.reverseOutput(Hardware.RIGHT_FWD_TALON_REVERSE);
    rightbacktalon.reverseOutput(Hardware.RIGHT_BACK_TALON_REVERSE);

    leftfwdtalon.setPrint(false);
    leftbacktalon.setPrint(false);
    rightfwdtalon.setPrint(false);
    rightbacktalon.setPrint(false);
    
    robotDr = new RobotDrive(leftfwdtalon, leftbacktalon, rightfwdtalon, rightbacktalon);
  }

  public static Drive getInstance() {
    if (drive == null)
      drive = new Drive();
    return drive;
  }

  @Override
  public void updateTeleop() {
    
    reverseState = Inputs.reverseButton.isPressed() ? -reverseState : reverseState;
    
    int holdManual = Inputs.reverseHoldButton.isHeld() ? -1 : 1;
    DriveSignal sig = mCheesyDriveHelper.cheesyDrive(-Inputs.driverJoystick.getRawAxis(2),
        Inputs.driverJoystick.getRawAxis(1) * reverseState * holdManual, Inputs.cheezyButton.isHeld());
    leftfwdtalon.set(sig.getLeft());
    rightfwdtalon.set(sig.getRight());
  }

  @Override
  public void updateAuto() {

  }

  @Override
  public void initTeleop() {
    leftfwdtalon.changeControlMode(TalonControlMode.PercentVbus);
    rightfwdtalon.changeControlMode(TalonControlMode.PercentVbus);
    setFollowerMode();
    leftfwdtalon.setEncPosition(0);
    rightfwdtalon.setEncPosition(0);
    navX.zeroYaw();
    
    reverseState = 1;
  }

  @Override
  public void initAuto() {
    leftfwdtalon.setEncPosition(0);
    rightfwdtalon.setEncPosition(0);
    navX.zeroYaw();
  }

  @Override
  public void sendToSmartDash() {
    SmartDashboard.putNumber("Left Encoder Position", leftfwdtalon.getMkPosition());
    SmartDashboard.putNumber("Right Encoder Position", rightfwdtalon.getMkPosition());
    SmartDashboard.putNumber("Left Encoder Velocity", leftfwdtalon.getMkVelocity());
    SmartDashboard.putNumber("Right Encoder Velocity", rightfwdtalon.getMkVelocity());
    SmartDashboard.putNumber("NavX Yaw", navX.getYaw());
  }

  public void setControlMode(TalonControlMode mode) {
    leftfwdtalon.changeControlMode(mode);
    rightfwdtalon.changeControlMode(mode);
    setFollowerMode();
  }

  public void setDrive(double val) {
    robotDr.arcadeDrive(-val, -(navX.getYaw() * 0.2));
  }

  public void setMagicPosition(double pos) {
    setMagicMode();
    leftfwdtalon.setEncPosition(0);
    rightfwdtalon.setEncPosition(0);
    leftfwdtalon.set(pos);
    rightfwdtalon.set(pos);
  }

  public boolean getMotionMagicOnTarget() {
    return Math.abs(leftfwdtalon.getMkError()) <= DRIVE.DRIVE_MAGIC_MOTION_TOL
        && Math.abs(rightfwdtalon.getMkError()) <= DRIVE.DRIVE_MAGIC_MOTION_TOL;
  }

  private void setFollowerMode() {
    leftbacktalon.changeControlMode(TalonControlMode.Follower);
    leftbacktalon.set(Hardware.LEFT_FWD_TALON_ID);

    rightbacktalon.changeControlMode(TalonControlMode.Follower);
    rightbacktalon.set(Hardware.RIGHT_FWD_TALON_ID);
  }

  private void setMagicMode() {
    leftfwdtalon.changeControlMode(TalonControlMode.MotionMagic);
    rightfwdtalon.changeControlMode(TalonControlMode.MotionMagic);
    setFollowerMode();
  }

  public void setDriveTrajectory(Path traj, double dist) {
    leftfwdtalon.changeControlMode(TalonControlMode.Speed);
    rightfwdtalon.changeControlMode(TalonControlMode.Speed);
    setFollowerMode();
    trajFollower = new TrajectoryFollower();
    trajFollower.configure(DRIVE.DRIVE_FOLLOWER_P, DRIVE.DRIVE_FOLLOWER_D,
        DRIVE.DRIVE_FOLLOWER_ANG);
    trajFollower.setTrajectory(traj.getLeftWheelTrajectory());
    trajectoryDist = dist;
  }

  public void setTrajectoryPoint() {
    if (trajFollower != null) {
      leftfwdtalon.set(trajFollower.calculate(leftfwdtalon.getPosition(), navX.getYaw(), 1));
      rightfwdtalon.set(trajFollower.calculate(rightfwdtalon.getPosition(), navX.getYaw(), -1));
    }
  }

  public void setTurnTrajectoryPoint() {
    if (trajFollower != null) {
      leftfwdtalon.set(trajFollower.calculate(navX.getYaw(), 0, 0));
      rightfwdtalon.set(-trajFollower.calculate(navX.getYaw(), 0, 0));
    }
  }

  public boolean getTrajectoryFinished() {
    return trajFollower.isFinishedTrajectory() && (DRIVE.TRAJ_TOL >= Math
        .abs(trajectoryDist - ((leftfwdtalon.getPosition() + rightfwdtalon.getPosition()) / 2)));
  }
}
