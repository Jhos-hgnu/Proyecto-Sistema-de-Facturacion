/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;
/**
 *
 * @author jhosu
 */
public class ModeloDetalleVenta {

    // === Atributos ===
    private long idDetalleVenta;         // id_detalle_venta
    private long idVenta;                // id_venta
    private long idProducto;             // id_pruducto (error tipográfico en la BD, pero así está)
    private int cantidad;                // cantidad
    private double precioVenta;          // precio_venta
    private double descuento;            // descuento
    private double impuesto;             // impuesto
    private long ventaIdVenta;           // venta_id_venta (FK hacia venta)
    private long productosIdProducto;    // productos_id_producto (FK hacia productos)

    // === Constructores ===
    public ModeloDetalleVenta() {
    }

    public ModeloDetalleVenta(long idDetalleVenta, long idVenta, long idProducto, int cantidad,
                              double precioVenta, double descuento, double impuesto,
                              long ventaIdVenta, long productosIdProducto) {
        this.idDetalleVenta = idDetalleVenta;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
        this.descuento = descuento;
        this.impuesto = impuesto;
        this.ventaIdVenta = ventaIdVenta;
        this.productosIdProducto = productosIdProducto;
    }

    // === Getters y Setters ===
    public long getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(long idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(long idVenta) {
        this.idVenta = idVenta;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public long getVentaIdVenta() {
        return ventaIdVenta;
    }

    public void setVentaIdVenta(long ventaIdVenta) {
        this.ventaIdVenta = ventaIdVenta;
    }

    public long getProductosIdProducto() {
        return productosIdProducto;
    }

    public void setProductosIdProducto(long productosIdProducto) {
        this.productosIdProducto = productosIdProducto;
    }

    // === Método de utilidad ===
    public double calcularSubtotal() {
        double subtotal = cantidad * precioVenta;
        subtotal -= descuento;
        subtotal += impuesto;
        return subtotal;
    }

    @Override
    public String toString() {
        return "DetalleVenta{" +
                "ID Detalle=" + idDetalleVenta +
                ", ID Venta=" + idVenta +
                ", ID Producto=" + idProducto +
                ", Cantidad=" + cantidad +
                ", Precio Venta=" + precioVenta +
                ", Descuento=" + descuento +
                ", Impuesto=" + impuesto +
                '}';
    }
}
