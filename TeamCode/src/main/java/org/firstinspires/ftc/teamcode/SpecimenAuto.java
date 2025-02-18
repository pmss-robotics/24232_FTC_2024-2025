package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.commands.ActionCommand;
import org.firstinspires.ftc.teamcode.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.FeedForwardArmSubsystem;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SpecimenAuto extends CommandOpMode {
    DriveSubsystem drive;
    FeedForwardArmSubsystem arm;
    ClawSubsystem claw;

    public void initialize() {
        drive = new DriveSubsystem(new MecanumDrive(hardwareMap, new Pose2d(-10,60,-Math.PI/2)), telemetry);
        arm = new FeedForwardArmSubsystem(hardwareMap, telemetry);
        claw = new ClawSubsystem(hardwareMap, telemetry);

        arm.setDefaultCommand(new RunCommand(() -> arm.holdPosition(), arm));

        Action startSpecimenAction = drive.actionBuilder(drive.getPose())
                .strafeTo(new Vector2d(0, 32))
                .build();
        Command startSpecimen = new ActionCommand(startSpecimenAction, Stream.of(drive).collect(Collectors.toSet()));arm = new FeedForwardArmSubsystem(hardwareMap, telemetry);


        Action specimenTrajectoryAction = drive.actionBuilder(drive.getPose())
                .build();
        Command specimenTrajectory = new ActionCommand(specimenTrajectoryAction, Stream.of(drive).collect(Collectors.toSet()));arm = new FeedForwardArmSubsystem(hardwareMap, telemetry);


        Command auto = new SequentialCommandGroup(
                startSpecimen,
                new InstantCommand(() ->{
                    claw.clawOpen();
                    arm.shoulderMoveTo(arm.ps_SubmersibleIn);
                    arm.elbowMoveTo(arm.pe_SubmersibleIn);
                }), //can do things while driving; no need for wait
                new WaitCommand(1000),
                specimenTrajectory,
                new InstantCommand(() -> {arm.shoulderMoveTo(arm.ps_SubmersibleIn);
                })
        );

    }

}
