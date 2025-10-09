/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Modelo.ModeloRegistroCliente;
import Modelo.ModeloRegistroCliente.Cliente;
import java.util.Date;
import java.util.List;

/**
 *
 * @author anyi4
 */
import java.util.List;

public interface IRegistroCliente {
    boolean insertarCliente(ModeloRegistroCliente.Cliente cliente);
    boolean actualizarCliente(ModeloRegistroCliente.Cliente cliente);
    boolean eliminarCliente(int idCliente);
    List<ModeloRegistroCliente.Cliente> listarClientes();
    ModeloRegistroCliente.Cliente obtenerClientePorId(int idCliente);
}
