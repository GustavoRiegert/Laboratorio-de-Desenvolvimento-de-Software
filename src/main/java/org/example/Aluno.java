package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Aluno extends Usuario {
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

  public String visualizarBoleto(Secretaria secretaria) {
    if (isInadimplente) {
        return "Você está inadimplente. Por favor, regularize sua situação financeira.";
    } else {
        String boleto = "Detalhes do Boleto:\n";
        boleto += "Nome do Aluno: " + this.nome + "\n";
        boleto += "Curso: " + this.curso.getNome() + "\n";

        secretaria.verificaInadimplentes(universidade);

        return boleto;
    }
}

public void visualizarDisciplinasOfertadas(Universidade universidade) {
    System.out.println("Disciplinas ofertadas para o aluno " + this.nome + ":");

    for (Disciplinas disciplina : universidade.getDisciplinasOfertadas()) {
        if (disciplina.getStatus().equals("Disponível")) {
            System.out.println("ID: " + disciplina.getId());
            System.out.println("Nome: " + disciplina.getNome());
            System.out.println("Período: " + disciplina.getPeriodo());
            System.out.println();
        }
    }
}

    public void visualizarDisciplinasCorrentes() {
    System.out.println("Disciplinas correntes do aluno " + this.nome + ":");
    
    if (disciplinasAtivas.isEmpty()) {
        System.out.println("O aluno não está matriculado em nenhuma disciplina corrente.");
    } else {
        for (Disciplinas disciplina : disciplinasAtivas) {
            System.out.println("ID: " + disciplina.getId());
            System.out.println("Nome: " + disciplina.getNome());
            System.out.println("Período: " + disciplina.getPeriodo());
            System.out.println();
        }
    }
}

    private void cadastrarDisciplina(Universidade universidade, String nome, String curso, int periodo, int horas){
        for (Curso cursos : universidade.getCursos()){
            if(cursos.getNome().equals(curso)){
                cursos.getGrade().add(new Disciplinas(nome, periodo, horas));
            }
        }
    }

    public void trancarDisciplinas(List<Disciplinas> disciplinasParaTrancar) {
    System.out.println("Trancando disciplinas do aluno " + this.nome);
    
    for (Disciplinas disciplina : disciplinasParaTrancar) {
        if (disciplinasAtivas.contains(disciplina)) {
            disciplinasAtivas.remove(disciplina);
            System.out.println("Disciplina trancada: " + disciplina.getNome());
        } else {
            System.out.println("A disciplina " + disciplina.getNome() + " não está ativa para o aluno " + this.nome);
        }
    }
}
	
	
	