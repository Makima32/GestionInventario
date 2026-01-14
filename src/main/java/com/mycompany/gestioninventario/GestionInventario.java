package com.mycompany.gestioninventario;

import com.mycompany.view.InventarioFrame;

import javax.swing.*;

/**
 * Clase principal de la aplicación de Gestión de Inventario
 * Lanza la interfaz gráfica
 *
 */
public class GestionInventario {

    public static void main(String[] args) {
        // Configurar Look and Feel del sistema para mejor apariencia
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Lanzar la aplicación en el Event Dispatch Thread
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InventarioFrame ventana = new InventarioFrame();
                ventana.setVisible(true);
            }
        });
    }
}
