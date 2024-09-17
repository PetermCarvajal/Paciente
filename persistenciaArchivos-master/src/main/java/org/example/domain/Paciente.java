package org.example.domain;

import java.io.Serializable;

public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String nombre;
    private double edad;

    // Constructores, getters y setters

    public Paciente() {
    }

    public Paciente(int id, String nombre, double edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getEdad() {
        return edad;
    }

    public void setEdad(double edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Paciente: " + "id=" + id + ", Nombre='" + nombre + '\'' + ", Edad=" + edad + '}';
    }
}