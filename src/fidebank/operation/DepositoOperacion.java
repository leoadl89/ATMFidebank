package fidebank.operation;

import fidebank.controller.Sistema;

public class DepositoOperacion implements Operacion {
    private double monto;
    public DepositoOperacion(double monto) { this.monto = monto; }
    public void ejecutar() { Sistema.getClienteActual().getCuentaPrincipal().depositar(monto); }
}
