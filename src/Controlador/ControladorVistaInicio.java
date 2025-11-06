package Controlador;

import Implementacion.SesionInicioImp;
import Modelo.ModeloInicioUsuario;
import Modelo.ModeloVistaInicio;
import Vistas.VistaAdmin;
import Vistas.VistaVendedor;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class ControladorVistaInicio implements MouseListener {

    private final ModeloVistaInicio modelo;
    private final SesionInicioImp implementacion = new SesionInicioImp();

    public ControladorVistaInicio(ModeloVistaInicio modelo) {
        this.modelo = modelo;
        configurarEnter();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent().equals(modelo.getVistaInicio().btnAcceder)) {
            inputIsEmpty(); // Dispara validación completa
        }
    }

    @Override public void mousePressed(MouseEvent e) { }
    @Override public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getComponent().equals(modelo.getVistaInicio().btnAcceder)) {
            modelo.getVistaInicio().btnAcceder.setBackground(new Color(50, 95, 110));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getComponent().equals(modelo.getVistaInicio().btnAcceder)) {
            modelo.getVistaInicio().btnAcceder.setBackground(new Color(75, 128, 146));
        }
    }

    public void validarUsuario(String tipoUsuario,
                               String usuarioIngresado,
                               String contraIngresada,
                               String usuarioEncontrado,
                               String contraEncontrada,
                               boolean usuarioActivo) {

        if (tipoUsuario == null) {
            mostrarError("Error al Iniciar Sesión, usuario o contraseña incorrectos");
            limpiarDatos();
            return;
        }

        if (!credencialesCorrectas(usuarioIngresado, contraIngresada, usuarioEncontrado, contraEncontrada)) {
            mostrarError("Error al Iniciar Sesión. usuario o contraseña incorrectos");
            limpiarDatos();
            return;
        }

        if (!usuarioActivo) {
            mostrarError("Error al Iniciar Sesión, el usuario está dado de baja");
            limpiarDatos();
            return;
        }

        // Usuario OK: actualizar “sesión”/modelo y navegar
        ModeloInicioUsuario modeloInicioUsuarioActivo = new ModeloInicioUsuario();
        modeloInicioUsuarioActivo.setUsuarioActual(usuarioEncontrado);

        modelo.setUsuario(usuarioEncontrado);
        ModeloVistaInicio.setTipoUsuario(tipoUsuario);
        modelo.setUsuarioActivo(true);

        redirigirSegunTipo(tipoUsuario);
    }

    private boolean credencialesCorrectas(String usuarioIngresado,
                                          String contraIngresada,
                                          String usuarioEncontrado,
                                          String contraEncontrada) {
        return usuarioIngresado != null
                && contraIngresada != null
                && usuarioIngresado.equals(usuarioEncontrado)
                && contraIngresada.equals(contraEncontrada);
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "ERROR INICIO DE SESIÓN", JOptionPane.ERROR_MESSAGE);
    }

    private void redirigirSegunTipo(String tipoUsuario) {
        // Ajusta estos códigos a lo que te devuelve la BD:
        final String ADMIN = "1";     // ó "ADMINISTRADOR"
        final String VENDEDOR = "2"; // ó "VENDEDOR"

        JFrame actual = modelo.getVistaInicio();
        try {
            if (tipoUsuario != null && tipoUsuario.equalsIgnoreCase(ADMIN)) {
                VistaAdmin vistaAdmin = new VistaAdmin();
                vistaAdmin.setLocationRelativeTo(null);
                vistaAdmin.setVisible(true);
            } else if (tipoUsuario != null && tipoUsuario.equalsIgnoreCase(VENDEDOR)) {
                VistaVendedor vistaVendedor = new VistaVendedor();
                vistaVendedor.setLocationRelativeTo(null);
                vistaVendedor.setVisible(true);
            } else {
                mostrarError("Tipo de usuario no reconocido: " + tipoUsuario);
                return;
            }
        } finally {
            if (actual != null) actual.dispose();
        }
    }

    public void inputIsEmpty() {
        String u = modelo.getVistaInicio().txtUsuario.getText();
        String p = String.valueOf(modelo.getVistaInicio().txtPassword.getPassword());

        if (u == null || u.isBlank() || p == null || p.isBlank()) {
            JOptionPane.showInternalMessageDialog(null,
                    "Por favor ingresa usuario y contraseña",
                    "ERROR \"DATOS VACÍOS\"",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            capturaDeDatos();
        }
    }

    public void capturaDeDatos() {
        try {
            String usuarioIngresado = modelo.getVistaInicio().txtUsuario.getText();
            String contraseniaIngresada = String.valueOf(modelo.getVistaInicio().txtPassword.getPassword());

            // Llama a tu implementación/DAO
            ModeloVistaInicio model = implementacion.consultaUsuario(usuarioIngresado, contraseniaIngresada);

            // Siguiendo tu diseño actual, estos getters son estáticos
            String usuarioEncontrado = model.getUsuarioEncontrado();
            String contraseniaEncontrada = model.getContraseniaEncontrada();
            String tipoDeUsuario = model.getTipoUsuario();
            boolean usuarioActivo = model.isUsuarioActivo();

            validarUsuario(tipoDeUsuario, usuarioIngresado, contraseniaIngresada,
                           usuarioEncontrado, contraseniaEncontrada, usuarioActivo);

        } catch (NullPointerException ex) {
            System.out.println("ERROR: Datos no encontrados");
            mostrarError("No se encontró el usuario o hubo un problema con la consulta.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Error inesperado: " + ex.getMessage(),
                    "ERROR CRÍTICO",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void configurarEnter() {
        JFrame frame = modelo.getVistaInicio();
        JPanel contentPanel = (JPanel) frame.getContentPane();

        InputMap inputMap = contentPanel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = contentPanel.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "validarInput");
        actionMap.put("validarInput", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputIsEmpty();
            }
        });
    }

    public void limpiarDatos() {
        modelo.getVistaInicio().txtUsuario.setText("");
        modelo.getVistaInicio().txtPassword.setText("");
    }
}
