//package org.firstinspires.ftc.teamcode;
//
//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
//import com.arcrobotics.ftclib.command.CommandOpMode;
//import com.arcrobotics.ftclib.gamepad.GamepadEx;
//
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
//
//public class TestPinpoint extends CommandOpMode {
//    DriveSubsystem drive;
//
//    @Override
//    public void initialize() {
//        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
//        telemetry.log().setDisplayOrder(Telemetry.Log.DisplayOrder.NEWEST_FIRST);
//        telemetry.log().setCapacity(8);
//
//        GamepadEx driver = new GamepadEx(gamepad1);
//        GamepadEx tools = new GamepadEx(gamepad2);
//
//
//    }
//}
