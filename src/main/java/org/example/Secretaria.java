package org.example;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.UUID;

public class Secretaria extends Usuario{
    private static final int ALUNOSMINIMOS = 3;
    private static final int MAXIMOPORDISCIPLINA = 60;

    private void cadastrarAlunoDisciplina(Universidade universidade, String idAluno, String idDisciplina){
        for (Aluno aluno : universidade.getAlunos()){
            if (aluno.getId().equals(idAluno)){
                for (Disciplinas disciplinas : aluno.getCurso().getGrade()){
                    if (disciplinas.getId().equals(idDisciplina) && disciplinas.alunosInscritos() < MAXIMOPORDISCIPLINA &&
                    disciplinas.getStatus().equals("ATIVO")){
                        aluno.getDisciplinasAtivas().add(disciplinas);
                        disciplinas.getAlunos().add(aluno);
                    }
                }
            }
        }
    }

    private void cadastrarProfDisciplina(Universidade universidade, String idProf, String idDisciplina){
        for (Professor professor : universidade.getProfessores()){
            if (professor.getId().equals(idProf)){
                for (Disciplinas disciplinas : professor.getTurmas()){
                    if (disciplinas.getId().equals(idDisciplina)){
                        professor.getTurmas().add(disciplinas);
                        disciplinas.getProfessores().add(professor);
                    }
                }
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

    private void trancarDisciplina(Universidade universidade, UUID idDisciplina){
        for (Curso cursos : universidade.getCursos()){
            for (Disciplinas disciplinas : cursos.getGrade()){
                if(disciplinas.alunosInscritos() < ALUNOSMINIMOS) {
                    disciplinas.setStatus("INATIVA");
                }
            }
        }
    }

    private void cadastrarProfessor(Universidade universidade, String nome, String senha){
        universidade.getProfessores().add(new Professor(nome, senha));
    }

    private void editaProfessor(Universidade universidade, UUID id, String dado, int mudanca){
        for (Professor professor : universidade.getProfessores()) {
            if (professor.getId().equals(id)) {
                try {
                    if (mudanca == 1) {
                        professor.setNome(dado);
                    } else if (mudanca == 2) {
                        professor.setSenha(dado);
                    }
                } catch (InvalidParameterException e) {
                    e.printStackTrace();
                    System.out.println("FORNEÇA UM PARAMETRO VÁLIDO");
                }
            }
        }
    }

    private void cadastrarAluno(Universidade universidade, String curso, String nome, String senha){
        for (Curso cursos : universidade.getCursos()){
            if(cursos.getNome().equals(curso)){
                universidade.getAlunos().add(new Aluno(nome, cursos, senha));
            }
        }
    }

    private void editaAluno(Universidade universidade, UUID id, String dado, int mudanca){
        for (Aluno aluno : universidade.getAlunos()) {
            if (aluno.getId().equals(id)) {
                try {
                    if (mudanca == 1) {
                        aluno.setNome(dado);
                    } else if (mudanca == 2) {
                        aluno.setSenha(dado);
                    }
                } catch (InvalidParameterException e) {
                    e.printStackTrace();
                    System.out.println("FORNEÇA UM PARAMETRO VÁLIDO");
                }
            }
        }
    }

    private void geraCurriculo(Universidade universidade, String curso){
        for (Curso cursos : universidade.getCursos()){
            if (cursos.getNome().equals(curso)){
               for (Disciplinas disciplinas : cursos.getGrade()){
                   System.out.println("+" + "-".repeat(30) + "+");
                   System.out.println("| Nome: " + disciplinas.getNome() + " ".repeat(24 - disciplinas.getNome().length()) + "|");
                   System.out.println("| Horas: " + disciplinas.getHoras() + " ".repeat(23 - String.valueOf(disciplinas.getHoras()).length()) + "|");
                   System.out.println("| Periodo: " + disciplinas.getPeriodo() + " periodo" + " ".repeat(Integer.parseInt(19 - disciplinas.getPeriodo() + "|")));
                   System.out.println("+" + "-".repeat(30) + "+");
               }
            }
        }
    }

    private void cadastrarCurso(Universidade universidade, String nome, int numeroCreditos){
        universidade.getCursos().add(new Curso(nome, numeroCreditos));
    }

   private void cadastrarPagamentoInadimplencia(Universidade universidade, String nome, boolean pagou){
       for (Aluno alunos : universidade.getAlunos()){
           if (alunos.getNome().equals(nome)){
               alunos.setInadimplente(pagou);
           }
       }
   }

    private void verificaInadimplentes(Universidade universidade){
        System.out.println("Alunos inadimplentes: ");
        for (Aluno alunos : universidade.getAlunos()){
            if (alunos.isInadimplente()){
                System.out.println(alunos.getNome() + ", ");
            }
        }
    }




}
