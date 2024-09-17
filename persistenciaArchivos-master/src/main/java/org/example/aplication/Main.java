package org.example.aplication;
import javax.swing.*;
import org.example.aplication.service.PacienteService;
import org.example.aplication.service.PacienteServiceImpl;
import org.example.domain.Paciente;
import org.example.infraestructure.repository.PacienteRepositoryImpl;
import org.example.interfaces.PacienteRepository;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PacienteService PACIENTE_SERVICE;

    static {
        PacienteRepository pacienteRepository = new PacienteRepositoryImpl();
        PACIENTE_SERVICE = new PacienteServiceImpl(pacienteRepository);
    }

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {

            int opcion=Integer.parseInt(JOptionPane.showInputDialog(null,"Por Favor Elija Una Opcíon \n\n1. Lista de Pacientes \n2. Ingresar Nuevo Paciente \n3. Actualizar Paciente\n4. Dar de Baja al Paciente\n5. Salir"));

            switch (opcion) {
                case 1:
                    listarPacientes();
                    break;
                case 2:
                    crearPaciente();
                    break;
                case 3:
                    actualizarPaciente();
                    break;
                case 4:
                    eliminarPaciente();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"Opción inválida");
            }
        }
    }

    private static void listarPacientes() {
        List<Paciente> pacientes = PACIENTE_SERVICE.findAll();
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
        } else {
            System.out.println("Listado de pacientes:");
            for (Paciente Pacientes : pacientes) {
                System.out.println(Pacientes);
            }
        }
    }

    private static void crearPaciente() {
        System.out.print("Ingrese el còdigo del Pacientes: ");
        int cod  = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el nombre del Pacientes: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el Edad del Pacientes: ");
        double Edad = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea

        Paciente Pacientes = new Paciente();
        Pacientes.setId(cod);
        Pacientes.setNombre(nombre);
        Pacientes.setEdad(Edad);

        try {
            PACIENTE_SERVICE.save(Pacientes);
            System.out.println("Paciente creado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void actualizarPaciente() {
        System.out.print("Ingrese el ID del Pacientes a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Paciente Pacientes = PACIENTE_SERVICE.findById(id);
        if (Pacientes == null) {
            System.out.println("No se encontró el Pacientes con ID " + id);
            return;
        }

        System.out.print("Ingrese el nuevo nombre del Pacientes (dejar en blanco para no cambiar): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) {
            Pacientes.setNombre(nombre);
        }

        System.out.print("Ingrese el nuevo Edad del Pacientes (0 para no cambiar): ");
        double Edad = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea
        if (Edad > 0) {
            Pacientes.setEdad(Edad);
        }

        try {
            PACIENTE_SERVICE.update(Pacientes);
            System.out.println("Paciente actualizado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void eliminarPaciente() {
        System.out.print("Ingrese el ID del Pacientes a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Paciente Pacientes = PACIENTE_SERVICE.findById(id);
        if (Pacientes == null) {
            System.out.println("No se encontró el Pacientes con ID " + id);
            return;
        }

        PACIENTE_SERVICE.delete(id);
        System.out.println("Paciente eliminado exitosamente.");
    }
}
