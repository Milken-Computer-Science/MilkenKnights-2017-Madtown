package org.usfirst.frc.team1836.robot.util;

public class TrajectoryPoint {

	private final double pos;
	private final double vel;
	private final double count;
	protected final double duration;

	public TrajectoryPoint(double pos, double vel, int count, double duration) {
		this.pos = pos;
		this.vel = vel;
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

	public double getCount() {
		return count;
	}

    public String toString() {
        return "Time: " + getTimeS() + " X: " + getPos() + " V: " + getVel();
    }
}
