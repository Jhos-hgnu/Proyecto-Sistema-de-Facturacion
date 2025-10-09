package Controlador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import Modelo.ModeloVistaVendedor;
import Vistas.PanelClientes;
import Vistas.PanelVentas;
import Vistas.VistaInicio;

/**
 *
 * @author jhosu
 */
public class ControladorVistaVendedor implements MouseListener {

    ModeloVistaVendedor modelo;

    public ControladorVistaVendedor(ModeloVistaVendedor modelo) {
        this.modelo = modelo;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getComponent().equals(modelo.getVistaVendedor().btnRegresar)) {
            VistaInicio vistaInicio = new VistaInicio();
            vistaInicio.setVisible(true);
            modelo.getVistaVendedor().dispose();
        } else if (e.getComponent().equals(modelo.getVistaVendedor().btnVentas)) {
            PanelVentas vistaPanelVentas = new PanelVentas();
            mostrarPanel(vistaPanelVentas);
        } else if (e.getComponent().equals(modelo.getVistaVendedor().btnClientes)) {
            PanelClientes vistaPanelClientes = new PanelClientes();
            mostrarPanel(vistaPanelClientes);
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
        if (e.getComponent().equals(modelo.getVistaVendedor().btnRegresar)) {
            modelo.getVistaVendedor().btnRegresar.setBackground(new Color(50, 95, 110));
        } else if (e.getComponent().equals(modelo.getVistaVendedor().btnVentas)) {
            modelo.getVistaVendedor().btnVentas.setBackground(new Color(50, 95, 110));
        } else if (e.getComponent().equals(modelo.getVistaVendedor().btnClientes)) {
            modelo.getVistaVendedor().btnClientes.setBackground(new Color(50, 95, 110));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getComponent().equals(modelo.getVistaVendedor().btnRegresar)) {
            modelo.getVistaVendedor().btnRegresar.setBackground(new Color(75, 128, 146));
        } else if (e.getComponent().equals(modelo.getVistaVendedor().btnVentas)) {
            modelo.getVistaVendedor().btnVentas.setBackground(new Color(75, 128, 146));
        } else if (e.getComponent().equals(modelo.getVistaVendedor().btnClientes)) {
            modelo.getVistaVendedor().btnClientes.setBackground(new Color(75, 128, 146));
        }
    }

    public void mostrarPanel(JPanel p) {

        p.setSize(855, 700);
        p.setLocation(0, 0);

        modelo.getVistaVendedor().contenedorFondo.removeAll();
        modelo.getVistaVendedor().contenedorFondo.add(p, BorderLayout.CENTER);
        modelo.getVistaVendedor().contenedorFondo.revalidate();
        modelo.getVistaVendedor().contenedorFondo.repaint();
    }
}
