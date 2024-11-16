package com.hospital.mapper;

import com.hospital.dto.ReceitaDTO;
import com.hospital.model.Atendimento;
import com.hospital.model.Medicamento;
import com.hospital.model.Receita;

import java.util.List;

public class ReceitaMapper {
    public static ReceitaDTO toDTO(Receita model) {
        ReceitaDTO dto = new ReceitaDTO();
        dto.setId(model.getId());
        dto.setAtendimento(
                model.getAtendimento() != null ? model.getAtendimento().getId() : null
        );
        dto.setMedicamentos(model.getMedicamentos().stream().map(Medicamento::getId).toList());
        dto.setInstrucoes(model.getInstrucoes());

        return dto;
    }

    public static Receita toModel(ReceitaDTO dto) {
        Receita model = new Receita();
        Atendimento atendimento = new Atendimento();
        List<Medicamento> medicamentos = dto.getMedicamentos()
                .stream()
                .map(id -> {
                    Medicamento medicamento = new Medicamento();
                    medicamento.setId(id);
                    return medicamento;
                })
                .toList();
        atendimento.setId(dto.getAtendimento());
        model.setId(dto.getId());
        model.setAtendimento(atendimento);
        model.setMedicamentos(medicamentos);
        model.setInstrucoes(dto.getInstrucoes());

        return model;
    }
}