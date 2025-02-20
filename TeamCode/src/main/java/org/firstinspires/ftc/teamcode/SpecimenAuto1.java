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

    public static double specimenIntakeWaitTime = 1.0;
    public static double specimenOuttakeWaitTime = 2.0;
    public static double submersibleIntakeWaitTime = 2.0;

    public void initialize() {
        drive = new DriveSubsystem(new PinpointDrive(hardwareMap, new Pose2d(-10,60,-Math.PI/2)), telemetry);
        arm = new FeedForwardArmSubsystem(hardwareMap, telemetry);
        claw = new ClawSubsystem(hardwareMap, telemetry);

        arm.setDefaultCommand(new RunCommand(() -> arm.holdPosition(), arm));

        Action startSpecimenAction3 = drive.actionBuilder(drive.getPose())
                .strafeTo(new Vector2d(0, 32))
                .waitSeconds(specimenOuttakeWaitTime)
                .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(80)), Math.PI)
                .splineToLinearHeading(new Pose2d(-53, -53, Math.toRadians(45)), Math.PI)
                .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(105)), Math.PI)
                .waitSeconds(specimenIntakeWaitTime)
                .splineToLinearHeading(new Pose2d(-53, -53, Math.toRadians(45)), Math.PI)
                .waitSeconds(specimenOuttakeWaitTime)
                .splineToLinearHeading(new Pose2d(-53, -51, Math.toRadians(120)), Math.PI)
                .waitSeconds(specimenOuttakeWaitTime)
                .splineToLinearHeading(new Pose2d(-53, -53, Math.toRadians(45)), Math.PI)
                .waitSeconds(specimenOuttakeWaitTime)
                .splineToLinearHeading(new Pose2d(-24, -10, Math.PI), Math.toRadians(0))
                .waitSeconds(submersibleIntakeWaitTime)
                .build();
        //Command startSpecimen3 = new ActionCommand(startSpecimenAction, Stream.of(drive).collect(Collectors.toSet()));arm = new FeedForwardArmSubsystem(hardwareMap, telemetry);


        Action specimen1Action = drive.actionBuilder(drive.getPose())
                .strafeTo(new Vector2d(0, 32))
                .waitSeconds(specimenOuttakeWaitTime)
                .build();
        Command specimen1Traj = new ActionCommand(specimen1Action, Stream.of(drive).collect(Collectors.toSet()));arm = new FeedForwardArmSubsystem(hardwareMap, telemetry);

        Action driveTrajectoryAction = drive.actionBuilder(drive.getPose())
                .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(80)), Math.PI)
                .splineToLinearHeading(new Pose2d(-53, -53, Math.toRadians(45)), Math.PI)
                .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(105)), Math.PI)
                .build();
        Command driveTraj = new ActionCommand(driveTrajectoryAction, Stream.of(drive).collect(Collectors.toSet()));



        Action specimen2Action = drive.actionBuilder(drive.getPose())
                .splineToLinearHeading(new Pose2d(-53, -53, Math.toRadians(45)), Math.PI)
                .build();
        Command specimen2Traj = new ActionCommand(specimen2Action, Stream.of(drive).collect(Collectors.toSet()));arm = new FeedForwardArmSubsystem(hardwareMap, telemetry);


        /*Command auto = new SequentialCommandGroup(
                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenOut1)),
                specimen1Traj,
                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenOut2)), //can do things while driving; no need for wait
                driveTraj,
                specimenIntakePrep


        );*/

    }

}
