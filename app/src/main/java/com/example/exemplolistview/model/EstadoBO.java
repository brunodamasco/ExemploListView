package com.example.exemplolistview.model;

public class EstadoBO {
    public EstadoBO(Estado estado) {
    }

    public static boolean validaNome(Estado estado) {
        return estado.getNome() != null && !estado.getNome().isEmpty();
    }

    public static boolean validaSigla(Estado estado) {
        return estado.getSigla() != null && !estado.getSigla().isEmpty();
    }
}
