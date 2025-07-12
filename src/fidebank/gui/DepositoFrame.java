package fidebank.gui;

import javax.swing.*;
import java.awt.*;
import fidebank.controller.Sistema;
import fidebank.operation.DepositoOperacion;
import fidebank.model.Transaccion;

public class DepositoFrame extends JFrame {
    public DepositoFrame() {
        setTitle("Depositar Dinero");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 160);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(0,10));
        panel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        JLabel lbl = new JLabel("Monto a depositar:");
        JTextField txtMonto = new JTextField();

        JButton btn = new JButton("Continuar");
        btn.addActionListener(e -> {
            String s = txtMonto.getText().trim();
            try {
                double monto = Double.parseDouble(s);

                // Paso 1: pedimos al cliente insertar efectivo
                JOptionPane.showMessageDialog(
                    this,
                    "Por favor inserte ₡" + monto + " en efectivo y presione Aceptar"
                );

                // Paso 2: ejecutamos depósito
                new DepositoOperacion(monto).ejecutar();

                // Paso 3: comprobante con nuevo saldo
                double nuevoSaldo = Sistema
                    .getClienteActual()
                    .getCuentaPrincipal()
                    .getSaldo();
                Transaccion tx = new Transaccion("Depósito", monto);
                new ComprobanteFrame(tx, nuevoSaldo).setVisible(true);
                dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Monto inválido");
            }
        });

        panel.add(lbl, BorderLayout.NORTH);
        panel.add(txtMonto, BorderLayout.CENTER);
        panel.add(btn, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }
}