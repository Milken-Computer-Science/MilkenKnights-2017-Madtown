package org.usfirst.frc.team1836.robot;

import org.usfirst.frc.team1836.robot.auto.commandgroups.NoAuto;
import org.usfirst.frc.team1836.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class Robot extends IterativeRobot {

  private Command autonomousCommand;
  private static SendableChooser<Command> mainAutoChooser = new SendableChooser<Command>();

  @Override
  public void robotInit() {
    Drive.getInstance();
    mainAutoChooser.addObject("No Auto", new NoAuto());
  }

  @Override
  public void autonomousInit() {
    Systems.initAuto();
    autonomousCommand = mainAutoChooser.getSelected();
    if (autonomousCommand != null) {
      autonomousCommand.start();
    }
  }

  @Override
  public void autonomousPeriodic() {
    Systems.updateAuto();
    Scheduler.getInstance().run();
    Systems.smartDashboard();
  }

  @Override
  public void teleopInit() {
    Systems.initTeleop();
  }

  @Override
  public void teleopPeriodic() {
    Systems.updateTeleop();
    Systems.smartDashboard();
  }

}
