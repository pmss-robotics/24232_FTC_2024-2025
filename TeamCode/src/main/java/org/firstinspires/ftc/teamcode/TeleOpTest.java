package org.firstinspires.ftc.teamcode;
//This is port moody parrabellum team code
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.drive.Drawing;
import org.firstinspires.ftc.teamcode.drive.PinpointDrive;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.NewArmElbowSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.NewArmShoulderSubsystem;

import java.util.concurrent.atomic.AtomicReference;

@Config
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOP", group = "TeleOp")
public class TeleOpTest extends CommandOpMode {

    public static int maxVertical = -2900;
    public static int minVertical = 2;

    // Define preset positions for the arm
    private static final int[] SHOULDER_POSITIONS = {0, 500, 1000};
    private static final int[] ELBOW_POSITIONS = {0, 300, 600};

    @Override
    public void initialize() {
        // data sent to telemetry shows up on dashboard and driverGamepad station
        // data sent to the telemetry packet only shows up on the dashboard
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        telemetry.log().setDisplayOrder(Telemetry.Log.DisplayOrder.NEWEST_FIRST);
        telemetry.log().setCapacity(8);
        // GamepadEx wraps gamepad 1 or 2 for easier implementations of more complex key bindings
        GamepadEx driver = new GamepadEx(gamepad1);
        GamepadEx tools = new GamepadEx(gamepad2);
        // The driveSubsystem wraps Roadrunner's MecanumDrive to combine with Commands.
        DriveSubsystem drive = new DriveSubsystem(new PinpointDrive(hardwareMap, new Pose2d(0, 0, 0)), telemetry);
        DriveCommand driveCommand = new DriveCommand(drive,
                () -> -driver.getLeftX(),
                () -> driver.getLeftY(),
                () -> -driver.getRightX(),
                true);

        // Initialize arm subsystems below
        NewArmShoulderSubsystem shoulder = new NewArmShoulderSubsystem(hardwareMap, telemetry);
        NewArmElbowSubsystem elbow = new NewArmElbowSubsystem(hardwareMap, telemetry);

        // Add code to handle button presses and move the arm to preset positions
        tools.getGamepadButton(GamepadKeys.Button.A).whenPressed(() -> {
            shoulder.setPosition(SHOULDER_POSITIONS[0]);
            elbow.setPosition(ELBOW_POSITIONS[0]);
        });

        tools.getGamepadButton(GamepadKeys.Button.B).whenPressed(() -> {
            shoulder.setPosition(SHOULDER_POSITIONS[1]);
            elbow.setPosition(ELBOW_POSITIONS[1]);
        });

        tools.getGamepadButton(GamepadKeys.Button.X).whenPressed(() -> {
            shoulder.setPosition(SHOULDER_POSITIONS[2]);
            elbow.setPosition(ELBOW_POSITIONS[2]);
        });

        schedule(new RunCommand(() -> {
            TelemetryPacket packet = new TelemetryPacket();
            Pose2d pose = drive.getPose();
            telemetry.addData("x", pose.position.x);
            telemetry.addData("y", pose.position.y);
            telemetry.addData("heading (deg)", Math.toDegrees(pose.heading.toDouble()));
            telemetry.update();

            packet.fieldOverlay().setStroke("#3F51B5");
            Drawing.drawRobot(packet.fieldOverlay(), pose);
            FtcDashboard.getInstance().sendTelemetryPacket(packet);
        }));

        schedule(driveCommand);
    }
}
