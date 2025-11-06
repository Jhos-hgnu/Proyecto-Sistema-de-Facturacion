/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

/**
 *
 * @author brand
 */


import Implementacion.UsuarioDAOImpl;
import Interfaces.UsuarioDAO;
import Modelo.Usuario;

import java.util.List;
import java.util.Optional;

public class UsuarioController {
    private final UsuarioDAO dao = new UsuarioDAOImpl();

    public Long crear(Usuario u /* contraseña ya hash si aplica */) throws Exception {
        return dao.crear(u);
    }
    public boolean actualizar(Usuario u) throws Exception { return dao.actualizar(u); }
    public boolean eliminar(Long idUsuario) throws Exception { return dao.eliminar(idUsuario); }
    public Optional<Usuario> buscarPorId(Long idUsuario) throws Exception { return dao.buscarPorId(idUsuario); }
    public Optional<Usuario> buscarPorEmail(String email) throws Exception { return dao.buscarPorEmail(email); }
    public List<Usuario> listar(int limit, int offset) throws Exception { return dao.listar(limit, offset); }
}
   