// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/** An example subsystem that does nothing. */
public class WheelSubsystem extends SubsystemBase {
    // Use Bill Pugh Singleton Pattern for efficient lazy initialization
    // (thread-safe !)
    String CAN_BUS_NAME = "ctre";
    private TalonFX drivingMotor = new TalonFX(10, CAN_BUS_NAME);
    private TalonFX turningMotor = new TalonFX(9, CAN_BUS_NAME);
    private CANcoder turningEncoder = new CANcoder(3, CAN_BUS_NAME);

    private static class WheelSubsystemHolder {
        private static final WheelSubsystem INSTANCE = new WheelSubsystem();
    }

    /** Always use this method to get the singleton instance of this subsystem. */
    public static WheelSubsystem getInstance() {
        return WheelSubsystemHolder.INSTANCE;
    }

    private WheelSubsystem() {
        super("WheelSubsystem");
    }

    @Override
    public void periodic() {
    }

    /*
     * Sets the speed of the motor.
     * 
     * @param speed Sets speed between 1.0 and -1.0
     */
    public void setDrivingspeed(double speed) {
        drivingMotor.set(speed);
    }

    /*
     * Sets the speed of the motor.
     * 
     * @param speed Sets speed between 1.0 and -1.0
     */
    public void setTurningSpeed(double speed) {
        turningMotor.set(speed);
    }

    public double getTurningPosition() {
        return Units.radiansToDegrees(turningEncoder.getAbsolutePosition().getValueAsDouble());

    }

}
