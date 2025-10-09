package Modelo;

/**
 *
 * @author jhosu
 */
public class ModeloInicioUsuario {
    
    private static String usuarioActivo;
    private static int idUsuario;

    public static void setUsuarioActual(String usuario){
        usuarioActivo = usuario;
    }

    public static String getUsuarioActivo() {
        return usuarioActivo;
    }

    public static int getIdUsuario() {
        return idUsuario;
    }

    public static void setIdUsuario(int idUsuario) {
        ModeloInicioUsuario.idUsuario = idUsuario;
    }
     
    
    
    
}
