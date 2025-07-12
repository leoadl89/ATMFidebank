package fidebank.model;

import java.io.Serializable;

public class Comprobante implements Serializable {
    private Transaccion transaccion;

    public Comprobante(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    /** Genera un texto simple con los datos de la transacción */
    public String generarTexto() {
        return  "Tipo       : " + transaccion.getTipo()  + "\n" +
                "Monto      : ₡" + transaccion.getMonto() + "\n" +
                "Fecha      : " + transaccion.getFecha() + "\n";
    }
}