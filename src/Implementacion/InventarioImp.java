package Implementacion;

import Conector.DBConnection;
import Interfaces.Iinventario;
import Conector.SQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventarioImp implements Iinventario {
    private final DBConnection conector = new DBConnection();
    private final SQL sql = new SQL();
    private Connection con;  

    @Override
    public ResultSet obtenerRegistroLotes() throws SQLException {
        conectarBD();  
        return ejecutarConsulta(sql.getOBTENER_LOTES_ACTIVOS());
    }

    @Override
    public ResultSet obtenerRegistroVentasDelDia() throws SQLException {
        conectarBD();  
        return ejecutarConsulta("SELECT * FROM ventas WHERE DATE(fecha) = CURDATE()");
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