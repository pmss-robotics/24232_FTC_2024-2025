package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.FeedForwardArmSubsystem;

import java.util.function.DoubleSupplier;

public class ArmCommand extends CommandBase {
    private final FeedForwardArmSubsystem feedforwardArmSubsystem;
    private final DoubleSupplier lt, rt;
    public ArmCommand(FeedForwardArmSubsystem feedforwardArmSubsystem, DoubleSupplier lt, DoubleSupplier rt) {
        this.feedforwardArmSubsystem = feedforwardArmSubsystem;
        this.lt = lt;
        this.rt = rt;
        addRequirements(feedforwardArmSubsystem);
    }
    @Override
    public void execute() {
        if (lt.getAsDouble() == 0 && rt.getAsDouble() == 0){
            feedforwardArmSubsystem.holdPosition();
        } else if (lt.getAsDouble() == 0) {
            feedforwardArmSubsystem.shoulderHoldPosition();
        } else if (rt.getAsDouble() == 0) {
            feedforwardArmSubsystem.elbowHoldPosition();
        } else {
            feedforwardArmSubsystem.shoulderMoveTo(lt.getAsDouble());
            feedforwardArmSubsystem.elbowMoveTo(rt.getAsDouble());
        }
    }

}