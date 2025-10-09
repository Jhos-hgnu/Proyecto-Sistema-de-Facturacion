/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementacion;

import Conector.DBConnection;
import Interfaces.IReportes;
import Utilities.GeneradorPDFreporte;
import Vistas.VistaReporte;
import com.mysql.cj.xdevapi.Statement;

/**
 *
 * @author jhosu
 */
import Conector.SQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class ReporteImp implements IReportes {
      private final DBConnection conector = new DBConnection();
    private final SQL sql = new SQL();
    private Connection con;

    @Override
    public ResultSet obtenerVentasDelDia() throws SQLException {
        conectarBD();
        return ejecutarConsulta(sql.getOBTENER_VENTAS_DIA());
    }

    private void conectarBD() throws SQLException {
        if (con == null || con.isClosed()) {
            conector.conectar();
            con = conector.getConnection();
        }
    }

    private ResultSet ejecutarConsulta(String query) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            throw e;
        }
    }

    public void cerrarConexion() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

}