// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TankDrive extends SubsystemBase {
  /** Creates a new TankDrive. */

  private CANSparkMax frontLeftMotor;
  private CANSparkMax frontRightMotor;
  private CANSparkMax backLeftMotor;
  private CANSparkMax backRightMotor;

  private RelativeEncoder leftEncoder;
  private RelativeEncoder rightEncoder;

  DifferentialDrive differentialDrive;

  public TankDrive() {
    
    frontLeftMotor = new CANSparkMax(0, MotorType.kBrushless);
    frontRightMotor = new CANSparkMax(1, MotorType.kBrushless);
    leftEncoder = frontLeftMotor.getEncoder();
    rightEncoder = frontRightMotor.getEncoder();

    backLeftMotor = new CANSparkMax(2, MotorType.kBrushless);
    backRightMotor = new CANSparkMax(3, MotorType.kBrushless);
    backLeftMotor.follow(frontLeftMotor);
    backRightMotor.follow(frontRightMotor);

    differentialDrive = new DifferentialDrive(frontLeftMotor, frontRightMotor);

    frontLeftMotor.restoreFactoryDefaults();
    frontRightMotor.restoreFactoryDefaults();
    backLeftMotor.restoreFactoryDefaults();
    backRightMotor.restoreFactoryDefaults();

    frontLeftMotor.setInverted(false);
    frontRightMotor.setInverted(true);

    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("right encoder", getRightEncoderValue());
    SmartDashboard.putNumber("left encoder", getLeftEncoderValue());

  }

  public double getLeftEncoderValue() {
    return leftEncoder.getPosition();
  }
   public double getRightEncoderValue() {
    return rightEncoder.getPosition();
  }

  public void setArcade(double driveValue, double turnValue) {
    differentialDrive.arcadeDrive(driveValue, turnValue);
  }
  public void setTank(double leftSpeed, double rightSpeed) {
    differentialDrive.tankDrive(leftSpeed, rightSpeed);
  }
  public void emergencyPark() {
    differentialDrive.stopMotor();
  }
}
