package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Aluno extends Usuario{
    private String nome;
    private Curso curso;
    private List<Disciplinas> disciplinasAtivas;
    private boolean isInadimplente;

    public boolean isInadimplente() {
        return isInadimplente;
    }

    public Aluno(String nome, Curso curso, String senha) {
        this.nome = nome;
        this.curso = curso;
        this.disciplinasAtivas = new ArrayList<>();
        this.setId(UUID.randomUUID());
        this.setSenha(senha);
    }

}
