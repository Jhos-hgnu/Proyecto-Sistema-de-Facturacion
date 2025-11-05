/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

public class CuentaPorPagar {
    private int idCuentaPago;
    private int idCompra;
    private Date fechaEmision;
    private Date fechaVencimiento;
    private double montoTotal;
    private double saldoPendiente;
    private String estadoPago;

    // ====== Constructores ======
    public CuentaPorPagar() {}

    public CuentaPorPagar(int idCuentaPago, int idCompra, Date fechaEmision, Date fechaVencimiento,
                          double montoTotal, double saldoPendiente, String estadoPago, int comprasIdCompra) {
        this.idCuentaPago = idCuentaPago;
        this.idCompra = idCompra;
        this.fechaEmision = fechaEmision;
        this.fechaVencimiento = fechaVencimiento;
        this.montoTotal = montoTotal;
        this.saldoPendiente = saldoPendiente;
        this.estadoPago = estadoPago;
        
    }

    // ====== Getters y Setters ======

    public int getIdCuentaPago() {
        return idCuentaPago;
    }

    public void setIdCuentaPago(int idCuentaPago) {
        this.idCuentaPago = idCuentaPago;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }



    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public double getSaldoPendiente() {
        return saldoPendiente;
    }

    public void setSaldoPendiente(double saldoPendiente) {
        this.saldoPendiente = saldoPendiente;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }


    
    

}