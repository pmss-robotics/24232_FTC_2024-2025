package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
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

        joint1Motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        joint1Motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        joint1Motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        joint1Motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        joint2Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        joint2Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        arm = new DoubleJointArm(joint1Motor1, joint1Motor2, joint2Motor, telemetry);
        rollerIntake = new RollerIntakeSubsystem(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        GamepadEx tools = new GamepadEx(gamepad2);

        double scaleRange = 180;
        double targetY = tools.getLeftY() * scaleRange;

        arm.controlArm(targetY);

        if (tools.gamepad.a) {
            rollerIntake.startIntake();
        } else if (tools.gamepad.b) {
            rollerIntake.startOuttake();
        } else if (tools.gamepad.x) {
            rollerIntake.stop();
        }

        //telemetry.addData("Target X", targetX);
        telemetry.addData("Target Y", targetY);
        telemetry.addData("Joint 1 Angle", arm.getJoint1Angle());
        telemetry.addData("Joint 2 Angle", arm.getJoint2Angle());
        telemetry.update();
    }
}
