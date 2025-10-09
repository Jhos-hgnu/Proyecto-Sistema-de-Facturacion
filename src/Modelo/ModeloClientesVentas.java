package Modelo;

import java.util.List;

/**
 *
 * @author jhosu
 */
public class ModeloClientesVentas {
    
    private int idCliente;
    private String nombre;
    private String apellido;
    private String direccion;
    private String nit;
    private String identificacion;
    private String telefono;
    private boolean tieneSubsidio;
    private Integer idInstitucionSubsidio;
    private String nombreEncontradoNit;
    private String apellidoEncontradoNit;
    private String direccionEncontradaNit;
    private double subtotal;
    private double total;
    private double descuento;
    private String tipoPago;
    private boolean conSubsidio;
    private String observaciones;
    private List<ModeloDetalleVenta> carrito;

    public ModeloClientesVentas() {
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isTieneSubsidio() {
        return tieneSubsidio;
    }

    public void setTieneSubsidio(boolean tieneSubsidio) {
        this.tieneSubsidio = tieneSubsidio;
    }

    public Integer getIdInstitucionSubsidio() {
        return idInstitucionSubsidio;
    }

    public void setIdInstitucionSubsidio(Integer idInstitucionSubsidio) {
        this.idInstitucionSubsidio = idInstitucionSubsidio;
    }

    public String getNombreEncontradoNit() {
        return nombreEncontradoNit;
    }

    public void setNombreEncontradoNit(String nombreEncontradoNit) {
        this.nombreEncontradoNit = nombreEncontradoNit;
    }

    public String getApellidoEncontradoNit() {
        return apellidoEncontradoNit;
    }

    public void setApellidoEncontradoNit(String apellidoEncontradoNit) {
        this.apellidoEncontradoNit = apellidoEncontradoNit;
    }

    public String getDireccionEncontradaNit() {
        return direccionEncontradaNit;
    }

    public void setDireccionEncontradaNit(String direccionEncontradaNit) {
        this.direccionEncontradaNit = direccionEncontradaNit;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public boolean isConSubsidio() {
        return conSubsidio;
    }

    public void setConSubsidio(boolean conSubsidio) {
        this.conSubsidio = conSubsidio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<ModeloDetalleVenta> getCarrito() {
        return carrito;
    }

    public void setCarrito(List<ModeloDetalleVenta> carrito) {
        this.carrito = carrito;
    }
    
    
    
    
}
