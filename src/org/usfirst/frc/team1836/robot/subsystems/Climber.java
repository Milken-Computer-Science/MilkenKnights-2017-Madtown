package org.usfirst.frc.team1836.robot.subsystems;

import org.usfirst.frc.team1836.robot.Constants;
import org.usfirst.frc.team1836.robot.Inputs;
import org.usfirst.frc.team1836.robot.util.MkCANTalon;
import org.usfirst.frc.team1836.robot.util.Subsystem;

public class Climber extends Subsystem {
  private static Climber climber;
  private MkCANTalon climberTalon;

  public Climber() {
    super("CLIMBER");
    climberTalon = new MkCANTalon(Constants.Hardware.CLIMBER_TALON_ID, true);
    climberTalon.setPrint(false);
  }

  public static Climber getInstance() {
    if (climber == null) {
      climber = new Climber();
    }
    return climber;
  }

  @Override
  public void updateTeleop() {
    if (Inputs.climberFwdButton.isHeld()) {
      climberTalon.set(Constants.Climb.CLIMBER_SPEED);
    } else {
      climberTalon.set(0);
    }
  }

  @Override
  public void updateAuto() {

  }

  @Override
  public void initTeleop() {

  }

  @Override
  public void initAuto() {

  }

  @Override
  public void sendToSmartDash() {}

}
