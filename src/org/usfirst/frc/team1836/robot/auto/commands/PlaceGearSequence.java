package org.usfirst.frc.team1836.robot.auto.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1836.robot.Constants;
import org.usfirst.frc.team1836.robot.subsystems.GearMech;

public class PlaceGearSequence extends Command {

  private final double time;
  private Timer timer;

  public PlaceGearSequence(double time) {
    this.time = time;
  }

  @Override
  protected void initialize() {
    timer = new Timer();
    timer.start();
    GearMech.getInstance().set(GearMech.GearMechanismState.PICKUP);
  }

  @Override
  protected void execute() {

   // GearMech.getInstance().setRollers(-Constants.GearMech.ROLLER_SPEED);
  }

  @Override
  protected boolean isFinished() {
    return timer.get() > time;

  }

  @Override
  protected void end() {

  }

  @Override
  protected void interrupted() {

  }
}
