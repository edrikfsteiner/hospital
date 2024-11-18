package com.hospital.mapper;

import com.hospital.dto.AtendimentoDTO;
import com.hospital.model.Atendimento;
import com.hospital.model.Receita;

public class AtendimentoMapper {
    public static AtendimentoDTO toDTO(Atendimento model) {
        return new AtendimentoDTO(
                model.getId(),
                model.getReceita() != null ? model.getReceita().getId() : null,
                model.getPaciente(),
                model.getMedico(),
                model.getData()
        );
    }

    public static Atendimento toModel(AtendimentoDTO dto) {
        Atendimento model = new Atendimento();
        Receita receita = new Receita();
        receita.setId(dto.receita());
        model.setId(dto.id());
        model.setReceita(receita);
        model.setPaciente(dto.paciente());
        model.setMedico(dto.medico());
        model.setData(dto.data());

        return model;
    }
}