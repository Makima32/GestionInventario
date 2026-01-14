package com.mycompany.view;

import com.mycompany.models.Producto;

import javax.swing.*;
import java.awt.*;

/**
 * Diálogo modal para añadir nuevos productos
 */
public class ProductoDialog extends JDialog {

    private JTextField txtNombre;
    private JTextField txtCantidad;
    private JTextField txtPrecio;
    private JTextField txtCategoria;

    private Producto productoResultado;
    private boolean aceptado = false;

    /**
     * Constructor del diálogo
     * @param parent Frame padre
     */
    public ProductoDialog(JFrame parent) {
        super(parent, "Añadir Producto", true); // Modal
        initComponents();
    }

    /**
     * Inicializa los componentes del diálogo
     */
    private void initComponents() {
        setLayout(new BorderLayout());
        setSize(400, 250);
        setLocationRelativeTo(getParent());
        setResizable(false);

        // Panel central con el formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nombre
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        panelFormulario.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtNombre = new JTextField(20);
        panelFormulario.add(txtNombre, gbc);

        // Cantidad
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        panelFormulario.add(new JLabel("Cantidad:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtCantidad = new JTextField(20);
        panelFormulario.add(txtCantidad, gbc);

        // Precio
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        panelFormulario.add(new JLabel("Precio:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtPrecio = new JTextField(20);
        panelFormulario.add(txtPrecio, gbc);

        // Categoría
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        panelFormulario.add(new JLabel("Categoría:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtCategoria = new JTextField(20);
        panelFormulario.add(txtCategoria, gbc);

        add(panelFormulario, BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");

        btnAceptar.addActionListener(e -> aceptar());
        btnCancelar.addActionListener(e -> cancelar());

        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        add(panelBotones, BorderLayout.SOUTH);

        // Configurar teclas Enter y Escape
        getRootPane().setDefaultButton(btnAceptar);

        // Escape para cancelar
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke("ESCAPE");
        getRootPane().registerKeyboardAction(e -> cancelar(),
            escapeKeyStroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    /**
     * Maneja el evento de aceptar
     */
    private void aceptar() {
        // Validar campos
        if (!validarCampos()) {
            return;
        }

        try {
            // Crear producto
            String nombre = txtNombre.getText().trim();
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());
            double precio = Double.parseDouble(txtPrecio.getText().trim());
            String categoria = txtCategoria.getText().trim();

            productoResultado = new Producto(nombre, cantidad, precio, categoria);
            aceptado = true;
            dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Error en los valores numéricos. Verifica cantidad y precio.",
                "Error de formato",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Valida que todos los campos estén completos y sean válidos
     */
    private boolean validarCampos() {
        // Validar nombre
        if (txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "El nombre no puede estar vacío",
                "Campo requerido",
                JOptionPane.WARNING_MESSAGE);
            txtNombre.requestFocus();
            return false;
        }

        // Validar cantidad
        String cantidadStr = txtCantidad.getText().trim();
        if (cantidadStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "La cantidad no puede estar vacía",
                "Campo requerido",
                JOptionPane.WARNING_MESSAGE);
            txtCantidad.requestFocus();
            return false;
        }

        try {
            int cantidad = Integer.parseInt(cantidadStr);
            if (cantidad < 0) {
                JOptionPane.showMessageDialog(this,
                    "La cantidad no puede ser negativa",
                    "Valor inválido",
                    JOptionPane.WARNING_MESSAGE);
                txtCantidad.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "La cantidad debe ser un número entero",
                "Formato inválido",
                JOptionPane.WARNING_MESSAGE);
            txtCantidad.requestFocus();
            return false;
        }

        // Validar precio
        String precioStr = txtPrecio.getText().trim();
        if (precioStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "El precio no puede estar vacío",
                "Campo requerido",
                JOptionPane.WARNING_MESSAGE);
            txtPrecio.requestFocus();
            return false;
        }

        try {
            double precio = Double.parseDouble(precioStr);
            if (precio < 0) {
                JOptionPane.showMessageDialog(this,
                    "El precio no puede ser negativo",
                    "Valor inválido",
                    JOptionPane.WARNING_MESSAGE);
                txtPrecio.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "El precio debe ser un número decimal",
                "Formato inválido",
                JOptionPane.WARNING_MESSAGE);
            txtPrecio.requestFocus();
            return false;
        }

        // Validar categoría
        if (txtCategoria.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "La categoría no puede estar vacía",
                "Campo requerido",
                JOptionPane.WARNING_MESSAGE);
            txtCategoria.requestFocus();
            return false;
        }

        return true;
    }

    /**
     * Maneja el evento de cancelar
     */
    private void cancelar() {
        aceptado = false;
        dispose();
    }

    /**
     * Obtiene el producto creado
     */
    public Producto getProducto() {
        return productoResultado;
    }

    /**
     * Indica si el usuario aceptó el diálogo
     */
    public boolean isAceptado() {
        return aceptado;
    }
}
