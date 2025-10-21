package Implementacion;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import Conector.DBConnection;
import Conector.SQL;
import Interfaces.IVenta;
import Modelo.ModeloClientesVentas;
import Modelo.ModeloDetalleVenta;
import Modelo.ModeloProducto;
import Modelo.ModeloRegistroCliente;
import Modelo.ModeloVenta;
import Modelo.ModeloVistaInicio;
import Utilities.GeneradorPDFVentas;
import Utilities.generadorCodigo;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author jhosu
 */
public class VentaImp implements IVenta {

    private final DBConnection conector = new DBConnection();
    private final SQL sql = new SQL();
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    /**
     * Inserta una venta en la base de datos con los datos generales.
     */
    @Override
    public boolean hacerVenta(ModeloVenta modelo) {
        boolean resultado = false;

        // Fecha actual en formato yyyy-MM-dd
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaFormateada = LocalDateTime.now().format(formatter);

        conector.conectar();
        try {
            ps = conector.preparar(sql.getHACER_VENTA());
            // Ejemplo de SQL esperado:
            // INSERT INTO ventas (id_venta, nit, fecha, tipo_pago, documento, total, id_usuario, observacion, clientes_nit, usuarios_id_usuario)
            // VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
            ps.setLong(1, modelo.getIdVenta());
            ps.setString(2, modelo.getNit());
            ps.setString(3, fechaFormateada);
            ps.setString(4, modelo.getTipoPago());
            ps.setString(5, modelo.getDocumento());
            ps.setDouble(6, modelo.getTotal());
            ps.setLong(7, modelo.getIdUsuario());
            ps.setString(8, modelo.getObservacion());
            ps.setString(9, modelo.getClientesNit());
            ps.setLong(10, modelo.getUsuariosIdUsuario());

            int filas = ps.executeUpdate();
            resultado = filas > 0;

        } catch (SQLException e) {
            Logger.getLogger(VentaImp.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            cerrarRecursos();
        }

        return resultado;
    }

    /**
     * Guarda el detalle de una venta (productos vendidos).
     */
    @Override
    public boolean guardarDetalleVenta(ModeloVenta modelo) {
        boolean resultado = false;
        conector.conectar();

        try {
            // Este SQL debería venir de tu clase SQL (puedes reemplazarlo si difiere)
            // Ejemplo:
            // INSERT INTO detalle_ventas (id_venta, producto, precio, cantidad, subtotal) VALUES (?, ?, ?, ?, ?);
            ps = conector.preparar(sql.getINSERTAR_DETALLE_VENTA());

            // Los datos de detalle dependerán de cómo manejes los productos
            // Aquí te muestro un ejemplo base:
            ps.setLong(1, modelo.getIdVenta());
            ps.setString(2, modelo.getDocumento()); // Puedes cambiar según tu estructura
            ps.setDouble(3, modelo.getTotal());     // Ejemplo: usar total como precio de prueba
            ps.setInt(4, 1);                        // Ejemplo: cantidad
            ps.setDouble(5, modelo.getTotal());     // subtotal igual al total (solo como muestra)

            int filas = ps.executeUpdate();
            resultado = filas > 0;

        } catch (SQLException e) {
            Logger.getLogger(VentaImp.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            cerrarRecursos();
        }

        return resultado;
    }

    /**
     * Busca un producto por nombre o código.
     */
    @Override
    public ModeloProducto buscarProducto(String nombreP, String codigoB) {
        ModeloProducto modelo = null;
        boolean buscarPorNombre = nombreP != null && !nombreP.isEmpty();
        String sqlEjecutar = buscarPorNombre ? sql.getBUSCAR_PRODUCTOS_NOMBRES() : sql.getBUSCAR_PRODUCTO_CODIGO();

        conector.conectar();
        try {
            ps = conector.preparar(sqlEjecutar);

            if (buscarPorNombre) {
                ps.setString(1, "%" + nombreP + "%");
                ps.setString(2, "%" + nombreP + "%");
            } else {
                ps.setString(1, codigoB);
            }

            rs = ps.executeQuery();
            if (rs.next()) {
                modelo = new ModeloProducto();
                modelo.setIdProducto(rs.getInt("id_producto"));
                modelo.setNombreOficialP(rs.getString("nombre_oficial"));
                modelo.setDescripcionP(rs.getString("descripcion"));
                modelo.setCodigoBarrasP(rs.getString("codigo_barras"));
                modelo.setRequiereRecetaP(rs.getBoolean("requiere_receta"));
                modelo.setActivoP(rs.getBoolean("activo"));
                modelo.setPrecioVenta(rs.getBigDecimal("precio"));
                modelo.setCantidadDisponible(rs.getInt("cantidad_disponible"));
            }

        } catch (SQLException e) {
            Logger.getLogger(VentaImp.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            cerrarRecursos();
        }

        return modelo;
    }

    /**
     * Consulta un cliente por NIT o número de identificación.
     */
    public ModeloClientesVentas consultarCliente(String criterioNitId) {
        ModeloClientesVentas modelo = null;
        conector.conectar();

        try {
            ps = conector.preparar(sql.getBUSCAR_CLIENTE_NIT_IDENTIFICACION());
            ps.setString(1, criterioNitId);
            ps.setString(2, criterioNitId);
            rs = ps.executeQuery();

            if (rs.next()) {
                modelo = new ModeloClientesVentas();
                modelo.setIdCliente(rs.getInt("id_cliente"));
                modelo.setNombre(rs.getString("nombre"));
                modelo.setApellido(rs.getString("apellido"));
                modelo.setDireccion(rs.getString("direccion"));
                modelo.setNit(rs.getString("nit"));
                modelo.setIdentificacion(rs.getString("identificacion"));
                modelo.setTelefono(rs.getString("telefono"));
                modelo.setTieneSubsidio(rs.getBoolean("tiene_subsidio"));
                int idInstitucion = rs.getInt("id_institucion_subsidio");
                if (!rs.wasNull()) {
                    modelo.setIdInstitucionSubsidio(idInstitucion);
                }
            }

        } catch (SQLException e) {
            Logger.getLogger(VentaImp.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            cerrarRecursos();
        }

        return modelo;
    }

    /**
     * Cierra los recursos abiertos (ps, rs y conexión).
     */
    
    private void cerrarRecursos() {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
        }
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
        }
        conector.desconectar();
    }
}
