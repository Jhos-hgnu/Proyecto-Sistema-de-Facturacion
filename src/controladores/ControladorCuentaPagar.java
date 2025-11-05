/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

/**
 *
 * @author jciri
 */

//importaciones 


import Vistas.PanelCuentaPagar;
import Implementacion.CuentaPorPagarImpl;
import Modelo.CuentaPorPagar;
import javax.swing.JOptionPane;
import java.sql.Date;



public class ControladorCuentaPagar {

    private PanelCuentaPagar vista;
    private CuentaPorPagarImpl dao;

    public ControladorCuentaPagar(PanelCuentaPagar vista, CuentaPorPagarImpl dao) {
        this.vista = vista;
        this.dao = dao;

    }


    public void agregarCuenta() {
        try {
            CuentaPorPagar cuenta = new CuentaPorPagar();
            cuenta.setIdCuentaPago(Integer.parseInt(vista.getTxtIdCuentaPago().getText()));
            cuenta.setIdCompra(Integer.parseInt(vista.getTxtIdCompra().getText()));
            
            
            //arreglar problema fecha
            try {
                cuenta.setFechaEmision(Date.valueOf(vista.getTxtFechaEmision().getText()));
                cuenta.setFechaVencimiento(Date.valueOf(vista.getTxtFechaVencimiento().getText()));
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use yyyy-MM-dd");
                return;
            }
            cuenta.setMontoTotal(Double.parseDouble(vista.getTxtMontoTotal().getText()));
            cuenta.setSaldoPendiente(Double.parseDouble(vista.getTxtSaldoPendiente().getText()));
            cuenta.setEstadoPago(vista.getTxtEstadoPago().getText());

            if (dao.agregar(cuenta)) {
                JOptionPane.showMessageDialog(null, "Cuenta por pagar agregada correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al agregar la cuenta");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    public void actualizarCuenta() {
        try {
            CuentaPorPagar cuenta = new CuentaPorPagar();
            cuenta.setIdCuentaPago(Integer.parseInt(vista.getTxtIdCuentaPago().getText()));
            cuenta.setIdCompra(Integer.parseInt(vista.getTxtIdCompra().getText()));    
            
            //arreglar problema fecha
            try {
                cuenta.setFechaEmision(Date.valueOf(vista.getTxtFechaEmision().getText()));
                cuenta.setFechaVencimiento(Date.valueOf(vista.getTxtFechaVencimiento().getText()));
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use yyyy-MM-dd");
                return;
            }
            cuenta.setMontoTotal(Double.parseDouble(vista.getTxtMontoTotal().getText()));
            cuenta.setSaldoPendiente(Double.parseDouble(vista.getTxtSaldoPendiente().getText()));
            cuenta.setEstadoPago(vista.getTxtEstadoPago().getText());

            if (dao.actualizar(cuenta)) {
                JOptionPane.showMessageDialog(null, "Cuenta por pagar actualizada correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar la cuenta");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    public void eliminarCuenta() {
        try {
            int id = Integer.parseInt(vista.getTxtIdCuentaPago().getText());
            if (dao.eliminar(id)) {
                JOptionPane.showMessageDialog(null, "Cuenta por pagar eliminada correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar la cuenta");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }
}
