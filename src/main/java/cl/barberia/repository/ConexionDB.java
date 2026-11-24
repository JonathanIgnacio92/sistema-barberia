package cl.barberia.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    // Si tu phpMyAdmin tiene contraseña (como en Mac/MAMP suele ser "root"), cámbiala aquí. En XAMPP suele ser "".
    private static final String URL = "jdbc:mysql://localhost:3306/barberia_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConexion() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }
}