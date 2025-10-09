/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Modelo.ModeloInventario;
import Modelo.ModeloProducto;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author jhosu
 */
public interface Iinventario {
    ResultSet obtenerRegistroLotes() throws SQLException;
    ResultSet obtenerRegistroVentasDelDia() throws SQLException;  
}
