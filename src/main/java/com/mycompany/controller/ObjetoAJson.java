package com.mycompany.controller;

import com.google.gson.Gson;
import com.mycompany.models.Producto;

import java.io.FileWriter;
import java.util.List;

/**
 * Conversor de lista de productos a archivo JSON
 * Guarda productos en formato JSON
 */
public class ObjetoAJson {

    /**
     * Convierte una lista de productos a formato JSON
     *
     * @param productos Lista de productos a guardar
     * @param rutaJson Ruta del archivo JSON de salida
     * @throws Exception Si hay error al escribir el archivo
     */
    public static void convertir(List<Producto> productos, String rutaJson) throws Exception {
        Gson gson = new Gson();

        try (FileWriter writer = new FileWriter(rutaJson)) {
            gson.toJson(productos, writer);
        }
    }

    /**
     * Método main para ejecutar la conversión de forma independiente
     */
    public static void main(String[] args) {
        String rutaDat = "src/main/java/com/mycompany/Recursos/productos.dat";
        String rutaJson = "src/main/java/com/mycompany/Recursos/nuevoProductos.json";

        try {
            // Cargar productos desde DAT
            List<Producto> productos = DatHelper.cargar(rutaDat);

            // Convertir a JSON
            convertir(productos, rutaJson);

            System.out.println("Conversión a JSON completada: " + productos.size() + " productos.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
