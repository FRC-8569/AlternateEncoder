//install url
//https://software-metadata.revrobotics.com/REVLib-2023.json

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAlternateEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
  private Joystick joystick;
  private CANSparkMax motor1;
  private RelativeEncoder encoder1;

  @Override
  public void robotInit() {
    motor1 = new CANSparkMax(1, MotorType.kBrushless);
    motor1.restoreFactoryDefaults();
    encoder1 = motor1.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, 8192);
    encoder1.setPosition(0);

    joystick = new Joystick(0);
  }

  @Override
  public void teleopPeriodic() {
    double motorPos1 = encoder1.getPosition();
    double motorSpd1 = encoder1.getVelocity();

    if (joystick.getRawButton(0)) {
      motor1.set(0.3);
    } else if (joystick.getRawButton(1)) {
      motor1.set(-0.3);
    } else {
      motor1.set(0);
    }

    System.out.println("motorPos1: " + motorPos1 + ", motorSpd1: " + motorSpd1);

    SmartDashboard.putNumber("motorPos1", motorPos1);
    SmartDashboard.putNumber("motorSpd1", motorSpd1);
  }
}