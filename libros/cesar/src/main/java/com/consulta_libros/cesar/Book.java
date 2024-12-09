package com.consulta_libros.cesar;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Aquí el id es de tipo Long

    private String title;
    private int downloadCount;
    private boolean copyright;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;  // Relación Many-to-One con Author

    // Constructor vacío
    public Book() {}

    // Constructor sin id, para creación de un nuevo libro (id manejado por JPA)
    public Book(String title, int downloadCount, boolean copyright, Author author) {
        this.title = title;
        this.downloadCount = downloadCount;
        this.copyright = copyright;
        this.author = author;
    }

    // Constructor con id, para casos en que el id ya está definido (usando Long)
    public Book(Long id, String title, Author author, int downloadCount, boolean copyright) {
        this.id = id;  // El id ahora es de tipo Long
        this.title = title;
        this.author = author;
        this.downloadCount = downloadCount;
        this.copyright = copyright;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public boolean isCopyright() {
        return copyright;
    }

    public void setCopyright(boolean copyright) {
        this.copyright = copyright;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
