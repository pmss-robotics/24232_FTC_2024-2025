package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSlides;
import org.firstinspires.ftc.teamcode.subsystems.OuttakeSlides;

import java.util.function.DoubleSupplier;

public class PowerOuttakeCommand extends CommandBase {
    private final OuttakeSlides outtakeSlides;
    DoubleSupplier lt, rt;
    public PowerOuttakeCommand(OuttakeSlides outtakeSlides, DoubleSupplier lt, DoubleSupplier rt) {
        this.outtakeSlides = outtakeSlides;
        // instantiate other parameters if there are
        addRequirements(outtakeSlides);
    }


    @Override
    public void execute() {
        outtakeSlides.setPower(rt.getAsDouble() - lt.getAsDouble());
    }
}
