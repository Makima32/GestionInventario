package com.mycompany.controller;

import com.mycompany.models.Producto;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase auxiliar para operaciones con archivos DAT (binarios)
 * Proporciona métodos para cargar y guardar listas de productos
 */
public class DatHelper {

    /**
     * Carga productos desde un archivo binario DAT
     *
     * @param rutaDat Ruta del archivo DAT a leer
     * @return Lista de productos leídos del archivo
     * @throws Exception Si hay error al leer el archivo
     */
    public static List<Producto> cargar(String rutaDat) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaDat))) {
            @SuppressWarnings("unchecked")
            List<Producto> productos = (List<Producto>) ois.readObject();
            return productos != null ? productos : new ArrayList<>();
        }
    }

    /**
     * Guarda productos en un archivo binario DAT
     *
     * @param productos Lista de productos a guardar
     * @param rutaDat Ruta del archivo DAT de salida
     * @throws Exception Si hay error al escribir el archivo
     */
    public static void guardar(List<Producto> productos, String rutaDat) throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaDat))) {
            oos.writeObject(productos);
        }
    }
}
