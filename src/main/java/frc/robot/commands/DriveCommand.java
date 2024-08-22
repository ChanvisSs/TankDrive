// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.TankDrive;

public class DriveCommand extends Command {
  
  private Constants constants = new Constants();

  private TankDrive tankDrive;
  private Joystick joystick;

  /** Creates a new DriveCommand. */
  public DriveCommand(TankDrive tankDrive, Joystick joystick) {
    this.tankDrive = tankDrive;
    this.joystick = joystick;

    // Use addRequirements() here to declare subsystem dependencies.

    this.addRequirements(tankDrive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    tankDrive.setArcade(joystick.getRawAxis(constants.driveAxis), joystick.getRawAxis(constants.turnAxis));

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
