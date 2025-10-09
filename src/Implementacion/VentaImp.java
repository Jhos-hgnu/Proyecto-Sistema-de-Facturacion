package Implementacion;

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

    DBConnection conector = new DBConnection();
    SQL sql = new SQL();
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public boolean hacerVenta(ModeloVenta modelo) {

        boolean resultado = true;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = LocalDateTime.now().format(formatter);

        conector.conectar();
        try {
            ps = conector.preparar(sql.getHACER_VENTA());
            ps.setInt(1, 1);
            ps.setInt(2, 0);
            ps.setString(3, fechaFormateada);
            ps.setBigDecimal(4, modelo.getSubTotal());
            ps.setBigDecimal(5, modelo.getDescuento());
            ps.setBigDecimal(6, modelo.getTotal());
            ps.setString(7, modelo.getTipoPago());
            ps.setBoolean(8, modelo.isSubsidio());
            ps.setString(9, modelo.getInstitucion());
            ps.setString(10, modelo.getObservaciones());

        } catch (Exception e) {
        }
        return resultado;
    }

    @Override
    public boolean guardarDetalleVenta(ModeloVenta modelo) {

        conector.conectar();
        boolean resultado = false;

        try {
            ps = conector.preparar(sql.getINSERTAR_DETALLE_VENTA());
            ps.setInt(1, 0);

        } catch (Exception e) {
        }
        return resultado;
    }

    @Override
    public ModeloProducto buscarProducto(String nombreP, String codigoB) {

        System.out.println("HOLA DESDE IMP");
        ModeloProducto modelo = null;
        String sqlEjecutar;
        boolean buscarPorNombre = !nombreP.isEmpty();

        if (buscarPorNombre) {
            sqlEjecutar = sql.getBUSCAR_PRODUCTOS_NOMBRES();
        } else {
            sqlEjecutar = sql.getBUSCAR_PRODUCTO_CODIGO();
        }

        conector.conectar();

        try {
            ps = conector.preparar(sqlEjecutar);

            if (buscarPorNombre) {
                ps.setString(1, "%" + nombreP + "%");
                ps.setString(2, "%" + nombreP + "%");
            } else {
                ps.setString(1, codigoB);
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
                    modelo.setPrecioVenta(rs.getBigDecimal("precio"));
                    modelo.setCantidadDisponible(rs.getInt("cantidad_disponible"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductoImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conector.desconectar();
        }
        System.out.println("Nombre " + modelo.getNombreOficialP());
        return modelo;
    }

    public ModeloClientesVentas consultarCliente(String criterioNitId) {

        ModeloClientesVentas modelo = null;

        conector.conectar();

        try {
            ps = conector.preparar(sql.getBUSCAR_CLIENTE_NIT_IDENTIFICACION());
            ps.setString(1, criterioNitId);
            ps.setString(2, criterioNitId);

            try (ResultSet rs = ps.executeQuery()) {
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
            } catch (Exception e) {
                Logger.getLogger(this.getClass().getName()).log(
                        Level.SEVERE,
                        "Error al procesar resultados de la consulta para cliente: " + criterioNitId,
                        e
                );
            }

        } catch (SQLException e) {
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                conector.desconectar();
            } catch (SQLException e) {

                Logger.getLogger(this.getClass().getName()).log(
                        Level.WARNING,
                        "Error al cerrar recursos de conexión",
                        e
                );

            }
        }

        return modelo;
    }

    public boolean hacerVentaCompleta(ModeloClientesVentas venta, String UsuarioObtenido, int idUsuarioObtenido) {

        DBConnection db = new DBConnection();
        PreparedStatement psVenta = null;
        PreparedStatement psDetalle = null;
        PreparedStatement psUpdateLote = null;
        PreparedStatement psComprobante = null;
        ResultSet rs = null;
        

        try {

            db.conectar();
            db.comenzarTransaccion(); // Iniciar transacción

            // 1. Insertar en ventas
            String sqlVenta = "INSERT INTO ventas (id_usuario, id_cliente, subtotal, descuento_subsidio, total, tipo_pago, con_subsidio, id_institucion_subsidio, observaciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            psVenta = db.preparar(sqlVenta, true); // Usamos el método que permite retornar IDs
            psVenta.setInt(1, idUsuarioObtenido);
            psVenta.setObject(2, venta.getIdCliente() != 0 ? venta.getIdCliente() : null);
            psVenta.setDouble(3, venta.getSubtotal());
            psVenta.setDouble(4, venta.getDescuento());
            psVenta.setDouble(5, venta.getTotal());
            psVenta.setString(6, venta.getTipoPago());
            psVenta.setBoolean(7, venta.isConSubsidio());
            psVenta.setObject(8, venta.getIdInstitucionSubsidio() != 0 ? venta.getIdInstitucionSubsidio() : null);
            psVenta.setString(9, venta.getObservaciones());
            psVenta.executeUpdate();
            
            String descuento = String.valueOf(venta.getDescuento());
            String subTotal = String.valueOf(venta.getSubtotal());
            String total = String.valueOf(venta.getTotal());
            rs = psVenta.getGeneratedKeys();
            if (rs.next()) {
                int idVenta = rs.getInt(1);

               // 2. Insertar detalles de venta y actualizar lotes
            String sqlDetalle = "INSERT INTO detalle_ventas (id_venta, id_lote, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
            psDetalle = db.preparar(sqlDetalle);

            String sqlUpdateLote = "UPDATE lotes SET cantidad_disponible = cantidad_disponible - ? WHERE id_lote = ?";
            psUpdateLote = db.preparar(sqlUpdateLote);

            for (ModeloDetalleVenta det : venta.getCarrito()) {
                // Insertar detalle
                psDetalle.setInt(1, idVenta);
                psDetalle.setInt(2, det.getIdLote());
                psDetalle.setInt(3, det.getCantidad());
                psDetalle.setDouble(4, det.getPrecioUnitario());
                psDetalle.setDouble(5, det.getSubtotal());
                psDetalle.addBatch();

                // Actualizar lote
                psUpdateLote.setInt(1, det.getCantidad());
                psUpdateLote.setInt(2, det.getIdLote());
                psUpdateLote.addBatch();
            }

            psDetalle.executeBatch();
            psUpdateLote.executeBatch();    
            
            GeneradorPDFVentas comprobante = new GeneradorPDFVentas();
            
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea generar el comprobante en PDF?", "Comprobante",JOptionPane.YES_NO_OPTION);
                if(opcion == JOptionPane.YES_OPTION ){  
                    System.out.println("Comprobante");
                   
                    String tipoDoc = "Venta";
                    //Insertar Comprobante
                    String codigo = generadorCodigo.generarCodigoCompleto(idVenta, tipoDoc);
                    String nombreArchivo = generadorCodigo.generarNombrePDF(idVenta, tipoDoc);
                    String rutaArchivo = GeneradorPDFVentas.obtenerRutaComprobantes();
                    
                    
                    String sqlComprobante = "INSERT INTO comprobantes (id_venta, codigo_comprobante, nombre_archivo,ruta) VALUES (?,?,?,?)";
                    psComprobante = db.preparar(sqlComprobante);
                    psComprobante.setInt(1, idVenta);
                    psComprobante.setString(2, codigo);
                    psComprobante.setString(3, nombreArchivo);
                    psComprobante.setString(4, rutaArchivo);
                    psComprobante.executeUpdate();
                    
                    db.confirmarTransaccion();
                    
                    //Crear comprobante Agregar Ruta y TOTAL
                    comprobante.generarFacturaPDF(venta.getCarrito(), nombreArchivo,codigo, venta, UsuarioObtenido, idVenta, subTotal, total, descuento, rutaArchivo);

                 return true;
                } else {
                    db.revertirTransaccion();
                    return false;
                }
            }
            db.revertirTransaccion();
            return false;

        } catch (HeadlessException | SQLException e) {
            db.revertirTransaccion();
            return false;
        } finally {
            //Cerrar recursos
        try { if (rs != null) rs.close(); } catch (SQLException e) {}
        try { if (psVenta != null) psVenta.close(); } catch (SQLException e) {}
        try { if (psDetalle != null) psDetalle.close(); } catch (SQLException e) {}
        try { if (psUpdateLote != null) psUpdateLote.close(); } catch (SQLException e) {}
        try { if (psComprobante != null) psComprobante.close(); } catch (SQLException e) {}
        
        db.desconectar();
        
        }       
    }

}
