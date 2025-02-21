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
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.ActionCommand;
import org.firstinspires.ftc.teamcode.drive.PinpointDrive;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.FeedForwardArmSubsystem;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Autonomous(name="SpecimenAuto1", group="Auto")
public class SpecimenAuto1 extends CommandOpMode {
    DriveSubsystem drive;
    FeedForwardArmSubsystem arm;
    ClawSubsystem claw;

    public void initialize() {
        drive = new DriveSubsystem(new PinpointDrive(hardwareMap, new Pose2d(-10,60,-Math.PI/2)), telemetry);
        arm = new FeedForwardArmSubsystem(hardwareMap, telemetry);
        claw = new ClawSubsystem(hardwareMap, telemetry);
        arm.setDefaultCommand(new RunCommand(() -> arm.holdPosition(), arm));
        Action specimen1Action = drive.actionBuilder(drive.getPose())
                .waitSeconds(0.5)
                .strafeTo(new Vector2d(0, 32))
                .build();
        Command specimen1Traj = new ActionCommand(specimen1Action, Stream.of(drive).collect(Collectors.toSet()));arm = new FeedForwardArmSubsystem(hardwareMap, telemetry);

        Action pushAction = drive.actionBuilder(drive.getPose())
                //.setTangent(Math.toRadians(120))
                //.splineTo(new Vector2d(-25.76, 37.60), Math.toRadians(209.44))
                .setTangent(Math.toRadians(120))
                .splineToSplineHeading(new Pose2d(-37.76, 9.44, Math.toRadians(90.00)), Math.toRadians(-90.00))
//                .splineToConstantHeading(new Vector2d(-47.20, 47.04), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(-44.32, 13.12), Math.toRadians(253.43))+
//                .splineToConstantHeading(new Vector2d(-55.84, 47.20), Math.toRadians(91.13))
//                .splineToConstantHeading(new Vector2d(-57.44, 13.44), Math.toRadians(259.86))
//                .splineToConstantHeading(new Vector2d(-66.72, 49.60), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(-44.16, 48.16), Math.toRadians(6.43))
//                .splineToConstantHeading(new Vector2d(-44.16, 55.04), Math.toRadians(88.00))
                .build();
        Command pushTraj = new ActionCommand(pushAction, Stream.of(drive).collect(Collectors.toSet()));

        Action specimen2OutAction = drive.actionBuilder(drive.getPose())
                .setTangent(0)
                .splineToSplineHeading(new Pose2d(-3.20, 34.56, Math.toRadians(-90.00)), Math.toRadians(0.00))
                .build();
        Command specimen2OutTraj = new ActionCommand(specimen2OutAction, Stream.of(drive).collect(Collectors.toSet()));

        Action specimen3InAction = drive.actionBuilder(drive.getPose())
                .setTangent(90)
                .splineToSplineHeading(new Pose2d(-44.16, 46.32, Math.toRadians(90.00)), Math.toRadians(154.96))
                .splineToConstantHeading(new Vector2d(-44.16, 55.68), Math.toRadians(73.01))
                .build();
        Command specimen3InTraj = new ActionCommand(specimen3InAction, Stream.of(drive).collect(Collectors.toSet()));

        Action specimen3OutAction = drive.actionBuilder(drive.getPose())
                .setTangent(0)
                .splineToSplineHeading(new Pose2d(-6.56, 34.56, Math.toRadians(-90.00)), Math.toRadians(0.00))
                .build();
        Command specimen3OutTraj = new ActionCommand(specimen3OutAction, Stream.of(drive).collect(Collectors.toSet()));

        Action specimen4InAction = drive.actionBuilder(drive.getPose())
                .setTangent(90)
                .splineToSplineHeading(new Pose2d(-44.16, 46.32, Math.toRadians(90.00)), Math.toRadians(160.04))
                .splineToConstantHeading(new Vector2d(-44.32, 55.20), Math.toRadians(70.25))
                .build();
        Command specimen4InTraj = new ActionCommand(specimen4InAction, Stream.of(drive).collect(Collectors.toSet()));

        Action specimen4OutAction = drive.actionBuilder(drive.getPose())
                .setTangent(0)
                .splineToSplineHeading(new Pose2d(-9.60, 34.40, Math.toRadians(-90.00)), Math.toRadians(-30.96))
                .build();
        Command specimen4OutTraj = new ActionCommand(specimen4OutAction, Stream.of(drive).collect(Collectors.toSet()));

        Action specimen5InAction = drive.actionBuilder(drive.getPose())
                .setTangent(90)
                .splineToSplineHeading(new Pose2d(-43.84, 46.84, Math.toRadians(90.00)), Math.toRadians(154.18))
                .splineToConstantHeading(new Vector2d(-44.32, 55.20), Math.toRadians(52.59))
                .build();
        Command specimen5InTraj = new ActionCommand(specimen5InAction, Stream.of(drive).collect(Collectors.toSet()));

        Action specimen5OutAction = drive.actionBuilder(drive.getPose())
                .setTangent(0)
                .splineToSplineHeading(new Pose2d(-12.00, 34.24, Math.toRadians(-90.00)), Math.toRadians(-34.59))
                .build();
        Command specimen5OutTraj = new ActionCommand(specimen5OutAction, Stream.of(drive).collect(Collectors.toSet()));

        int specInTime = 300;
        int specOutTime = 100;

        Command auto = new SequentialCommandGroup(
                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenOut1)),
                specimen1Traj,
                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenIn1)),
                new WaitCommand(specOutTime),

                pushTraj//,
//                new WaitCommand(specInTime),
//                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenOut1)),
//                specimen2OutTraj,
//                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenIn1)),
//                new WaitCommand(specOutTime),
//
//                specimen3InTraj,
//                new WaitCommand(specInTime),
//                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenOut1)),
//                specimen3OutTraj,
//                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenIn1)),
//                new WaitCommand(specOutTime),
//
//                specimen4InTraj,
//                new WaitCommand(specInTime),
//                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenOut1)),
//                specimen4OutTraj,
//                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenIn1)),
//                new WaitCommand(specOutTime),
//
//                specimen5InTraj,
//                new WaitCommand(specInTime),
//                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenOut1)),
//                specimen5OutTraj,
//                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenIn1))
        );
        schedule(auto);

    }

}
