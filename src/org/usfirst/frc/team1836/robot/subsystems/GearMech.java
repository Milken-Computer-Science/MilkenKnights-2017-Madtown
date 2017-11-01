package org.usfirst.frc.team1836.robot.subsystems;

import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import org.usfirst.frc.team1836.robot.Constants;
import org.usfirst.frc.team1836.robot.Inputs;
import org.usfirst.frc.team1836.robot.util.MkCANTalon;
import org.usfirst.frc.team1836.robot.util.Subsystem;

public class GearMech extends Subsystem {

    private MkCANTalon gearTalon = new MkCANTalon(Constants.Hardware.GEAR_PICKUP_TALON_ID, true);
    private GearMechanismState gearMechState;


    public GearMech() {
        super("Gear Pickup");
        gearMechState = GearMechanismState.STOW;
        gearTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
        gearTalon.reverseSensor(Constants.Hardware.GEAR_ARM_TALON_SENSOR_REVERSE);
        gearTalon.reverseOutput(Constants.Hardware.GEAR_ARM_TALON_REVERSE);
        gearTalon.configNominalOutputVoltage(+0.0f, -0.0f);
        gearTalon.configPeakOutputVoltage(+12.0f, -12.0f);
        gearTalon.setProfile(0);
        gearTalon.setF(Constants.GearMech.GEAR_F);
        gearTalon.setP(Constants.GearMech.GEAR_P);
        gearTalon.setI(Constants.GearMech.GEAR_I);
        gearTalon.setD(Constants.GearMech.GEAR_D);
        gearTalon.setIZone(Constants.GearMech.GEAR_I_ZONE);
        gearTalon.setMotionMagicCruiseVelocity(Constants.GearMech.GEAR_V);
        gearTalon.setMotionMagicAcceleration(Constants.GearMech.GEAR_A);
    }

    @Override public void updateTeleop() {
        if (Inputs.gearPickupButton.isPressed()) {
            set(GearMechanismState.PICKUP);
        } else if (Inputs.gearStowButton.isPressed()) {
            set(GearMechanismState.STOW);
        } else if (Inputs.gearPlaceButton.isPressed()) {
            set(GearMechanismState.PLACE);
        }

    }

    @Override public void updateAuto() {

    }

    @Override public void initTeleop() {
        gearTalon.changeControlMode(TalonControlMode.MotionMagic);
    }

    @Override public void initAuto() {
        gearTalon.changeControlMode(TalonControlMode.MotionMagic);
    }

    @Override public void sendToSmartDash() {

    }

    public void set(GearMechanismState state) {
        gearMechState = state;
        gearTalon.set(state.state);
    }

    public enum GearMechanismState {
        PICKUP(Constants.GearMech.GEAR_PICKUP), STOW(Constants.GearMech.GEAR_STOW), PLACE(
            Constants.GearMech.GEAR_PLACE);
        public final double state;

        GearMechanismState(final double state) {
            this.state = state;
        }
    }

}
