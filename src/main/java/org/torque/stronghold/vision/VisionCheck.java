package org.torque.stronghold.vision;

import com.ni.vision.NIVision;

/**
 * Created by Jaxon A Brown on 1/30/2016.
 */
public interface VisionCheck {
    boolean check(NIVision.Image image);
}
