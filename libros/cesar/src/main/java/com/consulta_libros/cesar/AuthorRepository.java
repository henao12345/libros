package com.consulta_libros.cesar;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    // Puedes agregar consultas personalizadas si es necesario
}
