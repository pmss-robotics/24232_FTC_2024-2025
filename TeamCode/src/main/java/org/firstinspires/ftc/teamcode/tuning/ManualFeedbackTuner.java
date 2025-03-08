package org.firstinspires.ftc.teamcode.tuning;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.*;
import org.firstinspires.ftc.teamcode.subsystems.FeedForwardArmSubsystem;

public final class ManualFeedbackTuner extends LinearOpMode {
    public static double DISTANCE = 64;

    @Override
    public void runOpMode() throws InterruptedException {
        if (TuningOpModes.DRIVE_CLASS.equals(PinpointDrive.class)) {
            PinpointDrive drive = new PinpointDrive(hardwareMap, new Pose2d(0, 0, 0));

            waitForStart();
            FeedForwardArmSubsystem arm = new FeedForwardArmSubsystem(hardwareMap, telemetry);
            arm.shoulderMoveTo(350);
            Action traj = drive.actionBuilder(new Pose2d(0, 0, 0))
/*                                .lineToX(DISTANCE) //change back to lineToX for forwards and backwards
                                .lineToX(0) */
                    .strafeTo(new Vector2d(DISTANCE, 0))
                    .strafeTo(new Vector2d(0, 0))
                    .build();

            Action hold = telemetryPacket -> {
                arm.holdPosition();
                return true;
            };
            while (opModeIsActive()) {
                Actions.runBlocking(new ParallelAction(
                        traj,
                        hold
                ));

            }
        } else if (TuningOpModes.DRIVE_CLASS.equals(MecanumDrive.class)) {
            MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));


            waitForStart();

            while (opModeIsActive()) {
                Actions.runBlocking(
                        drive.actionBuilder(new Pose2d(0, 0, 0))
                                .lineToX(DISTANCE)
                                .lineToX(0)
                                .build());
            }
        } else {
            throw new RuntimeException();
        }
    }
}