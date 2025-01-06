package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.function.DoubleSupplier;

@Config
public class RollerIntakeSubsystem extends SubsystemBase {
    private final Telemetry telemetry;
    private final Servo intakeServoLeft;
    private final Servo intakeServoRight;
    private static final String INTAKE_SERVO_LEFT_NAME = "intakeServoLeft";
    private static final String INTAKE_SERVO_RIGHT_NAME = "intakeServoRight";

    public static final double INTAKE_SPEED = 1.0;
    public static final double OUTTAKE_SPEED = 0.0;
    public static final double STOP_SPEED = 0.5;

    public RollerIntakeSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        this.intakeServoLeft = hardwareMap.get(Servo.class, INTAKE_SERVO_LEFT_NAME);
        this.intakeServoRight = hardwareMap.get(Servo.class, INTAKE_SERVO_RIGHT_NAME);

        this.intakeServoLeft.setDirection(Servo.Direction.REVERSE);
    }
    @Override
    public void periodic() {
        if(!Double.isNaN(intakeServoRight.getPosition())){
            telemetry.addData("Intake Servo Left Position", intakeServoLeft.getPosition());
            telemetry.addData("Intake Servo Right Position", intakeServoRight.getPosition());
        }
    }
    public void setPower(double power) {
        power = Math.max(0.0, Math.min(1.0, power)); // Clamp power between 0.0 and 1.0
        intakeServoLeft.setPosition(power);
        intakeServoRight.setPosition(power);
    }

    public void setPower(DoubleSupplier powerSupplier) {
        setPower(powerSupplier.getAsDouble());
    }
    public void startIntake() {
        setPower(INTAKE_SPEED);
    }
    public void startOuttake() {
        setPower(OUTTAKE_SPEED);
    }
    public void stop() {
        setPower(STOP_SPEED);
    }

}
