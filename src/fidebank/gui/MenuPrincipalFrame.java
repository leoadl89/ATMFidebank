package fidebank.gui;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipalFrame extends JFrame {

    public MenuPrincipalFrame() {
        setTitle("FideBank - Menú Principal");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450, 350);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Botón Consultar Saldo
        JButton btnSaldo = new JButton("Consultar Saldo");
        btnSaldo.addActionListener(e -> new SaldoFrame().setVisible(true));
        panel.add(btnSaldo);

        // Botón Depositar Dinero
        JButton btnDeposito = new JButton("Depositar");
        btnDeposito.addActionListener(e -> new DepositoFrame().setVisible(true));
        panel.add(btnDeposito);

        // Botón Retirar Dinero
        JButton btnRetiro = new JButton("Retirar");
        btnRetiro.addActionListener(e -> new RetiroFrame().setVisible(true));
        panel.add(btnRetiro);

        // Botón Transferir Dinero
        JButton btnTransferencia = new JButton("Transferir");
        btnTransferencia.addActionListener(e -> new TransferenciaFrame().setVisible(true));
        panel.add(btnTransferencia);

        // Botón Cambiar PIN
        JButton btnCambiarPin = new JButton("Cambiar PIN");
        btnCambiarPin.addActionListener(e -> new CambiarPinFrame().setVisible(true));
        panel.add(btnCambiarPin);

        // Botón Registrar Cuenta (HU-006)
        JButton btnRegistrar = new JButton("Registrar Cuenta");
        btnRegistrar.addActionListener(e -> new RegistrarCuentaFrame().setVisible(true));
        panel.add(btnRegistrar);

        // Botón Cerrar Sesión
        JButton btnSalir = new JButton("Cerrar Sesión");
        btnSalir.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
        panel.add(btnSalir);

        panel.add(Box.createGlue());

        add(panel);
        setVisible(true);
    }
}