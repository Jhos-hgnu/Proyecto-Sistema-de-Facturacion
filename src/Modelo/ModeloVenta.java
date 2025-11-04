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

    // === Atributos (según la tabla "venta") ===
    private long idVenta;               // id_venta
    private String nit;                 // nit del cliente
    private Date fecha;                 // fecha
    private String tipoPago;            // tipo_pago
    private String documento;           // documento
    private double total;               // total
    private long idUsuario;             // id_usuario (vendedor)
    private String observacion;         // observacion
    private String clientesNit;         // clientes_nit (relación con tabla clientes)
    private long usuariosIdUsuario;     // Usuarios_Id_usuario (relación con tabla usuarios)

    // Constructor vacío (por defecto)
    public ModeloVenta() {
    }

    // Constructor con todos los parámetros
    public ModeloVenta(long idVenta, String nit, Date fecha, String tipoPago,
                       String documento, double total, long idUsuario,
                       String observacion, String clientesNit, long usuariosIdUsuario) {
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

    // === Getters y Setters ===
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

    // === Método de utilidad para mostrar información ===
    public void mostrarInfo() {
        System.out.println("=== Información de Venta ===");
        System.out.println("ID Venta: " + idVenta);
        System.out.println("NIT Cliente: " + nit);
        System.out.println("Fecha: " + fecha);
        System.out.println("Tipo de Pago: " + tipoPago);
        System.out.println("Documento: " + documento);
        System.out.println("Total: Q" + total);
        System.out.println("ID Usuario (Vendedor): " + idUsuario);
        System.out.println("Observación: " + observacion);
        System.out.println("===============================");
    }
}
