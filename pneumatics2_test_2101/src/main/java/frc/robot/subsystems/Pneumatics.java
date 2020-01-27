/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Pneumatics extends SubsystemBase {

  private Compressor compressor;
  private AnalogInput analogInput;

  /**
   * Creates a new ExampleSubsystem.
   */
  public Pneumatics() {

  }

  public void updateCompressorStatus() {
    int pressure = 250 * (analogInput.getAverageValue() / Constants.VCC) - 25;
    SmartDashboard.putNumber("pressure", pressure);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
