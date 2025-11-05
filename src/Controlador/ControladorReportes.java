package Controlador;

import Modelo.ModeloReporte;
import Vistas.VistaAdmin;
import Vistas.VistaReportes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControladorReportes {

    private ModeloReporte modelo;
    private VistaReportes vista;

    public ControladorReportes(ModeloReporte modelo, VistaReportes vista) {
        this.modelo = modelo;
        this.vista = vista;
        configurarListeners();
    }

    private void configurarListeners() {
        vista.getBtnVolver().addActionListener(e -> volverMenuPrincipal());

    }

    private void volverMenuPrincipal() {
        VistaAdmin vistaAD = new VistaAdmin();
        vistaAD.setVisible(true);
        vista.dispose();
   
    }

}
