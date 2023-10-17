package com.example.msclientes.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String nome;
    private String idade;

    public Cliente(String cpf, String nome, String idade) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
    }
}