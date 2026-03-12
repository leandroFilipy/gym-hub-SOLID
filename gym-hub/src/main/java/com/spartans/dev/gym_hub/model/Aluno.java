package com.spartans.dev.gym_hub.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "aluno")
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "altura", nullable = false)
    private double altura;

    @Column(name = "massa_corporal", nullable = false)
    private double massaCorporal;

    @Column(name = "data_nascimento", nullable = false)
    private Date dataNascimento;

    @Column(name = "user",nullable = false)
    private String user;

    @Column(name = "senha",nullable = false)
    private String senha;

    @Column(name = "data_cadastro", nullable = false)
    private Date dataCadastro;

    @Column(name = "imc" ,nullable = false)
    private String imc;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    public Aluno(String nome, double altura, double massaCorporal, Date dataNascimento, String user, String senha, Date dataCadastro, String imc, String cpf) {
        this.nome = nome;
        this.altura = altura;
        this.massaCorporal = massaCorporal;
        this.dataNascimento = dataNascimento;
        this.user = user;
        this.senha = senha;
        this.dataCadastro = dataCadastro;
        this.imc = imc;
        this.cpf = cpf;
    }

}
