package org.peter.infraestructure.repository;

import org.peter.domain.Paciente;
import org.peter.interfaces.PacienteRepository;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class PacienteRepositoryImpl implements PacienteRepository {

    private static final String FILE_NAME = "Pacientes.dat";

    @Override
    public List<Paciente> findAll() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Paciente>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Paciente findById(int id) {
        return findAll().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Paciente producto) {
        List<Paciente> Pacientes = findAll();
        Pacientes.add(producto);
        saveAll(Pacientes);
    }

    @Override
    public void update(Paciente producto) {
        List<Paciente> Pacientes = findAll();
        Pacientes = Pacientes.stream()
                .map(p -> p.getId() == producto.getId() ? producto : p)
                .collect(Collectors.toList());
        saveAll(Pacientes);
    }

    @Override
    public void delete(int id) {
        List<Paciente> Pacientes = findAll();
        Pacientes = Pacientes.stream()
                .filter(p -> p.getId() != id)
                .collect(Collectors.toList());
        saveAll(Pacientes);
    }

    private void saveAll(List<Paciente> Pacientes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(Pacientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
