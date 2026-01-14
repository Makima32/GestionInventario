package com.mycompany.controller;

import com.mycompany.models.Producto;
import java.util.List;

/**
 * Clase utilitaria para leer y mostrar productos desde archivos DAT
 * Útil para verificar el contenido de archivos binarios
 */
public class LeerDatos {

    /**
     * Lee y muestra los productos de un archivo DAT
     *
     * @param rutaDat Ruta del archivo DAT a leer
     * @throws Exception Si hay error al leer el archivo
     */
    public static void mostrar(String rutaDat) throws Exception {
        List<Producto> productos = DatHelper.cargar(rutaDat);

        System.out.println("=== Productos en " + rutaDat + " ===");
        System.out.println("Total: " + productos.size() + " productos\n");

        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            System.out.println((i + 1) + ". " + p.getNombre());
            System.out.println("   Cantidad: " + p.getCantidad());
            System.out.println("   Precio: " + p.getPrecio() + " €");
            System.out.println("   Categoría: " + p.getCategoria());
            System.out.println();
        }
    }

    /**
     * Método main para ejecutar la lectura de forma independiente
     */
    public static void main(String[] args) {
        String rutaDat = "src/main/java/com/mycompany/Recursos/productos.dat";

        try {
            mostrar(rutaDat);
        } catch (Exception e) {
            System.out.println("Error al leer datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
