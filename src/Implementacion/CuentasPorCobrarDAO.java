/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementacion;

/**
 *
 * @author luisd
 */

import Modelo.ModeloCuentasPorCobrar;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuentasPorCobrarDAO {

    private Connection conexion;

    // ✅ Constructor: recibe una conexión abierta o crea una nueva
    public CuentasPorCobrarDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // ✅ Inserta una nueva cuenta por cobrar
    public void insertar(ModeloCuentasPorCobrar c) throws SQLException {
        String sql = "INSERT INTO cuentas_por_cobrar (idCuentaCobro, idVenta, fechaEmision, fechaVence, monto, saldo, estado, venta_idVenta) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setLong(1, c.getIdCuentaCobro());
            ps.setLong(2, c.getIdVenta());
            ps.setDate(3, new java.sql.Date(c.getFechaEmision().getTime()));
            ps.setDate(4, new java.sql.Date(c.getFechaVence().getTime()));
            ps.setDouble(5, c.getMonto());
            ps.setDouble(6, c.getSaldo());
            ps.setString(7, c.getEstado());
            ps.setLong(8, c.getVentaIdVenta());
            ps.executeUpdate();
        }
    }

    // ✅ Buscar por ID
    public ModeloCuentasPorCobrar buscarPorId(long idCuentaCobro) throws SQLException {
        String sql = "SELECT * FROM cuentas_por_cobrar WHERE idCuentaCobro = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setLong(1, idCuentaCobro);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ModeloCuentasPorCobrar c = new ModeloCuentasPorCobrar();
                c.setIdCuentaCobro(rs.getLong("idCuentaCobro"));
                c.setIdVenta(rs.getLong("idVenta"));
                c.setFechaEmision(rs.getDate("fechaEmision"));
                c.setFechaVence(rs.getDate("fechaVence"));
                c.setMonto(rs.getDouble("monto"));
                c.setSaldo(rs.getDouble("saldo"));
                c.setEstado(rs.getString("estado"));
                c.setVentaIdVenta(rs.getLong("venta_idVenta"));
                return c;
            }
        }
        return null;
    }

    // ✅ Actualizar (por ID)
    public void actualizar(ModeloCuentasPorCobrar c) throws SQLException {
        String sql = "UPDATE cuentas_por_cobrar SET idVenta=?, fechaEmision=?, fechaVence=?, monto=?, saldo=?, estado=?, venta_idVenta=? WHERE idCuentaCobro=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setLong(1, c.getIdVenta());
            ps.setDate(2, new java.sql.Date(c.getFechaEmision().getTime()));
            ps.setDate(3, new java.sql.Date(c.getFechaVence().getTime()));
            ps.setDouble(4, c.getMonto());
            ps.setDouble(5, c.getSaldo());
            ps.setString(6, c.getEstado());
            ps.setLong(7, c.getVentaIdVenta());
            ps.setLong(8, c.getIdCuentaCobro());
            ps.executeUpdate();
        }
    }

    // ✅ Eliminar por ID
    public void eliminar(long idCuentaCobro) throws SQLException {
        String sql = "DELETE FROM cuentas_por_cobrar WHERE idCuentaCobro=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setLong(1, idCuentaCobro);
            ps.executeUpdate();
        }
    }

    // ✅ Listar todas (opcional)
    public List<ModeloCuentasPorCobrar> listarTodas() throws SQLException {
        List<ModeloCuentasPorCobrar> lista = new ArrayList<>();
        String sql = "SELECT * FROM cuentas_por_cobrar";
        try (Statement st = conexion.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                ModeloCuentasPorCobrar c = new ModeloCuentasPorCobrar(
                        rs.getLong("idCuentaCobro"),
                        rs.getLong("idVenta"),
                        rs.getDate("fechaEmision"),
                        rs.getDate("fechaVence"),
                        rs.getDouble("monto"),
                        rs.getDouble("saldo"),
                        rs.getString("estado"),
                        rs.getLong("venta_idVenta")
                );
                lista.add(c);
            }
        }
        return lista;
    }
}
