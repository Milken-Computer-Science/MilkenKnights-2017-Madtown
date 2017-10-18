package org.usfirst.frc.team1836.robot.util;

public class TrajectoryPoint {

  private final double pos;
  private final double vel;
  private final double accel;
  private final double jerk;
  private final double count;
  private final double duration;

  public TrajectoryPoint(double pos, double vel, double accel, double jerk, int count,
      double duration) {
    this.pos = pos;
    this.vel = vel;
    this.accel = accel;
    this.jerk = jerk;
    this.count = count;
    this.duration = duration;
  }

  public double getTimeMs() {
    return getCount() * duration;
  }

  public double getTimeS() {
    return (getCount() * duration) / 1000;
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

  public double getJerk() {
    return jerk;
  }

  public double getCount() {
    return count;
  }

  @Override
  public String toString() {
    return "Time: " + getTimeS() + " X: " + getPos() + " V: " + getVel();
  }
}
