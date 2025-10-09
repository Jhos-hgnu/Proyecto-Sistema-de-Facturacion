/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Modelo.ModeloProducto;
import Modelo.ModeloVenta;

/**
 *
 * @author jhosu
 */
public interface IVenta {

    public boolean hacerVenta(ModeloVenta modelo);

    public ModeloProducto buscarProducto(String nombreP, String codigoB);

    public boolean guardarDetalleVenta(ModeloVenta modelo);
}
