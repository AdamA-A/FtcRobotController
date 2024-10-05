package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
    // Counts per revolution
    final double CPR = 28;
    private static DcMotor rotationalMotor = null;
    private static DcMotor extendMotor = null;
    private static Arm instance = null;

    public static Arm getInstance() {
        return instance = instance == null ? new Arm() : instance;
    }

    private Arm() {
        rotationalMotor = hardwareMap.dcMotor.get("rotationalMotor");
        extendMotor = hardwareMap.dcMotor.get("extendMotor");

        rotationalMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rotationalMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void rotateTo(double angle, boolean toNormalize) {

        // Convert angle to position
        int position = angleToTicks((toNormalize ? angle % 360 : angle));

        // Set Position
        rotationalMotor.setTargetPosition(position);
    }

    private void extendTo(double inches) {

        // Convert angle to position
        int position = inchesToTicks(inches);

        // Set Position
        extendMotor.setTargetPosition(position);
    }
    private int inchesToTicks(double inches) {
        double exactTicks = inches * 0; // TODO find raw ticks, then find ratio of ticks to inches
        return (int) exactTicks;
    }
    private int angleToTicks(double angle) {
        double revolutions = angle / 360;
        double exactPosition = revolutions * CPR;
        return (int) exactPosition;
    }
}
