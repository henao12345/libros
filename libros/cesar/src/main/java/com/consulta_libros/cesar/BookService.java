package com.consulta_libros.cesar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public void insertBookWithAuthor(Book book) {
        Author author = book.getAuthor();

        // Verificar si el autor ya existe en la base de datos
        if (author != null && (author.getId() == null || !authorRepository.existsById(author.getId()))) {
            authorRepository.save(author);
        }

        // Insertar el libro con el autor asociado
        bookRepository.save(book);
    }

    public List<Author> listarAutores(List<Book> libros) {
        // Extraer los autores de la lista de libros y eliminar duplicados
        return libros.stream()
                .map(Book::getAuthor)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Author> listarAutoresVivosEnAno(List<Book> libros, int año) {
        // Filtrar autores que estén vivos en el año especificado
        return libros.stream()
                .map(Book::getAuthor)
                .distinct()
                .filter(author -> author.getBirthYear() <= año &&
                        (author.getDeathYear() == null || author.getDeathYear() > año)) // Verificar que deathYear es null o mayor que el año
                .collect(Collectors.toList());
    }

}

