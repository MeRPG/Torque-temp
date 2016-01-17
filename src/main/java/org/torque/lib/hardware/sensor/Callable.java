package org.torque.lib.hardware.sensor;

import com.google.common.collect.Sets;
import org.torque.lib.concurrent.Task;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Manages tasks which can called.
 * Extend this class with another in order to manage these tasks outside of the class being extended.
 * This class can also be used to handle these tasks as a field.
 * @author Jaxon A Brown
 */
public class Callable {
    private Set<Task> taskList;

    /**
     * Create a new callable.
     */
    public Callable() {
        taskList = Sets.newHashSet();
    }

    /**
     * Add a task to the list of tasks to call
     * @param task task to add
     */
    public synchronized void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Remove a task for the list of tasks to call
     * @param task task to remove
     */
    public synchronized void removeTask(Task task) {
        this.taskList.remove(task);
    }

    /**
     * Calls the tasks in the set. This will not call any tasks which have been cancelled, and it will remove any such
     * tasks from the task set.
     */
    public synchronized void callTasks() {
        taskList = taskList.stream().filter((task) -> {
            if(task.isCancelled()) {
                return false;
            }
            task.run();
            return !task.isCancelled();
        }).collect(Collectors.toSet());
    }
}
