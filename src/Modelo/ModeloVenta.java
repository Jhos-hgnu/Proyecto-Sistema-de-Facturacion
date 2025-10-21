/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;
import Vistas.PanelVentas;
import java.math.BigDecimal;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jhosu
 */
public class ModeloVenta {

    // Atributos
    private long idVenta;
    private String nit;
    private Date fecha;
    private String tipoPago;
    private String documento;
    private double total;
    private long idUsuario;
    private String observacion;
    private String clientesNit;
    private long usuariosIdUsuario;

    private PanelVentas vistaVentas;

    public ModeloVenta(PanelVentas vistaVentas) {
        this.vistaVentas = vistaVentas;
    }

    public PanelVentas getVistaVentas() {
        return vistaVentas;
    }

    // Constructor vacío
    public ModeloVenta() {
    }

    // Constructor con parámetros
    public ModeloVenta(long idVenta, String nit, Date fecha, String tipoPago, String documento,
            double total, long idUsuario, String observacion,
            String clientesNit, long usuariosIdUsuario) {
        this.idVenta = idVenta;
        this.nit = nit;
        this.fecha = fecha;
        this.tipoPago = tipoPago;
        this.documento = documento;
        this.total = total;
        this.idUsuario = idUsuario;
        this.observacion = observacion;
        this.clientesNit = clientesNit;
        this.usuariosIdUsuario = usuariosIdUsuario;
    }

    // Getters y Setters
    public long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(long idVenta) {
        this.idVenta = idVenta;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getClientesNit() {
        return clientesNit;
    }

    public void setClientesNit(String clientesNit) {
        this.clientesNit = clientesNit;
    }

    public long getUsuariosIdUsuario() {
        return usuariosIdUsuario;
    }

    public void setUsuariosIdUsuario(long usuariosIdUsuario) {
        this.usuariosIdUsuario = usuariosIdUsuario;
    }

    // Método para mostrar información
    public void mostrarInfo() {
        System.out.println("ID Venta: " + idVenta);
        System.out.println("NIT Cliente: " + nit);
        System.out.println("Fecha: " + fecha);
        System.out.println("Tipo de Pago: " + tipoPago);
        System.out.println("Total: " + total);
        System.out.println("Usuario que atendió: " + idUsuario);
        System.out.println("Observación: " + observacion);
    }
}
