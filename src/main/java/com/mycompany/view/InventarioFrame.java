package com.mycompany.view;

import com.mycompany.models.Producto;
import com.mycompany.controller.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Frame principal de la aplicación de gestión de inventario
 * Permite abrir, editar, añadir, eliminar y guardar productos en diferentes formatos
 */
public class InventarioFrame extends JFrame {

    // Componentes
    private JTable tabla;
    private ProductoTableModel tableModel;
    private JButton btnAbrir, btnGuardar, btnAnadir, btnEliminar;

    /**
     * Constructor del frame
     */
    public InventarioFrame() {
        initComponents();
        configurarTabla();
        configurarEventos();
    }

    /**
     * Inicializa los componentes de la interfaz
     */
    private void initComponents() {
        setTitle("Gestión de Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Toolbar
        JToolBar toolbar = crearToolbar();
        add(toolbar, BorderLayout.NORTH);

        // Tabla
        tableModel = new ProductoTableModel();
        tabla = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Crea la barra de herramientas con botones
     */
    private JToolBar crearToolbar() {
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnAbrir = new JButton("Abrir");
        btnGuardar = new JButton("Guardar");
        btnAnadir = new JButton("Añadir Producto");
        btnEliminar = new JButton("Eliminar Producto");

        // Configurar tamaño de botones
        Dimension btnSize = new Dimension(150, 30);
        btnAbrir.setPreferredSize(btnSize);
        btnGuardar.setPreferredSize(btnSize);
        btnAnadir.setPreferredSize(btnSize);
        btnEliminar.setPreferredSize(btnSize);

        toolbar.add(btnAbrir);
        toolbar.add(Box.createHorizontalStrut(5));
        toolbar.add(btnGuardar);
        toolbar.addSeparator(new Dimension(20, 0));
        toolbar.add(btnAnadir);
        toolbar.add(Box.createHorizontalStrut(5));
        toolbar.add(btnEliminar);

        return toolbar;
    }

    /**
     * Configura las propiedades de la tabla
     */
    private void configurarTabla() {
        // Modo de selección
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setFillsViewportHeight(true);
        tabla.getTableHeader().setReorderingAllowed(false);

        // Renderizado de celdas numéricas alineadas a la derecha
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        tabla.getColumnModel().getColumn(1).setCellRenderer(rightRenderer); // Cantidad

        // Formato de precio con 2 decimales
        DefaultTableCellRenderer currencyRenderer = new DefaultTableCellRenderer() {
            private final DecimalFormat formatter = new DecimalFormat("#,##0.00");

            @Override
            public void setValue(Object value) {
                setText((value == null) ? "" : formatter.format(value) + " €");
            }
        };
        currencyRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        tabla.getColumnModel().getColumn(2).setCellRenderer(currencyRenderer); // Precio

        // Ancho de columnas
        tabla.getColumnModel().getColumn(0).setPreferredWidth(250); // Nombre
        tabla.getColumnModel().getColumn(1).setPreferredWidth(100); // Cantidad
        tabla.getColumnModel().getColumn(2).setPreferredWidth(120); // Precio
        tabla.getColumnModel().getColumn(3).setPreferredWidth(200); // Categoría

        // Altura de filas
        tabla.setRowHeight(25);
    }

    /**
     * Configura los eventos de los botones
     */
    private void configurarEventos() {
        btnAbrir.addActionListener(e -> abrirArchivo());
        btnGuardar.addActionListener(e -> guardarArchivo());
        btnAnadir.addActionListener(e -> anadirProducto());
        btnEliminar.addActionListener(e -> eliminarProducto());
    }

    /**
     * Abre un archivo de productos en cualquier formato soportado
     */
    private void abrirArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Abrir archivo de productos");

        // Agregar filtros de extensión
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Archivos CSV (*.csv)", "csv"));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Archivos JSON (*.json)", "json"));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Archivos XML (*.xml)", "xml"));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Archivos DAT (*.dat)", "dat"));
        fileChooser.setAcceptAllFileFilterUsed(true);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            String rutaArchivo = archivo.getAbsolutePath();

            try {
                // Detectar formato por extensión
                String extension = archivo.getName().substring(archivo.getName().lastIndexOf(".") + 1).toLowerCase();

                // Cargar productos según el formato
                List<Producto> productos = switch (extension) {
                    case "csv" -> CsvAObjeto.convertir(rutaArchivo);
                    case "json" -> JsonAObjeto.convertir(rutaArchivo);
                    case "xml" -> XmlAObjeto.convertir(rutaArchivo);
                    case "dat" -> DatHelper.cargar(rutaArchivo);
                    default -> {
                        JOptionPane.showMessageDialog(this,
                            "Formato de archivo no reconocido. Use CSV, JSON, XML o DAT.",
                            "Formato no soportado",
                            JOptionPane.ERROR_MESSAGE);
                        yield null;
                    }
                };

                if (productos == null) return;

                // Actualizar tabla
                tableModel.setProductos(productos);

                JOptionPane.showMessageDialog(this,
                    "Archivo cargado exitosamente: " + productos.size() + " productos",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error al cargar el archivo:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    /**
     * Guarda los productos en un archivo del formato seleccionado
     */
    private void guardarArchivo() {
        // Verificar que hay productos para guardar
        if (tableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,
                "No hay productos para guardar",
                "Advertencia",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar productos como...");

        // Agregar filtros de extensión
        FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("Archivos CSV (*.csv)", "csv");
        FileNameExtensionFilter jsonFilter = new FileNameExtensionFilter("Archivos JSON (*.json)", "json");
        FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("Archivos XML (*.xml)", "xml");
        FileNameExtensionFilter datFilter = new FileNameExtensionFilter("Archivos DAT (*.dat)", "dat");

        fileChooser.addChoosableFileFilter(csvFilter);
        fileChooser.addChoosableFileFilter(jsonFilter);
        fileChooser.addChoosableFileFilter(xmlFilter);
        fileChooser.addChoosableFileFilter(datFilter);
        fileChooser.setFileFilter(csvFilter); // Por defecto CSV

        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            String rutaArchivo = archivo.getAbsolutePath();

            try {
                // Obtener el filtro seleccionado
                String extension = "";
                if (fileChooser.getFileFilter() instanceof FileNameExtensionFilter) {
                    FileNameExtensionFilter filtro = (FileNameExtensionFilter) fileChooser.getFileFilter();
                    extension = filtro.getExtensions()[0];
                }

                // Asegurar que el archivo tiene la extensión correcta
                if (!rutaArchivo.toLowerCase().endsWith("." + extension)) {
                    rutaArchivo += "." + extension;
                }

                // Obtener productos de la tabla
                List<Producto> productos = tableModel.getProductos();

                // Guardar según el formato
                switch (extension.toLowerCase()) {
                    case "csv" -> ObjetoACsv.convertir(productos, rutaArchivo);
                    case "json" -> ObjetoAJson.convertir(productos, rutaArchivo);
                    case "xml" -> ObjetoAXml.convertir(productos, rutaArchivo);
                    case "dat" -> DatHelper.guardar(productos, rutaArchivo);
                    default -> throw new Exception("Formato no soportado: " + extension);
                }

                JOptionPane.showMessageDialog(this,
                    "Archivo guardado exitosamente:\n" + rutaArchivo,
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error al guardar el archivo:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    /**
     * Abre el diálogo para añadir un nuevo producto
     */
    private void anadirProducto() {
        ProductoDialog dialog = new ProductoDialog(this);
        dialog.setVisible(true);

        if (dialog.isAceptado()) {
            Producto nuevoProducto = dialog.getProducto();
            tableModel.addProducto(nuevoProducto);

            // Seleccionar la última fila (el producto recién añadido)
            int ultimaFila = tableModel.getRowCount() - 1;
            tabla.setRowSelectionInterval(ultimaFila, ultimaFila);
            tabla.scrollRectToVisible(tabla.getCellRect(ultimaFila, 0, true));
        }
    }

    /**
     * Elimina el producto seleccionado después de confirmación
     */
    private void eliminarProducto() {
        int selectedRow = tabla.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Por favor, seleccione un producto para eliminar",
                "Advertencia",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        Producto p = tableModel.getProductoAt(selectedRow);

        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de eliminar el producto '" + p.getNombre() + "'?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            tableModel.removeProducto(selectedRow);

            JOptionPane.showMessageDialog(this,
                "Producto eliminado correctamente",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
