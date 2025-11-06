/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author brand
 */
package Interfaces;

import Modelo.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioDAO {
    Long crear(Usuario u) throws Exception;
    boolean actualizar(Usuario u) throws Exception;
    boolean eliminar(Long idUsuario) throws Exception;
    Optional<Usuario> buscarPorId(Long idUsuario) throws Exception;
    Optional<Usuario> buscarPorEmail(String email) throws Exception;
    List<Usuario> listar(int limit, int offset) throws Exception;
}

