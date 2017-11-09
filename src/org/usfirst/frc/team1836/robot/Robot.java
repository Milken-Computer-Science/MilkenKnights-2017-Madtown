package org.usfirst.frc.team1836.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import org.usfirst.frc.team1836.robot.auto.commandgroups.MagicCenterAuto;
import org.usfirst.frc.team1836.robot.auto.commandgroups.NoAuto;
import org.usfirst.frc.team1836.robot.auto.commandgroups.TimedCenterAuto;
import org.usfirst.frc.team1836.robot.auto.commands.CenterPathFollower;
import org.usfirst.frc.team1836.robot.subsystems.Climber;
import org.usfirst.frc.team1836.robot.subsystems.Drive;
import org.usfirst.frc.team1836.robot.subsystems.GearMech;

public class Robot extends IterativeRobot {
    private static SendableChooser<CommandGroup> mainAutoChooser = new SendableChooser<>();
    private CommandGroup autonomousCommand;
RobotDrive robotdr;
    @Override public void robotInit() {
        Drive.getInstance();
        GearMech.getInstance();
        Climber.getInstance();
        CameraServer.getInstance().startAutomaticCapture();
        mainAutoChooser.addObject("No Auto", new NoAuto());
        mainAutoChooser.addObject("Time Auto", new TimedCenterAuto());
    }

    @Override public void autonomousInit() {
        Systems.initAuto();
        autonomousCommand = new TimedCenterAuto();
        if (autonomousCommand != null) {
            autonomousCommand.start();
        }
    }

    @Override public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        Systems.updateAuto();
        Systems.smartDashboard();
    }

    @Override public void disabledInit() {
        autonomousCommand = null;
    }

    @Override public void teleopInit() {
        Systems.initTeleop();
    }

    @Override public void teleopPeriodic() {
        Systems.updateTeleop();
        Systems.smartDashboard();
    }

    public void disabledPeriodic() {
        Systems.smartDashboard();
    }

}
