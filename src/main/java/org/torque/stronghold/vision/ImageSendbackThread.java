package org.torque.stronghold.vision;

import com.ni.vision.NIVision;
import edu.wpi.first.wpilibj.CameraServer;
import org.torque.lib.concurrent.Task;
import org.torque.stronghold.ConfigurationService;

/**
 * Created by Jaxon A Brown on 1/30/2016.
 */
public class ImageSendbackThread extends Task {
    private NIVision.Image latestImage;

    public ImageSendbackThread() {}

    @Override
    public void run() {
        latestImage = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        NIVision.IMAQdxGrab(ConfigurationService.FORWARD_FACING_CAMERA_ID, latestImage, 1);
        NIVision.Image image = latestImage;

        CameraServer.getInstance().setImage(image);
    }

    public NIVision.Image getLatestImage() {
        return latestImage;
    }
}
