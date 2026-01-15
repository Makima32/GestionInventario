package com.mycompany.controller;

import com.mycompany.models.Producto;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

/**
 * Conversor de lista de productos a archivo CSV
 * Guarda productos en formato CSV
 */
public class ObjetoACsv {

    /**
     * Convierte una lista de productos a formato CSV
     * Formato de salida: Nombre,Cantidad,Precio,Categoria
     *
     * @param productos Lista de productos a guardar
     * @param rutaCsv Ruta del archivo CSV de salida
     * @throws Exception Si hay error al escribir el archivo
     */
    public static void convertir(List<Producto> productos, String rutaCsv) throws Exception {
        try (PrintWriter pw = new PrintWriter(new FileWriter(rutaCsv))) {
            for (Producto p : productos) {
                pw.println(
                    p.getNombre() + "," +
                    p.getCantidad() + "," +
                    p.getPrecio() + "," +
                    p.getCategoria()
                );
            }
        }
    }

    /**
     * Método main para ejecutar la conversión de forma independiente
     */
    public static void main(String[] args) {
        String rutaDat = "src/main/java/com/mycompany/Recursos/productos.dat";
        String rutaCsv = "src/main/java/com/mycompany/Recursos/productos.csv";

        try {
            // Cargar productos desde DAT
            List<Producto> productos = DatHelper.cargar(rutaDat);

            // Convertir a CSV
            convertir(productos, rutaCsv);

            System.out.println("Conversión a CSV completada: " + productos.size() + " productos.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}