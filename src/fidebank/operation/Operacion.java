package fidebank.operation;

import fidebank.exception.PinInvalidoException;
import fidebank.exception.SaldoInsuficienteException;
public interface Operacion {
    void ejecutar() throws SaldoInsuficienteException, PinInvalidoException;
}
