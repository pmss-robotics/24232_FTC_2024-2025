package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.SampleSubsystem;

import java.util.function.DoubleSupplier;

public class IntakeCommand extends CommandBase {
    private final Intake intake;
    boolean rb, lb;
    DoubleSupplier ry;
    public IntakeCommand(Intake intake, boolean rb, boolean lb, DoubleSupplier ry) {
        this.rb = rb;
        this.lb = lb;
        this.ry = ry;
        this.intake = intake;
        // instantiate other parameters if there are
        addRequirements(intake);
    }


    @Override
    public void execute() {
        // runs repeatedly until the command is finished or interrupted
        intake.spin(ry.getAsDouble());
        if(rb){
            intake.tilt(true);
        } else if(lb){
            intake.tilt(false);
        }
    }



}
