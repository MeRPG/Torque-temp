package org.torque.lib.concurrent;

import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * @author Jaxon A Brown
 */
public class Scheduler {
    private static Scheduler instance;
    static {
        instance = new Scheduler();
    }

    private HashSet<Thread> tasks;

    public Scheduler() {
        this.tasks = new LinkedHashSet<Thread>();
    }

    synchronized void remove(Thread thread) {
        tasks.remove(thread);
    }

    public synchronized void scheduleTaskDelayed(Task task, long delay) {
        tasks.add(new DelayedTask(task, delay));
    }

    public synchronized void scheduleTaskRepeating(Task task, long delay, long interval) {
        tasks.add(new RepeatingTask(task, delay, interval));
    }

    public static void scheduleDelayedTask(Task task, long delay) {
        getScheduler().scheduleTaskDelayed(task, delay);
    }

    public static void scheduleRepeatingTask(Task task, long delay, long interval) {
        getScheduler().scheduleTaskRepeating(task, delay, interval);
    }

    static Scheduler getScheduler() {
        return instance;
    }
}
