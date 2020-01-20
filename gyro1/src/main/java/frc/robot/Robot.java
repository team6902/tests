/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static Timer m_timer = new Timer();

  private Spark motorsLeft = new Spark(9);
  private Spark motorsRight = new Spark(0);
  private DifferentialDrive drive = new DifferentialDrive(motorsLeft, motorsRight);

  private static final double kTurnP = .0045;
  private static final double kTurnI = .006;
  private static final double kTurnD = .0001;
  private double setpoint = 0.0;

  private final AHRS m_gyro = new AHRS(I2C.Port.kOnboard);
  private final PIDController m_pidTurnController = new PIDController(kTurnP, kTurnI, kTurnD);
  
  public final Joystick stick1 = new Joystick(0);
  public final Joystick stick2 = new Joystick(0);

  private double zRotation = 0.0;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    stick2.setYChannel(5);
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    m_timer.start();
  }

  @Override
  public void teleopPeriodic() {

    if (stick1.getRawButton(1)) {
      m_pidTurnController.setSetpoint(0);
      setpoint = 0;
    } 
    if (stick1.getRawButton(2)) {
      m_pidTurnController.setSetpoint(90);
      setpoint = 90;
    } 
    if (stick1.getRawButton(3)) {
      m_pidTurnController.setSetpoint(-90);
      setpoint = -90;
    } 
    if (stick1.getRawButton(4)) {
      m_pidTurnController.setSetpoint(170);
      setpoint = 170;
    } 
    if (stick1.getRawButton(5)) {
      m_pidTurnController.setSetpoint(-170);
      setpoint = -170;
    } 

    if (stick1.getRawButton(8)) {
      double pidOutput = m_pidTurnController.calculate(m_gyro.pidGet());
      zRotation = pidOutput;
      drive.arcadeDrive(0.0, zRotation);
    } else {
      drive.tankDrive(stick1.getY(), stick2.getY(), true);
      zRotation = 0;
    }
    log();
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Angle", m_gyro.getYaw());
    SmartDashboard.putNumber("Turning Value", zRotation);
    SmartDashboard.putNumber("SetPoint", setpoint);
  }

  private void log() {
    System.out.printf("%f,%f,%f,%f,%f,%f,%f,%b\n", 
                      Robot.m_timer.get(),
                      this.m_pidTurnController.getP(),
                      this.m_pidTurnController.getI(),
                      this.m_pidTurnController.getD(),
                      this.m_gyro.pidGet(),
                      this.zRotation,
                      this.m_pidTurnController.getSetpoint(),
                      this.m_pidTurnController.atSetpoint());
  }

}
