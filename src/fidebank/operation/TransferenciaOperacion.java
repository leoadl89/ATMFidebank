package fidebank.operation;

import fidebank.exception.SaldoInsuficienteException;
import fidebank.controller.Sistema;

public class TransferenciaOperacion implements Operacion {
    private double monto;
    public TransferenciaOperacion(double monto) { this.monto = monto; }
    public void ejecutar() throws SaldoInsuficienteException {
        if (!Sistema.getClienteActual().getCuentaPrincipal().retirar(monto)) {
            throw new SaldoInsuficienteException("Saldo insuficiente para transferencia ₡" + monto);
        }
        Sistema.getClienteActual().getCuentaPrincipal().depositar(monto);
    }
}
