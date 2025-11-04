/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author luisd
 */

import Conector.DBConnection;
import Implementacion.CuentasPorCobrarDAO;
import Vistas.PanelCuentasCobrar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Implementacion.CuentasPorCobrarDAO;
import Modelo.ModeloCuentasPorCobrar;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ControladorCuentasCobrar {

    private final PanelCuentasCobrar vista;
    private final CuentasPorCobrarDAO dao;
    private final DBConnection conexion;

    public ControladorCuentasCobrar(PanelCuentasCobrar vista) {
        this.vista = vista;
        this.conexion = new DBConnection();
        conexion.conectar();
        Connection link = conexion.getConnection();
        this.dao = new CuentasPorCobrarDAO(link);

        agregarEventos();
    }

    /**
     * Asocia los botones del panel con las acciones del DAO.
     */
    private void agregarEventos() {
        // 🔹 Agregar
        vista.btnAgregar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                insertarCuenta();
            }
        });

        // 🔹 Buscar
        vista.btnBuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buscarCuenta();
            }
        });

        // 🔹 Actualizar / Cobrar
        vista.btnCobrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actualizarCuenta();
            }
        });

        // 🔹 Eliminar
        vista.btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eliminarCuenta();
            }
        });
    }

    // ==================================================
    // 🔸 MÉTODOS DE CONTROL
    // ==================================================

    private void insertarCuenta() {
        try {
            ModeloCuentasPorCobrar c = obtenerDatosFormulario();
            dao.insertar(c);
            JOptionPane.showMessageDialog(vista, "✅ Cuenta registrada correctamente");
            limpiarCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "❌ Error al insertar cuenta: " + ex.getMessage());
        }
    }

    private void buscarCuenta() {
        try {
            long id = Long.parseLong(vista.txtIdCuentaCobro.getText());
            ModeloCuentasPorCobrar c = dao.buscarPorId(id);

            if (c != null) {
                mostrarEnFormulario(c);
            } else {
                JOptionPane.showMessageDialog(vista, "⚠️ No se encontró la cuenta con ID: " + id);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "❌ Error al buscar: " + ex.getMessage());
        }
    }

    private void actualizarCuenta() {
        try {
            ModeloCuentasPorCobrar c = obtenerDatosFormulario();
            dao.actualizar(c);
            JOptionPane.showMessageDialog(vista, "✏️ Cuenta actualizada/cobrada correctamente");
            limpiarCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "❌ Error al actualizar: " + ex.getMessage());
        }
    }

    private void eliminarCuenta() {
        try {
            long id = Long.parseLong(vista.txtIdCuentaCobro.getText());
            dao.eliminar(id);
            JOptionPane.showMessageDialog(vista, "🗑️ Cuenta eliminada correctamente");
            limpiarCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "❌ Error al eliminar: " + ex.getMessage());
        }
    }

    // ==================================================
    // 🔹 MÉTODOS AUXILIARES
    // ==================================================

    /**
     * Convierte los datos de los JTextField en un objeto ModeloCuentasPorCobrar.
     */
    private ModeloCuentasPorCobrar obtenerDatosFormulario() {
        try {
            ModeloCuentasPorCobrar c = new ModeloCuentasPorCobrar();

            c.setIdCuentaCobro(Long.parseLong(vista.txtIdCuentaCobro.getText()));
            c.setIdVenta(Long.parseLong(vista.txtIdVenta.getText()));
            c.setMonto(Double.parseDouble(vista.txtMonto.getText()));
            c.setSaldo(Double.parseDouble(vista.txtSaldo.getText()));
            c.setEstado(vista.txtEstado.getText());
            c.setVentaIdVenta(Long.parseLong(vista.txtIdVenta.getText()));

            // Convierte texto a fecha
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaEmision = sdf.parse(vista.txtFechaEmision.getText());
            Date fechaVence = sdf.parse(vista.txtFechaVencimiento.getText());
            c.setFechaEmision(fechaEmision);
            c.setFechaVence(fechaVence);

            return c;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "⚠️ Error en los datos del formulario: " + e.getMessage());
            return null;
        }
    }

    /**
     * Llena los campos del formulario con los datos del modelo.
     */
    private void mostrarEnFormulario(ModeloCuentasPorCobrar c) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        vista.txtIdCuentaCobro.setText(String.valueOf(c.getIdCuentaCobro()));
        vista.txtIdVenta.setText(String.valueOf(c.getIdVenta()));
        vista.txtFechaEmision.setText(sdf.format(c.getFechaEmision()));
        vista.txtFechaVencimiento.setText(sdf.format(c.getFechaVence()));
        vista.txtMonto.setText(String.valueOf(c.getMonto()));
        vista.txtSaldo.setText(String.valueOf(c.getSaldo()));
        vista.txtEstado.setText(c.getEstado());
    }

    /**
     * Limpia los campos del formulario.
     */
    private void limpiarCampos() {
        vista.txtIdCuentaCobro.setText("");
        vista.txtIdVenta.setText("");
        vista.txtFechaEmision.setText("");
        vista.txtFechaVencimiento.setText("");
        vista.txtMonto.setText("");
        vista.txtSaldo.setText("");
        vista.txtEstado.setText("");
    }
}