package Modelo;

import java.util.Date;
import java.util.List;

/**
 *
 * @author jhosu
 */
public class ModeloClientesVentas {

    private String nit;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String apellidoCasada;
    private String direccion;
    private String telefono;
    private String email;
    private String tipoCliente;
    private double limiteCredito;
    private String estado;
    private Date fechaRegistro;

    // 🔹 Constructor vacío
    public ModeloClientesVentas() {
    }

    // 🔹 Constructor con parámetros
    public ModeloClientesVentas(String nit, String primerNombre, String segundoNombre,
            String primerApellido, String segundoApellido, String apellidoCasada,
            String direccion, String telefono, String email, String tipoCliente,
            double limiteCredito, String estado, Date fechaRegistro) {
        this.nit = nit;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.apellidoCasada = apellidoCasada;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.tipoCliente = tipoCliente;
        this.limiteCredito = limiteCredito;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }

    // 🔹 Getters y Setters
    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getApellidoCasada() {
        return apellidoCasada;
    }

    public void setApellidoCasada(String apellidoCasada) {
        this.apellidoCasada = apellidoCasada;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    // 🔹 Método para mostrar nombre completo
    public String getNombreCompleto() {
        StringBuilder sb = new StringBuilder();
        sb.append(primerNombre);
        if (segundoNombre != null && !segundoNombre.isEmpty()) {
            sb.append(" ").append(segundoNombre);
        }
        sb.append(" ").append(primerApellido);
        if (segundoApellido != null && !segundoApellido.isEmpty()) {
            sb.append(" ").append(segundoApellido);
        }
        if (apellidoCasada != null && !apellidoCasada.isEmpty()) {
            sb.append(" de ").append(apellidoCasada);
        }
        return sb.toString().trim();
    }

    @Override
    public String toString() {
        return getNombreCompleto() + " (" + nit + ")";
    }
}
