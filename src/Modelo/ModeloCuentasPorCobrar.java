/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author luisd
 */
public class ModeloCuentasPorCobrar {

    // Atributos según la tabla cuenta_por_cobrar
    private long idCuentaCobro;
    private long idVenta;
    private Date fechaEmision;
    private Date fechaVence;
    private double monto;
    private double saldo;
    private String estado;
    private long ventaIdVenta;

    // Constructor vacío
    public ModeloCuentasPorCobrar() {
    }

    // Constructor con parámetros
    public ModeloCuentasPorCobrar(long idCuentaCobro, long idVenta, Date fechaEmision,
                                 Date fechaVence, double monto, double saldo,
                                 String estado, long ventaIdVenta) {
        this.idCuentaCobro = idCuentaCobro;
        this.idVenta = idVenta;
        this.fechaEmision = fechaEmision;
        this.fechaVence = fechaVence;
        this.monto = monto;
        this.saldo = saldo;
        this.estado = estado;
        this.ventaIdVenta = ventaIdVenta;
    }

    // Getters y Setters
    public long getIdCuentaCobro() {
        return idCuentaCobro;
    }

    public void setIdCuentaCobro(long idCuentaCobro) {
        this.idCuentaCobro = idCuentaCobro;
    }

    public long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(long idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaVence() {
        return fechaVence;
    }

    public void setFechaVence(Date fechaVence) {
        this.fechaVence = fechaVence;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public long getVentaIdVenta() {
        return ventaIdVenta;
    }

    public void setVentaIdVenta(long ventaIdVenta) {
        this.ventaIdVenta = ventaIdVenta;
    }

    // Método para mostrar información
    public void mostrarInfo() {
        System.out.println("ID Cuenta por Cobrar: " + idCuentaCobro);
        System.out.println("Venta Asociada (ID): " + idVenta);
        System.out.println("Fecha de Emisión: " + fechaEmision);
        System.out.println("Fecha de Vencimiento: " + fechaVence);
        System.out.println("Monto Total: " + monto);
        System.out.println("Saldo Pendiente: " + saldo);
        System.out.println("Estado: " + estado);
    }
}
