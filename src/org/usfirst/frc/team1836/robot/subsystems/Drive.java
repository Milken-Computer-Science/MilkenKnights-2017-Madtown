package org.usfirst.frc.team1836.robot.subsystems;

import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;
import com.team254.lib.trajectory.Trajectory;
import com.team254.lib.trajectory.TrajectoryFollower;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1836.robot.Inputs;
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
		private final RobotDrive robotDr;
		MkGyro navX;
		private TrajectoryFollower trajFollower;
		private double trajDist;

		public Drive() {
				super("DRIVE");
				navX = new MkGyro(new AHRS(SPI.Port.kMXP));
				leftfwdtalon =
						new MkCANTalon(Hardware.LEFT_FWD_TALON_ID, DRIVE.WHEEL_DIAMETER,
								false);
				leftbacktalon =
						new MkCANTalon(Hardware.LEFT_BACK_TALON_ID, DRIVE.WHEEL_DIAMETER,
								false);
				rightbacktalon =
						new MkCANTalon(Hardware.RIGHT_BACK_TALON_ID, DRIVE.WHEEL_DIAMETER,
								false);
				rightfwdtalon =
						new MkCANTalon(Hardware.RIGHT_FWD_TALON_ID, DRIVE.WHEEL_DIAMETER,
								false);

				robotDr = new RobotDrive(leftfwdtalon, leftbacktalon, rightfwdtalon, rightbacktalon);

				leftfwdtalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
				leftfwdtalon.reverseSensor(false);
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
				rightfwdtalon.reverseSensor(false);
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

				leftfwdtalon.setPrint(false);
				leftbacktalon.setPrint(false);
				rightfwdtalon.setPrint(false);
				rightbacktalon.setPrint(false);
		}

		public static Drive getInstance() {
				if (drive == null)
						drive = new Drive();
				return drive;
		}

		@Override public void updateTeleop() {
				robotDr
						.arcadeDrive(Inputs.driverJoystick.getRawAxis(1), Inputs.driverJoystick.getRawAxis(2));
		}

		@Override public void updateAuto() {

		}

		@Override public void initTeleop() {
				leftfwdtalon.changeControlMode(TalonControlMode.PercentVbus);
				leftbacktalon.changeControlMode(TalonControlMode.PercentVbus);
				rightfwdtalon.changeControlMode(TalonControlMode.PercentVbus);
				rightbacktalon.changeControlMode(TalonControlMode.PercentVbus);
				leftfwdtalon.setEncPosition(0);
				rightfwdtalon.setEncPosition(0);
		}

		@Override public void initAuto() {
				leftfwdtalon.setEncPosition(0);
				rightfwdtalon.setEncPosition(0);
		}

		@Override public void sendToSmartDash() {
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
				if (Math.abs(leftfwdtalon.getClosedLoopError()) <= DRIVE.DRIVE_MAGIC_MOTION_TOL
						&& Math.abs(rightfwdtalon.getClosedLoopError())
						<= DRIVE.DRIVE_MAGIC_MOTION_TOL) {
						return true;
				} else {
						return false;
				}

		}

		public void setVelMode() {
				leftfwdtalon.changeControlMode(TalonControlMode.Speed);
				rightfwdtalon.changeControlMode(TalonControlMode.Speed);

				leftbacktalon.changeControlMode(TalonControlMode.Follower);
				leftbacktalon.set(Hardware.LEFT_FWD_TALON_ID);

				rightbacktalon.changeControlMode(TalonControlMode.Follower);
				rightbacktalon.set(Hardware.RIGHT_BACK_TALON_ID);
		}

		public void setMagicMode() {
				leftfwdtalon.changeControlMode(TalonControlMode.MotionMagic);
				rightfwdtalon.changeControlMode(TalonControlMode.MotionMagic);

				leftbacktalon.changeControlMode(TalonControlMode.Follower);
				leftbacktalon.set(Hardware.LEFT_FWD_TALON_ID);

				rightbacktalon.changeControlMode(TalonControlMode.Follower);
				rightbacktalon.set(Hardware.RIGHT_BACK_TALON_ID);
		}

		public void setDriveVelocity(double velocity) {
				leftfwdtalon.set(velocity);
				rightfwdtalon.set(velocity);
		}

		public void setDriveTrajectory(Trajectory traj, double dist) {
				trajFollower = new TrajectoryFollower();
				trajFollower.configure(DRIVE.DRIVE_FOLLOWER_P, DRIVE.DRIVE_FOLLOWER_D);
				trajFollower.setTrajectory(traj);
				trajDist = dist;
		}

		public void setTrajectoryPoint() {
				if (trajFollower != null) {
						leftfwdtalon.set(trajFollower.calculate(leftfwdtalon.getPosition()), true);
						rightfwdtalon.set(trajFollower.calculate(rightfwdtalon.getPosition()), true);
				}
		}

		public void setTurnTrajectoryPoint() {
				if (trajFollower != null) {
						leftfwdtalon.set(trajFollower.calculate(navX.getYaw()), true);
						rightfwdtalon.set(-trajFollower.calculate(navX.getYaw()), true);
				}
		}

		public boolean getTrajectoryFinished() {
				return trajFollower.isFinishedTrajectory() && (DRIVE.TRAJ_TOL >= Math
						.abs(trajDist - ((leftfwdtalon.getPosition() + rightfwdtalon.getPosition()) / 2)));
		}
}
