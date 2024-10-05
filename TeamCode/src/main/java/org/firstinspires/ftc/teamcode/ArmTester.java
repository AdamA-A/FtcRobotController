package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class ArmTester extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            int position = motor.getCurrentPosition();

            // Show the position of the motor on telemetry
            telemetry.addData("Encoder Position", position);
            telemetry.update();
        }
    }
}