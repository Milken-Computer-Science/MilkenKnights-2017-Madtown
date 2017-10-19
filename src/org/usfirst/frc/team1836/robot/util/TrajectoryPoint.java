package org.usfirst.frc.team1836.robot.util;

public class TrajectoryPoint {

  private final double pos;
  private final double vel;
  private final double accel;
  private final double duration;

  public TrajectoryPoint(double pos, double vel, double accel, double duration) {
    this.pos = pos;
    this.vel = vel;
    this.accel = accel;
    this.duration = duration;
  }

  public double getVel() {
    return vel;
  }

  public double getPos() {
    return pos;
  }

  public double getAccel() {
    return accel;
  }

  public double getDuration() {
    return duration;
  }

  @Override
  public String toString() {
    return " X: " + getPos() + " V: " + getVel();
  }
}
