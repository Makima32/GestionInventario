package com.mycompany.controller;

import com.mycompany.models.Producto;
import com.mycompany.models.ListaProductos;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Conversor de archivos XML a objetos Producto
 * Lee un archivo XML y lo convierte en una lista de productos
 */
public class XmlAObjeto {

    /**
     * Convierte un archivo XML en una lista de productos
     * Usa JAXB para deserializar
     *
     * @param rutaXml Ruta del archivo XML a leer
     * @return Lista de productos leídos del XML
     * @throws Exception Si hay error al leer el archivo
     */
    public static List<Producto> convertir(String rutaXml) throws Exception {
        JAXBContext contexto = JAXBContext.newInstance(ListaProductos.class);
        Unmarshaller deserializador = contexto.createUnmarshaller();

        File archivoXml = new File(rutaXml);
        ListaProductos contenedor = (ListaProductos) deserializador.unmarshal(archivoXml);

        return contenedor.getProductos() != null ? contenedor.getProductos() : new ArrayList<>();
    }

    /**
     * Método main para ejecutar la conversión de forma independiente
     */
    public static void main(String[] args) {
        String rutaXml = "src/main/java/com/mycompany/Recursos/productos.xml";

        try {
            List<Producto> productos = convertir(rutaXml);
            System.out.println("Conversión completada: " + productos.size() + " productos cargados.");

            for (Producto p : productos) {
                System.out.println("- " + p.getNombre() + " (" + p.getCategoria() + ")");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
