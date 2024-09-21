package org.peter.domain;

import java.io.Serializable;

public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String nombre;
    private String apellido;
    private int Edad;
    private String genero;
    private String direccion;
    private String telefono;

    // Constructores

    public Paciente() {
    }

    public Paciente(int id,int Edad,String nombre,String apellido,String genero,String direccion,String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.Edad = Edad;
        this.apellido = apellido;
        this.genero = genero;
        this.direccion = direccion;
        this.telefono = telefono;

    }

    //Getters


    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return Edad;
    }

    public String getGenero() {
        return genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    //Setters


    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEdad(int Edad) {
        this.Edad = Edad;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Paciente: \nID: " + id + "\nNombre: " + nombre + "\nApellido: " + apellido + "\nEdad: " + Edad + "\nGénero: " + genero + "\nDirección: " + direccion + "\nTeléfono: " + telefono;
    }
}