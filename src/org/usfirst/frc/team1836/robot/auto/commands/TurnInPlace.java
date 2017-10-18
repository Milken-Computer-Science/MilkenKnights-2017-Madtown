package org.usfirst.frc.team1836.robot.auto.commands;

import org.usfirst.frc.team1836.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnInPlace extends Command {

  private final double deg;

  public TurnInPlace(double deg) {
    this.deg = deg;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Drive.getInstance().setTurnSetpoint(deg);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {}

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Drive.getInstance().getTurnFinished();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {}

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {}
}
