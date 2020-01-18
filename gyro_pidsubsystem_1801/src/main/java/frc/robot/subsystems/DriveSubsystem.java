/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.kauailabs.navx.frc.AHRS;

import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {

  private PIDController controller;
  private final SpeedController m_leftMotors = new Spark(DriveConstants.kLeftMotorPort);
  private final SpeedController m_rightMotors = new Spark(DriveConstants.kRightMotorPort);
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);
  private final AHRS m_gyro = new AHRS(I2C.Port.kOnboard);

  public DriveSubsystem() {
    this.controller = new PIDController(Constants.DriveConstants.kTurnP, Constants.DriveConstants.kTurnI,
        Constants.DriveConstants.kTurnD);
    this.controller.setTolerance(Constants.DriveConstants.kTurnToleranceDeg);
    this.controller.
  }

  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, rot);
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    m_drive.tankDrive(leftSpeed, rightSpeed, true);
  }

  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
  }

  @Override
  protected void useOutput(double output, double setpoint) {
    this.m_drive.arcadeDrive(0, output);
  }

  @Override
  protected double getMeasurement() {
    return this.m_gyro.pidGet();
  }

}
