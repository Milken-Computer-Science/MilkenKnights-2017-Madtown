package org.usfirst.frc.team1836.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import org.usfirst.frc.team1836.robot.auto.commandgroups.NoAuto;
import org.usfirst.frc.team1836.robot.subsystems.Drive;

public class Robot extends IterativeRobot {
		private static SendableChooser<Command> mainAutoChooser = new SendableChooser<Command>();
		private Systems system;
		private Command autonomousCommand;

		@Override public void robotInit() {
				Drive.getInstance();
				mainAutoChooser.addObject("No Auto", new NoAuto());
				system = new Systems();
		}

		@Override public void autonomousInit() {
				system.initAuto();
				autonomousCommand = mainAutoChooser.getSelected();
				if (autonomousCommand != null) {
						autonomousCommand.start();
				}
		}

		@Override public void teleopInit() {
				system.initTeleop();
		}

		@Override public void autonomousPeriodic() {
				system.updateAuto();
				Scheduler.getInstance().run();
				system.smartDashboard();
		}

		@Override public void teleopPeriodic() {
				system.updateTeleop();
				system.smartDashboard();
		}

}
