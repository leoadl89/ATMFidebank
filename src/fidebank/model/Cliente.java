package fidebank.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona implements Serializable {
    private String pin;
    private List<Cuenta> cuentas;

    /**
     * Constructor principal.
     *
     * @param nombre   Nombre del cliente
     * @param apellido Apellido del cliente
     * @param pin       PIN de acceso (4 dígitos)
     */
    public Cliente(String nombre, String apellido, String pin) {
        super(nombre, apellido);
        this.pin = pin;
        this.cuentas = new ArrayList<>();
    
    }

    /**
     * Devuelve el PIN actual del cliente.
     */
    public String getPin() {
        return pin;
    }

    /**
     * Cambia el PIN del cliente.
     *
     * @param nuevoPin Nuevo PIN a registrar
     */
    public void cambiarPin(String nuevoPin) {
        this.pin = nuevoPin;
    }

    /**
     * Devuelve la lista de cuentas asociadas al cliente.
     */
    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    /**
     * Valida que el PIN ingresado coincida con el PIN del cliente.
     *
     * @param inputPin PIN ingresado por el usuario
     * @return true si coincide, false en caso contrario
     */
    public boolean validarPin(String inputPin) {
        return this.pin.equals(inputPin);
    }

    /**
     * Obtiene la primera cuenta de la lista (cuenta principal).
     *
     * @return La cuenta principal, o null si no hay ninguna
     */
    public Cuenta getCuentaPrincipal() {
        if (cuentas.isEmpty()) {
            return null;
        }
        return cuentas.get(0);
    }
}