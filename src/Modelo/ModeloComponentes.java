/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Conector.DBConnection;
import Conector.SQL;
import Implementacion.ComponentesImpl;
import Interfaces.IComponentesProducto;
import Vistas.PanelComponentesProducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author anyi4*/
public class ModeloComponentes {
    public final PanelComponentesProducto vista;
    private final IComponentesProducto dao;

    public ModeloComponentes(PanelComponentesProducto vista) {
        this.vista = vista;
        this.dao = new ComponentesImpl();
    }

    public boolean validarCampos() {
        return !vista.txtDescripcion.getText().trim().isEmpty() &&
               !vista.txtCodigoBarras.getText().trim().isEmpty();
    }

    public boolean guardarEnBD() {
        Connection conn = null;
        try {
//            conn = new DBConnection().getConexion();
            conn.setAutoCommit(false); 

            String sqlProd = "INSERT INTO productos (nombre_oficial, descripcion, codigo_barras, requiere_receta, activo) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement psProd = conn.prepareStatement(sqlProd, Statement.RETURN_GENERATED_KEYS);
            psProd.setString(1, vista.txtDescripcionOficial.getText().trim());
            psProd.setString(2, vista.txtDescripcion.getText().trim());
            psProd.setString(3, vista.txtCodigoBarras.getText().trim());
            psProd.setBoolean(4, vista.cmbReceta.getSelectedItem().toString().equalsIgnoreCase("SI"));
            psProd.setBoolean(5, vista.CActivo.getSelectedItem().toString().equalsIgnoreCase("SI"));
            psProd.executeUpdate();

            ResultSet rsProd = psProd.getGeneratedKeys();
            if (!rsProd.next()) throw new SQLException("No se pudo obtener el ID del producto.");
            int idProducto = rsProd.getInt(1);

            
            String nombreAlt = vista.txtNombreAlt.getText().trim();
            if (!nombreAlt.isEmpty()) {
                String sqlNombreAlt = "INSERT INTO nombres_alternativos (id_producto, nombre_alternativo) VALUES (?, ?)";
                PreparedStatement psAlt = conn.prepareStatement(sqlNombreAlt);
                psAlt.setInt(1, idProducto);
                psAlt.setString(2, nombreAlt);
                psAlt.executeUpdate();
            }

            int idComponente = Integer.parseInt(vista.txtComponentes.getText().trim());
            String concentracion = vista.txtDescripcion.getText().trim();
            dao.insertarComponenteProducto(idProducto, idComponente, concentracion); 

            conn.commit(); 
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException e) { e.printStackTrace(); }
            }
            return false;
        } finally {
            if (conn != null) try { conn.setAutoCommit(true); conn.close(); } catch (SQLException e) {}
        }
    }

    public void limpiarCampos() {
        vista.txtInfBasica.setText("");
        vista.txtComponentes.setText("");
        vista.txtDescripcion.setText("");
        vista.txtDescripcionOficial.setText("");
        vista.txtNombreAlt.setText("");
        vista.txtCodigoBarras.setText("");
        vista.cmbReceta.setSelectedIndex(0);
        vista.CActivo.setSelectedIndex(0);
    }

    public void generarCodigoBarras() {
        String codigo = "CB" + System.currentTimeMillis();
        vista.txtCodigoBarras.setText(codigo);
    }
}
