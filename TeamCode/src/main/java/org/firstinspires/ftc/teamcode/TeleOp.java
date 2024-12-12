package org.firstinspires.ftc.teamcode;
//This is port moody parrabellum team code
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.ActionCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.drive.Drawing;
import org.firstinspires.ftc.teamcode.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.drive.PinpointDrive;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtendSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.GenericContinuousServoSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.GenericMotorSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.GenericPositionServoSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeMotorSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeServosSubsystem;

@Config
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOP", group = "TeleOp")
public class TeleOp extends CommandOpMode {
    public static double servoIncrement = 0.002;
    public static double servoSpeed = 0.5;
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
        // The driveCommand uses methods defined in the DriveSubsystem to create behaviour.
        // we're passing in methods to get values instead of straight values because it avoids
        // disturbing the structure of the CommandOpMode. The aim is to define bindings in this
        // initialize() method through Commands and these will be looped and acted in the (hidden)
        // run() loop.
        //delete this line
        DriveCommand driveCommand = new DriveCommand(drive,
                () -> -driver.getLeftX(),
                () -> driver.getLeftY(),
                () -> -driver.getRightX(),
                true);

        ExtendSubsystem extendSubsystem = new ExtendSubsystem(hardwareMap, telemetry);
        extendSubsystem.setDefaultCommand(new RunCommand(
                () -> extendSubsystem.setPower(tools.getRightY()),
                extendSubsystem
        ));

        IntakeMotorSubsystem intakeMotorSubsystem = new IntakeMotorSubsystem(hardwareMap, telemetry, "intakeMotor");
        intakeMotorSubsystem.setDefaultCommand(new RunCommand(
                () -> intakeMotorSubsystem.setPower(tools.getLeftX() * 0.2),
                intakeMotorSubsystem
        ));

        IntakeServosSubsystem intakeServosSubsystem = new IntakeServosSubsystem(hardwareMap, telemetry);
        double intakeServosSubsystemSpeed;
        if (gamepad2.left_bumper){intakeServosSubsystemSpeed = 75.0;}
        else if (gamepad2.right_bumper){intakeServosSubsystemSpeed = -50.0;}
        else { intakeServosSubsystemSpeed = 0.0;}
        intakeServosSubsystem.setDefaultCommand(new RunCommand(
                () -> intakeServosSubsystem.setPower(intakeServosSubsystemSpeed),
                intakeServosSubsystem
        ));

        GenericPositionServoSubsystem genericPositionServoSubsystem1 = new GenericPositionServoSubsystem(hardwareMap, telemetry, "intakeServoLeft", 0.5);
        GenericPositionServoSubsystem genericPositionServoSubsystem2 = new GenericPositionServoSubsystem(hardwareMap, telemetry, "intakeServoRight", 0.5);
        genericPositionServoSubsystem1.setDefaultCommand(new RunCommand(
                () -> genericPositionServoSubsystem1.setPosition(genericPositionServoSubsystem1.position),
                genericPositionServoSubsystem1
        ));
        genericPositionServoSubsystem2.setDefaultCommand(new RunCommand(
                () -> genericPositionServoSubsystem2.setPosition(genericPositionServoSubsystem2.position),
                genericPositionServoSubsystem2
        ));
        new GamepadButton(tools, GamepadKeys.Button.LEFT_BUMPER)
                .whileHeld(new InstantCommand(
                        () -> genericPositionServoSubsystem1.incrementPosition(-servoIncrement),
                        genericPositionServoSubsystem1
                ));
        new GamepadButton(tools, GamepadKeys.Button.RIGHT_BUMPER)
                .whileHeld(new InstantCommand(
                        () -> genericPositionServoSubsystem2.incrementPosition(-servoIncrement),
                        genericPositionServoSubsystem2
                ));

        /*GenericContinuousServoSubsystem genericContinuousServoSubsystem = new GenericContinuousServoSubsystem(hardwareMap, telemetry, "servo");
        // to trigger you can do something similar to whats done in genericMotorSubsystem or...
        new GamepadButton(tools, GamepadKeys.Button.A).toggleWhenPressed(
                new InstantCommand(
                        () -> genericContinuousServoSubsystem.setPower(0.5+servoSpeed),
                        genericContinuousServoSubsystem),
                new InstantCommand(
                        () -> genericContinuousServoSubsystem.setPower(0.5),
                        genericContinuousServoSubsystem)
        );
        new GamepadButton(tools, GamepadKeys.Button.B).toggleWhenPressed(
                new InstantCommand(
                        () -> genericContinuousServoSubsystem.setPower(0.5-servoSpeed),
                        genericContinuousServoSubsystem),
                new InstantCommand(
                        () -> genericContinuousServoSubsystem.setPower(0.5),
                        genericContinuousServoSubsystem)
        ); */


        // sample for action and command synergy and binding
        // try to avoid this kind of usage as much as possible
        //SampleMechanism sampleMechanism = new SampleMechanism(hardwareMap);
        //Set<Subsystem> subsystemSet = Stream.of(drive).collect(Collectors.toSet());
        // the binding for whenPressed() is convenient since it only activates once even when A is held down.
        //driverGamepad.getGamepadButton(GamepadKeys.Button.A).whenPressed(new ActionCommand(sampleMechanism.doSampleMechanismAction(), subsystemSet));

        schedule(new RunCommand(() -> {
            TelemetryPacket packet = new TelemetryPacket();
            Pose2d pose = drive.getPose();
            telemetry.addData("x", pose.position.x);
            telemetry.addData("y",pose.position.y);
            telemetry.addData("heading (deg)", Math.toDegrees(pose.heading.toDouble()));
            telemetry.update();

            packet.fieldOverlay().setStroke("#3F51B5");
            Drawing.drawRobot(packet.fieldOverlay(), pose);
            FtcDashboard.getInstance().sendTelemetryPacket(packet);
        }));


        schedule(driveCommand);
    }


}
