package org.example;

import org.example.Model.Campamento;
import org.example.Model.Paciente;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainFase2 {

    public static void main(String[] args) {

        Campamento campamento1 = new Campamento(1);
        Campamento campamento2 = new Campamento(2);
        Campamento campamento3 = new Campamento(3);

        List<Paciente> pacientesIniciales = generarPacientes(25);
        campamento1.getFilaPacientes().addAll(pacientesIniciales);

        List<Paciente> sanados = new ArrayList<>();
        List<Paciente> muertos = new ArrayList<>();

        boolean provisionado1 = false, provisionado2 = false, provisionado3 = false;
        String inventarioInicial1 = "", inventarioInicial2 = "", inventarioInicial3 = "";

        int dia = 0;
        boolean huboActividad = true;

        while (huboActividad) {
            dia++;
            huboActividad = false;
            System.out.println("\n=== DIA " + dia + " ===");

            if (!campamento1.getFilaPacientes().isEmpty()) {
                huboActividad = true;
                campamento1.aprovisionar();
                if (!provisionado1) {
                    inventarioInicial1 = "Raciones: " + campamento1.getRaciones()
                            + " | Medicamentos: " + campamento1.getInventarioMedicamentos();
                    provisionado1 = true;
                }
                System.out.println("Campamento 1 atiende "
                        + campamento1.getFilaPacientes().size()
                        + " paciente(s)");

                int sanadosAntes = sanados.size();
                List<Paciente> bloqueados1 = campamento1.atenderDia(sanados);

                for (int i = sanadosAntes; i < sanados.size(); i++) {
                    System.out.println("  [Campamento 1] SANADO -> " + sanados.get(i));
                }
                for (Paciente p : bloqueados1) {
                    System.out.println("  [Campamento 1] TRASLADADO -> Campamento 2 : " + p);
                }
                campamento2.getFilaPacientes().addAll(bloqueados1);
            }

            if (!campamento2.getFilaPacientes().isEmpty()) {
                huboActividad = true;
                campamento2.aprovisionar();
                if (!provisionado2) {
                    inventarioInicial2 = "Raciones: " + campamento2.getRaciones()
                            + " | Medicamentos: " + campamento2.getInventarioMedicamentos();
                    provisionado2 = true;
                }
                System.out.println("Campamento 2 atiende " + campamento2.getFilaPacientes().size() + " paciente(s)");

                int sanadosAntes = sanados.size();
                List<Paciente> bloqueados2 = campamento2.atenderDia(sanados);

                for (int i = sanadosAntes; i < sanados.size(); i++) {
                    System.out.println("  [Campamento 2] SANADO -> " + sanados.get(i));
                }
                for (Paciente p : bloqueados2) {
                    System.out.println("  [Campamento 2] TRASLADADO -> Campamento 3 : " + p);
                }
                campamento3.getFilaPacientes().addAll(bloqueados2);
            }

            if (!campamento3.getFilaPacientes().isEmpty()) {
                huboActividad = true;
                campamento3.aprovisionar();
                if (!provisionado3) {
                    inventarioInicial3 = "Raciones: " + campamento3.getRaciones()
                            + " | Medicamentos: " + campamento3.getInventarioMedicamentos();
                    provisionado3 = true;
                }
                System.out.println("Campamento 3 atiende " + campamento3.getFilaPacientes().size() + " paciente(s)");

                int sanadosAntes = sanados.size();
                List<Paciente> bloqueados3 = campamento3.atenderDia(sanados);

                for (int i = sanadosAntes; i < sanados.size(); i++) {
                    System.out.println("  [Campamento 3] SANADO -> " + sanados.get(i));
                }
                for (Paciente p : bloqueados3) {
                    p.setEstado(Paciente.Estado.MUERTO);
                    muertos.add(p);
                    System.out.println("  [Campamento 3] MUERTO (sin mas campamentos) : " + p);
                }
            }
        }

        System.out.println("\n=== RESULTADOS ===");
        System.out.println("Dias transcurridos: " + dia);
        System.out.println("Pacientes sanados: " + sanados.size());
        System.out.println("Pacientes muertos: " + muertos.size());

        System.out.println("\n--- Inventario INICIAL " +
                "(tras primer aprovisionamiento de cada campamento) ---");
        System.out.println("Campamento 1 -> " + inventarioInicial1);
        System.out.println("Campamento 2 -> " + inventarioInicial2);
        System.out.println("Campamento 3 -> " + inventarioInicial3);

        System.out.println("\n--- Inventario FINAL ---");
        System.out.println("Campamento 1 -> Raciones: " + campamento1.getRaciones()
                + " | Medicamentos: " + campamento1.getInventarioMedicamentos());
        System.out.println("Campamento 2 -> Raciones: " + campamento2.getRaciones()
                + " | Medicamentos: " + campamento2.getInventarioMedicamentos());
        System.out.println("Campamento 3 -> Raciones: " + campamento3.getRaciones()
                + " | Medicamentos: " + campamento3.getInventarioMedicamentos());

        System.out.println("\n--- Detalle sanados ---");
        for (Paciente p : sanados) System.out.println(p);

        System.out.println("\n--- Detalle muertos ---");
        for (Paciente p : muertos) System.out.println(p);
    }

    private static List<Paciente> generarPacientes(int cantidad) {
        List<Paciente> pacientes = new ArrayList<>();
        Random rnd = new Random();
        char[] letras = {'A', 'B', 'C'};

        for (int i = 1; i <= cantidad; i++) {
            String cc = "CC" + (1000 + i);
            int preferencia = rnd.nextInt(2);
            int longitudReceta = 3 + rnd.nextInt(6);

            StringBuilder receta = new StringBuilder();
            for (int j = 0; j < longitudReceta; j++) {
                receta.append(letras[rnd.nextInt(letras.length)]);
            }

            pacientes.add(new Paciente(cc, preferencia, receta.toString()));
        }
        return pacientes;
    }
}
