package org.example;

import lombok.Data;

import java.util.List;

@Data
public class Universidade {
    private String nome;
    private String cnpj;
    private List<Curso> cursos;
    private List<Aluno> alunos;
    private List<Professor> professores;
    private Secretaria secretaria;
}
