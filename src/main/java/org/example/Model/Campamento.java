package org.example.Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;
import java.util.Random;

public class Campamento {

    private int id;
    private Queue<Paciente> filaPacientes;
    private Stack<Integer> raciones;
    private List<Character> inventarioMedicamentos;

    public Campamento(int id) {
        this.id = id;
        this.filaPacientes = new LinkedList<>();
        this.raciones = new Stack<>();
        this.inventarioMedicamentos = new ArrayList<>();
    }

    public int getId() { return id; }
    public Queue<Paciente> getFilaPacientes() { return filaPacientes; }
    public Stack<Integer> getRaciones() { return raciones; }
    public List<Character> getInventarioMedicamentos() { return inventarioMedicamentos; }

    public void aprovisionar() {
        Random rnd = new Random();

        int nuevasRaciones = 5 + rnd.nextInt(6);
        for (int i = 0; i < nuevasRaciones; i++) {
            raciones.push(rnd.nextInt(2));
        }

        int nuevosMedicamentos = 10 + rnd.nextInt(11);
        char[] letras = {'A', 'B', 'C'};
        for (int i = 0; i < nuevosMedicamentos; i++) {
            inventarioMedicamentos.add(letras[rnd.nextInt(letras.length)]);
        }
    }

    private boolean tieneMedicamentos(String receta) {
        List<Character> copia = new ArrayList<>(inventarioMedicamentos);
        for (char c : receta.toCharArray()) {
            if (!copia.remove((Character) c)) {
                return false;
            }
        }
        return true;
    }

    private void extraerMedicamentos(String receta) {
        for (char c : receta.toCharArray()) {
            inventarioMedicamentos.remove((Character) c);
        }
    }

    public List<Paciente> atenderDia(List<Paciente> sanados) {
        List<Paciente> bloqueados = new ArrayList<>();
        int rechazos = 0;
        int cantidadPacientes = filaPacientes.size();

        while (!filaPacientes.isEmpty() && rechazos < cantidadPacientes) {
            Paciente p = filaPacientes.poll();

            boolean condicion1 = !raciones.isEmpty() && raciones.peek() == p.getPreferenciaRacion();
            boolean condicion2 = tieneMedicamentos(p.getRecetaMedica());

            if (condicion1 && condicion2) {
                raciones.pop();
                extraerMedicamentos(p.getRecetaMedica());
                p.setEstado(Paciente.Estado.SANADO);
                sanados.add(p);
                rechazos = 0;
            } else {
                filaPacientes.add(p);
                rechazos++;
            }
        }

        if (rechazos >= cantidadPacientes) {
            while (!filaPacientes.isEmpty()) {
                Paciente p = filaPacientes.poll();
                p.restarIntento();
                bloqueados.add(p);
            }
        }

        return bloqueados;
    }

    @Override
    public String toString() {
        return "Campamento{id=" + id +
                ", filaPacientes=" + filaPacientes +
                ", raciones=" + raciones +
                ", inventarioMedicamentos=" + inventarioMedicamentos + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Campamento)) return false;
        Campamento c = (Campamento) o;
        return id == c.id &&
                Objects.equals(filaPacientes, c.filaPacientes) &&
                Objects.equals(raciones, c.raciones) &&
                Objects.equals(inventarioMedicamentos, c.inventarioMedicamentos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filaPacientes, raciones, inventarioMedicamentos);
    }
}
