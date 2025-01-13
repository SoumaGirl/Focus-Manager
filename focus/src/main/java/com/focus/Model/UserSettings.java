package com.focus.Model;

public class UserSettings {
    private int id;
    private String alarmSound;
    private int intervals;
    private String focusTime;
    private String shortBreak;
    private String longBreak;

    // Constructor
    public UserSettings(int id, String alarmSound, int intervals, String focusTime, String shortBreak, String longBreak) {
        this.id = id;
        this.alarmSound = alarmSound;
        this.intervals = intervals;
        this.focusTime = focusTime;
        this.shortBreak = shortBreak;
        this.longBreak = longBreak;
    }

    // Default Constructor
    public UserSettings() {}

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlarmSound() {
        return alarmSound;
    }

    public void setAlarmSound(String alarmSound) {
        this.alarmSound = alarmSound;
    }

    public int getIntervals() {
        return intervals;
    }

    public void setIntervals(int intervals) {
        this.intervals = intervals;
    }

    public String getFocusTime() {
        return focusTime;
    }

    public void setFocusTime(String focusTime) {
        this.focusTime = focusTime;
    }

    public String getShortBreak() {
        return shortBreak;
    }

    public void setShortBreak(String shortBreak) {
        this.shortBreak = shortBreak;
    }

    public String getLongBreak() {
        return longBreak;
    }

    public void setLongBreak(String longBreak) {
        this.longBreak = longBreak;
    }
}