package org.torque.lib.concurrent;

/**
 * @author Jaxon A Brown
 */
public abstract class Task {
    private boolean isCanelled;

    public Task() {
        isCanelled = false;
    }

    public final synchronized void setCanelled(boolean canelled) {
        this.isCanelled = canelled;
    }

    public final synchronized boolean isCanelled() {
        return isCanelled;
    }

    public abstract void run();
}
