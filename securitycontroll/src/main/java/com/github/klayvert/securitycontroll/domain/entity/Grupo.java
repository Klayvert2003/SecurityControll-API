package com.github.klayvert.securitycontroll.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nome;
}
