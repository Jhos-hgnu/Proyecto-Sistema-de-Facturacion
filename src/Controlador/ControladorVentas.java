/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Implementacion.VentaImp;
import Modelo.ModeloClientesVentas;
import Modelo.ModeloDetalleVenta;
import Modelo.ModeloInicioUsuario;
import Modelo.ModeloProducto;
import Modelo.ModeloRegistroCliente;
import Modelo.ModeloVenta;
import Modelo.ModeloVistaInicio;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jhosu
 */
public class ControladorVentas implements MouseListener, ActionListener {

    ModeloVenta modelo;
    private DefaultTableModel modeloTablaVentas;
    private JTable tablaVentas;

    public ControladorVentas(ModeloVenta modelo) {
        this.modelo = modelo;

        modeloTablaVentas = new DefaultTableModel();
        modeloTablaVentas.setColumnIdentifiers(new Object[]{"Nombre Producto", "Precio", "Cantidad", "SubTotal"});

        tablaVentas = new JTable(modeloTablaVentas);
        tablaVentas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane tableScroll = new JScrollPane(tablaVentas);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Carrito de Compras"));

        modelo.getVistaVentas().contenedorTablaVentas.add(tableScroll);
        modelo.getVistaVentas().contenedorTablaVentas.revalidate();
        modelo.getVistaVentas().contenedorTablaVentas.repaint();

    }

    VentaImp implementacion = new VentaImp();

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent().equals(modelo.getVistaVentas().btnAgregar)) {
            datosVaciosAgregarP();
        } else if (e.getComponent().equals(modelo.getVistaVentas().btnBuscar)) {
            datosVaciosBuscarP();
        } else if (e.getComponent().equals(modelo.getVistaVentas().btnEliminar)) {
            EliminarP();
        } else if (e.getComponent().equals(modelo.getVistaVentas().btnHacerVenta)) {
            datosVaciosHacerVenta();
        } else if (e.getComponent().equals(modelo.getVistaVentas().checkBoxVentaSinClienteR)) {
            clienteNoRegistrado();
        } else if (e.getComponent().equals(modelo.getVistaVentas().btnAceptar)) {
            elementosVisibles();
        } else if (e.getComponent().equals(modelo.getVistaVentas().btnConfirmarVenta)) {
            confirmarVenta();
        } else if (e.getComponent().equals(modelo.getVistaVentas().btnCancelarVenta)) {
            elementosInvisibles();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getComponent().equals(modelo.getVistaVentas().btnBuscar)) {
            modelo.getVistaVentas().btnBuscar.setBackground(new Color(50, 95, 110));
        } else if (e.getComponent().equals(modelo.getVistaVentas().btnAgregar)) {
            modelo.getVistaVentas().btnAgregar.setBackground(new Color(50, 95, 110));
        } else if (e.getComponent().equals(modelo.getVistaVentas().btnEliminar)) {
            modelo.getVistaVentas().btnEliminar.setBackground(new Color(50, 95, 110));
        } else if (e.getComponent().equals(modelo.getVistaVentas().btnAceptar)) {
            modelo.getVistaVentas().btnAceptar.setBackground(new Color(50, 95, 110));
        } else if (e.getComponent().equals(modelo.getVistaVentas().btnConfirmarVenta)) {
            modelo.getVistaVentas().btnConfirmarVenta.setBackground(new Color(50, 95, 110));
        } else if (e.getComponent().equals(modelo.getVistaVentas().btnCancelarVenta)) {
            modelo.getVistaVentas().btnCancelarVenta.setBackground(new Color(50, 95, 110));
        } else if (e.getComponent().equals(modelo.getVistaVentas().btnHacerVenta)) {
            modelo.getVistaVentas().btnHacerVenta.setBackground(new Color(50, 95, 110));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getComponent().equals(modelo.getVistaVentas().btnBuscar)) {
            modelo.getVistaVentas().btnBuscar.setBackground(new Color(75, 128, 146));
        } else if (e.getComponent().equals(modelo.getVistaVentas().btnAgregar)) {
            modelo.getVistaVentas().btnAgregar.setBackground(new Color(75, 128, 146));
        } else if (e.getComponent().equals(modelo.getVistaVentas().btnEliminar)) {
            modelo.getVistaVentas().btnEliminar.setBackground(new Color(75, 128, 146));
        } else if (e.getComponent().equals(modelo.getVistaVentas().btnAceptar)) {
            modelo.getVistaVentas().btnAceptar.setBackground(new Color(75, 128, 146));
        } else if (e.getComponent().equals(modelo.getVistaVentas().btnConfirmarVenta)) {
            modelo.getVistaVentas().btnConfirmarVenta.setBackground(new Color(75, 128, 146));
        } else if (e.getComponent().equals(modelo.getVistaVentas().btnCancelarVenta)) {
            modelo.getVistaVentas().btnCancelarVenta.setBackground(new Color(75, 128, 146));
        } else if (e.getComponent().equals(modelo.getVistaVentas().btnHacerVenta)) {
            modelo.getVistaVentas().btnHacerVenta.setBackground(new Color(75, 128, 146));
        }

    }

    private void capturaDatosBuscarP() {
        String nombreIngresado = modelo.getVistaVentas().txtNombreProducto.getText();
        String codigoIngresadoP = modelo.getVistaVentas().txtCodigoBarras.getText();

        consultarProducto(nombreIngresado, codigoIngresadoP);
    }

    private void capturaDatosAgregarP() {

        if (modelo.getVistaVentas().txtCantidad.getText().isEmpty()) {
            mostrarError("No se pudo agregar el producto, por favor ingresa una cantidad válidad de productos");
        } else {
            String nombreP = modelo.getVistaVentas().txtNombreProducto.getText();
            String precioP = modelo.getVistaVentas().txtPrecio.getText();
            String cantidadIngresada = modelo.getVistaVentas().txtCantidad.getText();
//            String descuentoSubsidio = modelo.getVistaVentas().txtDescuentoSubsidio.getText();
            String subtotal = calcularTotal(precioP, cantidadIngresada);;

            agregarDatosTabla(nombreP, precioP, cantidadIngresada, subtotal);
        }

    }

    private String calcularPrecioP(String precio, String descuento) {
        double precioDescuento;
        String precioFinal = "";
        double descuentoObtenido;
        if (descuento.equals("")) {
            precioDescuento = 0.00;
        } else {
            precioDescuento = (Double.parseDouble(descuento)) / 100;
        }

        if (modelo.getVistaVentas().cmbSubsidio.getItemAt(1).equals("SI")) {
            double precioInt = Double.parseDouble(precio);
            descuentoObtenido = precioInt * precioDescuento;
            precioFinal = String.valueOf(precioInt - descuentoObtenido);
        }

        return precioFinal;
    }

    private String calcularSubTotal() {

        return "";
    }

    private String calcularTotal(String precioFinal, String cantidad) {

        double precioCalculado = Double.parseDouble(precioFinal);
        double cantidadIngresada = Double.parseDouble(cantidad);

        double totalCalculado = precioCalculado * cantidadIngresada;
        String precioTotalCalculado = String.valueOf(totalCalculado);
        return precioTotalCalculado;
    }

    public void datosVaciosBuscarP() {
        if (modelo.getVistaVentas().txtNombreProducto.getText().isEmpty() && modelo.getVistaVentas().txtCodigoBarras.getText().isEmpty()) {
            mostrarError("Debe de ingresar al menos el nombre o código del producto");
        } else {
            capturaDatosBuscarP();
        }
    }

    public void datosVaciosAgregarP() {
        if (modelo.getVistaVentas().txtNombreProducto.getText().isEmpty()
                || modelo.getVistaVentas().txtCodigoBarras.getText().isEmpty()
                || modelo.getVistaVentas().txtPrecio.getText().isEmpty()) {
            mostrarError("No se pudo agregar el producto, por favor busque un producto para agregarlo");
        } else {
            capturaDatosAgregarP();
        }
    }

    public void datosVaciosHacerVenta() {
        if (modeloTablaVentas.getRowCount() == 0
                || (!modelo.getVistaVentas().checkBoxVentaSinClienteR.isSelected()
                && modelo.getVistaVentas().txtNITCliente.getText().isEmpty())) {
            mostrarError("No se puede realizar la venta, debe de llenar todos los campos");
        } else {
            hacerVenta();
        }

    }

    public void datosVaciosAceptar() {
        if (modeloTablaVentas.getRowCount() == 0) {
            mostrarError("No se puede realizar la acción, debe de llenar todos los campos");
        } else {
            elementosVisibles();
        }
    }

    public void elementosVisibles() {
        modelo.getVistaVentas().labelTipoPago.setVisible(true);
        modelo.getVistaVentas().cmbMetodoPago.setVisible(true);
        modelo.getVistaVentas().checkBoxVentaSinClienteR.setVisible(true);
        modelo.getVistaVentas().labelNIT.setVisible(true);
        modelo.getVistaVentas().txtNITCliente.setVisible(true);
//        modelo.getVistaVentas().labelSubsidio.setVisible(true);
        modelo.getVistaVentas().cmbSubsidio.setVisible(true);
//        modelo.getVistaVentas().labelDescuentoSubsidio.setVisible(true);
//        modelo.getVistaVentas().txtDescuentoSubsidio.setVisible(true);
//        modelo.getVistaVentas().labelInstitucionSubsidio.setVisible(true);
//        modelo.getVistaVentas().txtIdInsSubsidio.setVisible(true);
        modelo.getVistaVentas().labelObservaciones.setVisible(true);
        modelo.getVistaVentas().scrollPanel.setVisible(true);
        modelo.getVistaVentas().btnCancelarVenta.setVisible(true);
        modelo.getVistaVentas().btnConfirmarVenta.setVisible(true);

    }

    public void elementosInvisibles() {
        modelo.getVistaVentas().txtNITCliente.setText("");
        modelo.getVistaVentas().txtDescuentoSubsidio.setText("");
        modelo.getVistaVentas().txtIdInsSubsidio.setText("");
        modelo.getVistaVentas().txtArea.setText("");
        modelo.getVistaVentas().cmbSubsidio.setSelectedIndex(0);
        modelo.getVistaVentas().cmbMetodoPago.setSelectedIndex(0);
        modelo.getVistaVentas().checkBoxVentaSinClienteR.setSelected(false);
        modelo.getVistaVentas().labelTipoPago.setVisible(false);
        modelo.getVistaVentas().cmbMetodoPago.setVisible(false);
        modelo.getVistaVentas().checkBoxVentaSinClienteR.setVisible(false);
        modelo.getVistaVentas().labelNIT.setVisible(false);
        modelo.getVistaVentas().txtNITCliente.setVisible(false);
        modelo.getVistaVentas().labelSubsidio.setVisible(false);
        modelo.getVistaVentas().cmbSubsidio.setVisible(false);
        modelo.getVistaVentas().labelDescuentoSubsidio.setVisible(false);
        modelo.getVistaVentas().txtDescuentoSubsidio.setVisible(false);
        modelo.getVistaVentas().labelInstitucionSubsidio.setVisible(false);
        modelo.getVistaVentas().txtIdInsSubsidio.setVisible(false);
        modelo.getVistaVentas().labelObservaciones.setVisible(false);
        modelo.getVistaVentas().txtArea.setVisible(false);
        modelo.getVistaVentas().btnCancelarVenta.setVisible(false);
        modelo.getVistaVentas().btnConfirmarVenta.setVisible(false);
        modelo.getVistaVentas().labelSubtotal.setVisible(false);
        modelo.getVistaVentas().labelTotal.setVisible(false);
        modelo.getVistaVentas().txtSubTotal.setVisible(false);
        modelo.getVistaVentas().txtTotal.setVisible(false);
        modelo.getVistaVentas().btnHacerVenta.setVisible(false);

    }

    private void limpiarElementos() {
        modelo.getVistaVentas().txtNombreProducto.setText("");
        modelo.getVistaVentas().txtCodigoBarras.setText("");
        modelo.getVistaVentas().txtCantidad.setText("");
        modelo.getVistaVentas().txtDescuentoSubsidio.setText("");
        modelo.getVistaVentas().txtIdProducto.setText("");
//        modelo.getVistaVentas().cmbSubsidio.setSelectedIndex(0);
//        modelo.getVistaVentas().labelInstitucionSubsidio.setText("");
//        modelo.getVistaVentas().labelInstitucionSubsidio.setVisible(false);
//        modelo.getVistaVentas().labelDescuentoSubsidio.setText("");
//        modelo.getVistaVentas().txtDescuentoSubsidio.setVisible(false);
        modelo.getVistaVentas().txtPrecio.setText("");
        modelo.getVistaVentas().txtArea.setText("");

    }
//Metodos para botón confirmar

    public void confirmarVenta() {
        if (modeloTablaVentas.getRowCount() == 0
                || (!modelo.getVistaVentas().checkBoxVentaSinClienteR.isSelected()
                && modelo.getVistaVentas().txtNITCliente.getText().isEmpty())) {
            mostrarError("No se puede confirmar la venta, debe de llenar todos los campos");
        } else {
            modelo.getVistaVentas().labelSubtotal.setVisible(true);
            modelo.getVistaVentas().labelTotal.setVisible(true);
            modelo.getVistaVentas().txtSubTotal.setVisible(true);
            modelo.getVistaVentas().txtTotal.setVisible(true);
            modelo.getVistaVentas().btnHacerVenta.setVisible(true);

            double totalSubCalculado = calcularSubtotal(modeloTablaVentas);
            modelo.getVistaVentas().txtSubTotal.setText(String.valueOf(totalSubCalculado));
            modelo.getVistaVentas().txtTotal.setText(String.valueOf(calcularTotal(totalSubCalculado)));
        }

    }

    private double calcularSubtotal(DefaultTableModel tabla) {
        double suma = 0.0;
        int indiceColumna = 3;
        for (int i = 0; i < tabla.getRowCount(); i++) {
            Object valor = tabla.getValueAt(i, indiceColumna);
            try {
                suma += Double.parseDouble(valor.toString());
            } catch (NumberFormatException e) {
                // Manejar error si el valor no es numérico
                System.err.println("Valor no numérico en la fila " + i);
            }
        }

        return suma;
    }

    private double calcularTotal(double subtotal) {
        double descuento = 0;
        double calcularTotal = 0;
        if (modelo.getVistaVentas().cmbSubsidio.getSelectedIndex() == 0) {
            calcularTotal = subtotal - descuento;
        } else {
            descuento = Double.parseDouble(modelo.getVistaVentas().txtDescuentoSubsidio.getText());
            calcularTotal = subtotal - descuento;
        }
        return calcularTotal;
    }

    public void consultarProducto(String nombreP, String codigoB) {
        //Validar esta parte creo un error y no salta el jpaneOption
        try {

            ModeloProducto modeloP = implementacion.buscarProducto(nombreP, codigoB);

            if (modeloP == null) {
                mostrarError("Producto no encontrado");
                System.out.println("Hola no hay nada");
                return;
            }

            boolean estaActivo = modeloP.isActivoP();
            int cantidadDisponible = modeloP.getCantidadDisponible();

            if (!estaActivo == true) {
                mostrarError("El producto no se encuentra activo");
            } else if (cantidadDisponible < 1) {
                mostrarError("El Producto no tiene suficiente stock");
            } else {
                System.out.println("hola desde else");
                modelo.getVistaVentas().txtIdProducto.setText(String.valueOf(modeloP.getIdProducto()));
                modelo.getVistaVentas().txtCodigoBarras.setText(modeloP.getCodigoBarrasP());
                modelo.getVistaVentas().txtNombreProducto.setText(modeloP.getNombreOficialP());
                modelo.getVistaVentas().txtArea.setText(modeloP.getDescripcionP());
                modelo.getVistaVentas().txtPrecio.setText(String.valueOf(modeloP.getPrecioVenta()));
            }
        } catch (Exception e) {
            mostrarError("Producto no encontrado");
        }

    }

    //METODOS PARA GENERAR LA TABLA DE LOS PRODUCTOS PARA LA VENTA
    public void agregarDatosTabla(String nombreP, String precio, String cantidad, String subtotal) {

        modeloTablaVentas.addRow(new Object[]{
            nombreP,
            precio,
            cantidad,
            subtotal});

        limpiarElementos();
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "¡ADVERTENCIA!", JOptionPane.WARNING_MESSAGE);
    }

    private void hacerVenta() {
        try {
            ModeloClientesVentas modeloClientesVentas = new ModeloClientesVentas();
            if (modelo.getVistaVentas().checkBoxVentaSinClienteR.isSelected()) {
                modeloClientesVentas.setNombre("CF");
                modeloClientesVentas.setNombre("CONSUMIDOR FINAL");
            } else {
                ModeloClientesVentas cliente = consultarClienteNit(modelo.getVistaVentas().txtNITCliente.getText());
                if (cliente == null) {
                    return;
                }

                modeloClientesVentas.setIdCliente(cliente.getIdCliente());
                modeloClientesVentas.setNombre(cliente.getNombre());
                modeloClientesVentas.setApellido(cliente.getApellido());
                modeloClientesVentas.setNit(cliente.getNit());
                modeloClientesVentas.setDireccion(cliente.getDireccion());
                modeloClientesVentas.setTieneSubsidio(cliente.isTieneSubsidio());
            }

            //Obtener datos del usuario activo
            ModeloInicioUsuario modeloUsuarioActivo = new ModeloInicioUsuario();
            String usuarioObtenido = modeloUsuarioActivo.getUsuarioActivo();
            int idUsuarioObtenido = modeloUsuarioActivo.getIdUsuario();

            //Configurar datos de la venta
            modeloClientesVentas.setTipoPago(modelo.getVistaVentas().cmbMetodoPago.getSelectedItem().toString());
            modeloClientesVentas.setSubtotal(Double.parseDouble(modelo.getVistaVentas().txtSubTotal.getText()));
            modeloClientesVentas.setDescuento(Double.parseDouble(modelo.getVistaVentas().txtDescuentoSubsidio.getText()));
            modeloClientesVentas.setTotal(Double.parseDouble(modelo.getVistaVentas().txtTotal.getText()));

            //Crear y llenar al carrito Jtable
            List<ModeloDetalleVenta> carrito = new ArrayList<>();
            for (int i = 0; i < tablaVentas.getRowCount(); i++) {
                ModeloDetalleVenta detalle = new ModeloDetalleVenta();
                detalle.setNombreProducto(tablaVentas.getValueAt(i, 0).toString());
                detalle.setPrecioUnitario(Double.parseDouble(tablaVentas.getValueAt(i, 1).toString()));
                detalle.setCantidad(Integer.parseInt(tablaVentas.getValueAt(i, 2).toString()));
                detalle.setSubtotal(Double.parseDouble(tablaVentas.getValueAt(i, 3).toString()));
                carrito.add(detalle);
            }
            modeloClientesVentas.setCarrito(carrito);

            // Realizar la venta
            boolean ventaExitosa = implementacion.hacerVentaCompleta(modeloClientesVentas, usuarioObtenido, idUsuarioObtenido);

            if (ventaExitosa) {
                limpiarTablaYCampos();
                JOptionPane.showMessageDialog(null, "Venta realizada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            mostrarError("Error al procesar la venta: " + e.getMessage());
        }
//            implementacion.hacerVentaCompleta(modeloClienteVenta, usuarioObtenido, idUsuarioObtenido);

    }

    public void limpiarTablaYCampos() {

    }
//    private void validarVentaComprobante() {
//
//        //Llamar al método de implementación para la venta
//        int mensajeHacerComprobante = JOptionPane.showConfirmDialog(null, "¿Desea generar comprobante de esta venta?", "VENTA", JOptionPane.YES_NO_OPTION);
//        if (mensajeHacerComprobante == JOptionPane.YES_OPTION) {
//            generarComprobante();
//            JOptionPane.showMessageDialog(null, "Venta realizada con éxito", "VENTA", JOptionPane.WARNING_MESSAGE);
//        } else {
//            JOptionPane.showMessageDialog(null, "Venta realizada con éxito", "VENTA", JOptionPane.WARNING_MESSAGE);
//            //Tal vez llamar aquí el método para limpiar todo
//        }

    ////    }
//    private void generarComprobante() {
//        ModeloInicioUsuario modeloUsuarioActivo = new ModeloInicioUsuario();
//        String usuarioObtenido = modeloUsuarioActivo.getUsuarioActivo();
//
//        System.out.println("Usuario obtenido " + usuarioObtenido);
//    }

    //METODO PARA MOSTRAR CAMPOS, DEPENDIENDO SI SE DESEA INGRESAR SUBSIDIO
    private void comboBoxSubsidio() {
        int itemSeleccionado = modelo.getVistaVentas().cmbSubsidio.getSelectedIndex();

        if (itemSeleccionado == 1) {
            modelo.getVistaVentas().labelInstitucionSubsidio.setVisible(true);
            modelo.getVistaVentas().txtIdInsSubsidio.setVisible(true);
            modelo.getVistaVentas().labelDescuentoSubsidio.setVisible(true);
            modelo.getVistaVentas().txtDescuentoSubsidio.setVisible(true);
        } else {
            modelo.getVistaVentas().labelInstitucionSubsidio.setVisible(false);
            modelo.getVistaVentas().txtIdInsSubsidio.setVisible(false);
            modelo.getVistaVentas().labelDescuentoSubsidio.setVisible(false);
            modelo.getVistaVentas().txtDescuentoSubsidio.setVisible(false);

        }
    }

    //METODO PARA MOSTRAR CAMPOS SI EL CLIENTE ESTA REGISTRADO (No NIT)
    private void clienteNoRegistrado() {
        if (modelo.getVistaVentas().checkBoxVentaSinClienteR.isSelected()) {
            modelo.getVistaVentas().labelNIT.setVisible(false);
            modelo.getVistaVentas().txtNITCliente.setVisible(false);
        } else {
            modelo.getVistaVentas().labelNIT.setVisible(true);
            modelo.getVistaVentas().txtNITCliente.setVisible(true);

        }

    }

    private void EliminarP() {
        int filasSeleccionada = tablaVentas.getSelectedRow();
        if (filasSeleccionada != -1) {
            modeloTablaVentas.removeRow(filasSeleccionada);
        } else {
            mostrarError("Selecciona un producto agregado para eliminarlo");
        }
    }

    public ModeloClientesVentas consultarClienteNit(String nit) {

        ModeloClientesVentas modeloClienteV;
        modeloClienteV = implementacion.consultarCliente(nit);
        if (modeloClienteV == null) {
            mostrarError("El cliente no fue encontrado, por favor ingrese un cliente válido");
        } else {
//            int idClienteEncontrado = modeloClienteV.getIdCliente();
            modeloClienteV.getNombre();
            modeloClienteV.getApellido();
            modeloClienteV.getDireccion();
            modeloClienteV.getNit();
            modeloClienteV.isTieneSubsidio();
        }

        return modeloClienteV;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        comboBoxSubsidio();

    }

    private void generarComprobantePDF() {

    }

}
