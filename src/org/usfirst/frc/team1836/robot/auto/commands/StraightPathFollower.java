package org.usfirst.frc.team1836.robot.auto.commands;

import org.usfirst.frc.team1836.robot.Constants;
import org.usfirst.frc.team1836.robot.subsystems.Drive;

import com.team254.lib.trajectory.Main;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StraightPathFollower extends Command {


  private final Main m;
  private final double dist;

  public StraightPathFollower(double dist) {
    m = new Main();
    this.dist = dist;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Drive.getInstance().setDriveTrajectory(m.genTraj(dist, Constants.PID.dt, Constants.PID.mAccel,
        Constants.PID.mVel, Constants.PID.mJerk), dist);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Drive.getInstance().setTrajectoryPoint();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Drive.getInstance().getTrajectoryFinished();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {}

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {}
}
