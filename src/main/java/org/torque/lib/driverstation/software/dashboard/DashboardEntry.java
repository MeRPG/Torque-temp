package org.torque.lib.driverstation.software.dashboard;

import java.lang.reflect.Field;

/**
 * Configurable dashboard entry.
 * @author Jaxon A Brown
 */
public class DashboardEntry {
    private String name;
    private Field configurationField;

    /**
     * Construct a non-configurable dashboard entry.
     * @param name Name/key of entry
     */
    public DashboardEntry(String name) {
        this(name, null);
    }

    /**
     * Construct a configurable dashboard entry.
     * @param name Name/key of entry
     * @param configurationField field which should be updated to reflect changes in the Dashboard. The field should be static.
     */
    public DashboardEntry(String name, Field configurationField) {
        this.name = name;
        this.configurationField = configurationField;
    }

    /**
     * Get the Name/Key of this entry.
     * @return Name/Key of this entry.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the configuration field for this entry.
     * @return configuration field for this entry, null if this is a non-configurable entry.
     */
    public Field getConfigurationField() {
        return this.configurationField;
    }

    <T> void writeToField(T data) {
        if(!configurationField.getType().isAssignableFrom(data.getClass())) {
            throw new ClassCastException("Could not use " + data.getClass().getSimpleName() + " data for a " +
                    configurationField.getType().getSimpleName() + " field.");
        }
        try {
            configurationField.set(null, data);
        } catch(IllegalAccessException ex) {
            throw new RuntimeException("Could not write to field.", ex);
        }
    }
}
