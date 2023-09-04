package org.example;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.UUID;

public class Secretaria extends Usuario{
    private static final int ALUNOSMINIMOS = 3;
    private static final int MAXIMOPORDISCIPLINA = 60;
    /**
     * Método privado para cadastrar um aluno em uma disciplina.
     *
     * @param universidade A universidade onde a operação está ocorrendo.
     * @param idAluno      O ID do aluno a ser cadastrado.
     * @param idDisciplina O ID da disciplina em que o aluno será cadastrado.
     */
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
    /**
     * Método privado para cadastrar um professor em uma disciplina.
     *
     * @param universidade A universidade onde a operação está ocorrendo.
     * @param idProf       O ID do professor a ser cadastrado.
     * @param idDisciplina O ID da disciplina em que o professor será cadastrado.
     */
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
    /**
     * Método privado para cadastrar uma disciplina em um curso.
     *
     * @param universidade A universidade onde a operação está ocorrendo.
     * @param nome         O nome da disciplina a ser cadastrada.
     * @param curso        O nome do curso ao qual a disciplina será associada.
     * @param periodo      O período da disciplina.
     * @param horas        O número de horas da disciplina.
     */
    private void cadastrarDisciplina(Universidade universidade, String nome, String curso, int periodo, int horas){
        for (Curso cursos : universidade.getCursos()){
            if(cursos.getNome().equals(curso)){
                cursos.getGrade().add(new Disciplinas(nome, periodo, horas));
            }
        }
    }
    /**
     * Método privado para trancar uma disciplina se o número de alunos inscritos for menor que o mínimo.
     *
     * @param universidade   A universidade onde a operação está ocorrendo.
     * @param idDisciplina   O ID da disciplina a ser verificada e possivelmente trancada.
     */
    private void trancarDisciplina(Universidade universidade, UUID idDisciplina){
        for (Curso cursos : universidade.getCursos()){
            for (Disciplinas disciplinas : cursos.getGrade()){
                if(disciplinas.alunosInscritos() < ALUNOSMINIMOS) {
                    disciplinas.setStatus("INATIVA");
                }
            }
        }
    }
    /**
     * Método privado para cadastrar um professor na universidade.
     *
     * @param universidade A universidade onde a operação está ocorrendo.
     * @param nome         O nome do professor a ser cadastrado.
     * @param senha        A senha do professor.
     */
    private void cadastrarProfessor(Universidade universidade, String nome, String senha){
        universidade.getProfessores().add(new Professor(nome, senha));
    }
    /**
     * Método privado para editar informações de um professor.
     *
     * @param universidade A universidade onde a operação está ocorrendo.
     * @param id           O ID do professor a ser editado.
     * @param dado         A informação a ser editada (nome ou senha).
     * @param mudanca      O tipo de mudança a ser realizada (1 para nome, 2 para senha).
     */
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
    /**
     * Método privado para cadastrar um aluno na universidade.
     *
     * @param universidade A universidade onde a operação está ocorrendo.
     * @param curso        O nome do curso ao qual o aluno será associado.
     * @param nome         O nome do aluno a ser cadastrado.
     * @param senha        A senha do aluno.
     */
    private void cadastrarAluno(Universidade universidade, String curso, String nome, String senha){
        for (Curso cursos : universidade.getCursos()){
            if(cursos.getNome().equals(curso)){
                universidade.getAlunos().add(new Aluno(nome, cursos, senha));
            }
        }
    }
    /**
     * Método privado para editar informações de um aluno.
     *
     * @param universidade A universidade onde a operação está ocorrendo.
     * @param id           O ID do aluno a ser editado.
     * @param dado         A informação a ser editada (nome ou senha).
     * @param mudanca      O tipo de mudança a ser realizada (1 para nome, 2 para senha).
     */
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
    /**
     * Método privado para gerar o currículo de um curso.
     *
     * @param universidade A universidade onde a operação está ocorrendo.
     * @param curso        O nome do curso para o qual o currículo será gerado.
     */
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
  /**
     * Método privado para cadastrar um curso na universidade.
     *
     * @param universidade   A universidade onde a operação está ocorrendo.
     * @param nome           O nome do curso a ser cadastrado.
     * @param numeroCreditos O número de créditos do curso.
     */
    private void cadastrarCurso(Universidade universidade, String nome, int numeroCreditos){
        universidade.getCursos().add(new Curso(nome, numeroCreditos));
    }
    /**
     * Método privado para cadastrar o pagamento ou inadimplência de um aluno.
     *
     * @param universidade A universidade onde a operação está ocorrendo.
     * @param nome         O nome do aluno para o qual o pagamento/inadimplência será cadastrado.
     * @param pagou        Indica se o aluno pagou (true) ou está inadimplente (false).
     */
   private void cadastrarPagamentoInadimplencia(Universidade universidade, String nome, boolean pagou){
       for (Aluno alunos : universidade.getAlunos()){
           if (alunos.getNome().equals(nome)){
               alunos.setInadimplente(pagou);
           }
       }
   }
    /**
     * Método privado para verificar e exibir alunos inadimplentes.
     *
     * @param universidade A universidade onde a operação está ocorrendo.
     */
    private void verificaInadimplentes(Universidade universidade){
        System.out.println("Alunos inadimplentes: ");
        for (Aluno alunos : universidade.getAlunos()){
            if (alunos.isInadimplente()){
                System.out.println(alunos.getNome() + ", ");
            }
        }
    }




}
