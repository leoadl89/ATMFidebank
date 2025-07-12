package fidebank.gui;

import javax.swing.*;
import java.awt.*;
import fidebank.controller.Sistema;
import fidebank.operation.RetiroOperacion;
import fidebank.exception.SaldoInsuficienteException;
import fidebank.model.Transaccion;

public class RetiroFrame extends JFrame {
    public RetiroFrame() {
        setTitle("Retirar Dinero");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(320, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        JLabel lbl = new JLabel("Monto a retirar:", SwingConstants.CENTER);
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField txtMonto = new JTextField();
        txtMonto.setMaximumSize(new Dimension(200,25));
        txtMonto.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btn = new JButton("Retirar");
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.addActionListener(e -> {
            try {
                double monto = Double.parseDouble(txtMonto.getText());
                new RetiroOperacion(monto).ejecutar();
                // Nuevo saldo
                double nuevoSaldo = Sistema.getClienteActual()
                                         .getCuentaPrincipal()
                                         .getSaldo();
                // Transacción y comprobante
                Transaccion tx = new Transaccion("Retiro", monto);
                new ComprobanteFrame(tx, nuevoSaldo).setVisible(true);
                dispose();
            } catch (SaldoInsuficienteException sie) {
                JOptionPane.showMessageDialog(this, sie.getMessage());
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Monto inválido");
            }
        });

        panel.add(lbl);
        panel.add(Box.createVerticalStrut(8));
        panel.add(txtMonto);
        panel.add(Box.createVerticalStrut(12));
        panel.add(btn);

        add(panel);
        setVisible(true);
    }
}