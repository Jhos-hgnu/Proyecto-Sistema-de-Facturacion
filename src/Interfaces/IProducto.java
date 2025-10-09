package Interfaces;

import Modelo.ModeloProducto;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author jhosu
 */
public interface IProducto {

    public int guardarProducto(ModeloProducto modelo) throws SQLException;

    public boolean guardarLote(ModeloProducto modelo, int idProducto, Connection conn) throws SQLException;

    public boolean guardarNombreAlternativo(ModeloProducto modelo, int idProducto, Connection conn) throws SQLException;

    public ModeloProducto mostrarProducto(String nombreP, String codigoP);

    public boolean actualizarProducto(ModeloProducto modelo);

    public boolean eliminarProducto(int idProducto);
}
