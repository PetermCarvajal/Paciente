package org.peter.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Cita implements Serializable{
    private static final long serialVersionUID = 1L;
    private LocalDate fecha;
    private LocalTime hora;
    private String motivo;
    private Paciente paciente;

    // Constructores

    public Cita() {}

    public Cita(LocalDate fecha, LocalTime hora, String motivo, Paciente paciente) {
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.paciente = paciente;
    }

    //Getters

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    //Setters

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "Cita: \nFecha: " + fecha + "\nHora: " + hora + "\nMotivo: " + motivo + "\nPaciente: " + paciente;
    }
}

