package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Subsystems.Arm;

@TeleOp
public class ArmTester extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Arm arm = Arm.getInstance();
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            if (gamepad1.y) {
                arm.extendMotor.setPower(0.1);
            } else if (gamepad1.a) {
                arm.extendMotor.setPower(-0.1);
            } else if (gamepad1.x || gamepad1.b) {
                arm.extendMotor.setPower(0);
            }
            // Show the position of the motor on telemetry
            telemetry.addData("Encoder Position", arm.extendMotor.getCurrentPosition());
            telemetry.update();
        }
    }
}