package org.usfirst.frc.team1836.robot.subsystems;

import org.usfirst.frc.team1836.robot.Constants;
import org.usfirst.frc.team1836.robot.Inputs;
import org.usfirst.frc.team1836.robot.util.Subsystem;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

public class GearMech extends Subsystem {

  CANTalon gearTalon = new CANTalon(Constants.Hardware.GEAR_PICKUP_TALON_ID);
  GearMechanismState gearMechState;

  public enum GearMechanismState {
    PICKUP(Constants.GearMech.GearPickup), STOW(Constants.GearMech.GearStow), PLACE(
        Constants.GearMech.GearPlace);
    public final double state;

    private GearMechanismState(final double state) {
      this.state = state;
    }
  }


  public GearMech() {
    super("Gear Pickup");
    gearMechState = GearMechanismState.PLACE;
    gearTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    gearTalon.reverseSensor(false);
    gearTalon.configNominalOutputVoltage(+0.0f, -0.0f);
    gearTalon.configPeakOutputVoltage(+12.0f, -12.0f);
    gearTalon.setProfile(0);
    gearTalon.setF(Constants.PID.GearF);
    gearTalon.setP(Constants.PID.GearP);
    gearTalon.setI(Constants.PID.GearI);
    gearTalon.setD(Constants.PID.GearD);
    gearTalon.setIZone(Constants.PID.GearIZone);
    gearTalon.setMotionMagicCruiseVelocity(Constants.PID.GearV);
    gearTalon.setMotionMagicAcceleration(Constants.PID.GearA);
  }

  @Override
  public void updateTeleop() {

    if (Inputs.gearPickupButton.isPressed()) {
      gearMechState = GearMechanismState.PICKUP;
      gearTalon.set(gearMechState.state);
    } else if (Inputs.gearStowButton.isPressed()) {
      gearMechState = GearMechanismState.STOW;
      gearTalon.set(gearMechState.state);
    } else if (Inputs.gearPlaceButton.isPressed()) {
      gearMechState = GearMechanismState.PLACE;
      gearTalon.set(gearMechState.state);
    }

  }

  @Override
  public void updateAuto() {

  }

  @Override
  public void initTeleop() {
    gearTalon.changeControlMode(TalonControlMode.MotionMagic);
  }

  @Override
  public void initAuto() {
    gearTalon.changeControlMode(TalonControlMode.MotionMagic);
  }

  @Override
  public void sendToSmartDash() {

  }



}
