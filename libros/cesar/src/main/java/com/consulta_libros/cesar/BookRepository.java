package com.consulta_libros.cesar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BookRepository {

    public void insertarLibros(List<Book> libros) {
        try (Connection conn = DatabaseConnector.connect()) {
            String query = "INSERT INTO libros (id, title, authors, download_count, copyright) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);

            for (Book libro : libros) {
                stmt.setLong(1, libro.getId());
                stmt.setString(2, libro.getTitle());
                stmt.setString(3, libro.getAuthor().getName()); // Utilizamos el nombre del autor
                stmt.setInt(4, libro.getDownloadCount());
                stmt.setBoolean(5, libro.isCopyright());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar libros: " + e.getMessage());
        }
    }

    // Método save para guardar un único libro
    public void save(Book libro) {
        try (Connection conn = DatabaseConnector.connect()) {
            String query = "INSERT INTO libros (title, authors, download_count, copyright) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, libro.getTitle());
            stmt.setString(2, libro.getAuthor().getName());
            stmt.setInt(3, libro.getDownloadCount());
            stmt.setBoolean(4, libro.isCopyright());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al guardar el libro: " + e.getMessage());
        }
    }
}
