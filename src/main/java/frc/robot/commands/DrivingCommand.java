// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.WheelSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that does nothing. */
public class DrivingCommand extends Command {
    
    private double speed;

    public DrivingCommand(double speed) {
        setName("DrivingCommand");
        this.speed = speed;
        addRequirements(WheelSubsystem.getInstance());
    }


    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        WheelSubsystem.getInstance().setSpeedDrive(speed);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

    }


    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        WheelSubsystem.getInstance().setSpeedDrive(0);
    }


    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}

