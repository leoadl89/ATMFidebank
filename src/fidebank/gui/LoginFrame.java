package fidebank.gui;

import javax.swing.*;
import java.awt.*;
import fidebank.exception.PinInvalidoException;
import fidebank.controller.Sistema;

public class LoginFrame extends JFrame {
    private JPasswordField txtPin;
    public LoginFrame() {
        setTitle("FideBank");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(360, 280);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1,true));
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JLabel lblLogo = new JLabel("FideBank", SwingConstants.CENTER);
        lblLogo.setFont(new Font("SansSerif",Font.BOLD,32));
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(20)); panel.add(lblLogo);
        panel.add(Box.createVerticalStrut(20));
        JLabel lblInstr = new JLabel("Ingrese su numero PIN", SwingConstants.CENTER);
        lblInstr.setAlignmentX(Component.CENTER_ALIGNMENT); panel.add(lblInstr);
        panel.add(Box.createVerticalStrut(10));
        txtPin = new JPasswordField(4);
        txtPin.setMaximumSize(new Dimension(200,35));
        txtPin.setFont(new Font("SansSerif",Font.PLAIN,18));
        txtPin.setHorizontalAlignment(JTextField.CENTER);
        txtPin.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(txtPin);
        panel.add(Box.createVerticalStrut(20));
        JButton btn = new JButton("Continuar");
        btn.setMaximumSize(new Dimension(200,40));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.addActionListener(e -> {
            try {
                Sistema.validarPin(new String(txtPin.getPassword()));
                dispose();
                new MenuPrincipalFrame().setVisible(true);
            } catch (PinInvalidoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
                txtPin.setText("");
            }
        });
        panel.add(btn);
        add(panel);
    }
}
