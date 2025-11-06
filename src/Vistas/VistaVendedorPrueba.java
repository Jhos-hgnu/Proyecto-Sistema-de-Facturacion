package Vistas;

import javax.swing.*;
import java.awt.*;

public class VistaVendedorPrueba extends JFrame {
    public VistaVendedorPrueba() {
        setTitle("VENDEDOR (PRUEBA)");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(560, 360);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(12,12));
        root.setBorder(BorderFactory.createEmptyBorder(16,16,16,16));
        setContentPane(root);

        JLabel title = new JLabel("Bienvenido, VENDEDOR (PRUEBA)", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 18f));
        root.add(title, BorderLayout.NORTH);

        JTextArea info = new JTextArea(
            "Vista de vendedor de PRUEBA (sin imágenes).\n" +
            "Úsala para validar login/rol."
        );
        info.setEditable(false);
        root.add(new JScrollPane(info), BorderLayout.CENTER);

        JButton salir = new JButton("Cerrar");
        salir.addActionListener(e -> dispose());
        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        south.add(salir);
        root.add(south, BorderLayout.SOUTH);
    }
}                                                                                                       