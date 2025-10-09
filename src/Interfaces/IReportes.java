/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Vistas.VistaReporte;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jhosu
 */
import java.sql.Connection;

public interface IReportes {
     ResultSet obtenerVentasDelDia() throws SQLException;
   // void generarReportePDF(VistaReporte vista, int idUsuario);
}
