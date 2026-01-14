package com.mycompany.view;

import com.mycompany.models.Producto;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Modelo de tabla personalizado para gestionar productos
 * Extiende AbstractTableModel para proporcionar funcionalidad CRUD
 */
public class ProductoTableModel extends AbstractTableModel {

    private List<Producto> productos;
    private final String[] columnNames = {"Nombre", "Cantidad", "Precio", "Categoría"};

    /**
     * Constructor que inicializa con una lista vacía
     */
    public ProductoTableModel() {
        this.productos = new ArrayList<>();
    }

    // ========== MÉTODOS OBLIGATORIOS DE AbstractTableModel ==========

    @Override
    public int getRowCount() {
        return productos.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Producto p = productos.get(row);

        return switch (col) {
            case 0 -> p.getNombre();
            case 1 -> p.getCantidad();
            case 2 -> p.getPrecio();
            case 3 -> p.getCategoria();
            default -> null;
        };
    }

    /**
     * Hace todas las celdas editables
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        return true;
    }

    /**
     * Actualiza el modelo cuando se edita una celda
     * Incluye validación de datos
     */
    @Override
    public void setValueAt(Object value, int row, int col) {
        Producto p = productos.get(row);

        try {
            switch (col) {
                case 0 -> {
                    String nombre = value.toString().trim();
                    if (nombre.isEmpty()) {
                        throw new IllegalArgumentException("El nombre no puede estar vacío");
                    }
                    p.setNombre(nombre);
                }
                case 1 -> {
                    int cantidad = Integer.parseInt(value.toString());
                    if (cantidad < 0) {
                        throw new IllegalArgumentException("La cantidad no puede ser negativa");
                    }
                    p.setCantidad(cantidad);
                }
                case 2 -> {
                    double precio = Double.parseDouble(value.toString());
                    if (precio < 0) {
                        throw new IllegalArgumentException("El precio no puede ser negativo");
                    }
                    p.setPrecio(precio);
                }
                case 3 -> {
                    String categoria = value.toString().trim();
                    if (categoria.isEmpty()) {
                        throw new IllegalArgumentException("La categoría no puede estar vacía");
                    }
                    p.setCategoria(categoria);
                }
            }
            fireTableCellUpdated(row, col);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                "Valor numérico inválido: " + value,
                "Error de formato",
                JOptionPane.ERROR_MESSAGE);

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null,
                e.getMessage(),
                "Error de validación",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    // ========== MÉTODOS PERSONALIZADOS ==========

    /**
     * Reemplaza toda la lista de productos y notifica a la tabla
     */
    public void setProductos(List<Producto> productos) {
        this.productos = productos != null ? productos : new ArrayList<>();
        fireTableDataChanged();
    }

    /**
     * Obtiene una copia defensiva de la lista de productos
     */
    public List<Producto> getProductos() {
        return new ArrayList<>(productos);
    }

    /**
     * Añade un nuevo producto a la lista
     */
    public void addProducto(Producto p) {
        productos.add(p);
        fireTableRowsInserted(productos.size() - 1, productos.size() - 1);
    }

    /**
     * Elimina un producto por índice de fila
     */
    public void removeProducto(int row) {
        if (row >= 0 && row < productos.size()) {
            productos.remove(row);
            fireTableRowsDeleted(row, row);
        }
    }

    /**
     * Obtiene el producto en una fila específica
     */
    public Producto getProductoAt(int row) {
        if (row >= 0 && row < productos.size()) {
            return productos.get(row);
        }
        return null;
    }
}
