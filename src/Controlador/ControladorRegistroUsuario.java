/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Implementacion.RegistroUsuarioImp;
import Modelo.ModeloRegistroUsuario;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author jhosu
 */
public class ControladorRegistroUsuario implements MouseListener {

    ModeloRegistroUsuario modelo;

    RegistroUsuarioImp implementacion = new RegistroUsuarioImp();

    public ControladorRegistroUsuario(ModeloRegistroUsuario modelo) {
        this.modelo = modelo;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent().equals(modelo.getVistaRegistro().btnRegistrar)) {
            inputisEmptyRegistrar();
        } else if (e.getComponent().equals(modelo.getVistaRegistro().btnBuscar)) {
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getComponent().equals(modelo.getVistaRegistro().btnRegistrar)) {
            modelo.getVistaRegistro().btnRegistrar.setBackground(new Color(50, 95, 110));
        } else if (e.getComponent().equals(modelo.getVistaRegistro().btnBorrar)) {
            modelo.getVistaRegistro().btnBorrar.setBackground(new Color(50, 95, 110));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getComponent().equals(modelo.getVistaRegistro().btnRegistrar)) {
            modelo.getVistaRegistro().btnRegistrar.setBackground(new Color(75, 128, 146));
        } else if (e.getComponent().equals(modelo.getVistaRegistro().btnBorrar)) {
            modelo.getVistaRegistro().btnBorrar.setBackground(new Color(75, 128, 146));
        }
    }

    public void inputisEmptyRegistrar() {

        if (modelo.getVistaRegistro().txtNombrePersonal.getText().isEmpty()
                || modelo.getVistaRegistro().txtApellidoPersonal.getText().isEmpty()
                || modelo.getVistaRegistro().txtPassword.getPassword().equals("")
                || modelo.getVistaRegistro().txtTelefono.getText().isEmpty()
                || modelo.getVistaRegistro().txtEmail.getText().isEmpty()
//                || modelo.getVistaRegistro().boxUsuarioActivo.getSelectedItem() == null
                || modelo.getVistaRegistro().boxTipoUsuario.getSelectedItem() == null) {
//warning
            JOptionPane.showInternalMessageDialog(null, "Por favor debe de ingresar todos los datos", "ADVERTENCIA \"DATOS VACIOS\"", JOptionPane.WARNING_MESSAGE);
        } else {
            capturaDeDatos();
            limpiarDatos();
        }
    }

    public void inputisEmptyBuscar() {

        if (modelo.getVistaRegistro().txtNombrePersonal.getText().isEmpty()
                || modelo.getVistaRegistro().txtApellidoPersonal.getText().isEmpty()
                || modelo.getVistaRegistro().txtPassword.getPassword().equals("")
                || modelo.getVistaRegistro().txtTelefono.getText().isEmpty()
                || modelo.getVistaRegistro().txtEmail.getText().isEmpty()
//                || modelo.getVistaRegistro().boxUsuarioActivo.getSelectedItem() == null
                || modelo.getVistaRegistro().boxTipoUsuario.getSelectedItem() == null) {
//warning
            JOptionPane.showInternalMessageDialog(null, "Por favor debe de ingresar todos los datos", "ADVERTENCIA \"DATOS VACIOS\"", JOptionPane.WARNING_MESSAGE);
        } else {
            capturaDeDatos();
            limpiarDatos();
        }
    }

    public void capturaDeDatos() {

        String nombreIngresado = modelo.getVistaRegistro().txtNombrePersonal.getText();
        String apellidoIngresado = modelo.getVistaRegistro().txtApellidoPersonal.getText();
        String contraseniaIngresada = String.valueOf(modelo.getVistaRegistro().txtPassword.getPassword());
        String telefonoIngresado = modelo.getVistaRegistro().txtTelefono.getText();
        String emailIngresado = modelo.getVistaRegistro().txtEmail.getText();
//        boolean usuarioActivo = validarUsuarioActivo(String.valueOf(modelo.getVistaRegistro().boxUsuarioActivo.getSelectedItem()));
        //String usuarioActivo = String.valueOf(modelo.getVistaRegistro().boxUsuarioActivo.getSelectedItem());
        String tipoUsuario = String.valueOf(modelo.getVistaRegistro().boxTipoUsuario.getSelectedItem());
        String nombreUsuarioInicio = modelo.getVistaRegistro().txtIdUsuario.getText();
//        creacionDeUsuario(nombreIngresado, apellidoIngresado, telefonoIngresado, emailIngresado, nombreUsuarioInicio, contraseniaIngresada, tipoUsuario, usuarioActivo);
    }

    public void creacionDeUsuario(String nombreIngre, String apellidoIngre, String telefonoIngre, String emailIngre, String nombreUsuario, String contraIngre, String tipoUsuario, boolean usuarioActivo) {

        ModeloRegistroUsuario model = new ModeloRegistroUsuario();

        model.setNombreUsuario(nombreIngre);
        model.setApellidoUsuario(apellidoIngre);
        model.setNumeroUsuario(telefonoIngre);
        model.setEmailUsuario(emailIngre);
        model.setUsuario(nombreUsuario);
        model.setContraseniaUsuario(contraIngre);
        model.setTipoUsuario(tipoUsuario);
        model.setActivoUsuario(usuarioActivo);

        System.out.println(nombreIngre + apellidoIngre + telefonoIngre + emailIngre + nombreUsuario + contraIngre + tipoUsuario + usuarioActivo);
        boolean creacionUsuario = implementacion.guardarUsuario(model);

        if (creacionUsuario == true) {
            JOptionPane.showInternalMessageDialog(null, "Usuario creado con éxito", "\"CREACIÓN DE USUARIOS\"", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showInternalMessageDialog(null, "Error en la creación del usuario", "ERROR \"ERROR AL CREAR USUARIO\"", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limpiarDatos() {

        modelo.getVistaRegistro().txtNombrePersonal.setText("");
        modelo.getVistaRegistro().txtApellidoPersonal.setText("");
        modelo.getVistaRegistro().txtPassword.setText("");
        modelo.getVistaRegistro().txtTelefono.setText("");
        modelo.getVistaRegistro().txtEmail.setText("");
//        modelo.getVistaRegistro().boxUsuarioActivo.setSelectedIndex(0);
        modelo.getVistaRegistro().boxTipoUsuario.setSelectedIndex(0);
        modelo.getVistaRegistro().txtIdUsuario.setText("");
    }

    public boolean validarUsuarioActivo(String usuarioActivo) {

        boolean estaActivo = true;

        if (usuarioActivo.equals("SI")) {
            estaActivo = true;
        } else {
            estaActivo = false;
        }
        return estaActivo;
    }
}
