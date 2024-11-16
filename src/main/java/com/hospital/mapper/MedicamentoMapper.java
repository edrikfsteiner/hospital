package com.hospital.mapper;

import com.hospital.dto.MedicamentoDTO;
import com.hospital.model.Medicamento;

public class MedicamentoMapper {
    public static MedicamentoDTO toDTO(Medicamento model) {
        MedicamentoDTO dto = new MedicamentoDTO();
        dto.setId(model.getId());
        dto.setNome(model.getNome());
        dto.setDosagem(model.getDosagem());

        return dto;
    }

    public static Medicamento toModel(MedicamentoDTO dto) {
        Medicamento model = new Medicamento();
        model.setId(dto.getId());
        model.setNome(dto.getNome());
        model.setDosagem(dto.getDosagem());

        return model;
    }
}