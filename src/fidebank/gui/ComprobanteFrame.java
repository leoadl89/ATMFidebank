package fidebank.gui;

import javax.swing.*;
import java.awt.*;
import fidebank.model.Comprobante;
import fidebank.model.Transaccion;

public class ComprobanteFrame extends JFrame {

    /**
     * Muestra un comprobante de la transacción y el saldo actualizado.
     *
     * @param tx           La transacción ejecutada (tipo y monto).
     * @param saldoActual  El saldo de la cuenta después de la operación.
     */
    public ComprobanteFrame(Transaccion tx, double saldoActual) {
        setTitle("Comprobante");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(320, 280);
        setLocationRelativeTo(null);

        // Panel principal con estilo uniforme
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Logo de FideBank
        JLabel lblLogo = new JLabel("FideBank", SwingConstants.CENTER);
        lblLogo.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(15));
        panel.add(lblLogo);

        // Area de texto con los detalles
        panel.add(Box.createVerticalStrut(15));
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setBackground(Color.WHITE);
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));
        StringBuilder sb = new StringBuilder();
        if (tx != null) {
            sb.append(new Comprobante(tx).generarTexto());
        } else {
            sb.append("Transacción no disponible\n");
        }
        sb.append("\nSaldo actual: ₡").append(String.format("%.2f", saldoActual));
        area.setText(sb.toString());
        area.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(area);

        // Botón para cerrar el comprobante
        panel.add(Box.createVerticalStrut(15));
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAceptar.addActionListener(e -> dispose());
        panel.add(btnAceptar);

        add(panel);
        setVisible(true);
    }
}