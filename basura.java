package Conector;

/**
 *
 * @author jhosu
 */
   public class basura{
    public static void main(String[] args) {
        DBConnection db = new DBConnection();
        db.conectar();
        db.desconectar();
    }
}
