package com.spartans.dev.gym_hub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Double altura;

    @Column(nullable = false)
    private Double massaCorporal;

    @Column(nullable = false)
    private Date nascimento;

    @Column(nullable = false)
    private String user;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private Date dataCadastro;

    @Column(nullable = false)
    private String imc;

    @Column(nullable = false)
    private String cpf;


    public Aluno(String nome, Double altura, Double massaCorporal, Date nascimento, String user, String senha, Date dataCadastro, String imc, String cpf) {
        this.nome = nome;
        this.altura = altura;
        this.massaCorporal = massaCorporal;
        this.nascimento = nascimento;
        this.user = user;
        this.senha = senha;
        this.dataCadastro = dataCadastro;
        this.imc = imc;
        this.cpf = cpf;
    }
}
