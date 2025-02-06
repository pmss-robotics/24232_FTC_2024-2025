package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.ActionCommand;
import org.firstinspires.ftc.teamcode.drive.Drawing;
import org.firstinspires.ftc.teamcode.drive.PinpointDrive;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DoubleJointArm;
import org.firstinspires.ftc.teamcode.subsystems.RollerIntakeSubsystem;

import java.util.stream.Collectors;
import java.util.stream.Stream;


@Config
@Autonomous(name="Operation_Specimen_Auto", group="Auto")
public class NewAuto_operationSpecimenAuto extends CommandOpMode {
    @Override
    public void initialize() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        DriveSubsystem drive = new DriveSubsystem(new PinpointDrive(hardwareMap, new Pose2d(0, 56, Math.toRadians(270))), telemetry);

        Action trajectoryAction = drive.actionBuilder(drive.getPose())
                .lineToY(32) //put on chamber
                .lineToY(44) //back away
                .turn(Math.toRadians(-90)) //turn to robot right
                .lineToX(-34) //go away from submersible
                .turn(Math.toRadians(90)) // go past the first blue brick
                .lineToY(10) //go far
                .turn(Math.toRadians(-90)) //turn to robot right
                .lineToX(-48) //line up with 3brick1
                .turn(Math.toRadians((-90))) //turn towards 3brick1
                .lineToY(60) //push 3brick2 into o-zone
                .lineToY(10) //back up
                .turn(Math.toRadians(90)) //turn to go X
                .lineToX(-55) //line up with 3brick2n
                .turn(Math.toRadians((-90))) //turn towards 3brick2
                .lineToY(60) //push 3brick2 into o-zone
                .lineToY(10) //back up
                .turn(Math.toRadians(90)) //turn to go X
                .lineToX(-60) //line up with 3brick3
                .turn(Math.toRadians((-90))) //turn towards 3brick3
                .lineToY(60) //push 3brick3 into o-zone






                //.turn(Math.toRadians(90)) save_fl

                .build();
        Command trajectory = new ActionCommand(trajectoryAction, Stream.of(drive).collect(Collectors.toSet()));

        //DoubleJointArm doubleJointArm = new DoubleJointArm(hardwareMap, telemetry);
        //doubleJointArm.setDefaultCommand(new RunCommand(doubleJointArm::holdPosition, doubleJointArm));

        SequentialCommandGroup routine = new SequentialCommandGroup(
               trajectory
               // other command
        );

        schedule(routine);
        schedule(new RunCommand(() -> {
            TelemetryPacket packet = new TelemetryPacket();
            Pose2d pose = drive.getPose();
            telemetry.addData("X pos", pose.position.x);
            telemetry.addData("Y pos",pose.position.y);
            telemetry.addData("Heading (deg)", Math.toDegrees(pose.heading.toDouble()));
            telemetry.update();
            packet.fieldOverlay().setStroke("#683fb5"); //color changed from #3F51B5
            Drawing.drawRobot(packet.fieldOverlay(), pose);
            FtcDashboard.getInstance().sendTelemetryPacket(packet);
        }));
    }
}