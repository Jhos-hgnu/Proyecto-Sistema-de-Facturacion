/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementacion;

//import Modelo.ModeloRegistroCliente;
//import Conector.DBConnection;
//import Conector.SQL;
//import Interfaces.IRegistroCliente;
import Modelo.ModeloRegistroCliente.Cliente;
//import controladores.ControladorClientes;
//import java.sql.Timestamp;
import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import javax.swing.table.DefaultTableModel;
import Interfaces.IRegistroCliente;
//import Modelo.Cliente;
import Conector.DBConnection;
import Conector.SQL;
import java.util.ArrayList;
import java.util.List;
import controladores.ControladorClientes;

public class RegistroClienteImpl implements IRegistroCliente {

    private final DBConnection conexion;
    private final SQL sql;

    public RegistroClienteImpl(DBConnection conexion, SQL sql) {
        this.conexion = conexion;
        this.sql = sql;
    }
        
   
//import java.sql.*;
    @Override
    public boolean insertarCliente(Cliente cliente) {
        try (Connection conn = conexion.getConnection()) {
            if (conn == null) {
                throw new SQLException("Conexión nula.");
            }
            try (PreparedStatement ps = conn.prepareStatement(sql.getINSERTAR_CLIENTE())) {
                ps.setString(1, cliente.getNombre());
                ps.setString(2, cliente.getApellido());
                ps.setString(3, cliente.getTelefono());
                ps.setString(4, cliente.getDireccion());
                ps.setString(5, cliente.getIdentificacion());
                ps.setString(6, cliente.getNit());
                ps.setBoolean(7, cliente.isTieneSubsidio());
                if (cliente.getIdInstitucionSubsidio() != null) {
                    ps.setObject(8, cliente.getIdInstitucionSubsidio(), Types.INTEGER);
                } else {
                    ps.setNull(8, Types.INTEGER);
                }
                ps.setTimestamp(9, cliente.getFechaRegistro());
                ps.setBoolean(10, cliente.isActivo());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizarCliente(Cliente cliente) {
        try (Connection conn = conexion.getConnection()) {
            if (conn == null) {
                throw new SQLException("Conexión nula. No se pudo conectar a la base de datos.");
            }
//        try (Connection conn = conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql.getACTUALIZAR_CLIENTE())) {
            try (PreparedStatement ps = conn.prepareStatement(sql.getACTUALIZAR_CLIENTE())) {
                ps.setString(1, cliente.getNombre());
                ps.setString(2, cliente.getApellido());
                ps.setString(3, cliente.getTelefono());
                ps.setString(4, cliente.getDireccion());
                ps.setString(5, cliente.getIdentificacion());
                ps.setString(6, cliente.getNit());
                ps.setBoolean(7, cliente.isTieneSubsidio());
//                ps.setObject(8, cliente.getIdInstitucionSubsidio(), Types.INTEGER);
//                ps.setBoolean(9, cliente.isActivo());
//                ps.setInt(10, cliente.getIdCliente());
//
//                return ps.executeUpdate() > 0;
//            } catch (SQLException e) {
//                e.printStackTrace();
//                return false;
//            }
//        }
                if (cliente.getIdInstitucionSubsidio() != null) {
                    ps.setObject(8, cliente.getIdInstitucionSubsidio(), Types.INTEGER);
                } else {
                    ps.setNull(8, Types.INTEGER);
                }
                ps.setBoolean(9, cliente.isActivo());
                ps.setInt(10, cliente.getIdCliente());

                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarCliente(int idCliente) {
        try (Connection conn = conexion.getConnection()) {
            if (conn == null) {
                throw new SQLException("Conexión nula. No se pudo conectar a la base de datos.");
            }

//            ps.setInt(1, idCliente);
//            return ps.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
            try (PreparedStatement ps = conn.prepareStatement(sql.getELIMINAR_CLIENTE())) {
                ps.setInt(1, idCliente);
                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();

//        try (Connection conn = conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql.getLISTAR_CLIENTES()); ResultSet rs = ps.executeQuery()) {
//
//            while (rs.next()) {
//                clientes.add(mapearCliente(rs));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return clientes;
//    }
        try (Connection conn = conexion.getConnection()) {
            if (conn == null) {
                throw new SQLException("Conexión nula. No se pudo conectar a la base de datos.");
            }
            try (PreparedStatement ps = conn.prepareStatement(sql.getLISTAR_CLIENTES()); ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    clientes.add(mapearCliente(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar clientes: " + e.getMessage());
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public Cliente obtenerClientePorId(int idCliente) {
//        try (Connection conn = conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql.getOBTENER_CLIENTE_POR_ID())) {
//
//            ps.setInt(1, idCliente);
//            try (ResultSet rs = ps.executeQuery()) {
//                if (rs.next()) {
//                    return mapearCliente(rs);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
        try (Connection conn = conexion.getConnection()) {
            if (conn == null) {
                throw new SQLException("Conexión nula. No se pudo conectar a la base de datos.");
            }

            try (PreparedStatement ps = conn.prepareStatement(sql.getOBTENER_CLIENTE_POR_ID())) {
                ps.setInt(1, idCliente);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return mapearCliente(rs);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener cliente por ID: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    private Cliente mapearCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(rs.getInt("id_cliente"));
        cliente.setNombre(rs.getString("nombre"));
        cliente.setApellido(rs.getString("apellido"));
        cliente.setTelefono(rs.getString("telefono"));
        cliente.setDireccion(rs.getString("direccion"));
        cliente.setIdentificacion(rs.getString("identificacion"));
        cliente.setNit(rs.getString("nit"));
        cliente.setTieneSubsidio(rs.getBoolean("tiene_subsidio"));

        // Validar si el campo puede ser nulo
        int idInstitucion = rs.getInt("id_institucion_subsidio");
        if (rs.wasNull()) {
            cliente.setIdInstitucionSubsidio(null);
        } else {
            cliente.setIdInstitucionSubsidio(idInstitucion);
        }

        cliente.setFechaRegistro(rs.getTimestamp("fecha_registro"));
        cliente.setActivo(rs.getBoolean("activo"));

        return cliente;
    }
}

