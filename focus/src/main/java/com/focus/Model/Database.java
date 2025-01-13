package com.focus.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    private static final String URL = "jdbc:mysql://localhost:3306/focus";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static final Logger LOGGER = Logger.getLogger(Database.class.getName());

    private static Connection connection;

    /**
     * Obtient une connexion à la base de données.
     * Si aucune connexion n'existe ou si elle est fermée, une nouvelle connexion est créée.
     *
     * @return Une instance de Connection.
     * @throws SQLException En cas d'échec de connexion ou si le driver JDBC est introuvable.
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                LOGGER.info("Connexion réussie à la base de données : " + URL);
            } catch (ClassNotFoundException e) {
                LOGGER.log(Level.SEVERE, "Driver JDBC introuvable !", e);
                throw new SQLException("Impossible de charger le driver JDBC.", e);
            }
        }
        return connection;
    }

    /**
     * Ferme la connexion à la base de données si elle est ouverte.
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                LOGGER.info("Connexion à la base de données fermée.");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Erreur lors de la fermeture de la connexion !", e);
        }
    }

    /**
     * Teste si une connexion à la base de données peut être établie.
     *
     * @return true si la connexion est réussie, false sinon.
     */
    public static boolean testConnection() {
        try (Connection r = DriverManager.getConnection(URL, USER, PASSWORD)) {
            LOGGER.info("Test de connexion réussi.");
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Échec du test de connexion !", e);
            return false;
        }
    }
}
