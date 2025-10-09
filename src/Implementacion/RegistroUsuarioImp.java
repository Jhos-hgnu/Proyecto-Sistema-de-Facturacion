package Implementacion;

import Conector.DBConnection;
import Conector.SQL;
import Interfaces.IRegistroUsuario;
import Modelo.ModeloRegistroUsuario;
import javax.swing.DefaultComboBoxModel;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author jhosu
 */
public class RegistroUsuarioImp implements IRegistroUsuario {

    DBConnection conector = new DBConnection();
    SQL sql = new SQL();
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public boolean guardarUsuario(ModeloRegistroUsuario modelo) {
        boolean resultado = false;

        conector.conectar();
        try {

            ps = conector.preparar(sql.getAGREGAR_USUARIO());

            ps.setString(1, modelo.getNombreUsuario());
            ps.setString(2, modelo.getApellidoUsuario());
            ps.setString(3, modelo.getNumeroUsuario());
            ps.setString(4, modelo.getEmailUsuario());
            ps.setString(5, modelo.getUsuario());
            ps.setString(6, modelo.getContraseniaUsuario());
            ps.setString(7, modelo.getTipoUsuario());
            ps.setBoolean(8, modelo.isActivoUsuario());

            int filasAfectadas = ps.executeUpdate();
            resultado = (filasAfectadas > 0);

        } catch (SQLException e) {
            resultado = false;
            System.out.println("Error metodo GuardarUsuario " + e);
        }
        return resultado;
    }

    @Override
    public DefaultComboBoxModel mostrarTiposUsuarios() {
        return null;
    }

    @Override
    public boolean elimiarUsuario(String nombreU) {
        return false;
    }

    @Override
    public ModeloRegistroUsuario validarUsuario(String nombreU, String contraU) {
        return null;
    }
}
