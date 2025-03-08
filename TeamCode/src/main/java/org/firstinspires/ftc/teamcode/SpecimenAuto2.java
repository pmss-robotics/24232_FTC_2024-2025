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

@Autonomous(name="SpecimenAuto", group="Auto")
public class SpecimenAuto2 extends CommandOpMode {

    DriveSubsystem drive;
    FeedForwardArmSubsystem arm;
    ClawSubsystem claw;

    public void initialize() {
        drive = new DriveSubsystem(new PinpointDrive(hardwareMap, new Pose2d(12,-63,Math.PI/2)), telemetry);
        arm = new FeedForwardArmSubsystem(hardwareMap, telemetry);
        claw = new ClawSubsystem(hardwareMap, telemetry);
        arm.setDefaultCommand(new RunCommand(() -> arm.holdPosition(), arm));
        Action specimen1Action = drive.actionBuilder(drive.getPose())
                .splineToConstantHeading(new Vector2d(2.75, -32.06), Math.toRadians(112.70))
                .build();
        Command specimen1Traj = new ActionCommand(specimen1Action, Stream.of(drive).collect(Collectors.toSet()));

        Action pushAction = drive.actionBuilder(new Pose2d(8.24, -33.50, Math.toRadians(90.0)))
                .splineToConstantHeading(new Vector2d(35.75, -35.06), Math.toRadians(-45)) // push back a bit
                .splineToConstantHeading(new Vector2d(35.75, -20.06), Math.toRadians(90)) // move forward
                .splineToConstantHeading(new Vector2d(40.75, -35.06), Math.toRadians(-65)) // push back 1

//                .setTangent(Math.toRadians(-45))
//                .splineToConstantHeading(new Vector2d(36.27, -26.20), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(38.84, -20.34), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(52.40, -48.55), Math.toRadians(-75.37))
//                .splineToConstantHeading(new Vector2d(46.17, -20.52), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(60.46, -48.18), Math.toRadians(270.00))
//                .splineToConstantHeading(new Vector2d(55.15, -20.34), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(69.24, -48.50), Math.toRadians(270.00))
//                .splineToSplineHeading(new Pose2d(40.00, -54.50, Math.toRadians(-90.00)), Math.toRadians(-90.00))
//                .splineToSplineHeading(new Pose2d(40.00, -64.50, Math.toRadians(90.00)), Math.toRadians(-90.00))
                .build();
        Command pushTraj = new ActionCommand(pushAction, Stream.of(drive).collect(Collectors.toSet()));

        Action specimen2InAction = drive.actionBuilder(new Pose2d(2.75, -32.06, Math.toRadians(90)))
                .splineToSplineHeading(new Pose2d(42.32, -55.33, Math.toRadians(-90.00)), Math.toRadians(-33.21))
                .splineToConstantHeading(new Vector2d(42.14, -64.85), Math.toRadians(-88.26))
                .build();
        Command specimen2In = new ActionCommand(specimen2InAction, Stream.of(drive).collect(Collectors.toSet()));

        Action specimen2OutAction = drive.actionBuilder(new Pose2d(40.00, -64.50, Math.toRadians(90.00)))
                .splineToSplineHeading(new Pose2d(7.33, -32.24, Math.toRadians(90.00)), Math.toRadians(136.87))
                .build();
        Command specimen2OutTraj = new ActionCommand(specimen2OutAction, Stream.of(drive).collect(Collectors.toSet()));

        Action specimen3InAction = drive.actionBuilder(new Pose2d(-2.56, -37.50, Math.PI/2))
                .setTangent(Math.toRadians(-45))
                .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(-45.00))
                .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
                .build();
        Command specimen3InTraj = new ActionCommand(specimen3InAction, Stream.of(drive).collect(Collectors.toSet()));

        Action specimen3OutAction = drive.actionBuilder(new Pose2d(40.00, -61.00, -Math.PI/2))
                .setTangent(Math.toRadians(125))
                .splineToSplineHeading(new Pose2d(0.73, -37.50, Math.toRadians(90.00)), Math.toRadians(139.26))
                .build();
        Command specimen3OutTraj = new ActionCommand(specimen3OutAction, Stream.of(drive).collect(Collectors.toSet()));

        Action specimen4InAction = drive.actionBuilder(new Pose2d(0.73, -37.50, Math.PI/2))
                .setTangent(Math.toRadians(-45))
                .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(-45.00))
                .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
                .build();
        Command specimen4InTraj = new ActionCommand(specimen4InAction, Stream.of(drive).collect(Collectors.toSet()));

        Action specimen4OutAction = drive.actionBuilder(new Pose2d(40.00, -61.00, -Math.PI/2))
                .setTangent(Math.toRadians(125))
                .splineToSplineHeading(new Pose2d(3.48, -37.50, Math.toRadians(90.00)), Math.toRadians(90.00))
                .build();
        Command specimen4OutTraj = new ActionCommand(specimen4OutAction, Stream.of(drive).collect(Collectors.toSet()));

        /*Action specimen5InAction = drive.actionBuilder(new Pose2d(3.48, -37.50, Math.PI))
                .setTangent(Math.toRadians(-45))
                .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(-45.00))
                .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
                .build();
        Command specimen5InTraj = new ActionCommand(specimen5InAction, Stream.of(drive).collect(Collectors.toSet()));*/

        Action park/*specimen5OutAction*/ = drive.actionBuilder(new Pose2d(40.00, -61.00, -Math.PI/2))
                .setTangent(Math.toRadians(125))
                .splineToSplineHeading(new Pose2d(26.20, -4.21, Math.toRadians(180.00)), Math.toRadians(180.00))
                .build();
        Command parkTraj = new ActionCommand(park, Stream.of(drive).collect(Collectors.toSet()));


        //Action park2Traj = drive.actionBuilder(new Pose2d(40.00, -61.00, -Math.PI/2)

        int specInTime = 400;
        int specOutTime = 300;

        Command auto = new SequentialCommandGroup(
                new WaitCommand(500),
                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenOut1)),
                specimen1Traj,
                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenIn1)),
                new WaitCommand(specOutTime),

                //pushTraj,
                specimen2In,
                new WaitCommand(specInTime),
                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenOut1)),
                specimen2OutTraj,
                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenIn1)),
                new WaitCommand(specOutTime)

                //park2Traj

                /*specimen3InTraj,
                new WaitCommand(specInTime),
                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenOut1)),
                specimen3OutTraj,
                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenIn1)),
                new WaitCommand(specOutTime),
                parkTraj

                /*specimen4InTraj,
                new WaitCommand(specInTime),
                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenOut1)),
                specimen4OutTraj,
                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenIn1)),
                new WaitCommand(specOutTime),

                specimen5InTraj,
                new WaitCommand(specInTime),
                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenOut1)),
                specimen5OutTraj,
                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenIn1))*/
        );
        schedule(auto);

    }
}
