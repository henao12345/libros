package com.consulta_libros.cesar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/literAlura"; // Cambia localhost y literAlura si es necesario
    private static final String USER = "postgres"; // Cambia por tu usuario de PostgreSQL
    private static final String PASSWORD = "0"; // Cambia por tu contraseña de PostgreSQL

    public static Connection connect() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos");
            return connection;
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            return null;  // Retorna null si no se puede establecer la conexión
        }
    }
}
