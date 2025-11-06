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
import Interfaces.RolDAO;
import Modelo.Rol;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RolDAOImpl implements RolDAO {

    private final DBConnection db;

    public RolDAOImpl() {
        this.db = new DBConnection();
        this.db.conectar(); // se conecta al instanciar el DAO
    }

@Override
public Long crear(Rol rol) throws Exception {
    String sql = "INSERT INTO roles (nombre_rol, descripcion) VALUES (?, ?)";
    try (PreparedStatement ps = db.getConnection()
            .prepareStatement(sql, new String[] { "ID_ROL" })) {

        ps.setString(1, rol.getNombreRol());
        ps.setString(2, rol.getDescripcion());
        ps.executeUpdate();

        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) return rs.getLong(1);
        }
    }
    return null;
}


    @Override
    public boolean actualizar(Rol rol) throws Exception {
        String sql = "UPDATE roles SET nombre_rol = ?, descripcion = ? WHERE id_rol = ?";
        try (PreparedStatement ps = db.preparar(sql)) {
            ps.setString(1, rol.getNombreRol());
            ps.setString(2, rol.getDescripcion());
            ps.setLong(3, rol.getIdRol());
            return ps.executeUpdate() == 1;
        }
    }

    @Override
    public boolean eliminar(Long idRol) throws Exception {
        String sql = "DELETE FROM roles WHERE id_rol = ?";
        try (PreparedStatement ps = db.preparar(sql)) {
            ps.setLong(1, idRol);
            return ps.executeUpdate() == 1;
        }
    }

    @Override
    public Optional<Rol> buscarPorId(Long idRol) throws Exception {
        String sql = "SELECT id_rol, nombre_rol, descripcion FROM roles WHERE id_rol = ?";
        try (PreparedStatement ps = db.preparar(sql)) {
            ps.setLong(1, idRol);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Rol(
                        rs.getLong("id_rol"),
                        rs.getString("nombre_rol"),
                        rs.getString("descripcion")
                    ));
                }
                return Optional.empty();
            }
        }
    }

    @Override
    public Optional<Rol> buscarPorNombre(String nombreRol) throws Exception {
        String sql = "SELECT id_rol, nombre_rol, descripcion FROM roles WHERE nombre_rol = ?";
        try (PreparedStatement ps = db.preparar(sql)) {
            ps.setString(1, nombreRol);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Rol(
                        rs.getLong("id_rol"),
                        rs.getString("nombre_rol"),
                        rs.getString("descripcion")
                    ));
                }
                return Optional.empty();
            }
        }
    }

    @Override
    public List<Rol> listar() throws Exception {
        List<Rol> lista = new ArrayList<>();
        String sql = "SELECT id_rol, nombre_rol, descripcion FROM roles ORDER BY id_rol";
        try (PreparedStatement ps = db.preparar(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Rol(
                    rs.getLong("id_rol"),
                    rs.getString("nombre_rol"),
                    rs.getString("descripcion")
                ));
            }
        }
        return lista;
    }
}
