package org.peter.interfaces;

import org.peter.domain.Paciente;

import java.util.*;

public interface PacienteRepository {
    List<Paciente> findAll();
    Paciente findById(int id);
    void save(Paciente pacientes);
    void update(Paciente pacientes);
    void delete(int id);
}

