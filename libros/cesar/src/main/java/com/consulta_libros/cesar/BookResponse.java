package com.consulta_libros.cesar;

import java.util.List;

public class BookResponse {
    private List<Book> books;

    // Getters and setters
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
