package org.torque.stronghold.vision;

import com.ni.vision.NIVision;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.AxisCamera;
import edu.wpi.first.wpilibj.vision.USBCamera;
import javafx.scene.Camera;
import org.torque.lib.concurrent.Task;

/**
 * Created by Jaxon A Brown on 1/30/2016.
 */
public class ImageSendbackThread extends Task {
    private boolean shooterCam;
    private NIVision.Image latestImageUSB;
    private NIVision.Image latestImageAxis;
    private NIVision.Image latestImage;

    private int camses;
    private AxisCamera armCamera;

    public ImageSendbackThread() {
        this.shooterCam = false;

        this.armCamera = new AxisCamera("10.52.83.11");
        camses = NIVision.IMAQdxOpenCamera("cam2", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(camses);

        this.latestImage = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

        CameraServer.getInstance().setSize(1);
        CameraServer.getInstance().setQuality(30);
    }

    @Override
    public void run() {
        if(shooterCam) {
            NIVision.IMAQdxGrab(camses, latestImage, 1);
        } else {
            armCamera.getImage(latestImage);
        }
        CameraServer.getInstance().setImage(latestImage);
    }

    public void setShooterCam(boolean shooterCam) {
        this.shooterCam = shooterCam;
    }

    public NIVision.Image getLatestImage() {
        return latestImage;
    }
}
