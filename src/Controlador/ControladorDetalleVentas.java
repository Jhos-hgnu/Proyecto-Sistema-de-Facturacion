/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Implementacion.DetalleVentasDAO;
import Modelo.ModeloDetalleVenta;
import Vistas.PanelDetalleVenta;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author luisd
 */

public class ControladorDetalleVentas {

    private final PanelDetalleVenta vista;
    private final DetalleVentasDAO dao;
    private final ModeloDetalleVenta modelo;

    public ControladorDetalleVentas(PanelDetalleVenta vista) {
        this.vista = vista;
        this.dao = new DetalleVentasDAO();
        this.modelo = new ModeloDetalleVenta();

        // Asignar eventos
        this.vista.agregar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                agregarDetalle();
            }
        });

        this.vista.actualizar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actualizarDetalle();
            }
        });

        this.vista.btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eliminarDetalle();
            }
        });
    }

    // === MÉTODO: Agregar Detalle de Venta ===
    private void agregarDetalle() {
        try {
            modelo.setIdDetalleVenta(Long.parseLong(vista.txtIdDetalleVenta.getText()));
            modelo.setIdVenta(Long.parseLong(vista.txtIdVenta.getText()));
            modelo.setIdProducto(Long.parseLong(vista.txtIdProducto.getText()));
            modelo.setCantidad(Integer.parseInt(vista.txtCantidad.getText()));
            modelo.setPrecioVenta(Double.parseDouble(vista.txtPrecioVenta.getText()));
            modelo.setDescuento(Double.parseDouble(vista.txtDescuento.getText()));
            modelo.setImpuesto(Double.parseDouble(vista.txtImpuesto.getText()));

            // Claves foráneas (por ahora igual que los ID)
            modelo.setVentaIdVenta(modelo.getIdVenta());
            modelo.setProductosIdProducto(modelo.getIdProducto());

            boolean exito = dao.insertarDetalleVenta(modelo);

            if (exito) {
                JOptionPane.showMessageDialog(vista, "✅ Detalle de venta agregado correctamente.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, "❌ No se pudo agregar el detalle de venta.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "⚠️ Verifica los datos ingresados. Deben ser numéricos donde corresponda.");
        }
    }

    // === MÉTODO: Actualizar Detalle ===
    private void actualizarDetalle() {
        try {
            modelo.setIdDetalleVenta(Long.parseLong(vista.txtIdDetalleVenta.getText()));
            modelo.setIdVenta(Long.parseLong(vista.txtIdVenta.getText()));
            modelo.setIdProducto(Long.parseLong(vista.txtIdProducto.getText()));
            modelo.setCantidad(Integer.parseInt(vista.txtCantidad.getText()));
            modelo.setPrecioVenta(Double.parseDouble(vista.txtPrecioVenta.getText()));
            modelo.setDescuento(Double.parseDouble(vista.txtDescuento.getText()));
            modelo.setImpuesto(Double.parseDouble(vista.txtImpuesto.getText()));
            modelo.setVentaIdVenta(modelo.getIdVenta());
            modelo.setProductosIdProducto(modelo.getIdProducto());

            boolean exito = dao.actualizarDetalleVenta(modelo);

            if (exito) {
                JOptionPane.showMessageDialog(vista, "✅ Detalle actualizado correctamente.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, "❌ No se pudo actualizar el detalle.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "⚠️ Ingresa valores válidos para actualizar.");
        }
    }

    // === MÉTODO: Eliminar Detalle ===
    private void eliminarDetalle() {
        try {
            long id = Long.parseLong(vista.txtIdDetalleVenta.getText());

            int confirm = JOptionPane.showConfirmDialog(vista,
                    "¿Seguro que deseas eliminar este detalle?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                boolean exito = dao.eliminarDetalleVenta(id);

                if (exito) {
                    JOptionPane.showMessageDialog(vista, "🗑️ Detalle eliminado correctamente.");
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(vista, "❌ No se pudo eliminar el detalle.");
                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "⚠️ Ingresa un ID válido para eliminar.");
        }
    }

    // === MÉTODO: Limpiar campos ===
    private void limpiarCampos() {
        vista.txtIdDetalleVenta.setText("");
        vista.txtIdVenta.setText("");
        vista.txtIdProducto.setText("");
        vista.txtCantidad.setText("");
        vista.txtPrecioVenta.setText("");
        vista.txtDescuento.setText("");
        vista.txtImpuesto.setText("");
    }
}