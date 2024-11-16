package com.hospital.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReceitaDTO {
    private Long id;
    private Long atendimento;
    private List<Long> medicamentos;
    private String instrucoes;
}