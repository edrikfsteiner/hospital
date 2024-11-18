package com.hospital.dto;

import java.util.List;

public record ReceitaDTO(Long id, Long atendimento, List<Long> medicamentos, String instrucoes) {}