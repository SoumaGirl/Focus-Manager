package com.focus.utils;

public class PomodoroTimer {

    private long startTime;
    private long sessionDuration; // en millisecondes

    public PomodoroTimer(long sessionDurationMinutes) {
        this.sessionDuration = sessionDurationMinutes * 60 * 1000; // Convertir en millisecondes
    }

    public void startTimer() {
        this.startTime = System.currentTimeMillis();
    }

    public boolean isSessionOver() {
        long currentTime = System.currentTimeMillis();
        return (currentTime - startTime) >= sessionDuration;
    }

    public long getTimeRemaining() {
        long currentTime = System.currentTimeMillis();
        return sessionDuration - (currentTime - startTime);
    }
}