/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conector.DBConnection;
import Implementacion.ProductoImp;
import Interfaces.Iinventario;
import Modelo.ModeloInventario;
import Modelo.ModeloProducto;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControladorInventario implements MouseListener {

 
    private final ModeloInventario modelo;

    public ControladorInventario(ModeloInventario modelo) {
        this.modelo = modelo;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        
        if (source == modelo.vista.btnRegistroInventario) {
            modelo.cargarLotesEnTabla();
        } 
        else if (source == modelo.vista.btnRegistroVenta) {
            modelo.cargarVentasDelDiaEnTabla();
        }
    }

    
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}

