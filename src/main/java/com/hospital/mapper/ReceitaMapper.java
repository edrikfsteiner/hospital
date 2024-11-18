package com.hospital.mapper;

import com.hospital.dto.ReceitaDTO;
import com.hospital.model.Atendimento;
import com.hospital.model.Medicamento;
import com.hospital.model.Receita;

import java.util.List;

public class ReceitaMapper {
    public static ReceitaDTO toDTO(Receita model) {
        return new ReceitaDTO(
                model.getId(),
                model.getAtendimento() != null ? model.getAtendimento().getId() : null,
                model.getMedicamentos().stream().map(Medicamento::getId).toList(),
                model.getInstrucoes()
        );
    }

    public static Receita toModel(ReceitaDTO dto) {
        Receita model = new Receita();
        Atendimento atendimento = new Atendimento();
        List<Medicamento> medicamentos = dto.medicamentos()
                .stream()
                .map(id -> {
                    Medicamento medicamento = new Medicamento();
                    medicamento.setId(id);
                    return medicamento;
                })
                .toList();
        atendimento.setId(dto.atendimento());
        model.setId(dto.id());
        model.setAtendimento(atendimento);
        model.setMedicamentos(medicamentos);
        model.setInstrucoes(dto.instrucoes());

        return model;
    }
}