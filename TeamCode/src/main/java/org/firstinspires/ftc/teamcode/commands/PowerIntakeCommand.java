package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSlides;
import org.firstinspires.ftc.teamcode.subsystems.SampleSubsystem;

import java.util.function.DoubleSupplier;

public class PowerIntakeCommand extends CommandBase {
    private final IntakeSlides intakeSlides;
    DoubleSupplier lt, rt;
    public PowerIntakeCommand(IntakeSlides intakeSlides, DoubleSupplier lt, DoubleSupplier rt) {
        this.intakeSlides = intakeSlides;
        // instantiate other parameters if there are
        addRequirements(intakeSlides);
    }


    @Override
    public void execute() {
        intakeSlides.setPower(rt.getAsDouble() - lt.getAsDouble());
    }
}
