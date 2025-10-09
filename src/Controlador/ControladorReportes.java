package Controlador;

import Modelo.ModeloReporte;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControladorReportes implements MouseListener {
   
     private final ModeloReporte modelo;

    public ControladorReportes(ModeloReporte modelo) {
        this.modelo = modelo;
    }

   @Override
public void mouseClicked(MouseEvent e) {
    Object fuente = e.getSource();

    if (fuente == modelo.vista.btnMostrarVentas) {
        modelo.cargarVentasDelDiaEnTabla();
    }

    if (fuente == modelo.vista.btnExportarPDF) {
        modelo.exportarReporteADiaPDF(1);
    }
}



    
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
