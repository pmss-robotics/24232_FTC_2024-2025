package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

//import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.ArmCommand;
import org.firstinspires.ftc.teamcode.commands.ClawCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.drive.Drawing;
import org.firstinspires.ftc.teamcode.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.drive.PinpointLocalizer;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.FeedForwardArmSubsystem;
import org.firstinspires.ftc.teamcode.util.States;
//import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

/*
 * Configuration File
 * Control Hub:
 * Motor Port 0: leftFront
 * Motor Port 1: rightFront
 * Motor Port 2: rightBack
 * Motor Port 3: leftBack
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TestTeleOP", group = "TeleOp")
public class TestTeleOp extends CommandOpMode {

    GamepadEx driver, tools;
    DriveSubsystem drive;
    FeedForwardArmSubsystem arm;
    ClawSubsystem claw;
    //Pose startPose;

    @Override
    public void initialize() {
        // data sent to telemetry shows up on dashboard and driverGamepad station
        // data sent to the telemetry packet only shows up on the dashboard
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        telemetry.log().setDisplayOrder(Telemetry.Log.DisplayOrder.NEWEST_FIRST);
        telemetry.log().setCapacity(8);

        // GamepadEx wraps gamepad 1 or 2 for easier implementations of more complex key bindings
        driver = new GamepadEx(gamepad1);
        tools = new GamepadEx(gamepad2);

        drive = new DriveSubsystem(new MecanumDrive(hardwareMap, new Pose2d(0,0,0)), telemetry);
        DriveCommand driveCommand = new DriveCommand(drive,
                () -> -driver.getLeftX(),
                () -> driver.getLeftY(),
                () -> -driver.getRightX(),
                true);

        arm = new FeedForwardArmSubsystem(hardwareMap, telemetry);
        ArmCommand armCommand = new ArmCommand(arm,
                () -> -tools.getLeftY(),
                () -> -tools.getRightY()
        );
        arm.setDefaultCommand(armCommand);

        claw = new ClawSubsystem(hardwareMap, telemetry);
        /*
        ClawCommand clawCommand = new ClawCommand(arm,
                () ->
                () ->
        );*/


        schedule(new RunCommand(() -> {
            TelemetryPacket packet = new TelemetryPacket();
            //Pose2d pose = drive.localizer.getPose();
            //telemetry.addData("x", pose.position.x);
            //telemetry.addData("y",pose.position.y);
            //telemetry.addData("heading (deg)", Math.toDegrees(pose.heading.toDouble()));
            telemetry.update();

            packet.fieldOverlay().setStroke("#3F51B5");
            //Drawing.drawRobot(packet.fieldOverlay(), pose);
            FtcDashboard.getInstance().sendTelemetryPacket(packet);
        }));


        schedule(driveCommand);
    }
}
