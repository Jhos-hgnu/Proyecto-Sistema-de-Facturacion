/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author brand
 */
package Interfaces;

import Modelo.Rol;
import java.util.List;
import java.util.Optional;

public interface RolDAO {
    Long crear(Rol rol) throws Exception;
    boolean actualizar(Rol rol) throws Exception;
    boolean eliminar(Long idRol) throws Exception;
    Optional<Rol> buscarPorId(Long idRol) throws Exception;
    Optional<Rol> buscarPorNombre(String nombreRol) throws Exception;
    List<Rol> listar() throws Exception;
}
