package org.example;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Curso {
    private String nome;
    private int numeroCreditos;
    private List<Disciplinas> grade;

    public Curso(String nome, int numeroCreditos) {
        this.nome = nome;
        this.numeroCreditos = numeroCreditos;
        List<Grade> grade = new ArrayList<>();
    }

    private void gerarCurriculo(){

    }
}
