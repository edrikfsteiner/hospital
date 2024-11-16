package com.hospital.dto;

import lombok.Data;

@Data
public class AtendimentoDTO {
    private Long id;
    private Long receita;
    private String paciente;
    private String medico;
    private String data;
}