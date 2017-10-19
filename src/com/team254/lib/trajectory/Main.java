package com.team254.lib.trajectory;

/**
 *
 * @author Jared341
 */
public class Main {

  public static void main(String[] args) {
    double d = System.nanoTime();

    TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();

    final double kWheelbaseWidth = 25.5 / 12;

    {
      config.dt = .02;
      config.max_acc = 3.0;
      config.max_jerk = 50.0;
      config.max_vel = 8;

      // Description of this auto mode path.
      WaypointSequence p = new WaypointSequence(10);
      p.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
      p.addWaypoint(new WaypointSequence.Waypoint(14, 0, 0));

      Path path = PathGenerator.makePath(p, config, kWheelbaseWidth);
      System.out.println(System.nanoTime() - d);
      //System.out.println(path.getLeftWheelTrajectory().toString());
    }
  }
}
