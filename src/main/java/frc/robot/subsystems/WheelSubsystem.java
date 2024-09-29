// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class WheelSubsystem extends SubsystemBase {
    // Thread-safe singleton design pattern.
    private static volatile WheelSubsystem instance;
    private static Object mutex = new Object();

    private CANSparkMax drivingMotor = new CANSparkMax(2, MotorType.kBrushless);
    private CANSparkMax turningMotor = new CANSparkMax(3, MotorType.kBrushless);
    private CANcoder turningEncoder = new CANcoder(10,"swerve");

    public static WheelSubsystem getInstance() {
        WheelSubsystem result = instance;
       
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null) {
                    instance = result = new WheelSubsystem();
                }
            }
        }
        return instance;
    }

    public void setSpeedDrive(double speed)
    {
        this.drivingMotor.set(speed);
    }

    public void setSpeedTurn(double speed){
        this.turningMotor.set(speed);
    }

    /** Creates a new ExampleSubsystem. */
    private WheelSubsystem() {
        super("WheelSubsystem");
    }


    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public double getWheelMotorPosition(){
        return Units.rotationsToDegrees(turningEncoder.
                                        getAbsolutePosition().getValue());
    }
}