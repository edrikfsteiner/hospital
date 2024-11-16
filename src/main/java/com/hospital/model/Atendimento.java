package com.hospital.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "atendimentos")
public class Atendimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "atendimento", cascade = CascadeType.ALL, orphanRemoval = true)
    private Receita receita;
    private String paciente;
    private String medico;
    private String data;
}