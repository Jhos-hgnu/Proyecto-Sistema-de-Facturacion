package Vistas;

import Controlador.ControladorVistaInicio;
import Modelo.ModeloVistaInicio;

import javax.swing.*;
import java.awt.*;

public class VistaInicioPrueba extends JFrame {

    public JPanel btnAcceder;
    public JTextField txtUsuario;
    public JPasswordField txtPassword;

    public VistaInicioPrueba() {
        initUI();

        ModeloVistaInicio modelo = new ModeloVistaInicio(this);
        ControladorVistaInicio controlador = new ControladorVistaInicio(modelo);
        setControlador(controlador);
    }

    private void initUI() {
        setTitle("Inicio (PRUEBA)");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(480, 320);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new GridBagLayout());
        root.setBorder(BorderFactory.createEmptyBorder(16,16,16,16));
        setContentPane(root);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8,8,8,8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("INICIO (PRUEBA)", SwingConstants.CENTER);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 18f));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        root.add(lblTitulo, gbc);

        gbc.gridwidth = 1; gbc.gridy++;
        root.add(new JLabel("Usuario (email):"), gbc);
        gbc.gridx = 1;
        txtUsuario = new JTextField();
        root.add(txtUsuario, gbc);

        gbc.gridx = 0; gbc.gridy++;
        root.add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1;
        txtPassword = new JPasswordField();
        root.add(txtPassword, gbc);

        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        btnAcceder = new JPanel(new BorderLayout());
        btnAcceder.setBackground(new Color(75,128,146));
        btnAcceder.setBorder(BorderFactory.createBevelBorder(1));
        btnAcceder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JLabel lblAcceder = new JLabel("Acceder", SwingConstants.CENTER);
        lblAcceder.setForeground(Color.WHITE);
        lblAcceder.setFont(lblAcceder.getFont().deriveFont(Font.BOLD, 14f));
        btnAcceder.add(lblAcceder, BorderLayout.CENTER);
        root.add(btnAcceder, gbc);
    }

    public void setControlador(ControladorVistaInicio controlador) {
        btnAcceder.addMouseListener(controlador);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VistaInicioPrueba().setVisible(true));
    }
}
