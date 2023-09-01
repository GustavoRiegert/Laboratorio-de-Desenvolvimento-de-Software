package org.example;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Disciplinas {

    private UUID id;
    private String nome;
    private String status;
    private int periodo;
    private int horas;
    private List<Aluno> Alunos ;
    private List<Professor> professores ;

    public Disciplinas(String nome, int periodo, int horas) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.status = "ATIVO";
        this.periodo = periodo;
    }

    public int alunosInscritos(){
        return getAlunos().size();
    }
}
