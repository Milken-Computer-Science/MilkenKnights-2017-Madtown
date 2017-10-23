package org.usfirst.frc.team1836.robot.auto.commands;

import org.usfirst.frc.team1836.robot.Constants;
import org.usfirst.frc.team1836.robot.subsystems.Drive;

import com.team254.lib.trajectory.Main;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnInPlace extends Command {
  
  private final Main m;
  private final double deg;
  
  public TurnInPlace(double deg) {
    m = new Main();
    this.deg = deg;
  }

  @Override
  protected void initialize() {
    Drive.getInstance().setDriveTrajectory(m.genTraj(deg, Constants.PID.dt, Constants.PID.mAccel,
        Constants.PID.mVel, Constants.PID.mJerk), deg);
  }

  @Override
  protected void execute() {
    Drive.getInstance().setTurnTrajectoryPoint();
  }

  @Override
  protected boolean isFinished() {
    return Drive.getInstance().getTrajectoryFinished();
  }

  @Override
  protected void end() {}

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {}
}
