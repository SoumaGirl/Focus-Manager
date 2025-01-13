package com.focus.Model;

public class Report {
    private String period; // Ex: "Day", "Week", "Month", "Year"
    private int focusedTime; // Temps concentré en minutes

    public Report(String period, int focusedTime) {
        this.period = period;
        this.focusedTime = focusedTime;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public int getFocusedTime() {
        return focusedTime;
    }

    public void setFocusedTime(int focusedTime) {
        this.focusedTime = focusedTime;
    }
}


