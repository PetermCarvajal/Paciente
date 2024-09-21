package org.peter.infraestructure.repository;

import org.peter.domain.Cita;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CitaRepository {
    private static final String FILE_NAME = "citas.txt";

    public List<Cita> cargarCitas() {
        List<Cita> citas = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            citas = (List<Cita>) ois.readObject();
        } catch (FileNotFoundException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return citas;
    }

    public void guardarCitas(List<Cita> citas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(citas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
