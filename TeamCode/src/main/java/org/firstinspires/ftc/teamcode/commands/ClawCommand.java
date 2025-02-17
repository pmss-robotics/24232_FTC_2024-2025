package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.FeedForwardArmSubsystem;

import java.util.function.DoubleSupplier;

public class ClawCommand extends CommandBase {
    private final ClawSubsystem clawSubsystem;
    private final DoubleSupplier lt, rt;
    public ClawCommand(ClawSubsystem clawSubsystem, DoubleSupplier lt, DoubleSupplier rt) {
        this.clawSubsystem = clawSubsystem;
        this.lt = lt;
        this.rt = rt;
        addRequirements(clawSubsystem);
    }

    @Override
    public void execute() {
        clawSubsystem.setClawPosition(lt.getAsDouble()); //TODO: CHANGE
        clawSubsystem.setClawPosition(rt.getAsDouble());
    }
}
