package com.mycompany.models;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "inventario") 
public class ListaProductos {
    
    private List<Producto> productos;

    public ListaProductos() {}

    public ListaProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @XmlElement(name = "producto") 
    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}