package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Data
public class Professor extends Usuario{

    private String nome;
    private List<Disciplinas> turmas;

    public Professor(String nome, String senha) {
        this.nome = nome;
        this.setSenha(senha);
        this.setId(UUID.randomUUID());
    }
}
