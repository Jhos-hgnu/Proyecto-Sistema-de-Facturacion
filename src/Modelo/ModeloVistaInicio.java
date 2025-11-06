package Modelo;

import Vistas.VistaInicio;
import javax.swing.JFrame;

/**
 * Modelo para la Vista de Inicio.
 * Mantiene referencia a la vista y datos de sesión/usuario.
 */
public class ModeloVistaInicio {

    // Referencia a la vista principal (si se usa)
    private VistaInicio vistaInicio;
    // Referencia genérica para vistas de prueba u otros JFrame
    private JFrame frame;

    private String usuario;
    private String contrasenia;

    // Estos tres son estáticos según tu diseño actual
    private static String tipoUsuario;
    private static String usuarioEncontrado;
    private static String contraseniaEncontrada;
    private static int idUsuarioEncontrado;

    private boolean usuarioActivo;

    // ------------------ CONSTRUCTORES ------------------

    public ModeloVistaInicio() {}

    // Vista principal del equipo
    public ModeloVistaInicio(VistaInicio vistaInicio) {
        this.vistaInicio = vistaInicio;
        this.frame = vistaInicio; // también lo guardamos como frame genérico
    }

    // Vista de prueba u otro JFrame
    public ModeloVistaInicio(JFrame frame) {
        this.frame = frame;
        if (frame instanceof VistaInicio) {
            this.vistaInicio = (VistaInicio) frame;
        }
    }

    // ------------------ GETTERS / SETTERS ------------------

    public VistaInicio getVistaInicio() { return vistaInicio; }
    public void setVistaInicio(VistaInicio vistaInicio) { this.vistaInicio = vistaInicio; }

    // frame genérico para cuando no hay VistaInicio
    public JFrame getFrame() { return (vistaInicio != null) ? vistaInicio : frame; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getContrasenia() { return contrasenia; }
    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }

    // Campos "estáticos" de tu diseño actual
    public static String getUsuarioEncontrado() { return usuarioEncontrado; }
    public static void setUsuarioEncontrado(String usuarioEncontrado) { ModeloVistaInicio.usuarioEncontrado = usuarioEncontrado; }

    public static String getContraseniaEncontrada() { return contraseniaEncontrada; }
    public static void setContraseniaEncontrada(String contraseniaEncontrada) { ModeloVistaInicio.contraseniaEncontrada = contraseniaEncontrada; }

    public static int getIdUsuarioEncontrado() { return idUsuarioEncontrado; }
    public static void setIdUsuarioEncontrado(int idUsuarioEncontrado) { ModeloVistaInicio.idUsuarioEncontrado = idUsuarioEncontrado; }

    public static String getTipoUsuario() { return tipoUsuario; }
    public static void setTipoUsuario(String tipoUsuario) { ModeloVistaInicio.tipoUsuario = tipoUsuario; }

    public boolean isUsuarioActivo() { return usuarioActivo; }
    public void setUsuarioActivo(boolean usuarioActivo) { this.usuarioActivo = usuarioActivo; }
}
