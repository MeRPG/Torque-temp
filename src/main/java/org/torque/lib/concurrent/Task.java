package org.torque.lib.concurrent;

/**
 * Task which can be called and cancelled.
 * @author Jaxon A Brown
 */
public abstract class Task {
    private boolean isCanelled;

    /**
     * Construct a new task, set cancelled to false.
     */
    public Task() {
        isCanelled = false;
    }

    /**
     * Cancel the task
     * @param canelled the state of cancellation to set the task to
     */
    public final synchronized void setCanelled(boolean canelled) {
        this.isCanelled = canelled;
    }

    /**
     * Check if this task is cancelled
     * @return true if the task is cancelled, false otherwise
     */
    public final synchronized boolean isCanelled() {
        return isCanelled;
    }

    /**
     * The runnable part of the task.
     */
    public abstract void run();
}
