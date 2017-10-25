package com.team254.lib.trajectory;

/**
 * @author Jared341
 */
public class Main {

		public Trajectory genTraj(double dist, double dt, double accel, double vel, double mjerk) {
				double t = System.nanoTime();

				TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();

				final double kWheelbaseWidth = 25.5 / 12;

				{
						config.dt = dt;
						config.max_acc = accel;
						config.max_jerk = mjerk;
						config.max_vel = vel;

						// Description of this auto mode path.
						WaypointSequence p = new WaypointSequence(10);
						p.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
						p.addWaypoint(new WaypointSequence.Waypoint(dist, 0, 0));

						Path path = PathGenerator.makePath(p, config, kWheelbaseWidth);
						System.out.println(System.nanoTime() - t);
						return path.getLeftWheelTrajectory();
				}
		}
}
