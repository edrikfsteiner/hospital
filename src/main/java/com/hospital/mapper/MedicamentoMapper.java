package com.hospital.mapper;

import com.hospital.dto.MedicamentoDTO;
import com.hospital.model.Medicamento;

public class MedicamentoMapper {
    public static MedicamentoDTO toDTO(Medicamento model) {
        return new MedicamentoDTO(
                model.getId(),
                model.getNome(),
                model.getDosagem()
        );
    }

    public static Medicamento toModel(MedicamentoDTO dto) {
        Medicamento model = new Medicamento();
        model.setId(dto.id());
        model.setNome(dto.nome());
        model.setDosagem(dto.dosagem());

        return model;
    }
}