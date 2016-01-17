package org.torque.lib.concurrent;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author Jaxon A Brown
 */
class RepeatingTask extends Thread {
    private Task task;
    private long targetMillis;
    private long interval;

    RepeatingTask(Task task, long waitMillis, long interval) {
        this.task = task;
        this.targetMillis = waitMillis + System.currentTimeMillis();
        this.interval = interval;
        super.start();
    }

    public Task getTask() {
        return task;
    }

    @Override
    public void run() {
        while(!task.isCanelled()) {
            if(System.currentTimeMillis() >= targetMillis) {
                break;
            }
            Timer.waitMillis(1);
        }

        while(!task.isCanelled()) {
            task.run();
            if(task.isCanelled()) {
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
