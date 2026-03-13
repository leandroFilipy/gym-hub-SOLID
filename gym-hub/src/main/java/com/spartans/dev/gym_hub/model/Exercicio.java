package com.spartans.dev.gym_hub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "exercicios")
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String videoURL;

    @Column(nullable = false)
    private int series;

    @Column(nullable = false)
    private int repeticoes;

    @Column(nullable = false)
    private String musculoAlvo;

    public Exercicio(String nome, String descricao, String videoURL, int series, int repeticoes, String musculoAlvo) {
        this.nome = nome;
        this.descricao = descricao;
        this.videoURL = videoURL;
        this.series = series;
        this.repeticoes = repeticoes;
        this.musculoAlvo = musculoAlvo;
    }
}