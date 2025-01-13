package com.focus.DAO;

import com.focus.Model.Database;
import com.focus.Model.Pomodoro;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PomodoroDAO {
    private Connection connection;

    public PomodoroDAO() throws SQLException {
        this.connection = Database.getConnection();
    }

    // Ajouter une session Pomodoro
    public void addPomodoro(Pomodoro pomodoro) throws SQLException {
        String sql = "INSERT INTO pomodoro (user_id, session_duration, break_duration, session_date, session_start_time) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, pomodoro.getUserId());
        stmt.setInt(2, pomodoro.getSessionDuration());
        stmt.setInt(3, pomodoro.getBreakDuration());
        stmt.setDate(4, java.sql.Date.valueOf(pomodoro.getSessionDate())); // LocalDate -> SQL Date
        stmt.setTimestamp(5, java.sql.Timestamp.valueOf(pomodoro.getSessionStartTime())); // LocalDateTime -> SQL Timestamp
        stmt.executeUpdate();
    }
    

    // Vérifier si une session Pomodoro est active
    public Pomodoro getActivePomodoro(int userId) throws SQLException {
        String sql = """
                SELECT * FROM pomodoro 
                WHERE user_id = ? 
                AND session_date + INTERVAL session_duration MINUTE > NOW()
                """;
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return mapToPomodoro(rs);
        }
        return null; // Pas de session active
    }

    // Obtenir une session Pomodoro par ID
    public Pomodoro getPomodoroById(int id) throws SQLException {
        String sql = "SELECT * FROM pomodoro WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return mapToPomodoro(rs);
        }
        return null;
    }

    // Liste des sessions Pomodoro d'un utilisateur
    public List<Pomodoro> getPomodorosByUserId(int userId) throws SQLException {
        List<Pomodoro> pomodoros = new ArrayList<>();
        String sql = "SELECT * FROM pomodoro WHERE user_id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            pomodoros.add(mapToPomodoro(rs));
        }
        return pomodoros;
    }

    // Mettre à jour une session Pomodoro
    public void updatePomodoro(Pomodoro pomodoro) throws SQLException {
        String sql = "UPDATE pomodoro SET session_duration = ?, break_duration = ?, session_date = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, pomodoro.getSessionDuration());
        stmt.setInt(2, pomodoro.getBreakDuration());
        stmt.setDate(3, Date.valueOf(pomodoro.getSessionDate())); // Conversion LocalDate -> SQL Date
        stmt.setInt(4, pomodoro.getId());
        stmt.executeUpdate();
    }

    // Supprimer une session Pomodoro
    public void deletePomodoro(int id) throws SQLException {
        String sql = "DELETE FROM pomodoro WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    // Mapper un ResultSet à un objet Pomodoro
    private Pomodoro mapToPomodoro(ResultSet rs) throws SQLException {
    return new Pomodoro(
        rs.getInt("id"),
        rs.getInt("user_id"),
        rs.getInt("session_duration"),
        rs.getInt("break_duration"),
        rs.getTimestamp("session_date").toLocalDateTime() // Conversion SQL Timestamp -> LocalDateTime
    );
}

}
