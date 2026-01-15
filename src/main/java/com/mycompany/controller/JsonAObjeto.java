package com.mycompany.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.models.Producto;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Conversor de archivos JSON a objetos Producto
 * Lee un archivo JSON y lo convierte en una lista de productos
 */
public class JsonAObjeto {

    /**
     * Convierte un archivo JSON en una lista de productos
     *
     * @param rutaJson Ruta del archivo JSON a leer
     * @return Lista de productos leídos del JSON
     * @throws Exception Si hay error al leer el archivo
     */
    public static List<Producto> convertir(String rutaJson) throws Exception {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(rutaJson)) {
            List<Producto> productos = gson.fromJson(reader, new TypeToken<List<Producto>>() {}.getType());
            return productos != null ? productos : new ArrayList<>();
        }
    }

    /**
     * Método main para ejecutar la conversión de forma independiente
     */
    public static void main(String[] args) {
        String rutaJson = "src/main/java/com/mycompany/Recursos/productos.json";

        try {
            List<Producto> productos = convertir(rutaJson);
            System.out.println("Conversión completada: " + productos.size() + " productos cargados.");

            for (Producto p : productos) {
                System.out.println("- " + p.getNombre());
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
