package org.torque.lib.robot;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;
import edu.wpi.first.wpilibj.communication.UsageReporting;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * Mostly copied from IterativeRobot.
 * Modified to make Autonomous a linear program rather than a looping program.
 * @author Jaxon A Brown
 */
public abstract class TorqueRobot extends RobotBase {
    private boolean disabledInitialized = false;
    private boolean autonomousRun = false;
    private boolean teleopInitialized = false;
    private boolean testInitialized = false;

    public final void startCompetition() {
        UsageReporting.report(22, 1);
        this.robotInit();
        FRCNetworkCommunicationsLibrary.FRCNetworkCommunicationObserveUserProgramStarting();
        LiveWindow.setEnabled(false);

        while(true) {
            if(this.isDisabled()) {
                if(!this.disabledInitialized) {
                    LiveWindow.setEnabled(false);
                    this.disabledInit();
                    this.disabledInitialized = true;
                    this.teleopInitialized = false;
                    this.testInitialized = false;
                    this.autonomousRun = false;
                }

                if(this.nextPeriodReady()) {
                    FRCNetworkCommunicationsLibrary.FRCNetworkCommunicationObserveUserProgramDisabled();
                    this.disabledPeriodic();
                    Timer.delay(0.001D);
                }
            } else if(this.isTest()) {
                if(!this.testInitialized) {
                    LiveWindow.setEnabled(true);
                    this.testInit();
                    this.testInitialized = true;
                    this.autonomousRun = false;
                    this.teleopInitialized = false;
                    this.disabledInitialized = false;
                }

                if(this.nextPeriodReady()) {
                    FRCNetworkCommunicationsLibrary.FRCNetworkCommunicationObserveUserProgramTest();
                    this.testPeriodic();
                }
            } else if(this.isAutonomous()) {
                if(!this.autonomousRun) {
                    LiveWindow.setEnabled(false);
                    this.autonomousRun = true;
                    this.testInitialized = false;
                    this.teleopInitialized = false;
                    this.disabledInitialized = false;
                    FRCNetworkCommunicationsLibrary.FRCNetworkCommunicationObserveUserProgramAutonomous();

                    this.autonomous();
                }
            } else {
                if(!this.teleopInitialized) {
                    LiveWindow.setEnabled(false);
                    this.teleopInit();
                    this.teleopInitialized = true;
                    this.testInitialized = false;
                    this.autonomousRun = false;
                    this.disabledInitialized = false;
                }

                if(this.nextPeriodReady()) {
                    FRCNetworkCommunicationsLibrary.FRCNetworkCommunicationObserveUserProgramTeleop();
                    this.teleopPeriodic();
                    Timer.delay(0.001D);
                }
            }

            this.m_ds.waitForData();
        }
    }

    private boolean nextPeriodReady() {
        return this.m_ds.isNewControlData();
    }

    /**
     * Called when the robot is first started.
     */
    public abstract void robotInit();

    /**
     * Called when the robot is first stopped.
     */
    public abstract void disabledInit();

    /**
     * Called when the Teleop is first started.
     */
    public abstract void teleopInit();

    /**
     * Called when the Test is first started.
     */
    public abstract void testInit();

    /**
     * Called periodically throughout robot disablement.
     */
    public abstract void disabledPeriodic();

    /**
     * Called to run the autonomous phase.
     */
    public abstract void autonomous();

    /**
     * Called periodically throughout the Teleop phase.
     */
    public abstract void teleopPeriodic();

    /**
     * Called periodically when the robot is in testing.
     */
    public abstract void testPeriodic();
}
