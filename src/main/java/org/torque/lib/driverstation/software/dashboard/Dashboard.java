package org.torque.lib.driverstation.software.dashboard;

import com.google.common.collect.Maps;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.Map;

/**
 * Singleton class that helps to manage dashboard entries.
 * Primarily it should be used to handle Configuration entries.
 * @author Jaxon A Brown
 */
public class Dashboard {
    private static final Dashboard instance;
    static {
        instance = new Dashboard();
    }

    /**
     * Grab yourself your very own Dashboard instance!
     * @return JK, its the same one I'm giving everyone else, because this is a Singleton class.
     */
    public static Dashboard getInstance() {
        return instance;
    }

    private Map<String, DashboardEntry> dashboardEntries;

    private Dashboard() {
        NetworkTable networkTable;
        try {
            networkTable = (NetworkTable) SmartDashboard.class.getDeclaredField("table").get(null);
        } catch(NoSuchFieldException | IllegalAccessException ex) {
            throw new RuntimeException("Couldn't start Dashboard.", ex);
        }
        this.dashboardEntries = Maps.newHashMap();
        networkTable.addTableListener((source, key, value, isNew) -> {
            if(!isNew) {
                return;
            }
            if(!dashboardEntries.containsKey(key)) {
                return;
            }

            DashboardEntry dashboardEntry = dashboardEntries.get(key);
            if(dashboardEntry.getConfigurationField() == null) {
                return;
            }

            dashboardEntry.writeToField(value);
        }, true);
    }

    /**
     * Add a dashboard entry to the tracking list of the Dashboard. This allows it to update configurations.
     * @param entry Entry to add to the list.
     */
    public void addDashboardEntry(DashboardEntry entry) {
        dashboardEntries.put(entry.getName(), entry);
    }

    /**
     * Blatantly wraps SmartDashboard's version of this method. You can use that one, if you like.
     */
    public void putBoolean(String key, boolean value) {
        SmartDashboard.putBoolean(key, value);
    }

    /**
     * Blatantly wraps SmartDashboard's version of this method. You can use that one, if you like.
     */
    public void putDouble(String key, double value) {
        SmartDashboard.putNumber(key, value);
    }

    /**
     * Blatantly wraps SmartDashboard's version of this method. You can use that one, if you like.
     */
    public void putString(String key, String value) {
        SmartDashboard.putString(key, value);
    }

    /**
     * Blatantly wraps SmartDashboard's version of this method. You can use that one, if you like.
     */
    public boolean getBoolean(String key) {
        return SmartDashboard.getBoolean(key);
    }

    /**
     * Blatantly wraps SmartDashboard's version of this method. You can use that one, if you like.
     */
    public double getDouble(String key) {
        return SmartDashboard.getNumber(key);
    }

    /**
     * Blatantly wraps SmartDashboard's version of this method. You can use that one, if you like.
     */
    public String getString(String key) {
        return SmartDashboard.getString(key);
    }
}
