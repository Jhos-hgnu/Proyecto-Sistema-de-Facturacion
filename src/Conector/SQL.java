package Conector;

/**
 *
 * @author jhosu
 */
public class SQL {

    //DOCUMENTO PARA HACER LAS CONSULTAS A LA BASE DE DATOS
    //CONSULTAS PARA USUARIOS
    private final String CONSULTA_USUARIO = "SELECT id_usuario, usuario, contrasena, tipo_usuario, activo FROM usuarios WHERE usuario = ?";

    //CONSULTAS PARA PRODUCTOS (REGISTRO)
    private final String AGREGAR_PRODUCTO = "INSERT INTO productos (nombre_oficial, descripcion, codigo_barras, requiere_receta, activo) VALUES (?,?,?,?,?)";
    private final String AGREGAR_LOTE_PRODUCTO = "INSERT INTO lotes (id_producto, numero_lote, fecha_vencimiento, fecha_fabricacion, cantidad_disponible, precio_compra, precio_venta, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String AGREGAR_NOMBRE_ALTERNATIVO = "INSERT INTO nombres_alternativos (id_producto, nombre_alternativo) VALUES (?, ?)";

    //Metodos de productos (posible actualización de más funcionalidades)
    private final String CONSULTA_PRODUCTO_NOMBRE = "SELECT id_producto, nombre_oficial, descripcion, codigo_barras, requiere_receta, activo FROM productos WHERE nombre_oficial = ?";
    private final String ELIMINAR_PRODUCTO = "DELETE FROM ProductoS WHERE id_producto = ?";
    private final String ACTUALIZAR_PRODUCTO = "UPDATE productos SET nombre_oficial = ?,descripcion = ?,codigo_barras = ?,requiere_receta = ?,activo = ?,WHERE id_producto = ?";
    private final String CONSULTA_PRODUCTO_CODIGO = "SELECT id_producto, nombre_oficial, descripcion, codigo_barras, requiere_receta, activo FROM productos WHERE codigo_barras = ?";
    private final String CONSULTA_AMBOS_NC = "";

    // CONSULTA SOBRE LOTES
    private final String AGREGAR_LOTE
            = "INSERT INTO lotes (id_producto, numero_lote, fecha_vencimiento, fecha_fabricacion, "
            + "cantidad_disponible, precio_compra, precio_venta, activo) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private final String ACTUALIZAR_LOTE
            = "UPDATE lotes SET id_producto = ?, numero_lote = ?, fecha_vencimiento = ?, "
            + "fecha_fabricacion = ?, cantidad_disponible = ?, precio_compra = ?, "
            + "precio_venta = ?, activo = ? WHERE id_lote = ?";

    private final String ELIMINAR_LOTE
            = "UPDATE lotes SET activo = false WHERE id_lote = ?";

    private final String OBTENER_LOTES_ACTIVOS
            = "SELECT * FROM lotes WHERE activo = true";

    private final String BUSCAR_LOTE_POR_NUMERO
            = "SELECT * FROM lotes WHERE numero_lote LIKE ? AND activo = true";

    private final String OBTENER_LOTES_POR_PRODUCTO
            = "SELECT * FROM lotes WHERE id_producto = ? AND activo = true";

    public String getAGREGAR_LOTE() {
        return AGREGAR_LOTE;
    }

    public String getACTUALIZAR_LOTE() {
        return ACTUALIZAR_LOTE;
    }

    public String getELIMINAR_LOTE() {
        return ELIMINAR_LOTE;
    }

    public String getOBTENER_LOTES_ACTIVOS() {
        return OBTENER_LOTES_ACTIVOS;
    }

    public String getBUSCAR_LOTE_POR_NUMERO() {
        return BUSCAR_LOTE_POR_NUMERO;
    }

    public String getOBTENER_LOTES_POR_PRODUCTO() {
        return OBTENER_LOTES_POR_PRODUCTO;
    }

// COSULRA PARA CLIENTES USUSARIOS
    private final String INSERTAR_CLIENTE
            = "INSERT INTO clientes (nombre, apellido, telefono, direccion, identificacion, nit, tiene_subsidio, id_institucion_subsidio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private final String ACTUALIZAR_CLIENTE
            = "UPDATE clientes SET nombre = ?, apellido = ?, telefono = ?, direccion = ?, identificacion = ?, nit = ?, tiene_subsidio = ?, id_institucion_subsidio = ?, activo = ? WHERE id_cliente = ?";

    private final String ELIMINAR_CLIENTE
            = "UPDATE clientes SET activo = FALSE WHERE id_cliente = ?";

    private final String LISTAR_CLIENTES
            = "SELECT * FROM clientes WHERE activo = TRUE";

    private final String OBTENER_CLIENTE_POR_ID
            = "SELECT * FROM clientes WHERE id_cliente = ?";

    public String getCONSULTA_AMBOS_NC() {
        return CONSULTA_AMBOS_NC;
    }

    public String getINSERTAR_CLIENTE() {
        return INSERTAR_CLIENTE;
    }

    public String getACTUALIZAR_CLIENTE() {
        return ACTUALIZAR_CLIENTE;
    }

    public String getELIMINAR_CLIENTE() {
        return ELIMINAR_CLIENTE;
    }

    public String getLISTAR_CLIENTES() {
        return LISTAR_CLIENTES;
    }

    public String getOBTENER_CLIENTE_POR_ID() {
        return OBTENER_CLIENTE_POR_ID;
    }

    //CONSULTA PARA LOS USUARIOS
    private final String AGREGAR_USUARIO = "INSERT INTO usuarios (nombre, apellido, telefono, email, usuario, contrasena, tipo_usuario, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    //CONSULTA PARA LAS VENTAS
    private final String HACER_VENTA = "INSERT INTO ventas (id_usuario,id_cliente,fecha, subtotal, descuento_subsidio, total, tipo_pago, con_subsidio, id_institucion_subsidio, observaciones) VALUES (?, ?, NOW(), ?, ?, ?, ?, ?, ?, ?);";
    private final String INSERTAR_DETALLE_VENTA = "INSERT INTO detalle_ventas (id_venta, id_lote, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
    private final String ACTUALIZAR_STOCK = "UPDATE lotes SET cantidad_disponible = cantidad_disponible - ? WHERE id_lote = ?";
    private final String BUSCAR_CLIENTE_NIT_IDENTIFICACION = "SELECT id_cliente, nombre, apellido, direccion, nit, identificacion," + "telefono, tiene_subsidio, id_institucion_subsidio" + "FROM clientes"
            + "WHERE (nit = ? OR identificacion = ?) AND activo = TRUE";
    private final String BUSCAR_PRODUCTOS_NOMBRES = "SELECT p.id_producto, p.nombre_oficial, p.descripcion, "
            + "p.codigo_barras, p.requiere_receta, p.activo, "
            + "l.precio_venta AS precio, SUM(l.cantidad_disponible) AS cantidad_disponible "
            + "FROM productos p "
            + "LEFT JOIN nombres_alternativos na ON p.id_producto = na.id_producto "
            + "LEFT JOIN lotes l ON p.id_producto = l.id_producto "
            + "WHERE (p.nombre_oficial LIKE ? OR na.nombre_alternativo LIKE ?) "
            + "AND l.activo = TRUE "
            + "GROUP BY p.id_producto, p.nombre_oficial, p.descripcion, "
            + "p.codigo_barras, p.requiere_receta, p.activo, l.precio_venta "
            + "ORDER BY p.nombre_oficial";

    private final String BUSCAR_PRODUCTO_CODIGO = "SELECT p.id_producto, p.nombre_oficial, p.descripcion, "
            + "p.codigo_barras, p.requiere_receta, p.activo, "
            + "l.precio_venta AS precio, SUM(l.cantidad_disponible) AS cantidad_disponible "
            + "FROM productos p "
            + "LEFT JOIN lotes l ON p.id_producto = l.id_producto "
            + "WHERE p.codigo_barras = ? "
            + "AND l.activo = TRUE "
            + "GROUP BY p.id_producto, p.nombre_oficial, p.descripcion, "
            + "p.codigo_barras, p.requiere_receta, p.activo, l.precio_venta";
    private final String GENERAR_COMPROBANTE = "";
    private final String CONSULTAR_USUARIO = "";

    public String getCONSULTA_USUARIO() {
        return CONSULTA_USUARIO;
    }

    public String getAGREGAR_PRODUCTO() {
        return AGREGAR_PRODUCTO;
    }

    public String getELIMINAR_PRODUCTO() {
        return ELIMINAR_PRODUCTO;
    }

    public String getACTUALIZAR_PRODUCTO() {
        return ACTUALIZAR_PRODUCTO;
    }

    public String getAGREGAR_USUARIO() {
        return AGREGAR_USUARIO;
    }

    public String getAtualizar_USUARIO() {
        return AGREGAR_USUARIO;
    }

    public String geteiminar_USUARIO() {
        return AGREGAR_USUARIO;
    }

    public String getHACER_VENTA() {
        return HACER_VENTA;
    }

    public String getGENERAR_COMPROBANTE() {
        return GENERAR_COMPROBANTE;
    }

    public String getCONSULTA_PRODUCTO_NOMBRE() {
        return CONSULTA_PRODUCTO_NOMBRE;
    }

    public String getCONSULTA_PRODUCTO_CODIGO() {
        return CONSULTA_PRODUCTO_CODIGO;
    }

    public String getAGREGAR_LOTE_PRODUCTO() {
        return AGREGAR_LOTE_PRODUCTO;
    }

    public String getAGREGAR_NOMBRE_ALTERNATIVO() {
        return AGREGAR_NOMBRE_ALTERNATIVO;
    }

    public String getINSERTAR_DETALLE_VENTA() {
        return INSERTAR_DETALLE_VENTA;
    }

    public String getACTUALIZAR_STOCK() {
        return ACTUALIZAR_STOCK;
    }

    public String getCONSULTAR_USUARIO() {
        return CONSULTAR_USUARIO;
    }

    public String getBUSCAR_PRODUCTOS_NOMBRES() {
        return BUSCAR_PRODUCTOS_NOMBRES;
    }

    public String getBUSCAR_PRODUCTO_CODIGO() {
        return BUSCAR_PRODUCTO_CODIGO;
    }

    public String getBUSCAR_CLIENTE_NIT_IDENTIFICACION() {
        return BUSCAR_CLIENTE_NIT_IDENTIFICACION;
    }

    // reportes sql 
    private final String OBTENER_VENTAS_DIA
            = "SELECT v.id_venta, DATE_FORMAT(v.fecha, '%d/%m/%Y %H:%i') AS fecha, "
            + "CONCAT(c.nombre, ' ', c.apellido) AS cliente, v.subtotal, "
            + "v.descuento_subsidio AS descuento, v.total, v.tipo_pago "
            + "FROM ventas v "
            + "JOIN clientes c ON v.id_cliente = c.id_cliente "
            + "WHERE DATE(v.fecha) = CURDATE() "
            + "ORDER BY v.fecha DESC";

    private final String INSERTAR_REPORTE
            = "INSERT INTO reportes (tipo_reporte, id_usuario_generador, "
            + "parametros, nombre_archivo, ruta_archivo) "
            + "VALUES (?, ?, ?, ?, ?)";

    public String getOBTENER_VENTAS_DIA() {
        return OBTENER_VENTAS_DIA;
    }

    public String getINSERTAR_REPORTE() {
        return INSERTAR_REPORTE;
    }
    // sql de componentes
    
    private final String AGREGAR_COMPONENTE_PRODUCTO = 
    "INSERT INTO componentes_producto (id_producto, id_componente, concentracion) VALUES (?, ?, ?)";

public String getAGREGAR_COMPONENTE_PRODUCTO() {
    return AGREGAR_COMPONENTE_PRODUCTO;
}
    
}
