package fidebank.gui;

import javax.swing.*;
import java.awt.*;
import fidebank.controller.Sistema;
import fidebank.exception.SaldoInsuficienteException;
import fidebank.operation.TransferenciaOperacion;
import fidebank.model.Cliente;
import fidebank.model.Transaccion;

public class TransferenciaFrame extends JFrame {
    public TransferenciaFrame() {
        setTitle("Transferir Dinero");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(320, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        // Paso A: ingreso de número de cuenta destino
        JLabel lblCuenta = new JLabel("Cuenta destino (número en formato 000000):", SwingConstants.CENTER);
        JTextField txtCuenta = new JTextField();
        txtCuenta.setMaximumSize(new Dimension(200,25));

        JButton btnBuscar = new JButton("Siguiente");
        btnBuscar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBuscar.addActionListener(e -> {
            String numero = txtCuenta.getText().trim();
            Cliente destino = Sistema.buscarClientePorCuenta(numero);
            if (destino == null) {
                JOptionPane.showMessageDialog(this, "Cuenta no encontrada");
                return;
            }

            // Paso B: mostramos nombre del titular y pedimos monto
            String nombreDest = destino.getNombre() + " " + destino.getApellido();
            String input = JOptionPane.showInputDialog(
                this,
                "Titular: " + nombreDest + "\nIngrese monto a transferir:",
                "Transferir a " + nombreDest,
                JOptionPane.PLAIN_MESSAGE
            );
            if (input == null) return;
            try {
                double monto = Double.parseDouble(input);
                // Ejecutamos transferencia
                new TransferenciaOperacion(monto).ejecutar();
                // Depositamos al destino
                destino.getCuentaPrincipal().depositar(monto);

                // Paso C: comprobante con nuevo saldo de la cuenta original
                double nuevoSaldo = Sistema
                    .getClienteActual()
                    .getCuentaPrincipal()
                    .getSaldo();
                Transaccion tx = new Transaccion("Transferencia", monto);
                new ComprobanteFrame(tx, nuevoSaldo).setVisible(true);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Monto inválido");
            } catch (SaldoInsuficienteException sie) {
                JOptionPane.showMessageDialog(this, sie.getMessage());
            }
        });

        panel.add(lblCuenta);
        panel.add(Box.createVerticalStrut(5));
        panel.add(txtCuenta);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnBuscar);

        add(panel);
        setVisible(true);
    }
}