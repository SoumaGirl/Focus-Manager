package com.focus.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pomodoro {
    private int id;
    private int userId;
    private int sessionDuration; // en minutes
    private int breakDuration;   // en minutes
    private LocalDate sessionDate;
    private LocalDateTime sessionStartTime;

    public Pomodoro(int id, int userId, int sessionDuration, int breakDuration, LocalDate sessionDate) {
        this.id = id;
        this.userId = userId;
        this.sessionDuration = sessionDuration;
        this.breakDuration = breakDuration;
        this.sessionDate = sessionDate;
    }

    public Pomodoro(int id, int userId, int sessionDuration, int breakDuration, LocalDateTime sessionStartTime) {
        this.id = id;
        this.userId = userId;
        this.sessionDuration = sessionDuration;
        this.breakDuration = breakDuration;
        this.sessionStartTime = sessionStartTime;
        this.sessionDate = sessionStartTime.toLocalDate(); // Extract date from start time
    }
    
    public Pomodoro(int id, int userId, int sessionDuration, int breakDuration, LocalDate sessionDate, LocalDateTime sessionStartTime) {
        this.id = id;
        this.userId = userId;
        this.sessionDuration = sessionDuration;
        this.breakDuration = breakDuration;
        this.sessionDate = sessionDate;
        this.sessionStartTime = sessionStartTime;
    }
    

    // Calculer l'heure de fin de session
    public LocalDateTime getSessionEndTime() {
        return sessionStartTime != null ? sessionStartTime.plusMinutes(sessionDuration) : null;
    }

    // Calculer la durée totale (session + pause)
    public int getTotalDuration() {
        return sessionDuration + breakDuration;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSessionDuration() {
        return sessionDuration;
    }

    public void setSessionDuration(int sessionDuration) {
        this.sessionDuration = sessionDuration;
    }

    public int getBreakDuration() {
        return breakDuration;
    }

    public void setBreakDuration(int breakDuration) {
        this.breakDuration = breakDuration;
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }

    public LocalDateTime getSessionStartTime() {
        return sessionStartTime;
    }

    public void setSessionStartTime(LocalDateTime sessionStartTime) {
        this.sessionStartTime = sessionStartTime;
        if (sessionStartTime != null) {
            this.sessionDate = sessionStartTime.toLocalDate(); // Keep date consistent
        }
    }
}
