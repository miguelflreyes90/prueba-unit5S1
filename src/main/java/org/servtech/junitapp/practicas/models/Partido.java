package org.servtech.junitapp.practicas.models;

import org.servtech.junitapp.ejemplos.models.Banco;

public class Partido {
    private  String nombre;
    public Partido(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
