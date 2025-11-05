/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementacion;

import Modelo.ModeloVenta;
import Conector.DBConnection;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luisd
 */

public class VentasDAO {

    private final DBConnection conector = new DBConnection();
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    /**
     * Inserta una nueva venta en la base de datos.
     * Genera automáticamente el ID de venta usando la secuencia SEQ_VENTAS.
     */
    public boolean insertarVenta(ModeloVenta venta) {
        boolean resultado = false;
        conector.conectar();

        try {
//            long nuevoId = conector.obtenerSiguienteId("SEQ_VENTAS");
//            venta.setIdVenta(nuevoId);

            String sql = """
                    INSERT INTO ventas 
                    (id_venta, nit, fecha, tipo_pago, documento, total, 
                     id_usuario, observacion, clientes_nit, usuarios_id_usuario)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;

            ps = conector.preparar(sql);
            ps.setLong(1, venta.getIdVenta());
            ps.setString(2, venta.getNit());

            // Si no viene fecha, se asigna la actual
            Timestamp fechaSQL = venta.getFecha() != null ?
                    new Timestamp(venta.getFecha().getTime()) :
                    Timestamp.valueOf(LocalDateTime.now());
            ps.setTimestamp(3, fechaSQL);

            ps.setString(4, venta.getTipoPago());
            ps.setString(5, venta.getDocumento());
            ps.setDouble(6, venta.getTotal());
            ps.setLong(7, venta.getIdUsuario());
            ps.setString(8, venta.getObservacion());
            ps.setString(9, venta.getClientesNit());
            ps.setLong(10, venta.getUsuariosIdUsuario());

            resultado = ps.executeUpdate() > 0;

//            if (resultado)
//                System.out.println("✅ Venta insertada correctamente con ID: " + nuevoId);

        } catch (SQLException e) {
            Logger.getLogger(VentasDAO.class.getName()).log(Level.SEVERE, "❌ Error al insertar venta", e);
        } finally {
            cerrarRecursos();
        }

        return resultado;
    }

    /**
     * Obtiene una venta por su ID.
     */
    public ModeloVenta obtenerVentaPorId(long idVenta) {
        ModeloVenta venta = null;
        conector.conectar();

        try {
            String sql = "SELECT * FROM ventas WHERE id_venta = ?";
            ps = conector.preparar(sql);
            ps.setLong(1, idVenta);

            rs = ps.executeQuery();
            if (rs.next()) {
                venta = mapearVenta(rs);
            }

        } catch (SQLException e) {
            Logger.getLogger(VentasDAO.class.getName()).log(Level.SEVERE, "❌ Error al obtener venta", e);
        } finally {
            cerrarRecursos();
        }

        return venta;
    }

    /**
     * Devuelve una lista con todas las ventas registradas.
     */
    public List<ModeloVenta> listarVentas() {
        List<ModeloVenta> lista = new ArrayList<>();
        conector.conectar();

        try {
            String sql = "SELECT * FROM ventas ORDER BY fecha DESC";
            ps = conector.preparar(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapearVenta(rs));
            }

        } catch (SQLException e) {
            Logger.getLogger(VentasDAO.class.getName()).log(Level.SEVERE, "❌ Error al listar ventas", e);
        } finally {
            cerrarRecursos();
        }

        return lista;
    }

    /**
     * Actualiza una venta existente.
     */
    public boolean actualizarVenta(ModeloVenta venta) {
        boolean resultado = false;
        conector.conectar();

        try {
            String sql = """
                    UPDATE ventas
                    SET nit = ?, fecha = ?, tipo_pago = ?, documento = ?, total = ?,
                        id_usuario = ?, observacion = ?, clientes_nit = ?, usuarios_id_usuario = ?
                    WHERE id_venta = ?
                    """;

            ps = conector.preparar(sql);
            ps.setString(1, venta.getNit());
            ps.setTimestamp(2, new Timestamp(venta.getFecha().getTime()));
            ps.setString(3, venta.getTipoPago());
            ps.setString(4, venta.getDocumento());
            ps.setDouble(5, venta.getTotal());
            ps.setLong(6, venta.getIdUsuario());
            ps.setString(7, venta.getObservacion());
            ps.setString(8, venta.getClientesNit());
            ps.setLong(9, venta.getUsuariosIdUsuario());
            ps.setLong(10, venta.getIdVenta());

            resultado = ps.executeUpdate() > 0;

            if (resultado)
                System.out.println("✏️ Venta actualizada correctamente: ID " + venta.getIdVenta());

        } catch (SQLException e) {
            Logger.getLogger(VentasDAO.class.getName()).log(Level.SEVERE, "❌ Error al actualizar venta", e);
        } finally {
            cerrarRecursos();
        }

        return resultado;
    }

    /**
     * Elimina una venta por su ID.
     */
    public boolean eliminarVenta(long idVenta) {
        boolean resultado = false;
        conector.conectar();

        try {
            String sql = "DELETE FROM ventas WHERE id_venta = ?";
            ps = conector.preparar(sql);
            ps.setLong(1, idVenta);

            resultado = ps.executeUpdate() > 0;

            if (resultado)
                System.out.println("🗑️ Venta eliminada correctamente: ID " + idVenta);

        } catch (SQLException e) {
            Logger.getLogger(VentasDAO.class.getName()).log(Level.SEVERE, "❌ Error al eliminar venta", e);
        } finally {
            cerrarRecursos();
        }

        return resultado;
    }

    // ===============================================
    // 🔹 Métodos auxiliares
    // ===============================================

    private ModeloVenta mapearVenta(ResultSet rs) throws SQLException {
        ModeloVenta venta = new ModeloVenta();
        venta.setIdVenta(rs.getLong("id_venta"));
        venta.setNit(rs.getString("nit"));
        venta.setFecha(rs.getDate("fecha"));
        venta.setTipoPago(rs.getString("tipo_pago"));
        venta.setDocumento(rs.getString("documento"));
        venta.setTotal(rs.getDouble("total"));
        venta.setIdUsuario(rs.getLong("id_usuario"));
        venta.setObservacion(rs.getString("observacion"));
        venta.setClientesNit(rs.getString("clientes_nit"));
        venta.setUsuariosIdUsuario(rs.getLong("usuarios_id_usuario"));
        return venta;
    }

    private void cerrarRecursos() {
        try {
            if (rs != null && !rs.isClosed()) rs.close();
            if (ps != null && !ps.isClosed()) ps.close();
            conector.desconectar();
        } catch (SQLException e) {
            Logger.getLogger(VentasDAO.class.getName()).log(Level.WARNING, "⚠️ Error al cerrar recursos", e);
        }
    }
}
