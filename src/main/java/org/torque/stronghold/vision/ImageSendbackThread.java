package org.torque.stronghold.vision;

import com.ni.vision.NIVision;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.AxisCamera;
import edu.wpi.first.wpilibj.vision.USBCamera;
import javafx.scene.Camera;
import javafx.scene.shape.DrawMode;
import org.torque.lib.concurrent.Task;
import org.torque.lib.concurrent.Timer;

/**
 * Task to send back the camera image to the station.
 */
public class ImageSendbackThread extends Thread {
    //Weather or not the camera is the shooting one
    private boolean shooterCam;
    private NIVision.Image latestImage;


    NIVision.Image tempImage;
    NIVision.Image tempImage2;

    private boolean running = false;

    //Cameras
    private int camses;
    private AxisCamera armCamera;
    private int camses2;

    /**
     * Setup the cameras
     */
    public ImageSendbackThread() {
        this.shooterCam = false;

        try {
            //this.armCamera = new AxisCamera("10.52.83.11");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        /*try {
            camses = NIVision.IMAQdxOpenCamera("cam2", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
            NIVision.IMAQdxConfigureGrab(camses);
            camses2 = NIVision.IMAQdxOpenCamera("cam3", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
            NIVision.IMAQdxConfigureGrab(camses2);
        } catch(Exception ex) {
            ex.printStackTrace();
        }*/

        setShooterCam(false);

        latestImage = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        tempImage = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        tempImage2 = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        //NIVision.imaqDrawLineOnImage(latestImage, NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0),
        //        NIVision.DrawMode.PAINT_VALUE, new NIVision.Point(3, 3), new NIVision.Point(25, 25), 0.1f);

        CameraServer.getInstance().setSize(1);
        CameraServer.getInstance().setQuality(30);

        try {

            SmartDashboard.putInt("vis top", 56);
            SmartDashboard.putInt("vis left", 210);
            SmartDashboard.putInt("vis width", 132);
            SmartDashboard.putInt("vis height", 64);
            tempImage = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }


    public void begin() {
        running = true;
    }

    /**
     * Set the camera server image
     */
    @Override
    public void run() {
        while(true) {

            if(this.running) {
                if(true) {
                    if(shooterCam) {
                        try {
                            NIVision.IMAQdxGrab(camses, tempImage, 1);
                            int top = SmartDashboard.getInt("vis top", 75);
                            int left = SmartDashboard.getInt("vis left", 57);
                            int width = SmartDashboard.getInt("vis width", 100);
                            int height = SmartDashboard.getInt("vis height", 100);
                            NIVision.imaqDrawShapeOnImage(latestImage, tempImage, new NIVision.Rect(top, left, height, width), NIVision.DrawMode.DRAW_VALUE, NIVision.ShapeMode.SHAPE_RECT, 0.1f);
                            CameraServer.getInstance().setImage(latestImage);
                        } catch(Exception ex) {
                            System.out.println("err");
                            ex.printStackTrace();
                        }
                    } else {
                        try {
                            NIVision.IMAQdxGrab(camses2, tempImage, 1);
                            //int top = SmartDashboard.getInt("vis top", 75);
                            //int left = SmartDashboard.getInt("vis left", 57);
                            //int width = SmartDashboard.getInt("vis width", 100);
                            //int height = SmartDashboard.getInt("vis height", 100);
                            NIVision.imaqFlip(tempImage2, tempImage, NIVision.FlipAxis.HORIZONTAL_AXIS);
                            NIVision.imaqFlip(latestImage, tempImage2, NIVision.FlipAxis.VERTICAL_AXIS);
                            //NIVision.imaqDrawShapeOnImage(latestImage, tempImage, new NIVision.Rect(top, left, height, width), NIVision.DrawMode.DRAW_VALUE, NIVision.ShapeMode.SHAPE_RECT, 0.1f);
                            CameraServer.getInstance().setImage(latestImage);
                        } catch(Exception ex) {
                            System.out.println("err");
                            ex.printStackTrace();
                        }
                    }
                } else {
                    if(shooterCam) {
                        NIVision.IMAQdxGrab(camses, latestImage, 1);
                        System.out.println("Cammmmmm");
                    } else {
                        try {
                            NIVision.Image tempImage = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
                            NIVision.Image tempImage2 = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
                            armCamera.getImage(tempImage);
                            NIVision.imaqFlip(tempImage2, tempImage, NIVision.FlipAxis.HORIZONTAL_AXIS);
                            NIVision.imaqFlip(latestImage, tempImage2, NIVision.FlipAxis.VERTICAL_AXIS);
                        } catch(Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    CameraServer.getInstance().setImage(latestImage);
                }
            }
            Timer.waitMillis(50);
        }
    }

    public void end() {
        this.running = false;
    }

    public void setShooterCam(boolean shooterCam) {
        if(shooterCam) {
            try {
                NIVision.IMAQdxCloseCamera(camses2);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            try {
                NIVision.IMAQdxCloseCamera(camses);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            camses = NIVision.IMAQdxOpenCamera("cam2", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
            NIVision.IMAQdxConfigureGrab(camses);
        } else {
            try {
                NIVision.IMAQdxCloseCamera(camses);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            try {
                NIVision.IMAQdxCloseCamera(camses2);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            camses2 = NIVision.IMAQdxOpenCamera("cam3", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
            NIVision.IMAQdxConfigureGrab(camses2);
        }
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
