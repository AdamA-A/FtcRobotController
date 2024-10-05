package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class PIDControl extends LinearOpMode {

    DcMotorEx motor;

    double integralSum = 0;
    double Kp;

    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.get(DcMotorEx.class, "motor");

        waitForStart();
        while (opModeIsActive()) {

        }
    }

    public double PIDControl(double reference, double state) {
        double error = reference - state;
        return error;
    }
}
