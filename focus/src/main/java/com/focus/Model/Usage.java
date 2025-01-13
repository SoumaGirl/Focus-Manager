package com.focus.Model;

public class Usage {
    private String appName;
    private int usageTime; // Temps d'utilisation en minutes
    private boolean isProductive;

    // Constructeurs
    public Usage(String appName, int usageTime, boolean isProductive) {
        this.appName = appName;
        this.usageTime = usageTime;
        this.isProductive = isProductive;
    }

    // Getters et Setters
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

    public boolean isProductive() {
        return isProductive;
    }

    public void setProductive(boolean productive) {
        isProductive = productive;
    }
}
