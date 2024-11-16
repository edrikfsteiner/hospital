package com.hospital.dto;

import lombok.Data;

@Data
public class MedicamentoDTO {
    private Long id;
    private String nome;
    private String dosagem;
}