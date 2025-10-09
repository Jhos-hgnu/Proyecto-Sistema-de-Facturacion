/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Interfaces.IRegistroCliente;
import Vistas.PanelClientes;
import java.util.logging.Logger;
import Vistas.*;
import java.sql.Timestamp; 
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author anyi4
 */
import java.sql.Timestamp;

import java.sql.Timestamp;

public class ModeloRegistroCliente {

    private final PanelClientes vista;
    private final IRegistroCliente implementacion;

    public ModeloRegistroCliente(PanelClientes vista, IRegistroCliente implementacion) {
        this.vista = vista;
        this.implementacion = implementacion;
    }

    public boolean insertarCliente(Cliente cliente) {
        return implementacion.insertarCliente(cliente);
    }

    public static class Cliente {
        private int idCliente;
        private String nombre;
        private String apellido;
        private String telefono;
        private String direccion;
        private String identificacion;
        private String nit;
        private boolean tieneSubsidio;
        private Integer idInstitucionSubsidio;
        private Timestamp fecha_registro;
        private boolean activo;

        // Getters y Setters
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

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public String getIdentificacion() {
            return identificacion;
        }

        public void setIdentificacion(String identificacion) {
            this.identificacion = identificacion;
        }

        public String getNit() {
            return nit;
        }

        public void setNit(String nit) {
            this.nit = nit;
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

        public Timestamp getFechaRegistro() {
            return fecha_registro;
        }

        public void setFechaRegistro(Timestamp fecha_registro) {
            this.fecha_registro = fecha_registro;
        }

        public boolean isActivo() {
            return activo;
        }

        public void setActivo(boolean activo) {
            this.activo = activo;
        }
    }
}
