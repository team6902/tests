/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.TurnToAngle;
import frc.robot.commands.TurnToDefaultAngle;

public class PIDTuningCommand extends SequentialCommandGroup {
  /**
   * Creates a new PIDTuningCommand.
   * 
   * double[] angles: angles to test
   */
  public PIDTuningCommand(double ...angles) {
    super(
      new TurnToDefaultAngle(),
      new TurnToAngle(-90, RobotContainer.m_robotDrive),
      new TurnToAngle(90, RobotContainer.m_robotDrive),
      new TurnToAngle(0, RobotContainer.m_robotDrive),
      new TurnToAngle(170, RobotContainer.m_robotDrive),
      new TurnToAngle(-170, RobotContainer.m_robotDrive)
    );
  }
}
