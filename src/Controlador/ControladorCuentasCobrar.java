/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author luisd
 */

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

    public ControladorCuentasCobrar(PanelCuentasCobrar vista, Connection conexion) {
        this.vista = vista;
        this.dao = new CuentasPorCobrarDAO(conexion);
        inicializarEventos();
    }

    private void inicializarEventos() {
        vista.btnAgregar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                agregar();
            }
        });

        vista.btnBuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buscar();
            }
        });

        vista.btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eliminar();
            }
        });

        vista.btnCobrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cobrar();
            }
        });
    }

    // 🟢 AGREGAR
    private void agregar() {
        try {
            ModeloCuentasPorCobrar c = obtenerDesdeVista();
            dao.insertar(c);
            JOptionPane.showMessageDialog(vista, "Cuenta agregada correctamente.");
            limpiar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al agregar: " + ex.getMessage());
        }
    }

    // 🟡 BUSCAR
    private void buscar() {
        try {
            long id = Long.parseLong(vista.txtIdCuentaCobro.getText());
            ModeloCuentasPorCobrar c = dao.buscarPorId(id);

            if (c != null) {
                vista.txtIdVenta.setText(String.valueOf(c.getIdVenta()));
                vista.txtFechaEmision.setText(c.getFechaEmision().toString());
                vista.txtFechaVencimiento.setText(c.getFechaVence().toString());
                vista.txtMonto.setText(String.valueOf(c.getMonto()));
                vista.txtSaldo.setText(String.valueOf(c.getSaldo()));
                vista.txtEstado.setText(c.getEstado());
            } else {
                JOptionPane.showMessageDialog(vista, "No se encontró la cuenta.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al buscar: " + ex.getMessage());
        }
    }

    // 🔴 ELIMINAR
    private void eliminar() {
        try {
            long id = Long.parseLong(vista.txtIdCuentaCobro.getText());
            int conf = JOptionPane.showConfirmDialog(vista, "¿Seguro que deseas eliminar esta cuenta?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (conf == JOptionPane.YES_OPTION) {
                dao.eliminar(id);
                JOptionPane.showMessageDialog(vista, "Cuenta eliminada correctamente.");
                limpiar();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al eliminar: " + ex.getMessage());
        }
    }

    // 💰 COBRAR
    private void cobrar() {
        try {
            long id = Long.parseLong(vista.txtIdCuentaCobro.getText());
            ModeloCuentasPorCobrar c = dao.buscarPorId(id);

            if (c != null) {
                c.setSaldo(0);
                c.setEstado("Pagado");
                dao.actualizar(c);
                JOptionPane.showMessageDialog(vista, "Cuenta cobrada correctamente.");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(vista, "No se encontró la cuenta.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al cobrar: " + ex.getMessage());
        }
    }

    // 🧾 Obtener datos desde la vista
    private ModeloCuentasPorCobrar obtenerDesdeVista() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        long idCuentaCobro = Long.parseLong(vista.txtIdCuentaCobro.getText());
        long idVenta = Long.parseLong(vista.txtIdVenta.getText());
        Date fechaEmision = sdf.parse(vista.txtFechaEmision.getText());
        Date fechaVence = sdf.parse(vista.txtFechaVencimiento.getText());
        double monto = Double.parseDouble(vista.txtMonto.getText());
        double saldo = Double.parseDouble(vista.txtSaldo.getText());
        String estado = vista.txtEstado.getText();

        // ventaIdVenta se puede usar igual que idVenta si es la FK
        return new ModeloCuentasPorCobrar(idCuentaCobro, idVenta, fechaEmision, fechaVence, monto, saldo, estado, idVenta);
    }

    private void limpiar() {
        vista.txtIdCuentaCobro.setText("");
        vista.txtIdVenta.setText("");
        vista.txtFechaEmision.setText("");
        vista.txtFechaVencimiento.setText("");
        vista.txtMonto.setText("");
        vista.txtSaldo.setText("");
        vista.txtEstado.setText("");
    }
}
