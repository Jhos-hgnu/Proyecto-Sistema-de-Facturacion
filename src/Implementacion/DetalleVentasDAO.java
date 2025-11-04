/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementacion;

import Modelo.ModeloDetalleVenta;
import Conector.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luisd
 */

public class DetalleVentasDAO {

    private final DBConnection conexion = new DBConnection();
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    // === INSERTAR ===
    public boolean insertarDetalleVenta(ModeloDetalleVenta detalle) {
        boolean exito = false;
        String sql = """
            INSERT INTO detalle_venta (
                id_detalle_venta,
                id_venta,
                id_pruducto,
                cantidad,
                precio_venta,
                descuento,
                impuesto,
                venta_id_venta,
                productos_id_producto
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        conexion.conectar();
        try {
            ps = conexion.preparar(sql);

            ps.setLong(1, detalle.getIdDetalleVenta());
            ps.setLong(2, detalle.getIdVenta());
            ps.setLong(3, detalle.getIdProducto());
            ps.setInt(4, detalle.getCantidad());
            ps.setDouble(5, detalle.getPrecioVenta());
            ps.setDouble(6, detalle.getDescuento());
            ps.setDouble(7, detalle.getImpuesto());
            ps.setLong(8, detalle.getVentaIdVenta());
            ps.setLong(9, detalle.getProductosIdProducto());

            exito = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            Logger.getLogger(DetalleVentasDAO.class.getName()).log(Level.SEVERE, "Error al insertar detalle de venta", e);
        } finally {
            cerrarRecursos();
        }

        return exito;
    }

    // === ACTUALIZAR ===
    public boolean actualizarDetalleVenta(ModeloDetalleVenta detalle) {
        boolean exito = false;
        String sql = """
            UPDATE detalle_venta SET
                id_venta = ?,
                id_pruducto = ?,
                cantidad = ?,
                precio_venta = ?,
                descuento = ?,
                impuesto = ?,
                venta_id_venta = ?,
                productos_id_producto = ?
            WHERE id_detalle_venta = ?
        """;

        conexion.conectar();
        try {
            ps = conexion.preparar(sql);

            ps.setLong(1, detalle.getIdVenta());
            ps.setLong(2, detalle.getIdProducto());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getPrecioVenta());
            ps.setDouble(5, detalle.getDescuento());
            ps.setDouble(6, detalle.getImpuesto());
            ps.setLong(7, detalle.getVentaIdVenta());
            ps.setLong(8, detalle.getProductosIdProducto());
            ps.setLong(9, detalle.getIdDetalleVenta());

            exito = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            Logger.getLogger(DetalleVentasDAO.class.getName()).log(Level.SEVERE, "Error al actualizar detalle de venta", e);
        } finally {
            cerrarRecursos();
        }

        return exito;
    }

    // === ELIMINAR ===
    public boolean eliminarDetalleVenta(long idDetalleVenta) {
        boolean exito = false;
        String sql = "DELETE FROM detalle_venta WHERE id_detalle_venta = ?";

        conexion.conectar();
        try {
            ps = conexion.preparar(sql);
            ps.setLong(1, idDetalleVenta);
            exito = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(DetalleVentasDAO.class.getName()).log(Level.SEVERE, "Error al eliminar detalle de venta", e);
        } finally {
            cerrarRecursos();
        }

        return exito;
    }

    // === BUSCAR POR ID ===
    public ModeloDetalleVenta buscarPorId(long idDetalleVenta) {
        ModeloDetalleVenta detalle = null;
        String sql = "SELECT * FROM detalle_venta WHERE id_detalle_venta = ?";

        conexion.conectar();
        try {
            ps = conexion.preparar(sql);
            ps.setLong(1, idDetalleVenta);
            rs = ps.executeQuery();

            if (rs.next()) {
                detalle = mapearResultado(rs);
            }

        } catch (SQLException e) {
            Logger.getLogger(DetalleVentasDAO.class.getName()).log(Level.SEVERE, "Error al buscar detalle de venta", e);
        } finally {
            cerrarRecursos();
        }

        return detalle;
    }

    // === LISTAR TODOS ===
    public List<ModeloDetalleVenta> listarDetalles() {
        List<ModeloDetalleVenta> lista = new ArrayList<>();
        String sql = "SELECT * FROM detalle_venta ORDER BY id_detalle_venta DESC";

        conexion.conectar();
        try {
            ps = conexion.preparar(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapearResultado(rs));
            }

        } catch (SQLException e) {
            Logger.getLogger(DetalleVentasDAO.class.getName()).log(Level.SEVERE, "Error al listar detalles de venta", e);
        } finally {
            cerrarRecursos();
        }

        return lista;
    }

    // === MAPEO (ResultSet → Modelo) ===
    private ModeloDetalleVenta mapearResultado(ResultSet rs) throws SQLException {
        ModeloDetalleVenta detalle = new ModeloDetalleVenta();
        detalle.setIdDetalleVenta(rs.getLong("id_detalle_venta"));
        detalle.setIdVenta(rs.getLong("id_venta"));
        detalle.setIdProducto(rs.getLong("id_pruducto"));
        detalle.setCantidad(rs.getInt("cantidad"));
        detalle.setPrecioVenta(rs.getDouble("precio_venta"));
        detalle.setDescuento(rs.getDouble("descuento"));
        detalle.setImpuesto(rs.getDouble("impuesto"));
        detalle.setVentaIdVenta(rs.getLong("venta_id_venta"));
        detalle.setProductosIdProducto(rs.getLong("productos_id_producto"));
        return detalle;
    }

    // === CERRAR CONEXIONES ===
    private void cerrarRecursos() {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
            conexion.desconectar();
        } catch (SQLException e) {
            Logger.getLogger(DetalleVentasDAO.class.getName()).log(Level.WARNING, "Error al cerrar recursos", e);
        }
    }
}
