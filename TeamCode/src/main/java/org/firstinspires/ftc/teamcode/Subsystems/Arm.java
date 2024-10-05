package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
    private static DcMotor rotationalMotor = null;
    private static Servo extendServo = null;
    private static Arm instance = null;

    public Arm getInstance() {
        return instance = instance == null ? new Arm() : instance;
    }

    public static void init() {
        rotationalMotor = hardwareMap.dcMotor.get("rotationalMotor");
        extendServo = hardwareMap.servo.get("extendServo");

        rotationalMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rotationalMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public static boolean rotateTo() {
        return true;
    }

    private static boolean rotatePosition() {
        return true;
    }
}
