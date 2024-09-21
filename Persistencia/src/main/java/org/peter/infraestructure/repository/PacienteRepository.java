package org.peter.infraestructure.repository;

import org.peter.domain.Paciente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteRepository {
    private static final String FILE_NAME = "pacientes.txt";

    public List<Paciente> cargarPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            pacientes = (List<Paciente>) ois.readObject();
        } catch (FileNotFoundException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return pacientes;
    }

    public void guardarPacientes(List<Paciente> pacientes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(pacientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
