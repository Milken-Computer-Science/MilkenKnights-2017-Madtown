package org.usfirst.frc.team1836.robot.util;

import java.util.ArrayList;

public class TrapezoidGenerator {

  public TrapezoidGenerator(float time_delta, float acceleration, int segments_needed,
      float initial_distance, float initial_velocity) {

  }

  public int pf_trapezoid_segment_count(double time_delta, double distance, double acceleration,
      double max_velocity) {
    double accel_dist = 0.5 * max_velocity * max_velocity / acceleration;
    if (accel_dist * 2 > distance) {
      return (int) (Math.sqrt(distance / acceleration) / time_delta) * 2;
    } else {
      return (int) ((max_velocity / acceleration / time_delta) * 2
          + (distance - accel_dist * 2) / max_velocity / time_delta);
    }
  }

  public int generate_profile(double time_delta, double distance, double acceleration,
      double max_velocity) {
    double accel_dist = (0.5 * max_velocity * max_velocity / acceleration);
    if (accel_dist * 2 > distance) {
      // We'll never steady out at max_velocity, use a triangular profile instead
      int accel_segments = (int) (Math.sqrt(distance / acceleration) / time_delta);

      return accel_segments * 2;
    } else {
      double remaining_distance = distance - (accel_dist * 2);
      double hold_time = remaining_distance / max_velocity;

      int accel_segments = (int) (max_velocity / acceleration / time_delta);
      int hold_segments = (int) (hold_time / time_delta);

      ArrayList<TrajectoryPoint> trajectory = new ArrayList<>();
      int i;
      for (i = 0; i < accel_segments; i++) {
        double time = time_delta * i;
        // v = u + at
        double velocity = acceleration * time;
        // s = s0 + ut + 0.5at^2
        double distance = 0.5 * acceleration * time * time;
        trajectory.add(new TrajectoryPoint(distance, velocity, acceleration, 0, i, 20));
      }
      
      return accel_segments * 2 + hold_segments;
    }
  }



}
