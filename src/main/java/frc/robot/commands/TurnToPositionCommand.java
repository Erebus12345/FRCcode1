// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.WheelSubsystem;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class TurnToPositionCommand extends Command {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private double speed;
  private PIDController pid;
  private double goalPosition;

  /**
   * Creates a new DrivingCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public TurnToPositionCommand(double goalPosition) {
    setName("TurnToPositionCommand");
    this.goalPosition = goalPosition;
    this.pid = new PIDController(1, 0, 0);
    this.pid.setTolerance(Units.degreesToRadians(1));
    this.pid.enableContinuousInput(0, 360);

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(WheelSubsystem.getInstance());
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.pid.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double currentPosition = WheelSubsystem.getInstance().getTurningPosition();
    double speed = pid.calculate(currentPosition, this.goalPosition);
    WheelSubsystem.getInstance().setTurningSpeed(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    WheelSubsystem.getInstance().setTurningSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return this.pid.atSetpoint();
  }
}
