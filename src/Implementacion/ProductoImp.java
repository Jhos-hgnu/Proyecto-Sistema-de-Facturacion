package Implementacion;

import Conector.DBConnection;
import Conector.SQL;
import Interfaces.IProducto;
import Modelo.ModeloProducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jhosu
 */
public class ProductoImp implements IProducto {

    DBConnection conector = new DBConnection();
    SQL sql = new SQL();
    PreparedStatement ps;
    ResultSet rs;

//    @Override
//    public boolean guardarProducto(ModeloProducto modelo) throws SQLException {
//        boolean resultado = false;
//        conector.conectar();
//        System.out.println("Hola desde implementacion");
//        
//        try {
//            ps = conector.preparar(sql.getAGREGAR_PRODUCTO());
//            System.out.println("nombre " + modelo.getNombreOficialP());
//            System.out.println("descripcion " + modelo.getDescripcionP());
//            ps.setString(1, modelo.getNombreOficialP());
//            ps.setString(2, modelo.getDescripcionP());
//            ps.setString(3, modelo.getCodigoBarrasP());
//            ps.setBoolean(4, modelo.isRequiereRecetaP());
//            ps.setBoolean(5, modelo.isActivoP());
//
//            int filasAfectadas = ps.executeUpdate();
//            resultado = (filasAfectadas > 0);
//        } catch (SQLException e) {
//            System.out.println("Error al en Imp guardarP" + e);
//            resultado = false;
//        }
//        System.out.println(resultado);
//        return resultado;
//    }
    @Override
    public boolean actualizarProducto(ModeloProducto modelo) {

        boolean resultado = false;
        conector.conectar();

//        try {
//            ps = conector.preparar(sql.getACTUALIZAR_PRODUCTO());
//            ps.setString(1, modelo.getNombreOficialP());
//            ps.setString(2, modelo.getDescripcionP());
//            ps.setString(3, modelo.getCodigoBarrasP());
//            ps.setBoolean(4, modelo.isRequiereRecetaP());
//            ps.setBoolean(5, modelo.isActivoP());
//
//            int filasAfectadas = ps.executeUpdate();
//            resultado = (filasAfectadas > 0);
//
//        } catch (SQLException e) {
//            resultado = false;
//        }
        return resultado;
    }

    @Override
    public boolean eliminarProducto(int idProducto) {

        boolean resultado = false;
        PreparedStatement ps = null;

//        try {
//            conector.conectar();
//            ps = conector.preparar(sql.getELIMINAR_PRODUCTO());
//
//            ps.setInt(1, idProducto);
//
//            int filasAfectadas = ps.executeUpdate();
//            resultado = (filasAfectadas > 0);
//
//        } catch (SQLException e) {
//            resultado = false;
//            System.out.println("No se puedo eliminar el producto Mimp(eliminarProducto) " + e);
//
//        } finally {
//
//            if (ps != null) {
//                try {
//                    ps.close();
//                } catch (SQLException e) {
//                }
//            }
//            if (conector != null) {
//                conector.desconectar();
//            }
//        }
        return resultado;
    }

    @Override
    public ModeloProducto mostrarProducto(String nombreP, String codigoP) {

        ModeloProducto modelo = null;
        String sqlEjecutar;
        boolean buscarPorNombre = !nombreP.isEmpty();

//        if (buscarPorNombre) {
//            sqlEjecutar = sql.getCONSULTA_PRODUCTO_NOMBRE();
//        } else {
//            sqlEjecutar = sql.getCONSULTA_PRODUCTO_CODIGO();
//        }

        conector.conectar();

        try {
//            ps = conector.preparar(sqlEjecutar);

            if (buscarPorNombre) {
                ps.setString(1, nombreP);
            } else {
                ps.setString(1, codigoP);
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    modelo = new ModeloProducto();
                    modelo.setIdProducto(rs.getInt("id_producto"));
                    modelo.setNombreOficialP(rs.getString("nombre_oficial"));
                    modelo.setDescripcionP(rs.getString("descripcion"));
                    modelo.setCodigoBarrasP(rs.getString("codigo_barras"));
                    modelo.setRequiereRecetaP(rs.getBoolean("requiere_receta")); // Corregido
                    modelo.setActivoP(rs.getBoolean("activo")); // Añadido si existe
//                    modelo.setFechaRegistro(rs.getString("fecha_registro"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductoImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conector.desconectar();
        }
        return modelo;
    }

    @Override
    public boolean guardarLote(ModeloProducto modelo, int idProducto, Connection conn) throws SQLException {

//        String sqlU = sql.getAGREGAR_LOTE_PRODUCTO();
//        try (PreparedStatement ps = conn.prepareStatement(sqlU)) {
//
//            //Formatear la fecha papu
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//            LocalDate vencimiento = LocalDate.parse(modelo.getFechaVencimiento(), formatter);
//            LocalDate fabricación = LocalDate.parse(modelo.getFechaFabricación(), formatter);
//
//            ps.setInt(1, idProducto);
//            ps.setString(2, modelo.getNumeroLote());
//            ps.setDate(3, Date.valueOf(vencimiento));
//            ps.setDate(4, Date.valueOf(fabricación));
//            ps.setInt(5, modelo.getCantidadDisponible());
//            ps.setBigDecimal(6, modelo.getPrecioCompra());
//            ps.setBigDecimal(7, modelo.getPrecioVenta());
//            ps.setBoolean(8, true); // o lote.isActivo()
//
//            return ps.executeUpdate() > 0;
//        }
        return false;
    }

    @Override
    public boolean guardarNombreAlternativo(ModeloProducto modelo, int idProducto, Connection conn) throws SQLException {

//        String sqlU = sql.getAGREGAR_NOMBRE_ALTERNATIVO();
//        try (PreparedStatement ps = conn.prepareStatement(sqlU)) {
//
//            ps.setInt(1, idProducto);
//            ps.setString(2, modelo.getNombreAlternativo());
//
//            return ps.executeUpdate() > 0;
//        }

//RETURN AGREGADO
         return false;
    }

    public boolean guardarProductoCompleto(ModeloProducto modelo) {
        boolean exito = false;
        try {
            conector.conectar();
            conector.comenzarTransaccion();
            Connection conn = conector.getConnection();
            
            
            int idProducto = guardarProducto(modelo);

            if (idProducto == -1) {
                throw new SQLException("No se pudo insertar el producto");
            }

            //Guardar lote
            if (modelo.getFechaVencimiento() != null && !guardarLote(modelo, idProducto, conn)) {
                throw new SQLException("Error al guardar lote");
            }

            if (modelo.getNombreAlternativo() != null && !guardarNombreAlternativo(modelo, idProducto, conn)) {
                throw new SQLException("Error al nombre alternativo");
            }

            conector.confirmarTransaccion();
            exito = true;

        } catch (SQLException e) {
            conector.revertirTransaccion();
            System.out.println("Error en la transacción " + e.getMessage());
        } finally {
            conector.desconectar();
        }
        return exito;
    }

    @Override
    public int guardarProducto(ModeloProducto modelo) throws SQLException {

        int idGenerado = -1;

//        try {
//            ps = conector.preparar(sql.getAGREGAR_PRODUCTO(), true);
//            System.out.println("nombre " + modelo.getNombreOficialP());
//            System.out.println("descripcion " + modelo.getDescripcionP());
//            ps.setString(1, modelo.getNombreOficialP());
//            ps.setString(2, modelo.getDescripcionP());
//            ps.setString(3, modelo.getCodigoBarrasP());
//            ps.setBoolean(4, modelo.isRequiereRecetaP());
//            ps.setBoolean(5, modelo.isActivoP());
//            ps.execute();
//
//            //Obtener ID 
//            ResultSet generatedKeys = ps.getGeneratedKeys();
//            if (generatedKeys.next()) {
//                idGenerado = generatedKeys.getInt(1);
//            }
//
//        } finally {
//        }

        return idGenerado;
    }

}
