package org.usfirst.frc.team1836.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1836.robot.auto.commandgroups.BaselineAuto;
import org.usfirst.frc.team1836.robot.auto.commandgroups.NoAuto;
import org.usfirst.frc.team1836.robot.auto.commandgroups.TimedCenterAuto;
import org.usfirst.frc.team1836.robot.subsystems.Climber;
import org.usfirst.frc.team1836.robot.subsystems.Drive;
import org.usfirst.frc.team1836.robot.subsystems.GearMech;

public class Robot extends IterativeRobot {

    private Command autonomousCommand;
    private SendableChooser<Command> chooser = new SendableChooser<>();

    @Override public void robotInit() {
        Drive.getInstance();
        GearMech.getInstance();
        Climber.getInstance();
        CameraServer.getInstance().startAutomaticCapture().setResolution(640, 360);
        chooser.addObject("No Auto", new NoAuto());
        chooser.addObject("Timed Center Auto", new TimedCenterAuto());
        chooser.addDefault("Baseline Auto", new BaselineAuto());
        SmartDashboard.putData("Auto mode", chooser);
    }

    @Override public void autonomousInit() {
        Systems.initAuto();
        autonomousCommand = chooser.getSelected();
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
