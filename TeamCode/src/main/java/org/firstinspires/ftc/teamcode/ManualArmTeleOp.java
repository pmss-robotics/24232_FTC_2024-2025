package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.subsystems.RollerIntakeSubsystem;

@TeleOp(name = "ManualControlArm")
public class ManualArmTeleOp extends OpMode {

    private DcMotorEx joint1Motor1, joint1Motor2, joint2Motor;
    private RollerIntakeSubsystem rollerIntake;

    @Override
    public void init() {
        joint1Motor1 = hardwareMap.get(DcMotorEx.class, "joint1Motor1");
        joint1Motor2 = hardwareMap.get(DcMotorEx.class, "joint1Motor2");
        joint2Motor = hardwareMap.get(DcMotorEx.class, "joint2Motor");

        joint1Motor2.setDirection(DcMotorEx.Direction.REVERSE);

        rollerIntake = new RollerIntakeSubsystem(hardwareMap, telemetry);

        resetEncoders();
        setZeroPowerBehavior();
    }

    @Override
    public void loop() {
        GamepadEx tools = new GamepadEx(gamepad2);

        double joint1Power = -tools.getLeftY(); // Joint 1 controlled by left joystick Y
        double joint2Power = -tools.getRightY(); // Joint 2 controlled by right joystick Y

        joint1Power = Math.max(-1.0, Math.min(1.0, joint1Power));
        joint2Power = Math.max(-1.0, Math.min(1.0, joint2Power));

        joint1Motor1.setPower(joint1Power);
        joint1Motor2.setPower(joint1Power);
        joint2Motor.setPower(joint2Power);

        if (tools.gamepad.a) {
            rollerIntake.startIntake();
        } else if (tools.gamepad.b) {
            rollerIntake.startOuttake();
        } else if (tools.gamepad.x) {
            rollerIntake.stop();
        }

        telemetry.addData("Joint 1 Power", joint1Power);
        telemetry.addData("Joint 2 Power", joint2Power);
        telemetry.addData("Left Joystick Y", tools.getLeftY());
        telemetry.addData("Right Joystick Y", tools.getRightY());
        telemetry.update();
    }

    private void resetEncoders() {
        joint1Motor1.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        joint1Motor2.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        joint2Motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        joint1Motor1.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        joint1Motor2.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        joint2Motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
    }

    private void setZeroPowerBehavior() {
        joint1Motor1.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        joint1Motor2.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        joint2Motor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }
}
