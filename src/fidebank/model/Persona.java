package fidebank.model;

import java.io.Serializable;

public abstract class Persona implements Serializable {
    protected String nombre;
    protected String apellido;

    public Persona(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
}
