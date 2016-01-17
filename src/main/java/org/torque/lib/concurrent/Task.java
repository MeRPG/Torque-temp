package org.torque.lib.concurrent;

/**
 * Task which can be called and cancelled.
 * @author Jaxon A Brown
 */
public abstract class Task {
    private boolean isCancelled;

    /**
     * Construct a new task, set cancelled to false.
     */
    public Task() {
        isCancelled = false;
    }

    /**
     * Cancel the task
     * @param cancelled the state of cancellation to set the task to
     */
    public final synchronized void setCancelled(boolean cancelled) {
        this.isCancelled = cancelled;
    }

    /**
     * Check if this task is cancelled
     * @return true if the task is cancelled, false otherwise
     */
    public final synchronized boolean isCancelled() {
        return isCancelled;
    }

    /**
     * The runnable part of the task.
     */
    public abstract void run();
}
