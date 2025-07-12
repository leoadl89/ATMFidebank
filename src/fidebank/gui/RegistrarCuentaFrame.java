package fidebank.gui;

import javax.swing.*;
import java.awt.*;
import fidebank.controller.Sistema;

public class RegistrarCuentaFrame extends JFrame {
    public RegistrarCuentaFrame() {
        setTitle("Registrar Nueva Cuenta");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(360, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextField txtNombre   = new JTextField();
        JTextField txtApellido = new JTextField();
        JPasswordField txtPin  = new JPasswordField(4);

        JButton btnRegistrar = new JButton("Crear Cuenta");
        btnRegistrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegistrar.addActionListener(e -> {
            String nombre   = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            String pin      = new String(txtPin.getPassword());
            if (nombre.isEmpty() || apellido.isEmpty() || pin.length() < 4) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos correctamente");
                return;
            }
            Sistema.registrarCliente(nombre, apellido, pin);
            JOptionPane.showMessageDialog(this,
                "Cuenta creada para " + nombre + " " + apellido + "\nPIN: " + pin);
            dispose();
        });

        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("Apellido:"));
        panel.add(txtApellido);
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("PIN (4 dígitos):"));
        panel.add(txtPin);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnRegistrar);

        add(panel);
        setVisible(true);
    }
}