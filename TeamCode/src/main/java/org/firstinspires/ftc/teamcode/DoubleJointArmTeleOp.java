package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.subsystems.DoubleJointArm;
import org.firstinspires.ftc.teamcode.subsystems.RollerIntakeSubsystem;


@TeleOp(name = "DoubleJointArmTeleOp")
public class DoubleJointArmTeleOp extends OpMode {

    private DoubleJointArm arm;
    private RollerIntakeSubsystem rollerIntake;

    @Override
    public void init() {
        DcMotorEx joint1Motor1 = hardwareMap.get(DcMotorEx.class, "joint1Motor1");
        DcMotorEx joint1Motor2 = hardwareMap.get(DcMotorEx.class, "joint1Motor2");
        DcMotorEx joint2Motor = hardwareMap.get(DcMotorEx.class, "joint2Motor");
        arm = new DoubleJointArm(joint1Motor1, joint1Motor2, joint2Motor);

        rollerIntake = new RollerIntakeSubsystem(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        GamepadEx tools = new GamepadEx(gamepad2);

        // Arm
        // Change !!!
        int scaleRange = 10;

        double targetX = tools.getLeftX() * scaleRange;
        double targetY = tools.getLeftY() * scaleRange;
        arm.controlArm(targetX, targetY);

        // Intake
        if (tools.gamepad.a) {
            rollerIntake.startIntake(); // Start intake
        } else if (tools.gamepad.b) {
            rollerIntake.startOuttake(); // Start outtake
        } else if (tools.gamepad.x) {
            rollerIntake.stop(); // Stop rollers
        }

        telemetry.addData("Target X", "%.2f", targetX);
        telemetry.addData("Target Y", "%.2f", targetY);
        telemetry.addData("Joint 1 Angle", "%.2f", arm.getJoint1Angle());
        telemetry.addData("Joint 2 Angle", "%.2f", arm.getJoint2Angle());
        telemetry.update();
    }
}
