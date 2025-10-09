package Modelo;

import Vistas.VistaInicio;

public class ModeloVistaInicio {

    VistaInicio vistaInicio;
    
    private String usuario;
    private String contrasenia;
    private static String tipoUsuario;
    private static String usuarioEncontrado;
    private static String contraseniaEncontrada;
    private static int idUsuarioEncontrado;
    private boolean usuarioActivo;

    public ModeloVistaInicio() {
    }

    public ModeloVistaInicio(VistaInicio vistaInicio) {
        this.vistaInicio = vistaInicio;
    }

    public VistaInicio getVistaInicio() {
        return vistaInicio;
    }

    public void setVistaInicio(VistaInicio vistaInicio) {
        this.vistaInicio = vistaInicio;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public static String getUsuarioEncontrado() {
        return usuarioEncontrado;
    }

    public static void setUsuarioEncontrado(String usuarioEncontrado) {
        ModeloVistaInicio.usuarioEncontrado = usuarioEncontrado;
    }

    public static String getContraseniaEncontrada() {
        return contraseniaEncontrada;
    }

    public static void setContraseniaEncontrada(String contraseniaEncontrada) {
        ModeloVistaInicio.contraseniaEncontrada = contraseniaEncontrada;
    }

    public static int getIdUsuarioEncontrado() {
        return idUsuarioEncontrado;
    }

    public static void setIdUsuarioEncontrado(int idUsuarioEncontrado) {
        ModeloVistaInicio.idUsuarioEncontrado = idUsuarioEncontrado;
    }

    public static String getTipoUsuario() {
        return tipoUsuario;
    }

    public static void setTipoUsuario(String tipoUsuario) {
        ModeloVistaInicio.tipoUsuario = tipoUsuario;
    }

    public boolean isUsuarioActivo() {
        return usuarioActivo;
    }

    public void setUsuarioActivo(boolean usuarioActivo) {
        this.usuarioActivo = usuarioActivo;
    }
}
