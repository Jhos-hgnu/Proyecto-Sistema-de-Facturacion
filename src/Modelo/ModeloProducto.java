package Modelo;

import Vistas.PanelProducto;
import java.math.BigDecimal;

public class ModeloProducto {

    PanelProducto vistaProducto;
    
    private int idProducto;
    private String nombreOficialP;
    private String descripcionP;
    private String codigoBarrasP;
    private boolean requiereRecetaP;
    private boolean activoP;
    private String fechaRegistro;
    private String numeroLote;
    private String fechaVencimiento;
    private String fechaFabricación;
    private int cantidadDisponible;
    private BigDecimal precioCompra;
    private BigDecimal precioVenta;
    private boolean activo;
    private String fechaRegistroLotes;
    private String nombreAlternativo;

    public ModeloProducto() {
    }

    public ModeloProducto(PanelProducto vistaProducto) {
        this.vistaProducto = vistaProducto;
    }

    public PanelProducto getVistaProducto() {
        return vistaProducto;
    }

    public void setVistaProducto(PanelProducto vistaProducto) {
        this.vistaProducto = vistaProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreOficialP() {
        return nombreOficialP;
    }

    public void setNombreOficialP(String nombreOficialP) {
        this.nombreOficialP = nombreOficialP;
    }

    public String getDescripcionP() {
        return descripcionP;
    }

    public void setDescripcionP(String descripcionP) {
        this.descripcionP = descripcionP;
    }

    public String getCodigoBarrasP() {
        return codigoBarrasP;
    }

    public void setCodigoBarrasP(String codigoBarrasP) {
        this.codigoBarrasP = codigoBarrasP;
    }

    public boolean isRequiereRecetaP() {
        return requiereRecetaP;
    }

    public void setRequiereRecetaP(boolean requiereRecetaP) {
        this.requiereRecetaP = requiereRecetaP;
    }

    public boolean isActivoP() {
        return activoP;
    }

    public void setActivoP(boolean activoP) {
        this.activoP = activoP;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(String numeroLote) {
        this.numeroLote = numeroLote;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getFechaFabricación() {
        return fechaFabricación;
    }

    public void setFechaFabricación(String fechaFabricación) {
        this.fechaFabricación = fechaFabricación;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = precioCompra;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getFechaRegistroLotes() {
        return fechaRegistroLotes;
    }

    public void setFechaRegistroLotes(String fechaRegistroLotes) {
        this.fechaRegistroLotes = fechaRegistroLotes;
    }

    public String getNombreAlternativo() {
        return nombreAlternativo;
    }

    public void setNombreAlternativo(String nombreAlternativo) {
        this.nombreAlternativo = nombreAlternativo;
    }
}
