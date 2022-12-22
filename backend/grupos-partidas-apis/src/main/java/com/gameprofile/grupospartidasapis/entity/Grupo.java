package com.gameprofile.grupospartidasapis.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity(name = "grupos")
@Data
public class Grupo {
    @Id
    @Column(name = "id_grupo")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idGrupo;

    @Column(name = "nome_grupo")
    @NotNull(message="{NotNull.Task.nomeGrupo}")
    private String nomeGrupo;

    @Column
    private Integer bloqueado;

    @Column
    @NotNull(message="{NotNull.Task.criador}")
    private Integer criador;
}