package fidebank.gui;

import javax.swing.*;
import java.awt.*;
import fidebank.controller.Sistema;

public class SaldoFrame extends JFrame {
    public SaldoFrame() {
        setTitle("Consultar Saldo");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        double saldo = Sistema.getClienteActual().getCuentaPrincipal().getSaldo();
        JLabel lblSaldo = new JLabel("Saldo disponible: ₡" + saldo, SwingConstants.CENTER);
        lblSaldo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblSaldo);

        add(panel);
        setVisible(true);
    }
}
