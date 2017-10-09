package org.usfirst.frc.team1836.robot;

import java.util.LinkedList;
import java.util.List;

import org.usfirst.frc.team1836.robot.util.Subsystem;

import edu.wpi.first.wpilibj.DriverStation;

public class Systems {
	private static List<Subsystem> systems = new LinkedList<Subsystem>();

	public static void addSystem(Subsystem e) {
		systems.add(e);
	}

	public static void update() {
		for (Subsystem e : systems) {
			if (DriverStation.getInstance().isAutonomous())
				e.updateAuto();
			else {
				e.updateTeleop();
			}
		}

	}

	public static void smartDashboard() {
		for (Subsystem e : systems) {
			e.sendToSmartDash();
		}
	}

	public static void init() {
		for (Subsystem e : systems) {
			if (DriverStation.getInstance().isAutonomous())
				e.initAuto();
			else
				e.initTeleop();
		}
	}

	public static void sendToSmartDashboard() {
		for (Subsystem e : systems)
			e.sendToSmartDash();
	}
}
