package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import java.util.ArrayList;
import java.util.List;

public class Limelight extends LinearOpMode {

    Limelight3A limelight = hardwareMap.get(Limelight3A.class, "Ethernet Device");


    LLResultTypes.CalibrationResult calibration = limelight.getCalDefault();
    private AprilTagProcessor myAprilTag;
    private AprilTagDetection targetTag = null;
    boolean targetFound = false;
    double xCor;
    double yCor;
    int tagId = -1; //Tag ID that we looking for; Set to -1 for ANY tag
    public void runOpMode() throws  InterruptedException {
        telemetry.setMsTransmissionInterval(11);

        if (isStopRequested()) return;
        while (opModeIsActive()) {
            LLStatus status = limelight.getStatus();
            telemetry.addData("Name", "%s",
                    status.getName());
            telemetry.addData("LL", "Temp: %.1fC, CPU: %.1f%%, FPS: %d",
                    status.getTemp(), status.getCpu(), (int) status.getFps());
            telemetry.addData("Pipeline ", "Index: %d, Type: %s\n",
                    status.getPipelineIndex(), status.getPipelineType());
            LLResult result = limelight.getLatestResult();
            if(result != null && result.isValid()){
                telemetry.addData("Tx: ", "%.1f", result.getTx()); //Unit: Degree
                telemetry.addData("Ty: ", "%.1f", result.getTy()); //Unit: Degree
                telemetry.addData("Ta: ", "%.1f", result.getTa()); //Unit: Degree
                telemetry.addLine();
                Pose3D botpose = result.getBotpose();
                double captureLatency = result.getCaptureLatency();
                double targetingLatency = result.getTargetingLatency();
                double parseLatency = result.getParseLatency();
                telemetry.addData("LL Latency", captureLatency + targetingLatency);
                telemetry.addData("Parse Latency", parseLatency);
            }

            ArrayList<AprilTagDetection> currentDetection = myAprilTag.getDetections();
            for (AprilTagDetection detection : currentDetection) {
                if (detection.metadata != null) {
                    if (tagId < 0 || detection.id == tagId) {
                        targetFound = true;
                        targetTag = detection;
                        break;
                    }
                }
            }
            if(gamepad1.a){
                limelight.pipelineSwitch(1);
            }
            if (gamepad1.x){
                limelight.pipelineSwitch(0);
            }
            if(gamepad1.b){
                limelight.pipelineSwitch(2);
            }
            telemetry.addData("Tag ID: ", "%2d", targetTag.id);
            telemetry.update();
        }
        limelight.stop();
    }


}
