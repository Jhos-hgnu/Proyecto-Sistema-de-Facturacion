/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ModeloComponentes;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author anyi4
 */
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

public class ControladorComponentes implements MouseListener {
    private final ModeloComponentes modelo;

    public ControladorComponentes(ModeloComponentes modelo) {
        this.modelo = modelo;
    }

   @Override
public void mouseClicked(MouseEvent e) {
    Object source = e.getSource();

    if (source == modelo.vista.btnguardar) {
        if (!modelo.validarCampos()) {
            JOptionPane.showMessageDialog(null, "Complete los campos obligatorios.");
            return;
        }

        if (modelo.guardarEnBD()) {
            JOptionPane.showMessageDialog(null, "Registro guardado correctamente.");
            modelo.limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Error al guardar el registro.");
        }

    } else if (source == modelo.vista.btncancelar) {
        modelo.limpiarCampos();

    } else if (source == modelo.vista.Generador) {
        modelo.generarCodigoBarras();
    }
}


    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
