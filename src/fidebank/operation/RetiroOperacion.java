package fidebank.operation;

import fidebank.exception.SaldoInsuficienteException;
import fidebank.controller.Sistema;

public class RetiroOperacion implements Operacion {
    private double monto;
    public RetiroOperacion(double monto) { this.monto = monto; }
    public void ejecutar() throws SaldoInsuficienteException {
        if (!Sistema.getClienteActual().getCuentaPrincipal().retirar(monto)) {
            throw new SaldoInsuficienteException("Saldo insuficiente para retirar ₡" + monto);
        }
    }
}
