

import controladores.RolController;
import controladores.UsuarioController;
import Modelo.Usuario;

public class MainPrueba {
    public static void main(String[] args) {
        try {
            RolController rolCtl = new RolController();
            UsuarioController userCtl = new UsuarioController();

            // 1) Crear rol si no existe
            var rolAdmin = rolCtl.buscarPorNombre("ADMIN")
                    .orElseGet(() -> {
                        try {
                            Long id = rolCtl.crear("ADMIN", "Administrador del sistema");
                            return rolCtl.buscarPorId(id).orElse(null);
                        } catch (Exception e) { throw new RuntimeException(e); }
                    });
            if (rolAdmin == null) throw new RuntimeException("No se pudo crear/obtener rol ADMIN");

            // 2) Crear usuario
            Usuario u = new Usuario();
            u.setTipoUsuario("INTERNO");
            u.setContrasena("hash-o-temporal"); // coloca hash real si ya lo tienes
            u.setPrimerNombre("Brandon");
            u.setPrimerApellido("Aquino");
            u.setEmail("brandon@example.com");
            u.setTelefono("5555-5555");
            u.setIdRol(rolAdmin.getIdRol());
            u.setEstado("ACTIVO");

            Long idNuevo = userCtl.crear(u);
            System.out.println("Usuario creado con id: " + idNuevo);

            // 3) Consultar por email
            var u2 = userCtl.buscarPorEmail("brandon@example.com").orElseThrow();
            System.out.println("Encontrado: " + u2.getIdUsuario() + " - " + u2.getEmail());

            // 4) Actualizar
            u2.setTelefono("5555-0000");
            boolean okUpd = userCtl.actualizar(u2);
            System.out.println("Actualizado: " + okUpd);

            // 5) Listar
            userCtl.listar(10, 0).forEach(x ->
                System.out.println(x.getIdUsuario() + " | " + x.getEmail() + " | " + x.getEstado())
            );

            // 6) (Opcional) Eliminar
            // boolean okDel = userCtl.eliminar(idNuevo);
            // System.out.println("Eliminado: " + okDel);

        } catch (Exception e) {
        }
    }
}
