package org.usfirst.frc.team1836.robot.auto.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team1836.robot.Constants;
import org.usfirst.frc.team1836.robot.auto.commands.MagicMotionDrive;
import org.usfirst.frc.team1836.robot.auto.commands.PlaceGearSequence;
import org.usfirst.frc.team1836.robot.subsystems.GearMech;

/**
 *
 */
public class MagicCenterAuto extends CommandGroup {

    MagicMotionDrive magicMotionDrive;

    public MagicCenterAuto() {
        GearMech.getInstance().set(GearMech.GearMechanismState.PLACE);
        addSequential(new MagicMotionDrive(Constants.Auto.CENTER_AUTO_DISTANCE));
        addSequential(new PlaceGearSequence(5));
    }


}
