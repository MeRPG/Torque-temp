package org.torque.lib.concurrent;

import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * Singleton class to handle the scheduling of tasks.
 * @author Jaxon A Brown
 */
public class Scheduler {
    private static Scheduler instance;
    static {
        instance = new Scheduler();
    }

    private HashSet<Thread> tasks;

    private Scheduler() {
        this.tasks = new LinkedHashSet<Thread>();
    }

    synchronized void remove(Thread thread) {
        tasks.remove(thread);
    }

    private synchronized void scheduleTaskDelayed(Task task, long delay) {
        tasks.add(new DelayedTask(task, delay));
    }

    private synchronized void scheduleTaskRepeating(Task task, long delay, long interval) {
        tasks.add(new RepeatingTask(task, delay, interval));
    }

    /**
     * Schedule a delayed task
     * @param task task to fire
     * @param delay milliseconds after which to fire the task
     */
    public static void scheduleDelayedTask(Task task, long delay) {
        getScheduler().scheduleTaskDelayed(task, delay);
    }

    /**
     * Schedule a repeating task
     * @param task task to fire
     * @param delay milliseconds after which to start the repeater
     * @param interval millisecond interval upon which to fire task
     */
    public static void scheduleRepeatingTask(Task task, long delay, long interval) {
        getScheduler().scheduleTaskRepeating(task, delay, interval);
    }

    static Scheduler getScheduler() {
        return instance;
    }
}
