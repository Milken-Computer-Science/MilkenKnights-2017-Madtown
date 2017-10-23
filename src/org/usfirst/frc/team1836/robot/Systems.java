package org.usfirst.frc.team1836.robot;

import java.util.LinkedList;
import java.util.List;

import org.usfirst.frc.team1836.robot.util.Subsystem;

public class Systems {
  private static List<Subsystem> systems = new LinkedList<Subsystem>();

  public static void addSystem(Subsystem e) {
    systems.add(e);
  }

  public void updateAuto() {
    for (Subsystem e : systems) {
      e.updateAuto();
    }
  }

  public void updateTeleop() {
    for (Subsystem e : systems) {
      e.updateTeleop();
    }
  }

  public void smartDashboard() {
    for (Subsystem e : systems) {
      e.sendToSmartDash();
    }
  }

  public void initAuto() {
    for (Subsystem e : systems) {
      e.initAuto();
    }
  }

  public void initTeleop() {
    for (Subsystem e : systems) {
      e.initTeleop();
    }
  }

}
