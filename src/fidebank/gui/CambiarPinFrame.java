package fidebank.gui;
import javax.swing.*;
import java.awt.*;
import fidebank.controller.Sistema;
import fidebank.operation.CambioPinOperacion;
import fidebank.exception.PinInvalidoException;

public class CambiarPinFrame extends JFrame {
    public CambiarPinFrame() {
        setTitle("Cambiar PIN");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JPasswordField txtActual = new JPasswordField(4);
        txtActual.setMaximumSize(new Dimension(200,30));
        txtActual.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPasswordField txtNuevo = new JPasswordField(4);
        txtNuevo.setMaximumSize(new Dimension(200,30));
        txtNuevo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btn = new JButton("Confirmar");
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.addActionListener(e -> {
            try {
                new CambioPinOperacion(
                    new String(txtActual.getPassword()),
                    new String(txtNuevo.getPassword())
                ).ejecutar();
                JOptionPane.showMessageDialog(this, "PIN actualizado");
                dispose();
            } catch (PinInvalidoException pie) {
                JOptionPane.showMessageDialog(this, pie.getMessage());
            }
        });

        panel.add(new JLabel("PIN actual:", SwingConstants.CENTER));
        panel.add(Box.createVerticalStrut(5));
        panel.add(txtActual);
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("PIN nuevo:", SwingConstants.CENTER));
        panel.add(Box.createVerticalStrut(5));
        panel.add(txtNuevo);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btn);

        add(panel);
        setVisible(true);
    }
}
