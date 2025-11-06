/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

/**
 *
 * @author brand
 */


import Implementacion.RolDAOImpl;
import Interfaces.RolDAO;
import Modelo.Rol;

import java.util.List;
import java.util.Optional;

public class RolController {
    private final RolDAO dao = new RolDAOImpl();

    public Long crear(String nombreRol, String descripcion) throws Exception {
        return dao.crear(new Rol(null, nombreRol, descripcion));
    }
    public boolean actualizar(Long idRol, String nombreRol, String descripcion) throws Exception {
        return dao.actualizar(new Rol(idRol, nombreRol, descripcion));
    }
    public boolean eliminar(Long idRol) throws Exception { return dao.eliminar(idRol); }
    public Optional<Rol> buscarPorId(Long idRol) throws Exception { return dao.buscarPorId(idRol); }
    public Optional<Rol> buscarPorNombre(String nombreRol) throws Exception { return dao.buscarPorNombre(nombreRol); }
    public List<Rol> listar() throws Exception { return dao.listar(); }
}
