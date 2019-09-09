package com.example.exemplolistview.model;

public class EstadoBO {
    public static boolean valida(Estado estado){
        return estado!= null && estado.getNome() != null && estado.getNome().isEmpty() &&
                estado.getSigla() != null;
    }
}
