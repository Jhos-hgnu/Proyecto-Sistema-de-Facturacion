/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementacion;

/**
 *
 * @author brand
 */



import Conector.DBConnection;
import Interfaces.UsuarioDAO;
import Modelo.Usuario;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioDAOImpl implements UsuarioDAO {

    private final DBConnection db;

    public UsuarioDAOImpl() {
        this.db = new DBConnection();
        this.db.conectar(); // conexión al instanciar el DAO
    }

    @Override
public Long crear(Usuario u) throws Exception {
    String sql = """
        INSERT INTO usuarios (
          tipo_usuario, contrasena, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido,
          apellido_casado, email, telefono, id_rol, estado, fecha_creacion
        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)
    """;

    // >>> prepara pidiendo la columna identidad por nombre <<<
    try (PreparedStatement ps = db.getConnection()
            .prepareStatement(sql, new String[] { "ID_USUARIO" })) {

        int i = 1;
        ps.setString(i++, u.getTipoUsuario());
        ps.setString(i++, u.getContrasena());
        ps.setString(i++, u.getPrimerNombre());
        ps.setString(i++, u.getSegundoNombre());
        ps.setString(i++, u.getPrimerApellido());
        ps.setString(i++, u.getSegundoApellido());
        ps.setString(i++, u.getApellidoCasado());
        ps.setString(i++, u.getEmail());
        ps.setString(i++, u.getTelefono());
        ps.setObject(i++, u.getIdRol(), java.sql.Types.NUMERIC);
        ps.setString(i++, u.getEstado());

        ps.executeUpdate();
        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) return rs.getLong(1);  // ahora sí es NUMBER
        }
    }
    return null;
}


    @Override
    public boolean actualizar(Usuario u) throws Exception {
        String sql = """
            UPDATE usuarios SET
              tipo_usuario = ?, contrasena = ?, primer_nombre = ?, segundo_nombre = ?,
              primer_apellido = ?, segundo_apellido = ?, apellido_casado = ?, email = ?, telefono = ?,
              id_rol = ?, estado = ?
            WHERE id_usuario = ?
        """;
        try (PreparedStatement ps = db.preparar(sql)) {
            int i = 1;
            ps.setString(i++, u.getTipoUsuario());
            ps.setString(i++, u.getContrasena());
            ps.setString(i++, u.getPrimerNombre());
            ps.setString(i++, u.getSegundoNombre());
            ps.setString(i++, u.getPrimerApellido());
            ps.setString(i++, u.getSegundoApellido());
            ps.setString(i++, u.getApellidoCasado());
            ps.setString(i++, u.getEmail());
            ps.setString(i++, u.getTelefono());
            ps.setObject(i++, u.getIdRol(), Types.NUMERIC);
            ps.setString(i++, u.getEstado());
            ps.setObject(i++, u.getIdUsuario(), Types.NUMERIC);
            return ps.executeUpdate() == 1;
        }
    }

    @Override
    public boolean eliminar(Long idUsuario) throws Exception {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        try (PreparedStatement ps = db.preparar(sql)) {
            ps.setLong(1, idUsuario);
            return ps.executeUpdate() == 1;
        }
    }

    @Override
    public Optional<Usuario> buscarPorId(Long idUsuario) throws Exception {
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
        try (PreparedStatement ps = db.preparar(sql)) {
            ps.setLong(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(map(rs));
                return Optional.empty();
            }
        }
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) throws Exception {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        try (PreparedStatement ps = db.preparar(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(map(rs));
                return Optional.empty();
            }
        }
    }

    @Override
    public List<Usuario> listar(int limit, int offset) throws Exception {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios ORDER BY id_usuario OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (PreparedStatement ps = db.preparar(sql)) {
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(map(rs));
            }
        }
        return lista;
    }

    private Usuario map(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setIdUsuario(rs.getLong("id_usuario"));
        u.setTipoUsuario(rs.getString("tipo_usuario"));
        u.setContrasena(rs.getString("contrasena"));
        u.setPrimerNombre(rs.getString("primer_nombre"));
        u.setSegundoNombre(rs.getString("segundo_nombre"));
        u.setPrimerApellido(rs.getString("primer_apellido"));
        u.setSegundoApellido(rs.getString("segundo_apellido"));
        u.setApellidoCasado(rs.getString("apellido_casado"));
        u.setEmail(rs.getString("email"));
        u.setTelefono(rs.getString("telefono"));
        u.setIdRol(rs.getLong("id_rol"));
        u.setEstado(rs.getString("estado"));
        Timestamp ts = rs.getTimestamp("fecha_creacion");
        if (ts != null) u.setFechaCreacion(ts.toLocalDateTime());
        return u;
    }
}
