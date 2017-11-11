package com.team254.lib.trajectory;

/**
 * DRIVE + Feedforward controller for following a Trajectory.
 *
 * @author Jared341
 */
public class TrajectoryFollower {

  double Dt;
  private double kp_;
  private double kd_;
  private double kAng_;
  private double last_error_;
  private double current_heading = 0;
  private int current_segment;
  private Trajectory profile_;



  public TrajectoryFollower() {

  }

  public void configure(double kp, double kd, double kAng) {
    kp_ = kp;
    kd_ = kd;
    kAng_ = kAng;
  }

  public void reset() {
    last_error_ = 0.0;
    current_segment = 0;
  }

  public void setTrajectory(Trajectory profile) {
    profile_ = profile;
    Dt = System.nanoTime();
  }

  /*
   * @param side Is a 1 or -1 to either add or subtract for the right or left side of the drivetrain
   * 
   * @param gyroHeading is the gyro heading to account for angle
   */
  public double calculate(double distance_so_far, double gyroHeading, int side) {
    if (current_segment < profile_.getNumSegments()) {
      double seg = (customRound((System.nanoTime() - Dt) * 1e-9) / 0.02);
      Trajectory.Segment segment = profile_.getSegment((int) seg);
      double error = segment.pos - distance_so_far;
      double output = kp_ * error + kd_ * ((error - last_error_) / segment.dt - segment.vel)
          + segment.vel + (Math.abs(segment.heading - gyroHeading) * kAng_ * side);

      last_error_ = error;
      current_heading = segment.heading;
      current_segment++;
      return output;
    } else {
      return 0;
    }
  }

  public double getHeading() {
    return current_heading;
  }

  public boolean isFinishedTrajectory() {
    return current_segment >= profile_.getNumSegments();
  }

  public double customRound(double num) {
    return Math.round(num * 50) / 50.0;
  }

}
