package org.torque.lib.concurrent;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Internal repeating task thread. This class will wait a certain time, then call it's task on a defined interval.
 * @author Jaxon A Brown
 */
class RepeatingTask extends Thread {
    private Task task;
    private long targetMillis;
    private long interval;

    /**
     * Constructs a repeating task, which will start itself.
     * @param task Task to call after the delay and every period.
     * @param waitMillis Time to delay.
     * @param interval Interval on which to call the task.
     */
    RepeatingTask(Task task, long waitMillis, long interval) {
        this.task = task;
        this.targetMillis = waitMillis + System.currentTimeMillis();
        this.interval = interval;
        super.start();
    }

    /**
     * The task which will be called.
     * @return the task this DelayedTask is tied to.
     */
    public Task getTask() {
        return task;
    }

    @Override
    public void run() {
        while(!task.isCancelled()) {
            if(System.currentTimeMillis() >= targetMillis) {
                break;
            }
            Timer.waitMillis(1);
        }

        while(!task.isCancelled()) {
            task.run();
            if(task.isCancelled()) {
                break;
            }

            Timer.waitMillis(interval);
        }

        Scheduler.getScheduler().remove(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.getId()).append(task).hashCode();
    }
}
