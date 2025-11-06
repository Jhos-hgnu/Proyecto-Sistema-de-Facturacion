package Conector;

/**
 * Sentencias SQL usadas por la capa de Implementación/DAO.
 */
public class SQL {

    // Si necesitas prefijar esquema: "C##CTM3." por ejemplo.
    private static final String SCHEMA_PREFIX = ""; // ej. "C##CTM3."

    // Consulta para login: compara usuario (case-insensitive), contraseña y activo=1
    private static final String CONSULTA_USUARIO =
        "SELECT ID_USUARIO, USUARIO, CONTRASENIA, TIPO_USUARIO, ACTIVO " +
        "FROM " + SCHEMA_PREFIX + "TBL_USUARIO " +
        "WHERE UPPER(USUARIO) = UPPER(?) " +
        "  AND CONTRASENIA = ? " +
        "  AND ACTIVO = 1";

    public String getCONSULTA_USUARIO() {
        return CONSULTA_USUARIO;
    }

    // (Opcional) pequeño smoke test manual de conexión
    public static void main(String[] args) {
        DBConnection db = new DBConnection();
        try {
            db.conectar();
            System.out.println("Conexión OK");
        } finally {
            db.desconectar();
        }
    }
}
