/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Implementacion.VentaImp;
import Modelo.ModeloClientesVentas;
import Modelo.ModeloDetalleVenta;
import Modelo.ModeloInicioUsuario;
import Modelo.ModeloProducto;
import Modelo.ModeloRegistroCliente;
import Modelo.ModeloVenta;
import Modelo.ModeloVistaInicio;
import Vistas.PanelVentas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jhosu
 */

public class ControladorVentas implements MouseListener {

    private ModeloVenta modelo;
    private PanelVentas vista;
    private DefaultTableModel modeloTabla;
    private JTable tabla;

    // 🔹 Nuevo constructor: recibe modelo y vista
    public ControladorVentas(ModeloVenta modelo, PanelVentas vista) {
        this.modelo = modelo;
        this.vista = vista;

        // Crear tabla de ventas
        modeloTabla = new DefaultTableModel(
                new Object[]{"Producto", "Precio", "Cantidad", "Subtotal"}, 0);
        tabla = new JTable(modeloTabla);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createTitledBorder("Carrito de compras"));
        vista.add(scroll);
        scroll.setBounds(100, 380, 650, 120);

        // Registrar eventos de los botones
        vista.btnAgregar.addMouseListener(this);
        vista.btnEliminar.addMouseListener(this);
        vista.btnHacerVenta.addMouseListener(this);
    }

    // =========================================
    //         EVENTOS DEL RATÓN
    // =========================================
    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();

        if (source.equals(vista.btnAgregar)) {
            agregarProducto();
        } else if (source.equals(vista.btnEliminar)) {
            eliminarProducto();
        } else if (source.equals(vista.btnHacerVenta)) {
            hacerVenta();
        }
    }

    // =========================================
    //         MÉTODOS DE LÓGICA
    // =========================================
    private void agregarProducto() {
        String idProducto = vista.txtIdVenta.getText();
        String precioTxt = vista.txtNIT.getText(); // usas txtNIT como precio
        String cantidadTxt = vista.txtDocumento.getText();

        if (idProducto.isEmpty() || precioTxt.isEmpty() || cantidadTxt.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Completa los campos de producto, precio y cantidad.");
            return;
        }

        try {
            double precio = Double.parseDouble(precioTxt);
            int cantidad = Integer.parseInt(cantidadTxt);
            double subtotal = precio * cantidad;

            modeloTabla.addRow(new Object[]{idProducto, precio, cantidad, subtotal});
            limpiarCampos();
            calcularTotal();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Precio o cantidad no válidos.");
        }
    }

    private void eliminarProducto() {
        int fila = tabla.getSelectedRow();
        if (fila >= 0) {
            modeloTabla.removeRow(fila);
            calcularTotal();
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un producto para eliminar.");
        }
    }

    private void hacerVenta() {
        if (modeloTabla.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No hay productos en la venta.");
            return;
        }

        double total = calcularTotal();
        JOptionPane.showMessageDialog(null, "Venta realizada. Total: $" + total);
        limpiarTabla();
    }

    private double calcularTotal() {
        double total = 0;
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            total += (double) modeloTabla.getValueAt(i, 3);
        }
        vista.txtTotal.setText(String.valueOf(total));
        return total;
    }

    private void limpiarCampos() {
        vista.txtIdVenta.setText("");
        vista.txtNIT.setText("");
        vista.txtDocumento.setText("");
    }

    private void limpiarTabla() {
        modeloTabla.setRowCount(0);
        vista.txtTotal.setText("");
    }

    // =========================================
    //        EFECTOS VISUALES (opcional)
    // =========================================
    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getComponent() instanceof JPanel) {
            e.getComponent().setBackground(new Color(50, 95, 110));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getComponent() instanceof JPanel) {
            e.getComponent().setBackground(new Color(75, 128, 146));
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
}
