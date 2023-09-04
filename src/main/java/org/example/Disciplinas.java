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

    public Disciplinas(String nome, int periodo, int horas){
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.status = "ATIVO";
        this.periodo = periodo;
        this.horas = horas;
    }

    public void addProfessor(Professor professor){
        this.professores.add(professor);
    }

    public void removeProfessor(Professor professor){
        this.professores.remove(professor);
    }

    public void addAluno(Aluno aluno){
        this.Alunos.add(aluno);
    }

    public void removeAluno(Aluno aluno){
        this.Alunos.remove(aluno);
    }

    public void encerrarDisciplina(){
        this.status = "ENCERRADO";
    }

    public void ativarDisciplina(){
        this.status = "ATIVO";
    }

    public void editarDisciplina(String nome, int periodo, int horas){
        this.nome = nome;
        this.periodo = periodo;
        this.horas = horas;
    }

    public void editarDisciplina(String nome, int periodo){
        this.nome = nome;
        this.periodo = periodo;
    }

    public void editarDisciplina(String nome){
        this.nome = nome;
    }

    public void editarDisciplina(int periodo){
        this.periodo = periodo;
    }

    public String listarAlunos(){
        String lista = "";
        for (Aluno aluno : Alunos){
            lista += aluno.getNome() + "\n";
        }
        return lista;
    }

    public String listarProfessores(){
        String lista = "";
        for (Professor professor : professores){
            lista += professor.getNome() + "\n";
        }
        return lista;
    }

    public String toString(){
        return "Nome: " + this.nome + "\n" +
                "Status: " + this.status + "\n" +
                "Periodo: " + this.periodo + "\n" +
                "Horas: " + this.horas + "\n" +
                "Alunos: " + listarAlunos() + "\n" +
                "Professores: " + listarProfessores() + "\n";
    }

}