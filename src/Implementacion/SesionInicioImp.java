package Implementacion;

import Conector.DBConnection;
import Conector.SQL;
import Interfaces.ISesionInicio;
import Modelo.ModeloVistaInicio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Implementación del inicio de sesión.
 * Devuelve un ModeloVistaInicio con usuarioActivo=true solo si hubo match.
 * Además setea los campos estáticos que usa tu controlador.
 */
public class SesionInicioImp implements ISesionInicio {

    private final DBConnection conector = new DBConnection();
    private final SQL sql = new SQL();

    @Override
    public ModeloVistaInicio consultaUsuario(String usuario, String contrasenia) {
        ModeloVistaInicio modelo = new ModeloVistaInicio();

        String u = (usuario == null) ? "" : usuario.trim();
        String p = (contrasenia == null) ? "" : contrasenia.trim();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conector.conectar(); // abre la conexión interna

            ps = conector.preparar(sql.getCONSULTA_USUARIO());
            ps.setString(1, u);
            ps.setString(2, p);

            rs = ps.executeQuery();

            if (rs.next()) {
                // Setea estáticos (tu controlador los lee así)
                ModeloVistaInicio.setIdUsuarioEncontrado(rs.getInt("ID_USUARIO"));
                ModeloVistaInicio.setUsuarioEncontrado(rs.getString("USUARIO"));
                ModeloVistaInicio.setContraseniaEncontrada(rs.getString("CONTRASENIA"));
                ModeloVistaInicio.setTipoUsuario(rs.getString("TIPO_USUARIO"));

                // también en campos de instancia
                modelo.setUsuario(rs.getString("USUARIO"));
                modelo.setContrasenia(rs.getString("CONTRASENIA"));
                modelo.setUsuarioActivo(rs.getInt("ACTIVO") == 1);
            } else {
                // No hubo match
                modelo.setUsuarioActivo(false);
            }
        } catch (Exception e) {
            System.err.println("Error en consultaUsuario: " + e.getMessage());
            modelo.setUsuarioActivo(false);
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignore) {}
            try { if (ps != null) ps.close(); } catch (Exception ignore) {}
            conector.desconectar(); // cierra la conexión interna
        }

        return modelo;
    }
}
