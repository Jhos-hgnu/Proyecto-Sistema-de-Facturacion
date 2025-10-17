package Controlador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import Modelo.ModeloVistaAdmin;
import Vistas.*;


public class ControladorVistaAdmin implements MouseListener {

    ModeloVistaAdmin modelo;

    public ControladorVistaAdmin(ModeloVistaAdmin modelo) {
        this.modelo = modelo;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getComponent().equals(modelo.getVistaAdmin().btnRegresar)) {
            VistaInicio vistaInicio = new VistaInicio();
            vistaInicio.setVisible(true);
            modelo.getVistaAdmin().dispose();
        } else if (e.getComponent().equals(modelo.getVistaAdmin().btnProductos)) {
            PanelProducto vistaPanelProd = new PanelProducto();
            mostrarPaneles(vistaPanelProd);
        } else if (e.getComponent().equals(modelo.getVistaAdmin().btnGestionInventario)) {
            PanelMostrarInventario vistaPanelInvent = new PanelMostrarInventario();
            mostrarPaneles(vistaPanelInvent);
        } else if (e.getComponent().equals(modelo.getVistaAdmin().btnReportes)) {
            VistaReportes vReportes = new VistaReportes();
            vReportes.setVisible(true);
            modelo.getVistaAdmin().dispose();
        } else if (e.getComponent().equals(modelo.getVistaAdmin().btnRegistroUsuarios)) {
            PanelRegistroUsuario vistaPanelRegistro = new PanelRegistroUsuario();
            mostrarPaneles(vistaPanelRegistro);
        }else if (e.getComponent().equals(modelo.getVistaAdmin().btnComponentesProductos)) {
            PanelComponentesProducto vistaComponentes = new PanelComponentesProducto();
            mostrarPaneles(vistaComponentes);
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
        if (e.getComponent().equals(modelo.getVistaAdmin().btnRegresar)) {
            modelo.getVistaAdmin().btnRegresar.setBackground(new Color(50, 95, 110));
        } else if (e.getComponent().equals(modelo.getVistaAdmin().btnProductos)) {
            modelo.getVistaAdmin().btnProductos.setBackground(new Color(50, 95, 110));
        } else if (e.getComponent().equals(modelo.getVistaAdmin().btnGestionInventario)) {
            modelo.getVistaAdmin().btnGestionInventario.setBackground(new Color(50, 95, 110));
        } else if (e.getComponent().equals(modelo.getVistaAdmin().btnReportes)) {
            modelo.getVistaAdmin().btnReportes.setBackground(new Color(50, 95, 110));
        } else if (e.getComponent().equals(modelo.getVistaAdmin().btnRegistroUsuarios)) {
            modelo.getVistaAdmin().btnRegistroUsuarios.setBackground(new Color(50, 95, 110));
        } else if (e.getComponent().equals(modelo.getVistaAdmin().btnComponentesProductos)) {
            modelo.getVistaAdmin().btnComponentesProductos.setBackground(new Color(50, 95, 110));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

        if (e.getComponent().equals(modelo.getVistaAdmin().btnRegresar)) {
            modelo.getVistaAdmin().btnRegresar.setBackground(new Color(75, 128, 146));
        } else if (e.getComponent().equals(modelo.getVistaAdmin().btnProductos)) {
            modelo.getVistaAdmin().btnProductos.setBackground(new Color(75, 128, 146));
        } else if (e.getComponent().equals(modelo.getVistaAdmin().btnGestionInventario)) {
            modelo.getVistaAdmin().btnGestionInventario.setBackground(new Color(75, 128, 146));
        } else if (e.getComponent().equals(modelo.getVistaAdmin().btnReportes)) {
            modelo.getVistaAdmin().btnReportes.setBackground(new Color(75, 128, 146));
        } else if (e.getComponent().equals(modelo.getVistaAdmin().btnRegistroUsuarios)) {
            modelo.getVistaAdmin().btnRegistroUsuarios.setBackground(new Color(75, 128, 146));
        } else if (e.getComponent().equals(modelo.getVistaAdmin().btnComponentesProductos)) {
            modelo.getVistaAdmin().btnComponentesProductos.setBackground(new Color(75, 128, 146));
        }
    }

    public void mostrarPaneles(JPanel p) {

        p.setSize(855, 700);
        p.setLocation(0, 0);

        modelo.getVistaAdmin().contenedorFondo.removeAll();
        modelo.getVistaAdmin().contenedorFondo.add(p, BorderLayout.CENTER);
        modelo.getVistaAdmin().contenedorFondo.revalidate();
        modelo.getVistaAdmin().contenedorFondo.repaint();
    }
}
