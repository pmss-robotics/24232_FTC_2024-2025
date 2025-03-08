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

@Autonomous(name="SpecimenAutoFinal", group="Auto")
public class SpecimenAutoFinal extends CommandOpMode {
    DriveSubsystem drive;
    FeedForwardArmSubsystem arm;
    ClawSubsystem claw;

    public void initialize() {
        drive = new DriveSubsystem(new PinpointDrive(hardwareMap, new Pose2d(10,-62,Math.PI/2)), telemetry);
        arm = new FeedForwardArmSubsystem(hardwareMap, telemetry);
        arm.setDefaultCommand(new RunCommand(() -> arm.holdPosition(), arm));

        Action specimen1Action = drive.actionBuilder(drive.getPose())
                .strafeTo(new Vector2d(1.5, -34.5))
                .build();
        Command specimen1Traj = new ActionCommand(specimen1Action, Stream.of(drive).collect(Collectors.toSet()));arm = new FeedForwardArmSubsystem(hardwareMap, telemetry);

        Action pushAction = drive.actionBuilder(new Pose2d(1.5, -34.5, Math.toRadians(90.0)))
                .setTangent(Math.toRadians(-45))
                .splineToConstantHeading(new Vector2d(32.07, -28.01), Math.toRadians(90.00))
                .splineToConstantHeading(new Vector2d(37.74, -19.97), Math.toRadians(90.00))
                .splineToConstantHeading(new Vector2d(44.34, -48.37), Math.toRadians(-69.84))
                .splineToConstantHeading(new Vector2d(49.65, -14.12), Math.toRadians(90.00))
                .splineToConstantHeading(new Vector2d(56.43, -46.17), Math.toRadians(90.00))
                .splineToConstantHeading(new Vector2d(57.89, -15.31), Math.toRadians(90.00))
                .splineToConstantHeading(new Vector2d(63.76, -46.17), Math.toRadians(-74.66))
                .build();
        Command pushTraj = new ActionCommand(pushAction, Stream.of(drive).collect(Collectors.toSet()));

        Action specimen2InAction = drive.actionBuilder(new Pose2d(63.76, -46.17, Math.toRadians(90.0)))
                .splineToLinearHeading(new Pose2d(40.00, -54.00, Math.toRadians(-90.00)), Math.toRadians(194.43))
                .build();
        Command specimen2InTraj = new ActionCommand(specimen2InAction, Stream.of(drive).collect(Collectors.toSet()));

        Action specimen2IntakeAction = drive.actionBuilder(new Pose2d(40.00, -54.00, Math.toRadians(-90.0)))
                .splineToConstantHeading(new Vector2d(40.00, -58.00), Math.toRadians(-90.00))
                .build();
        Command specimen2IntakeTraj = new ActionCommand(specimen2IntakeAction, Stream.of(drive).collect(Collectors.toSet()));

        Action specimen2OutAction = drive.actionBuilder(new Pose2d(40.00, -58.00, Math.toRadians(-90.0)))
                .setTangent(Math.toRadians(125))
                .splineToSplineHeading(new Pose2d(4.03, -34, Math.toRadians(90.00)), Math.toRadians(151.94))
                .build();
        Command specimen2OutTraj = new ActionCommand(specimen2OutAction, Stream.of(drive).collect(Collectors.toSet()));


        //
        /**
         * .splineToLinearHeading(new Pose2d(40.00, -54.00, Math.toRadians(-90.00)), Math.toRadians(0.00))
         * .splineToConstantHeading(new Vector2d(40.00, -56.00), Math.toRadians(90.00)) // intake 3
         * .splineToSplineHeading(new Pose2d(7.05, -37.00, Math.toRadians(90.00)), Math.toRadians(150.78)) //outtake 3
         * .splineToLinearHeading(new Pose2d(40.00, -54.00, Math.toRadians(-90.00)), Math.toRadians(-26.65))
         * .splineToConstantHeading(new Vector2d(40.00, -56.00), Math.toRadians(90.00))// intake 4
         * .splineToSplineHeading(new Pose2d(-1.65, -37.00, Math.toRadians(90.00)), Math.toRadians(90.00)) //outtake 4
         */
        Action specimen3InAction = drive.actionBuilder(new Pose2d(4.03, -34.5, Math.toRadians(90.00)))
                .setTangent(Math.toRadians(-40))
                .splineToLinearHeading(new Pose2d(40.00, -54.00, Math.toRadians(-90.00)), Math.toRadians(90.00)) //0
                .build();
        Command specimen3InTraj = new ActionCommand(specimen3InAction, Stream.of(drive).collect(Collectors.toSet()));

        Action specimen3IntakeAction = drive.actionBuilder(new Pose2d(40.00, -54.00, Math.toRadians(-90.0)))
                .splineToConstantHeading(new Vector2d(40.00, -58.00), Math.toRadians(-90.00))
                .build();
        Command specimen3IntakeTraj = new ActionCommand(specimen3IntakeAction, Stream.of(drive).collect(Collectors.toSet()));

        Action specimen3OutAction = drive.actionBuilder(new Pose2d(40.00, -58.00, Math.toRadians(-90.00)))
                .setTangent(Math.toRadians(120))
                .splineToSplineHeading(new Pose2d(7.05, -34.5, Math.toRadians(90.00)), Math.toRadians(150.78))
                .build();
        Command specimen3OutTraj = new ActionCommand(specimen3OutAction, Stream.of(drive).collect(Collectors.toSet()));

        Action specimen4InAction = drive.actionBuilder(new Pose2d(7.05, -34.5, Math.toRadians(90.00)))
                .setTangent(Math.toRadians(-50))
                .splineToLinearHeading(new Pose2d(40.00, -54.00, Math.toRadians(-90.00)), Math.toRadians(-90.00)) //-26.65
                .build();
        Command specimen4InTraj = new ActionCommand(specimen4InAction, Stream.of(drive).collect(Collectors.toSet()));

        Action specimen4IntakeAction = drive.actionBuilder(new Pose2d(40.00, -54.00, Math.toRadians(-90.0)))
                .splineToConstantHeading(new Vector2d(40.00, -58.00), Math.toRadians(-90.00))
                .build();
        Command specimen4IntakeTraj = new ActionCommand(specimen4IntakeAction, Stream.of(drive).collect(Collectors.toSet()));

        Action specimen4OutAction = drive.actionBuilder(new Pose2d(40.00, -58.00, Math.toRadians(-90.00)))
                .setTangent(130)
                .splineToSplineHeading(new Pose2d(-2.38, -34, Math.toRadians(90.00)), Math.toRadians(154.54))
                .build();
        Command specimen4OutTraj = new ActionCommand(specimen4OutAction, Stream.of(drive).collect(Collectors.toSet()));

        Action specimen5InAction = drive.actionBuilder(drive.getPose())
                .setTangent(-45)
                .splineToSplineHeading(new Pose2d(-40, 46.84, Math.toRadians(90.00)), Math.toRadians(154.18))
                .splineToConstantHeading(new Vector2d(-44.32, 55.20), Math.toRadians(52.59))
                .build();
        Command specimen5InTraj = new ActionCommand(specimen5InAction, Stream.of(drive).collect(Collectors.toSet()));

        Action specimen5OutAction = drive.actionBuilder(drive.getPose())
                .setTangent(0)
                .splineToSplineHeading(new Pose2d(-12.00, 34.24, Math.toRadians(-90.00)), Math.toRadians(-34.59))
                .build();
        Command specimen5OutTraj = new ActionCommand(specimen5OutAction, Stream.of(drive).collect(Collectors.toSet()));

        int specInTime = 100;
        int specInTime2 = 1000;
        int specOutTime = 100;

        waitForStart();

        Command auto = new SequentialCommandGroup(
                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenOut1)),
                specimen1Traj,
                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenOut2)),
                new WaitCommand(specOutTime),

                pushTraj,
                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenIn1)),
                specimen2InTraj,
                new WaitCommand(specInTime2),
                specimen2IntakeTraj,
                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenOut1)),
                new WaitCommand(specInTime),
                specimen2OutTraj,
                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenOut2)),
                new WaitCommand(specOutTime),

                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenIn1)),
                specimen3InTraj,
                new WaitCommand(specInTime2),
                specimen3IntakeTraj,
                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenOut1)),
                new WaitCommand(specInTime),
                specimen3OutTraj,
                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenOut2)),
                new WaitCommand(specOutTime),

                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenIn1)),
                specimen4InTraj,
                new WaitCommand(specInTime2),
                specimen4IntakeTraj,
                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenOut1)),
                new WaitCommand(specInTime),
                specimen4OutTraj,
                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenOut2)),
                new WaitCommand(specOutTime)



//                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenOut2)),
//                new WaitCommand(specOutTime)

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