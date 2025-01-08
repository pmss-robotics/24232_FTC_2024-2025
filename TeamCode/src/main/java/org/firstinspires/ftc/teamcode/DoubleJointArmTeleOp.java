package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
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
        arm = new DoubleJointArm(joint1Motor1, joint1Motor2, joint2Motor, telemetry);
        telemetry.addData("Encoder Ticks", joint1Motor1.getCurrentPosition());
        telemetry.addData("Joint Angle (rads)", arm.getJointAngle(joint1Motor1));

        rollerIntake = new RollerIntakeSubsystem(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        GamepadEx tools = new GamepadEx(gamepad2);

        // Arm
        // Change !!!
        int scaleRange = 180;

        double targetX = tools.getLeftX() * scaleRange;
        double targetY = tools.getLeftY() * scaleRange;
        arm.controlArm(targetX, targetY);

        double joint1M1Power = arm.getJoint1Motor1Power();
        double joint1M2Power = arm.getJoint1Motor2Power();
        double joint2Power = arm.getJoint2MotorPower();
        telemetry.addData("Joint 1 M1 Power", joint1M1Power);
        telemetry.addData("Joint 1 M2 Power", joint1M2Power);
        telemetry.addData("Joint 2 Power", joint2Power);

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
