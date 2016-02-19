package org.torque.lib.concurrent;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Internal delayed task thread. This class will wait a certain time, then call it's task and await GC.
 * @author Jaxon A Brown
 */
class DelayedTask extends Thread {
    private Task task;
    private long targetMillis;

    /**
     * Constructs a delayed task, which will start itself.
     * @param task Task to call after the delay.
     * @param waitMillis Time to wait before calling the task.
     */
    DelayedTask(Task task, long waitMillis) {
        this.task = task;
        this.targetMillis = waitMillis + System.currentTimeMillis();
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
                try {
                    task.run();
                } catch(Throwable ex) {
                    new RuntimeException("Uncaught error in thread.", ex).printStackTrace();
                }
                break;
            }
            Timer.waitMillis(1);
        }
        Scheduler.getScheduler().remove(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.getId()).append(task).hashCode();
    }
}
