/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementacion;

/**
 *
 * @author anyi4
 */
import Conector.DBConnection;
import Conector.SQL;
import Interfaces.IComponentesProducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ComponentesImpl implements IComponentesProducto {
    private final SQL sql = new SQL();
  DBConnection connection = new DBConnection();

    @Override
    public boolean insertarComponenteProducto(int idProducto, int idComponente, String concentracion) {
        try (Connection conn = connection.getConexion();
     PreparedStatement ps = conn.prepareStatement(sql.getAGREGAR_COMPONENTE_PRODUCTO())) {

            ps.setInt(1, idProducto);
            ps.setInt(2, idComponente);
            ps.setString(3, concentracion);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace(); 
            return false;
        }
    }
}

