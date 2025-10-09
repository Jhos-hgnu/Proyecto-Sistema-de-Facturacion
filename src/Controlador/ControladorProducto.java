package Controlador;

import Implementacion.ProductoImp;
import Modelo.ModeloProducto;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import javax.swing.JOptionPane;

public class ControladorProducto implements MouseListener {

    ModeloProducto modelo;
    ProductoImp implementacion = new ProductoImp();

    public ControladorProducto(ModeloProducto modelo) {
        this.modelo = modelo;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent().equals(modelo.getVistaProducto().btnBuscar)) {
            inputIsEmptyBuscarP();
        } else if (e.getComponent().equals(modelo.getVistaProducto().btnAgregar)) {
            inputIsEmptyAgregarP();
        } else if (e.getComponent().equals(modelo.getVistaProducto().btnActualizar)) {
            actualizarProducto();
        } else if (e.getComponent().equals(modelo.getVistaProducto().btnEliminar)) {
            cambiarElementosLimpiar();
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
        if (e.getComponent().equals(modelo.getVistaProducto().btnBuscar)) {
            modelo.getVistaProducto().btnBuscar.setBackground(new Color(50, 95, 110));
        } else if (e.getComponent().equals(modelo.getVistaProducto().btnAgregar)) {
            modelo.getVistaProducto().btnAgregar.setBackground(new Color(50, 95, 110));
        } else if (e.getComponent().equals(modelo.getVistaProducto().btnActualizar)) {
            modelo.getVistaProducto().btnActualizar.setBackground(new Color(50, 95, 110));
        } else if (e.getComponent().equals(modelo.getVistaProducto().btnEliminar)) {
            modelo.getVistaProducto().btnEliminar.setBackground(new Color(50, 95, 110));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getComponent().equals(modelo.getVistaProducto().btnBuscar)) {
            modelo.getVistaProducto().btnBuscar.setBackground(new Color(75, 128, 146));
        } else if (e.getComponent().equals(modelo.getVistaProducto().btnAgregar)) {
            modelo.getVistaProducto().btnAgregar.setBackground(new Color(75, 128, 146));
        } else if (e.getComponent().equals(modelo.getVistaProducto().btnActualizar)) {
            modelo.getVistaProducto().btnActualizar.setBackground(new Color(75, 128, 146));
        } else if (e.getComponent().equals(modelo.getVistaProducto().btnEliminar)) {
            modelo.getVistaProducto().btnEliminar.setBackground(new Color(75, 128, 146));
        }
    }

    public void inputIsEmptyAgregarP() {
        if (modelo.getVistaProducto().txtNombreProducto.getText().isEmpty()
                || modelo.getVistaProducto().txtCodigoBarras.getText().isEmpty()
                || modelo.getVistaProducto().boxActivo.getSelectedItem() == null
                || modelo.getVistaProducto().boxReceta.getSelectedItem() == null
                || modelo.getVistaProducto().txtDescripcion.getText().isEmpty()) {
            JOptionPane.showInternalMessageDialog(null, "Por favor debe de ingresar todos los datos", "ERROR \"DATOS VACIOS\"", JOptionPane.ERROR_MESSAGE);
        } else {
            capturaDeDatosAgregarP();
        }
    }

    public void inputIsEmptyBuscarP() {
        if (modelo.getVistaProducto().txtNombreProductoB.getText().isEmpty()
                && modelo.getVistaProducto().txtCodigoBarrasP.getText().isEmpty()) {
            JOptionPane.showInternalMessageDialog(null, "Por favor debe de ingresar todos los datos", "ERROR \"DATOS VACIOS\"", JOptionPane.ERROR_MESSAGE);
        } else {
            buscarProducto();
        }
    }
    
    
    private void inputIsEmptyActualizar(){
        if(modelo.getVistaProducto().txtNombreProducto.getText().isEmpty()
                || modelo.getVistaProducto().txtNombreAlterno.getText().isEmpty()
                || modelo.getVistaProducto().txtNumeroLote.getText().isEmpty()
                || modelo.getVistaProducto().txtCantidad.getText().isEmpty()
                || modelo.getVistaProducto().txtPrecioCompra.getText().isEmpty()
                || modelo.getVistaProducto().txtPrecioVenta.getText().isEmpty()
                || modelo.getVistaProducto().txtFechaVencimiento.getText().isEmpty()
                || modelo.getVistaProducto().txtCodigoBarras.getText().isEmpty()){
            
        } else {
            actualizarProducto();
        }
    }

    public void capturaDeDatosAgregarP() {

        String nombreIngresado = modelo.getVistaProducto().txtNombreProducto.getText();
        String descripcionIngresada = modelo.getVistaProducto().txtDescripcion.getText();
        String codigoIngresado = modelo.getVistaProducto().txtCodigoBarras.getText();
        String receta = String.valueOf(modelo.getVistaProducto().boxReceta.getSelectedItem());
        String activo = String.valueOf(modelo.getVistaProducto().boxActivo.getSelectedItem());
        String nombreAlternativo = modelo.getVistaProducto().txtNombreAlterno.getText();
 
        String numeroLote = modelo.getVistaProducto().txtNumeroLote.getText();
        String fechaVencimiento = modelo.getVistaProducto().txtFechaVencimiento.getText();
        String fechaFabricacion = modelo.getVistaProducto().txtFechaFabricacion.getText();
        String cantidad = modelo.getVistaProducto().txtCantidad.getText();
        BigDecimal precioCompra = new BigDecimal(modelo.getVistaProducto().txtPrecioCompra.getText());
        BigDecimal precioVenta = new BigDecimal(modelo.getVistaProducto().txtPrecioVenta.getText());

        registrarProducto(nombreIngresado, descripcionIngresada, codigoIngresado, receta, activo, numeroLote, fechaVencimiento, fechaFabricacion, cantidad, precioCompra, precioVenta, nombreAlternativo);

    }

    public void registrarProducto(String nombre, String descripcion, String codigo, String receta, String activo, String numeroLote, String fechaVencimiento, String fechaFabricacion,String cantidad, BigDecimal precioCompra, BigDecimal precioVenta, String nombreAlterna) {
        
        ModeloProducto modeloRegistroProducto = new ModeloProducto();

        modeloRegistroProducto.setNombreOficialP(nombre);
        modeloRegistroProducto.setDescripcionP(descripcion);
        modeloRegistroProducto.setCodigoBarrasP(codigo);
        modeloRegistroProducto.setRequiereRecetaP(validarReceta(receta));
        modeloRegistroProducto.setActivoP(validarActivoP(activo));
        modeloRegistroProducto.setNumeroLote(numeroLote);
        modeloRegistroProducto.setFechaVencimiento(fechaVencimiento);
        modeloRegistroProducto.setFechaFabricación(validarFechaFab(fechaFabricacion));
        modeloRegistroProducto.setCantidadDisponible(Integer.parseInt(cantidad));
        modeloRegistroProducto.setPrecioCompra(precioCompra);
        modeloRegistroProducto.setPrecioVenta(precioVenta);
        modeloRegistroProducto.setNombreAlternativo(nombreAlterna);
        //Terminar de poner los demás datos para pasarlos a la implementación
        boolean guardarP = implementacion.guardarProductoCompleto(modeloRegistroProducto);

        notificarProductoGuardado(guardarP);
    }
    
    
    private boolean validarReceta(String receta){
        boolean recetaIngresada = false;
       
        if (receta.equals("SI")) {
            recetaIngresada = true;
        } else {
            recetaIngresada = false;
        }
        return recetaIngresada;
    }
    
    private boolean validarActivoP(String activoP){
        boolean productoActivo;
       
        if (activoP.equals("SI")) {
            productoActivo = true;
        } else {
            productoActivo = false;
        }
        return productoActivo;
    }
    
    private String validarFechaFab(String fecha){
        String fechaFabriValidacion = "";
        if(fecha.equals("")){
            fechaFabriValidacion = null;
        } else {
            fechaFabriValidacion = fecha;
        }
        return fechaFabriValidacion;
    }

    private void actualizarProducto(){
        
    }
    
    
//    public void registrarNombreAlternativo(String nombre) {
//        ModeloProducto modeloNombreAlternativo = new ModeloProducto();
//        modeloNombreAlternativo.setNombreAlternativo(nombre);
//        int idObtenido = implementacion.obtenerUltimoIDProducto();
//        implementacion.guardarNombreAlternativo(modeloNombreAlternativo, idObtenido);
//    }

//    public void registrarLoteProducto(String numeroLote, String fechaVencimiento, String fechaFabrica, String cantidad, BigDecimal precioCompra, BigDecimal precioVenta) {
//        ModeloProducto modeloLote = new ModeloProducto();
//        modeloLote.setNumeroLote(numeroLote);
//        modeloLote.setFechaVencimiento(fechaVencimiento);
//        modeloLote.setFechaFabricación(fechaFabrica);
//        modeloLote.setCantidadDisponible(Integer.parseInt(cantidad));
//        modeloLote.setPrecioCompra(precioCompra);
//        modeloLote.setPrecioVenta(precioVenta);
//
//        int idObtenido = implementacion.obtenerUltimoIDProducto();
//        implementacion.guardarLote(modelo, idObtenido);
//    }

    public void notificarProductoGuardado(boolean productoGuardado) {
        if (productoGuardado == true) {
            JOptionPane.showInternalMessageDialog(null, "Producto Registrado con éxito", "\"PRODUCTO REGISTRADO\"", JOptionPane.INFORMATION_MESSAGE);
            limpiarDatosAgregarP();
        } else {
            JOptionPane.showInternalMessageDialog(null, "Error al registrar el producto", "\"ERROR EN EL REGISTRO DEL PRODUCTO\"", JOptionPane.ERROR_MESSAGE);
            limpiarDatosAgregarP();
        }
    }

    public void buscarProducto() {
        String codigoB = modelo.getVistaProducto().txtCodigoBarrasP.getText();
        String nombreB = modelo.getVistaProducto().txtNombreProductoB.getText();

        ModeloProducto modeloBuscar = implementacion.mostrarProducto(nombreB, codigoB);
        if (modeloBuscar == null) {
            JOptionPane.showInternalMessageDialog(null, "Producto no encontrado", "\"PRODUCTO NO ENCONTRADO\"", JOptionPane.WARNING_MESSAGE);
        } else {
            agregarDatos(modeloBuscar);
        }
    }

    public void limpiarDatosAgregarP() {
        modelo.getVistaProducto().txtNombreProducto.setText("");
        modelo.getVistaProducto().txtDescripcion.setText("");
        modelo.getVistaProducto().txtCodigoBarras.setText("");
        modelo.getVistaProducto().boxActivo.setSelectedIndex(0);
        modelo.getVistaProducto().boxReceta.setSelectedIndex(0);
        modelo.getVistaProducto().txtNumeroLote.setText("");
        modelo.getVistaProducto().txtCantidad.setText("");
        modelo.getVistaProducto().txtPrecioCompra.setText("");
        modelo.getVistaProducto().txtPrecioVenta.setText("");
        modelo.getVistaProducto().txtFechaVencimiento.setText("");
        modelo.getVistaProducto().txtFechaFabricacion.setText("");
        
        
    }
    
    public void limpiarDatos() {
    }

    
    
    public void agregarDatos(ModeloProducto modeloB) {
        modelo.getVistaProducto().txtNombreProducto.setText(modeloB.getNombreOficialP());
        modelo.getVistaProducto().txtCodigoBarras.setText(modeloB.getCodigoBarrasP());
        modelo.getVistaProducto().txtDescripcion.setText(modeloB.getDescripcionP());
        modelo.getVistaProducto().txtCodigoBarras.setText(modeloB.getCodigoBarrasP());
        modelo.getVistaProducto().txtIdProducto.setText(String.valueOf(modeloB.getIdProducto()));
        boolean usuarioActivo = modeloB.isActivoP();

        if (usuarioActivo == true) {
            modelo.getVistaProducto().boxActivo.setSelectedIndex(0);
        } else {
            modelo.getVistaProducto().boxActivo.setSelectedIndex(1);
        }

        boolean requiereReceta = modeloB.isRequiereRecetaP();

        if (requiereReceta == true) {
            modelo.getVistaProducto().boxReceta.setSelectedIndex(0);
        } else {
            modelo.getVistaProducto().boxReceta.setSelectedIndex(1);
        }
        modelo.getVistaProducto().txtFechaFabricacion.setText(modeloB.getFechaRegistro());
        cambiarElementosB();
    }
    
    private void cambiarElementosB(){
        modelo.getVistaProducto().btnActualizar.setVisible(true);
        modelo.getVistaProducto().txtIdProducto.setVisible(true);
        modelo.getVistaProducto().labelIDProducto.setVisible(true);
        modelo.getVistaProducto().txtIdProducto.setEditable(false);
        modelo.getVistaProducto().btnAgregar.setVisible(false);
        modelo.getVistaProducto().btnEliminar.setVisible(true);
        modelo.getVistaProducto().btnBuscar.setVisible(false);
    }
    
    private void cambiarElementosLimpiar(){
        modelo.getVistaProducto().txtNombreProducto.setText("");
        modelo.getVistaProducto().txtNombreAlterno.setText("");
        modelo.getVistaProducto().txtNumeroLote.setText("");
        modelo.getVistaProducto().txtCantidad.setText("");
        modelo.getVistaProducto().txtPrecioCompra.setText("");
        modelo.getVistaProducto().txtPrecioVenta.setText("");
        modelo.getVistaProducto().boxReceta.setSelectedIndex(0);
        modelo.getVistaProducto().boxActivo.setSelectedIndex(0);
        modelo.getVistaProducto().txtCodigoBarras.setText("");
        modelo.getVistaProducto().txtFechaVencimiento.setText("");
        modelo.getVistaProducto().txtFechaFabricacion.setText("");
        modelo.getVistaProducto().btnActualizar.setVisible(false);
        modelo.getVistaProducto().btnEliminar.setVisible(false);
        modelo.getVistaProducto().btnAgregar.setVisible(true);
        modelo.getVistaProducto().txtIdProducto.setText("");
        modelo.getVistaProducto().txtNombreProductoB.setText("");
        modelo.getVistaProducto().txtCodigoBarrasP.setText("");
        modelo.getVistaProducto().txtDescripcion.setText("");
        modelo.getVistaProducto().txtIdProducto.setVisible(false);
        modelo.getVistaProducto().btnBuscar.setVisible(true);
    }
}
