package com.consulta_libros.cesar;

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class CesarApplication {

	public static void main(String[] args) {
		CesarApplication app = new CesarApplication();
		app.run();
	}

	public void run() {
		ApiConsumer apiConsumer = new ApiConsumer();
		BookService bookService = new BookService();
		Scanner scanner = new Scanner(System.in);

		boolean continuar = true;
		while (continuar) {
			mostrarMenu();
			System.out.print("Seleccione una opción: ");

			// Manejo de entrada incorrecta (InputMismatchException)
			int opcion = leerEntero(scanner, "Error: Entrada no válida. Por favor, ingrese un número entero.");

			switch (opcion) {
				case 1:
					// Buscar un libro por título
					System.out.print("Ingrese el título del libro para buscar: ");
					String titulo = scanner.nextLine();
					List<Book> libros = apiConsumer.buscarLibros(titulo); // Buscar libros con el título
					mostrarLibros(libros);
					break;
				case 2:
					// Listar autores de los libros encontrados
					List<Book> libros2 = apiConsumer.buscarLibros("Java"); // Aquí se hace una búsqueda por "Java", por ejemplo
					List<Author> autores = bookService.listarAutores(libros2);
					mostrarAutores(autores);
					break;
				case 3:
					// Listar autores vivos en un año específico
					System.out.print("Ingrese el año para listar autores vivos: ");
					int año = leerEntero(scanner, "Error: Entrada no válida. Por favor, ingrese un número entero para el año.");
					List<Book> libros3 = apiConsumer.buscarLibros("Java"); // Aquí se hace una búsqueda por "Java", por ejemplo
					List<Author> autoresVivos = bookService.listarAutoresVivosEnAno(libros3, año);
					mostrarAutores(autoresVivos);
					break;
				case 0:
					System.out.println("Saliendo de la aplicación...");
					continuar = false;
					break;
				default:
					System.out.println("Opción inválida. Intente nuevamente.");
					break;
			}
		}

		scanner.close();
	}

	private void mostrarMenu() {
		System.out.println("\n--- Menú ---");
		System.out.println("1. Buscar libro");
		System.out.println("2. Listar autores");
		System.out.println("3. Listar autores vivos en un año");
		System.out.println("0. Salir");
	}

	private void mostrarLibros(List<Book> libros) {
		if (libros.isEmpty()) {
			System.out.println("No se encontraron libros.");
		} else {
			System.out.println("\nLibros encontrados:");
			for (Book libro : libros) {
				System.out.println("Título: " + libro.getTitle());
				System.out.println("Autor: " + libro.getAuthor().getName());
			}
		}
	}

	private void mostrarAutores(List<Author> autores) {
		if (autores.isEmpty()) {
			System.out.println("No se encontraron autores.");
		} else {
			System.out.println("\nAutores encontrados:");
			for (Author autor : autores) {
				System.out.println("Nombre: " + autor.getName());
				System.out.println("Año de nacimiento: " + autor.getBirthYear());
				if (autor.getDeathYear() != null) {
					System.out.println("Año de fallecimiento: " + autor.getDeathYear());
				} else {
					System.out.println("Autor vivo");
				}
			}
		}
	}

	private int leerEntero(Scanner scanner, String mensajeError) {
		int valor = -1;
		boolean valido = false;
		while (!valido) {
			try {
				valor = scanner.nextInt();
				scanner.nextLine();  // Limpiar el buffer
				valido = true;
			} catch (InputMismatchException e) {
				System.out.println(mensajeError);
				scanner.nextLine();  // Limpiar el buffer
			}
		}
		return valor;
	}
}
