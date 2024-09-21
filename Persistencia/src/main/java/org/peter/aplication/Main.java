package org.peter.aplication;

import org.peter.domain.Cita;
import org.peter.domain.Paciente;
import org.peter.infraestructure.repository.CitaRepository;
import org.peter.infraestructure.repository.PacienteRepository;
import java.util.stream.Collectors;
import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Main {
    private static PacienteRepository pacienteRepo = new PacienteRepository();
    private static CitaRepository citaRepo = new CitaRepository();

    private static List<Paciente> pacientes = pacienteRepo.cargarPacientes();
    private static List<Cita> citas = citaRepo.cargarCitas();

    public static void main(String[] args) {
            boolean salir = false;
            while (!salir) {
                String opcion = JOptionPane.showInputDialog(null, "Elija una opción: \n1. Registrar un nuevo paciente\n2. Actualizar datos del paciente\n3. Registrar nueva cita para paciente\n4. Eliminar una cita\n5. Eliminar un paciente\n6. Mostrar lista de pacientes\n7. Mostrar citas de un paciente\n8. Salir");

                switch (opcion) {
                    case "1":
                        registrarPaciente();
                        break;
                    case "2":
                        actualizarPaciente();
                        break;
                    case "3":
                        registrarCita();
                        break;
                    case "4":
                        eliminarCita();
                        break;
                    case "5":
                        eliminarPaciente();
                        break;
                    case "6":
                        mostrarPacientes();
                        break;
                    case "7":
                        mostrarCitasDePaciente();
                        break;
                    case "8":
                        salir = true;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida.");
                }
            }
        }


        private static void registrarPaciente() {

        int id = pacientes.size() + 1;
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre:");
        String apellido = JOptionPane.showInputDialog(null, "Ingrese el apellido:");
        int Edad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la edad:"));
        String genero = JOptionPane.showInputDialog(null, "Ingrese el género:");
        String direccion = JOptionPane.showInputDialog(null, "Ingrese la dirección:");
        String telefono = JOptionPane.showInputDialog(null, "Ingrese el teléfono:");

        Paciente nuevoPaciente = new Paciente(id, Edad, nombre,apellido, genero, direccion, telefono);
        pacientes.add(nuevoPaciente);
        pacienteRepo.guardarPacientes(pacientes);

        JOptionPane.showMessageDialog(null, "Paciente registrado correctamente.");
    }

    private static void actualizarPaciente() {
        int pacienteId = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID del paciente que desea actualizar:"));
        Paciente paciente = buscarPacientePorId(pacienteId);

        if (paciente == null) {
            JOptionPane.showMessageDialog(null, "Paciente no encontrado.");
            return;
        }

        JOptionPane.showMessageDialog(null, "Datos actuales del paciente:\n" + paciente);

        String nuevoNombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre (deje en blanco para no cambiar):", paciente.getNombre());
        if (!nuevoNombre.isBlank()) {
            paciente.setNombre(nuevoNombre);
        }

        String nuevoApellido = JOptionPane.showInputDialog(null, "Ingrese el nuevo apellido (deje en blanco para no cambiar):", paciente.getApellido());
        if (!nuevoApellido.isBlank()) {
            paciente.setApellido(nuevoApellido);
        }

        String nuevaEdad = JOptionPane.showInputDialog(null, "Ingrese la nueva edad (deje en blanco para no cambiar):", String.valueOf(paciente.getEdad()));
        if (!nuevaEdad.isBlank()) {
            paciente.setEdad(Integer.parseInt(nuevaEdad));
        }

        String nuevoGenero = JOptionPane.showInputDialog(null, "Ingrese el nuevo género (deje en blanco para no cambiar):", paciente.getGenero());
        if (!nuevoGenero.isBlank()) {
            paciente.setGenero(nuevoGenero);
        }

        String nuevaDireccion = JOptionPane.showInputDialog(null, "Ingrese la nueva dirección (deje en blanco para no cambiar):", paciente.getDireccion());
        if (!nuevaDireccion.isBlank()) {
            paciente.setDireccion(nuevaDireccion);
        }

        String nuevoTelefono = JOptionPane.showInputDialog(null, "Ingrese el nuevo teléfono (deje en blanco para no cambiar):", paciente.getTelefono());
        if (!nuevoTelefono.isBlank()) {
            paciente.setTelefono(nuevoTelefono);
        }


        pacienteRepo.guardarPacientes(pacientes);

        JOptionPane.showMessageDialog(null, "Datos del paciente actualizados correctamente.");
    }


    private static void registrarCita() {
        int pacienteId = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID del paciente:"));
        Paciente paciente = buscarPacientePorId(pacienteId);
        if (paciente == null) {
            JOptionPane.showMessageDialog(null, "Paciente no encontrado.");
            return;
        }

        LocalDate fecha = LocalDate.parse(JOptionPane.showInputDialog(null, "Ingrese la fecha (YYYY-MM-DD):"));
        LocalTime hora = LocalTime.parse(JOptionPane.showInputDialog(null, "Ingrese la hora (HH:MM):"));
        String motivo = JOptionPane.showInputDialog(null, "Ingrese el motivo de la cita:");

        Cita nuevaCita = new Cita(fecha, hora, motivo, paciente);
        citas.add(nuevaCita);
        citaRepo.guardarCitas(citas);

        JOptionPane.showMessageDialog(null, "Cita registrada correctamente.");
    }

    private static void eliminarCita() {

        int pacienteId = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID del paciente:"));
        Paciente paciente = buscarPacientePorId(pacienteId);

        if (paciente == null) {
            JOptionPane.showMessageDialog(null, "Paciente no encontrado.");
            return;
        }

        citas.remove(citas);

        JOptionPane.showMessageDialog(null, "Cita Borrada Correctamente.");
    }
    private static void eliminarPaciente() {
        int pacienteId = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID del paciente que desea eliminar:"));
        Paciente paciente = buscarPacientePorId(pacienteId);

        if (paciente == null) {
            JOptionPane.showMessageDialog(null, "Paciente no encontrado.");
            return;
        }

        pacientes.remove(paciente);

        citas.removeIf(cita -> cita.getPaciente().getId() == pacienteId);

        pacienteRepo.guardarPacientes(pacientes);
        citaRepo.guardarCitas(citas);

        JOptionPane.showMessageDialog(null, "Paciente y sus citas eliminados correctamente.");
    }


    private static void mostrarPacientes() {
        StringBuilder listado = new StringBuilder("Lista de pacientes:\n");
        for (Paciente paciente : pacientes) {
            listado.append(paciente).append("\n");
        }
        JOptionPane.showMessageDialog(null, listado.toString());
    }

    private static void mostrarCitasDePaciente() {

        int pacienteId = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID del paciente para mostrar sus citas:"));
        Paciente paciente = buscarPacientePorId(pacienteId);

        if (paciente == null) {
            JOptionPane.showMessageDialog(null, "Paciente no encontrado.");
            return;
        }

        List<Cita> citasPaciente = citas.stream().filter(cita -> cita.getPaciente().getId() == pacienteId).collect(Collectors.toList());

        if (citasPaciente.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay citas registradas para el paciente " + paciente.getNombre() + " " + paciente.getApellido());
        } else {
            StringBuilder citasInfo = new StringBuilder("Citas del paciente " + paciente.getNombre() + " " + paciente.getApellido() + ":\n");
            for (Cita cita : citasPaciente) {
                citasInfo.append("Fecha: ").append(cita.getFecha()).append(", Hora: ").append(cita.getHora()).append(", Motivo: ").append(cita.getMotivo()).append("\n");
            }
            JOptionPane.showMessageDialog(null, citasInfo.toString());
        }
    }


    private static Paciente buscarPacientePorId(int id) {
        return pacientes.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }
}







































































































































































































































// Buenas Noches Profe si no es Mucha molestia despues de la clase del lunes o del  martes si tiene unos 10 o 15 minuticos tal ve para una asesoria tal bez no  , no se si de para tanto  Entonces tal vez solo unos  pocos minutos; Gracias