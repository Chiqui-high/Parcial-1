package org.example.Model;

import java.util.Objects;

public class Paciente {

    public enum Estado {
        EN_ESPERA,
        SANADO,
        MUERTO
    }

    private String cc;
    private int preferenciaRacion;
    private String recetaMedica;
    private int intentosRestantes;
    private Estado estado;

    public Paciente(String cc, int preferenciaRacion, String recetaMedica) {
        this.cc = cc;
        this.preferenciaRacion = preferenciaRacion;
        this.recetaMedica = recetaMedica;
        this.intentosRestantes = 3;
        this.estado = Estado.EN_ESPERA;
    }

    public String getCc() { return cc; }
    public int getPreferenciaRacion() { return preferenciaRacion; }
    public String getRecetaMedica() { return recetaMedica; }
    public int getIntentosRestantes() { return intentosRestantes; }
    public Estado getEstado() { return estado; }

    public void setEstado(Estado estado) { this.estado = estado; }
    public void restarIntento() { this.intentosRestantes--; }

    @Override
    public String toString() {
        return "Paciente{cc='" + cc + "', preferenciaRacion=" + preferenciaRacion +
                ", recetaMedica='" + recetaMedica + "', intentosRestantes=" + intentosRestantes +
                ", estado=" + estado + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Paciente)) return false;
        Paciente p = (Paciente) o;
        return preferenciaRacion == p.preferenciaRacion &&
                intentosRestantes == p.intentosRestantes &&
                Objects.equals(cc, p.cc) &&
                Objects.equals(recetaMedica, p.recetaMedica) &&
                estado == p.estado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cc, preferenciaRacion, recetaMedica, intentosRestantes, estado);
    }
}
