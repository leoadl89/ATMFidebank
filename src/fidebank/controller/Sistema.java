package fidebank.controller;

import fidebank.model.Cliente;
import fidebank.model.Cuenta;
import fidebank.exception.PinInvalidoException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private static List<Cliente> clientes;
    private static Cliente clienteActual;
    private static final String RUTA_CSV = "clientes.csv";

    static {
        try {
            cargarDatosCSV();
        } catch (Exception e) {
            // Si falla la lectura, inicio una lista vacía
            clientes = new ArrayList<>();
        }

        // Si tras cargar no hay clientes, agrego uno de prueba
        if (clientes.isEmpty()) {
            Cliente prueba = new Cliente("Leo", "Solorzano", "1234");
            // Eliminar la cuenta por defecto y asignar una nueva
            prueba.getCuentas().clear();
            String numero = generarNuevoNumeroCuenta();
            prueba.getCuentas().add(new Cuenta(numero, 150000));
            clientes.add(prueba);
        }

        // Guardar automáticamente al salir
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                guardarDatosCSV();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }));
    }

    /**
     * Valida el PIN ingresado.
     */
    public static void validarPin(String pin) throws PinInvalidoException {
        for (Cliente c : clientes) {
            if (c.validarPin(pin)) {
                clienteActual = c;
                return;
            }
        }
        throw new PinInvalidoException();
    }

    /**
     * Registra un nuevo cliente, le asigna un número de cuenta automático y persiste.
     */
    public static void registrarCliente(String nombre, String apellido, String pin) {
        Cliente c = new Cliente(nombre, apellido, pin);
        // Quitar cuenta por defecto y asignar nueva cuenta con saldo 0
        c.getCuentas().clear();
        String numero = generarNuevoNumeroCuenta();
        c.getCuentas().add(new Cuenta(numero, 0.0));
        clientes.add(c);
        try {
            guardarDatosCSV();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Cliente getClienteActual() {
        return clienteActual;
    }
    
    /**
     * Busca el Cliente que tenga la cuenta con el número dado.
     * @param numeroCuenta número de cuenta a buscar
     * @return Cliente si lo encuentra, o null si no existe
     */
    public static Cliente buscarClientePorCuenta(String numeroCuenta) {
        for (Cliente c : clientes) {
            for (Cuenta cu : c.getCuentas()) {
                if (cu.getNumeroCuenta().equals(numeroCuenta)) {
                    return c;
                }
            }
        }
        return null;
    }

    /**
     * Genera un nuevo número de cuenta incremental de 6 dígitos.
     */
    private static String generarNuevoNumeroCuenta() {
        int max = 0;
        for (Cliente c : clientes) {
            for (Cuenta cuenta : c.getCuentas()) {
                try {
                    int n = Integer.parseInt(cuenta.getNumeroCuenta());
                    if (n > max) max = n;
                } catch (NumberFormatException ignored) {}
            }
        }
        return String.format("%06d", max + 1);
    }

    /** Carga clientes desde el CSV; formato: nombre;apellido;pin;numeroCuenta;saldo */
    private static void cargarDatosCSV() throws IOException {
        clientes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_CSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(";");
                if (campos.length >= 5) {
                    String nombre = campos[0];
                    String apellido = campos[1];
                    String pin = campos[2];
                    String numCuenta = campos[3];
                    double saldo = Double.parseDouble(campos[4]);
                    Cliente c = new Cliente(nombre, apellido, pin);
                    c.getCuentas().clear();
                    c.getCuentas().add(new Cuenta(numCuenta, saldo));
                    clientes.add(c);
                }
            }
        }
    }

    /** Guarda todos los clientes en el CSV */
    private static void guardarDatosCSV() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_CSV))) {
            for (Cliente c : clientes) {
                for (Cuenta cuenta : c.getCuentas()) {
                    String línea = String.join(";",
                        c.getNombre(),
                        c.getApellido(),
                        c.getPin(),
                        cuenta.getNumeroCuenta(),
                        String.valueOf(cuenta.getSaldo())
                    );
                    bw.write(línea);
                    bw.newLine();
                }
            }
        }
    }
}