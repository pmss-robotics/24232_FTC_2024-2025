package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

//import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.ActionCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.drive.PinpointDrive;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.FeedForwardArmSubsystem;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TestTeleOP", group = "TeleOp")
public class TestTeleOp extends CommandOpMode {

    GamepadEx driver, tools;
    DriveSubsystem drive;
    FeedForwardArmSubsystem arm;
    ClawSubsystem claw;

    // For Triggers
    public static double triggerThreshold = 0.3;

    // Booleans
    private boolean dpadDownStateA = true;

    @Override
    public void initialize() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        driver = new GamepadEx(gamepad1);
        tools = new GamepadEx(gamepad2);

        drive = new DriveSubsystem(new PinpointDrive(hardwareMap, new Pose2d(0, 0, 0)), telemetry);
        arm = new FeedForwardArmSubsystem(hardwareMap, telemetry);
        claw = new ClawSubsystem(hardwareMap, telemetry);

        // Default commands
        arm.setDefaultCommand(new RunCommand(() -> arm.holdPosition(), arm));

        // Drive Command
        DriveCommand driveCommand = new DriveCommand(drive,
                () -> -driver.getLeftX(),
                () -> driver.getLeftY(),
                () -> -driver.getRightX(),
                true);
        drive.setDefaultCommand(driveCommand);

        new GamepadButton(driver, GamepadKeys.Button.X).whenPressed(() -> {
            Pose2d currentPose = drive.getPose();

            Action turn180Action = drive.actionBuilder(currentPose)
                    .turn(Math.toRadians(180))
                    .build();

            schedule(new ActionCommand(turn180Action, Collections.singleton(drive)));
        });

        new GamepadButton(driver, GamepadKeys.Button.Y).whenPressed(() ->
            new InstantCommand(() -> drive.drive.pinpoint.resetPosAndIMU())
        );


        new GamepadButton(driver, GamepadKeys.Button.A).whenPressed(() -> {
            Pose2d currentPose = drive.getPose();

            double heading = currentPose.heading.toDouble();

            double dx = 2 * Math.cos(heading);
            double dy = 2 * Math.sin(heading);

            Pose2d targetPose = new Pose2d(
                    currentPose.position.x + dx,
                    currentPose.position.y + dy,
                    heading
            );

            Action forwardAction = drive.actionBuilder(currentPose)
                    .splineToSplineHeading(targetPose, heading)
                    .build();

            schedule(new ActionCommand(forwardAction, Collections.singleton(drive)));
        });

        //-------------------------------------Gamepad Driver---------------------------------------------
        // 1. LEFT_BUMPER on DRIVER: Move arm to two submersible positions
        //      A: Claw is open and arms are in in SubmersibleIn positions
        //      B: Drop to submersible intake position, close the claw, then raise to get out of Submersible
        new GamepadButton(driver, GamepadKeys.Button.LEFT_BUMPER).whenPressed(() -> {
            if (dpadDownStateA) {
                schedule(new SequentialCommandGroup(
                        new InstantCommand(() -> claw.clawOpen(), claw),
                        new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SubmersibleIn), arm),
                        new WaitUntilCommand(() ->
                                Math.abs(arm.shoulder1.getCurrentPosition() - arm.shoulderTarget) <= arm.shoulderTolerance
                        ),
                        new InstantCommand(() -> arm.elbowMoveTo(arm.pe_SubmersibleIn), arm)
                ));
            } else {
                schedule(new SequentialCommandGroup(
                        new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SubmersibleIntake), arm),
                        new WaitUntilCommand(() ->
                                Math.abs(arm.shoulder1.getCurrentPosition() - arm.shoulderTarget) <= arm.shoulderTolerance
                        ),
                        new InstantCommand(() -> claw.clawClosed(), claw),
                        new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SubmersibleIn), arm),
                        new WaitUntilCommand(() ->
                                Math.abs(arm.shoulder1.getCurrentPosition() - arm.shoulderTarget) <= arm.shoulderTolerance
                        ),
                        new InstantCommand(() -> arm.elbowMoveTo(arm.pe_SubmersibleIn), arm)
                ));
            }
            dpadDownStateA = !dpadDownStateA;
        });

        // 2. RIGHT_BUMPER on DRIVER: Tuck in the arm for easier drive
        new GamepadButton(driver, GamepadKeys.Button.RIGHT_BUMPER).whenPressed(
                new SequentialCommandGroup(
                        new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_Home), arm),
                        new WaitUntilCommand(() ->
                                Math.abs(arm.shoulder1.getCurrentPosition() - arm.shoulderTarget) <= arm.shoulderTolerance
                        ),
                        new InstantCommand(() -> arm.elbowMoveTo(arm.pe_TuckIn), arm)
                )
        );

        // 3. RIGHT_TRIGGER on DRIVER: Release block in observation zone
        new Trigger(() -> driver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) < triggerThreshold).whenActive(
                new SequentialCommandGroup(
                        new InstantCommand(() -> arm.elbowMoveTo(arm.pe_Observation), arm),
                        new WaitUntilCommand(() ->
                                Math.abs(arm.elbow.getCurrentPosition() - arm.elbowTarget) <= arm.elbowTolerance
                        ),
                        new InstantCommand(() -> claw.clawOpen(), claw)
                )
        );

        // 4. LEFT_TRIGGER on DRIVER: Simply opens the claw - for Samples
        /*new Trigger(() -> driver.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > triggerThreshold)
                .whenActive(
                        new InstantCommand(() -> claw.clawOpen(), claw)
                );*/

        // (JIC) LEFT_BUMPER on DRIVER: Simply closes the claw
        /*new GamepadButton(tools, GamepadKeys.Button.LEFT_BUMPER).whenPressed(
                new InstantCommand(() -> claw.clawClosed(), claw)
        );*/

        //-------------------------------------Gamepad Driver---------------------------------------------
        // 1. RIGHT_BUMPER on TOOLS: Rotates the wrist
        new GamepadButton(tools, GamepadKeys.Button.DPAD_DOWN).whenPressed(
                new InstantCommand(() -> claw.changeWristPosition(), claw)
        );

        // 2. RIGHT TRIGGER on TOOLS: Specimen intake from observation zone
        new Trigger(() -> tools.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) < triggerThreshold).whenActive(
                new SequentialCommandGroup(
                        new InstantCommand(() -> arm.elbowMoveTo(arm.pe_Home), arm),
                        new WaitUntilCommand(() ->
                                Math.abs(arm.elbow.getCurrentPosition() - arm.elbowTarget) <= arm.elbowTolerance
                        ),
                        new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenIn1), arm)
                )
        );

        // 3. RIGHT BUMPER on TOOLS: Moving Specimen above Observation
        new GamepadButton(tools, GamepadKeys.Button.RIGHT_BUMPER).whenPressed(
                new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenIn2), arm)
        );

        // 4. LEFT TRIGGER on TOOLS: Preparing for Specimen release
        new Trigger(() -> tools.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) < triggerThreshold).whenActive(
                new SequentialCommandGroup(
                        new InstantCommand(() -> arm.elbowMoveTo(arm.pe_Home), arm),
                        new WaitUntilCommand(() ->
                                Math.abs(arm.elbow.getCurrentPosition() - arm.elbowTarget) <= arm.elbowTolerance
                        ),
                        new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenOut1), arm)
                )
        );

        // 5. LEFT BUMPER on TOOLS: Specimen outtake (hanging)
        new GamepadButton(tools, GamepadKeys.Button.LEFT_BUMPER).whenPressed(
                new SequentialCommandGroup(
                        new InstantCommand(() -> arm.elbowMoveTo(arm.pe_Home), arm),
                        new WaitUntilCommand(() ->
                                Math.abs(arm.elbow.getCurrentPosition() - arm.elbowTarget) <= arm.elbowTolerance
                        ),
                        new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_SpecimenOut2), arm)
                )
        );

        // 6. BUTTON A on TOOLS: Arm moves to Bucket to drop a Sample
        new GamepadButton(tools, GamepadKeys.Button.A).whenPressed(
                new SequentialCommandGroup(
                        new InstantCommand(() -> arm.elbowMoveTo(arm.pe_Home)),
                        new InstantCommand(() -> arm.shoulderMoveTo(arm.ps_Bucket), arm),
                        new WaitUntilCommand(() ->
                                Math.abs(arm.shoulder1.getCurrentPosition() - arm.shoulderTarget) <= arm.shoulderTolerance
                        ),
                        new InstantCommand(() -> arm.elbowMoveTo(arm.pe_Bucket), arm),
                        new WaitUntilCommand(() ->
                                Math.abs(arm.elbow.getCurrentPosition() - arm.elbowTarget) <= arm.elbowTolerance
                        ),
                        new InstantCommand(() -> claw.setWristPosition(ClawSubsystem.pW180), claw)
                )
        );

        // 7. BUTTON B on TOOLS: Simply open
        new GamepadButton(tools, GamepadKeys.Button.B).whenPressed(
                new InstantCommand(() -> claw.clawOpen(), claw)
        );

        // manual shoulder and arm control (optional)
        new Trigger(() -> tools.getLeftY() != 0 || tools.getRightY() != 0)
                .whileActiveContinuous(new InstantCommand(() -> {
                    arm.shoulder1.setPower(-tools.getRightY() * 0.5);
                    arm.shoulder2.setPower(-tools.getRightY() * 0.5);
                    arm.shoulderTarget = arm.shoulder1.getCurrentPosition();

                    arm.elbow.setPower(tools.getLeftY() * 0.8);
                    arm.elbowTarget = arm.elbow.getCurrentPosition();
                }, arm))
                .whenInactive(new InstantCommand(() -> {
                    arm.shoulder1.setPower(0);
                    arm.shoulder2.setPower(0);
                    arm.shoulderTarget = arm.shoulder1.getCurrentPosition();

                    arm.elbow.setPower(0);
                    arm.elbowTarget = arm.elbow.getCurrentPosition();
                }, arm));

        schedule(new RunCommand(() -> {
            TelemetryPacket packet = new TelemetryPacket();
            Pose2d pose = drive.getPose();
            telemetry.addData("x", pose.position.x);
            telemetry.addData("y",pose.position.y);
            telemetry.addData("heading (deg)", Math.toDegrees(pose.heading.toDouble()));
            telemetry.update();

            packet.fieldOverlay().setStroke("#3F51B5");
            FtcDashboard.getInstance().sendTelemetryPacket(packet);
        }));
    }

    @Override
    public void run() {
        super.run();
    }
}
