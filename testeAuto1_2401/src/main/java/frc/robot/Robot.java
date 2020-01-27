/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  Compressor m_compressor = new Compressor();
  DoubleSolenoid m_solenoid = new DoubleSolenoid(0, 1);
  Timer m_timer = new Timer();


  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    m_compressor.start();
  }

  @Override
  public void autonomousInit() {
    m_timer.start();
  }

  @Override
  public void autonomousPeriodic() {
    System.out.println(m_timer.get());
      m_solenoid.set(DoubleSolenoid.Value.kForward);
    if (m_timer.get() > 2)
      m_solenoid.set(DoubleSolenoid.Value.kReverse);
    if (m_timer.get() > 4)
      m_solenoid.set(DoubleSolenoid.Value.kForward);
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
