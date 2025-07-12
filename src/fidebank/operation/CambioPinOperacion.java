package fidebank.operation;

import fidebank.exception.PinInvalidoException;
import fidebank.controller.Sistema;

public class CambioPinOperacion implements Operacion {
    private String actual, nuevo;
    public CambioPinOperacion(String actual, String nuevo) {
        this.actual = actual; this.nuevo = nuevo;
    }
    public void ejecutar() throws PinInvalidoException {
        if (!Sistema.getClienteActual().validarPin(actual)) {
            throw new PinInvalidoException();
        }
        Sistema.getClienteActual().cambiarPin(nuevo);
    }
}
