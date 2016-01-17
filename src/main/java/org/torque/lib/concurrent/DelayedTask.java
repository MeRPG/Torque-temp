package org.torque.lib.concurrent;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author Jaxon A Brown
 */
class DelayedTask extends Thread {
    private Task task;
    private long targetMillis;

    DelayedTask(Task task, long waitMillis) {
        this.task = task;
        this.targetMillis = waitMillis + System.currentTimeMillis();
        super.start();
    }

    public Task getTask() {
        return task;
    }

    @Override
    public void run() {
        while(!task.isCanelled()) {
            if(System.currentTimeMillis() >= targetMillis) {
                task.run();
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
