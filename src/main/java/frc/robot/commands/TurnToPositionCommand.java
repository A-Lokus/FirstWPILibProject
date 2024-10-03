package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.WheelSubsystem;

public class TurnToPositionCommand extends Command{
    private PIDController pidController;
    private double goalPosition;

    public TurnToPositionCommand(double goalPosition){
        this.goalPosition = goalPosition;
        
        this.pidController = new PIDController(1,0,0);
        this.pidController.setTolerance(0.05);
        this.pidController.enableContinuousInput(0,360);
        addRequirements(WheelSubsystem.getInstance());
    }

    public void initialize(){
        this.pidController.reset();
    }

    public void execute(){
        double currentPosition = WheelSubsystem.getInstance().getWheelMotorPosition();
        double speed = this.pidController.calculate(currentPosition, this.goalPosition);

        WheelSubsystem.getInstance().setSpeedDrive(speed);
    }

    public void end(boolean interrupted){
        WheelSubsystem.getInstance().setSpeedDrive(0);
    }

    public boolean isFinished(){
        return this.pidController.atSetpoint();
    }

}