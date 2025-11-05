package Implementacion;

import Conector.DBConnection;
import Modelo.CuentaPorPagar;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CuentaPorPagarImpl {
    private final DBConnection db;

    public CuentaPorPagarImpl() {
        this.db = new DBConnection();
        this.db.conectar(); // 👈 abre la conexión a Oracle
    }

    // ➕ AGREGAR
    public boolean agregar(CuentaPorPagar cuenta) {
        
        System.out.println("ID de compra asignado: " + cuenta.getIdCompra());
String sql = "INSERT INTO FARMACIA.CUENTA_POR_PAGAR " +
             "(ID_CUENTA_PAGO,ID_COMPRA, FECHA_EMISION, FECHA_VENCIMIENTO, MONTO_TOTAL, SALDO_PENDIENTE, ESTADO_PAGO)" +
             "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = db.preparar(sql)) {
            ps.setInt(1,cuenta.getIdCuentaPago());
            ps.setInt(2, cuenta.getIdCompra());
            ps.setDate(3, new java.sql.Date(cuenta.getFechaEmision().getTime()));
            ps.setDate(4, new java.sql.Date(cuenta.getFechaVencimiento().getTime()));
            ps.setDouble(5, cuenta.getMontoTotal());
            ps.setDouble(6, cuenta.getSaldoPendiente());
            ps.setString(7, cuenta.getEstadoPago());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al agregar cuenta por pagar: " + e.getMessage());
            return false;
        } finally {
        }
    }

    // ✏️ ACTUALIZAR
    public boolean actualizar(CuentaPorPagar cuenta) {
        String sql = "UPDATE FARMACIA.CUENTA_POR_PAGAR SET " +
                     "ID_COMPRA=?, FECHA_EMISION=?, FECHA_VENCIMIENTO=?, MONTO_TOTAL=?, " +
                     "SALDO_PENDIENTE=?, ESTADO_PAGO=? WHERE ID_CUENTA_PAGO=?";
        try (PreparedStatement ps = db.preparar(sql)) {
            ps.setInt(1, cuenta.getIdCompra());
            ps.setDate(2, new java.sql.Date(cuenta.getFechaEmision().getTime()));
            ps.setDate(3, new java.sql.Date(cuenta.getFechaVencimiento().getTime()));
            ps.setDouble(4, cuenta.getMontoTotal());
            ps.setDouble(5, cuenta.getSaldoPendiente());
            ps.setString(6, cuenta.getEstadoPago());        
            ps.setInt(8, cuenta.getIdCuentaPago());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar cuenta por pagar: " + e.getMessage());
            return false;
        } finally {
        }
    }

    // ❌ ELIMINAR
    public boolean eliminar(int idCuentaPago) {
        String sql = "DELETE FROM FARMACIA.CUENTA_POR_PAGAR WHERE ID_CUENTA_PAGO=?";
        try (PreparedStatement ps = db.preparar(sql)) {
            ps.setInt(1, idCuentaPago);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar cuenta por pagar: " + e.getMessage());
            return false;
        } finally {
            db.desconectar();
        }
    }
}
