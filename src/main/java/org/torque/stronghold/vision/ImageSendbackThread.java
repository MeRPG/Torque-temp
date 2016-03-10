package org.torque.stronghold.vision;

import com.ni.vision.NIVision;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.AxisCamera;
import edu.wpi.first.wpilibj.vision.USBCamera;
import javafx.scene.Camera;
import org.torque.lib.concurrent.Task;

/**
 * Task to send back the camera image to the station.
 */
public class ImageSendbackThread extends Task {
    //Weather or not the camera is the shooting one
    private boolean shooterCam;
    private NIVision.Image latestImage;

    //Cameras
    private int camses;
    private AxisCamera armCamera;

    /**
     * Setup the cameras
     */
    public ImageSendbackThread() {
        this.shooterCam = false;

        this.armCamera = new AxisCamera("10.52.83.11");
        camses = NIVision.IMAQdxOpenCamera("cam2", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(camses);

        this.latestImage = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

        CameraServer.getInstance().setSize(1);
        CameraServer.getInstance().setQuality(30);
    }

    /**
     * Set the camera server image
     */
    @Override
    public void run() {
        if(shooterCam) {
            NIVision.IMAQdxGrab(camses, latestImage, 1);
        } else {
            NIVision.Image tempImage = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
            NIVision.Image tempImage2 = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
            armCamera.getImage(tempImage);
            NIVision.imaqFlip(tempImage2, tempImage, NIVision.FlipAxis.HORIZONTAL_AXIS);
            NIVision.imaqFlip(latestImage, tempImage2, NIVision.FlipAxis.VERTICAL_AXIS);
        }
        CameraServer.getInstance().setImage(latestImage);
    }

    public void setShooterCam(boolean shooterCam) {
        this.shooterCam = shooterCam;
    }

    /**
     * Get the image so you can read it
     * @return image, read only. Will be overridden
     */
    public NIVision.Image getLatestImage() {
        return latestImage;
    }
}
