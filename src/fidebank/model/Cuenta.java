package fidebank.model;

import java.io.Serializable;

public class Cuenta implements Serializable {
    private String numeroCuenta;
    private double saldo;

    /**
     * Constructor.
     * 
     * @param numeroCuenta Identificador único de la cuenta
     * @param saldo        Saldo inicial
     */
    public Cuenta(String numeroCuenta, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }

    /**
     * Devuelve el número de cuenta.
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * Devuelve el saldo actual.
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Deposita un monto en la cuenta.
     * 
     * @param monto Monto a depositar (debe ser positivo)
     */
    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
        }
    }

    /**
     * Intenta retirar un monto de la cuenta.
     * 
     * @param monto Monto a retirar
     * @return true si el retiro fue exitoso, false si fondos insuficientes
     */
    public boolean retirar(double monto) {
        if (monto > 0 && saldo >= monto) {
            saldo -= monto;
            return true;
        }
        return false;
    }
}
