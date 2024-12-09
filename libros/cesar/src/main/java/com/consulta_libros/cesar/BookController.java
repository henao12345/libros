package com.consulta_libros.cesar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public void insertBook(@RequestBody Book book) {
        bookService.insertBookWithAuthor(book);
    }
}
