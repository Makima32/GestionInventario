package com.mycompany.controller;

import com.mycompany.models.Producto;
import com.mycompany.models.ListaProductos;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

/**
 * Conversor de lista de productos a archivo XML
 * Guarda productos en formato XML usando JAXB
 */
public class ObjetoAXml {

    /**
     * Convierte una lista de productos a formato XML
     * Usa JAXB para serializar
     *
     * @param productos Lista de productos a guardar
     * @param rutaXml Ruta del archivo XML de salida
     * @throws Exception Si hay error al escribir el archivo
     */
    public static void convertir(List<Producto> productos, String rutaXml) throws Exception {
        ListaProductos contenedor = new ListaProductos(productos);

        JAXBContext contexto = JAXBContext.newInstance(ListaProductos.class);
        Marshaller serializador = contexto.createMarshaller();
        serializador.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        serializador.marshal(contenedor, new File(rutaXml));
    }

    /**
     * Método main para ejecutar la conversión de forma independiente
     */
    
    public static void main(String[] args) {
        String rutaDat = "src/main/java/com/mycompany/Recursos/productos.dat";
        String rutaXml = "src/main/java/com/mycompany/Recursos/productos.xml";

        try {
            // Cargar productos desde DAT
            List<Producto> productos = DatHelper.cargar(rutaDat);

            // Convertir a XML
            convertir(productos, rutaXml);

            System.out.println("Conversión a XML completada: " + productos.size() + " productos.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
