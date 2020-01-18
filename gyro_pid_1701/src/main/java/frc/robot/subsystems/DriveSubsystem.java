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
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.kauailabs.navx.frc.AHRS;

import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
  AHRS ahrs;
  // The motors on the left side of the drive.
  private final SpeedController m_leftMotors = new Spark(DriveConstants.kLeftMotorPort);
  // The motors on the right side of the drive.
  private final SpeedController m_rightMotors = new Spark(DriveConstants.kRightMotorPort);

  // The robot's drive
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

  // The gyro sensor
  private final AHRS m_gyro = new AHRS(I2C.Port.kOnboard);

  /**
   * Creates a new DriveSubsystem.
   */
  public DriveSubsystem() {
  }

  /**
   * Creates a new DriveSubsystem with maximum output.
   */
  public DriveSubsystem(double maxOutput) {
    this.setMaxOutput(maxOutput);
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   */
  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, rot);
  }

  /**
   * Drives the robot using tank controls.
   *
   * @param leftSpeed the commanded leftSpeed
   * @param rightSpeed the commanded rigthSpeed
   */
  public void tankDrive(double leftSpeed, double rightSpeed) {
    m_drive.tankDrive(leftSpeed, rightSpeed, true);
  }

  /**
   * Sets the max output of the drive. Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
  }

  /**
   * Zeroes the heading of the robot.
   */
  public void zeroHeading() {
    m_gyro.reset();
  }

  /**
   * Returns the heading of the robot.
   *
   * @return the robot's heading in degrees, from -180 to 180
   */
  // public double getHeading() {
  //   return Math.IEEEremainder(m_gyro.getAngle(), 360) * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
  // }
  public double getHeading() {
    return m_gyro.pidGet();
  }

  /**
   * Returns the turn rate of the robot.
   *
   * @return The turn rate of the robot, in degrees per second
   */
  // public double getTurnRate() {
  //   return m_gyro.getRate() * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
  // }
}
