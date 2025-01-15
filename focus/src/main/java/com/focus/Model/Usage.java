package com.focus.Model;

public class Usage {
    private int id;
    private String appName;
    private int usageTime; // In seconds or minutes, as preferred.

    public Usage(int id, String appName, int usageTime) {
        this.id = id;
        this.appName = appName;
        this.usageTime = usageTime;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getUsageTime() {
        return usageTime;
    }

    public void setUsageTime(int usageTime) {
        this.usageTime = usageTime;
    }

    @Override
    public String toString() {
        return "Usage{id=" + id + ", appName='" + appName + "', usageTime=" + usageTime + '}';
    }
}
