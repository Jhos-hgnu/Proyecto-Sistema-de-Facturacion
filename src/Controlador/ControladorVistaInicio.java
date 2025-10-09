package Controlador;

import Implementacion.SesionInicioImp;
import Modelo.ModeloInicioUsuario;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Modelo.ModeloVistaInicio;
import Vistas.VistaAdmin;
import Vistas.VistaVendedor;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author jhosu
 */
public class ControladorVistaInicio implements MouseListener {

    ModeloVistaInicio modelo;

    public ControladorVistaInicio(ModeloVistaInicio modelo) {
        this.modelo = modelo;
        
        configurarEnter();
    }

    SesionInicioImp implementacion = new SesionInicioImp();

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent().equals(modelo.getVistaInicio().btnAcceder)) {
            inputIsEmpty();
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

    public void validarUsuario(String tipoUsuario, String usuarioIngresado, String contraIngresada, String usuarioEncontrado, String contraEncontrada, boolean usuarioActivo) {

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
            mostrarError("Error al Iniciar Sesión, el usuario dado de baja");
            return;
        }
        ModeloInicioUsuario modeloInicioUsuarioActivo = new ModeloInicioUsuario();
        redirigirTipoUsuario(tipoUsuario);
        
        
        modeloInicioUsuarioActivo.setUsuarioActual(usuarioEncontrado);
        modelo.setUsuario(usuarioEncontrado);
        modelo.setTipoUsuario(tipoUsuario);
    }

    private boolean credencialesCorrectas(String usuarioIngresado, String contraIngresada, String usuarioEncontrado, String contraEncontrada) {
        return usuarioIngresado.equals(usuarioEncontrado) && contraIngresada.equals(contraEncontrada);
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "ERROR INICIO DE SESIÓN", JOptionPane.ERROR_MESSAGE);
    }

    private void redirigirTipoUsuario(String tipoUsuario) {
        final String ADMIN = "ADMINISTRADOR";
        final String VENDEDOR = "VENDEDOR";

        if (ADMIN.equals(tipoUsuario)) {
            VistaAdmin vistaAdmin = new VistaAdmin();
            vistaAdmin.setVisible(true);

        } else if (VENDEDOR.equals(tipoUsuario)) {
            VistaVendedor vistaVendedor = new VistaVendedor();
            vistaVendedor.setVisible(true);
        }
        modelo.getVistaInicio().dispose();
    }

    public void inputIsEmpty() {

        if (modelo.getVistaInicio().txtUsuario.getText().isEmpty() || String.valueOf(modelo.getVistaInicio().txtPassword.getPassword()).isEmpty()) {
            JOptionPane.showInternalMessageDialog(null, "Por favor debe de ingresar todos los datos", "ERROR \"DATOS VACIOS\"", JOptionPane.WARNING_MESSAGE);
        } else {
            capturaDeDatos();
        }
    }

    public void capturaDeDatos() {
        try {
            String usuarioIngresado = modelo.getVistaInicio().txtUsuario.getText();
            String contraseniaIngresada = String.valueOf(modelo.getVistaInicio().txtPassword.getPassword());

            ModeloVistaInicio model = implementacion.consultaUsuario(usuarioIngresado, contraseniaIngresada);

            String usuarioEncontrado = model.getUsuarioEncontrado();
            String contraseniaEncontrada = model.getContraseniaEncontrada();
            String tipoDeUsuario = model.getTipoUsuario();
            boolean usuarioActivo = model.isUsuarioActivo();

            validarUsuario(tipoDeUsuario, usuarioIngresado, contraseniaIngresada, usuarioEncontrado, contraseniaEncontrada, usuarioActivo);
        } catch (NullPointerException e) {
            System.out.println("ERROR: Datos no encontrados");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage(), "ERROR CRÍTICO", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void configurarEnter(){
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
        }
        
        );
    }
 
    public void limpiarDatos() {
        modelo.getVistaInicio().txtUsuario.setText("");
        modelo.getVistaInicio().txtPassword.setText("");
    }
}
