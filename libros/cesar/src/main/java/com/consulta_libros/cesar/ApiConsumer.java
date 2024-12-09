package com.consulta_libros.cesar;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ApiConsumer {

    // Método para buscar libros por título
    public List<Book> buscarLibros(String query) {
        List<Book> libros = new ArrayList<>();

        try {
            // Codificar la cadena de búsqueda para que sea segura en la URL
            String encodedQuery = URLEncoder.encode(query, "UTF-8");

            // Lógica para construir la URL de la API con el término de búsqueda
            String urlStr = "https://gutendex.com/books/?search=" + encodedQuery;
            System.out.println("Consultando la API con la URL: " + urlStr);

            // Crear la URL de la API
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(15000);

            // Leer la respuesta de la API
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            Gson gson = new Gson();

            // Analizar la respuesta JSON
            JsonObject responseJson = gson.fromJson(reader, JsonObject.class);

            // Obtener el array de resultados de la respuesta
            JsonArray results = responseJson.getAsJsonArray("results");

            // Si no se encuentran resultados, mostrar mensaje y salir
            if (results == null || results.size() == 0) {
                System.out.println("No se encontraron libros.");
                return libros;
            }

            // Iterar sobre los resultados
            for (int i = 0; i < results.size(); i++) {
                JsonObject bookJson = results.get(i).getAsJsonObject();

                // Extraer los datos de cada libro
                String bookTitle = bookJson.get("title").getAsString();
                int bookId = bookJson.get("id").getAsInt();
                int downloadCount = bookJson.get("download_count").getAsInt();
                boolean copyright = bookJson.get("copyright").getAsBoolean();

                // Manejar el autor de manera segura (el campo puede ser null)
                JsonObject authorJson = bookJson.has("author") ? bookJson.getAsJsonObject("author") : null;
                String authorName = null;
                int birthYear = 0;
                Integer deathYear = null;

                // Si el autor existe, extraer sus datos
                if (authorJson != null) {
                    authorName = authorJson.get("name").getAsString();
                    birthYear = authorJson.get("birth_year").getAsInt();
                    deathYear = authorJson.has("death_year") && !authorJson.get("death_year").isJsonNull()
                            ? authorJson.get("death_year").getAsInt()
                            : null;
                }

                // Si no se tiene autor, asignar valores por defecto
                if (authorName == null) {
                    authorName = "Desconocido";
                    birthYear = 0;
                }

                // Crear objetos Author y Book
                Author author = new Author(authorName, birthYear, deathYear);
                Long bookIdLong = Long.valueOf(bookId);
                Book book = new Book(bookIdLong, bookTitle, author, downloadCount, copyright);

                // Agregar el libro a la lista
                libros.add(book);
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al consultar la API: " + e.getMessage());
        }

        return libros;
    }
}
