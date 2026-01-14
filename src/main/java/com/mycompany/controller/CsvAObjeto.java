package com.mycompany.controller;

import com.mycompany.models.Producto;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Conversor de archivos CSV a objetos Producto
 * Lee un archivo CSV y lo convierte en una lista de productos
 */
public class CsvAObjeto {

    /**
     * Convierte un archivo CSV en una lista de productos
     * Formato esperado: Nombre,Cantidad,Precio,Categoria
     *
     * @param rutaCsv Ruta del archivo CSV a leer
     * @return Lista de productos leídos del CSV
     * @throws Exception Si hay error al leer el archivo
     */
    public static List<Producto> convertir(String rutaCsv) throws Exception {
        List<Producto> productos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(rutaCsv))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    Producto p = new Producto(
                        partes[0],                      // nombre
                        Integer.parseInt(partes[1]),    // cantidad
                        Double.parseDouble(partes[2]),  // precio
                        partes[3]                       // categoria
                    );
                    productos.add(p);
                }
            }
        }

        return productos;
    }

    /**
     * Método main para ejecutar la conversión de forma independiente
     */
    public static void main(String[] args) {
        String rutaCsv = "src/main/java/com/mycompany/Recursos/productos.csv";

        try {
            List<Producto> productos = convertir(rutaCsv);
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