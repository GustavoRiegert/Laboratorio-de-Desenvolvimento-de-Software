package org.example;

import lombok.Data;

import java.util.UUID;

@Data
public abstract class Usuario {
    private UUID id;
    private String senha;
}
