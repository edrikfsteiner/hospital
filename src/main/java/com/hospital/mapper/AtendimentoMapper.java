package com.hospital.mapper;

import com.hospital.dto.AtendimentoDTO;
import com.hospital.model.Atendimento;
import com.hospital.model.Receita;

public class AtendimentoMapper {
    public static AtendimentoDTO toDTO(Atendimento model) {
        AtendimentoDTO dto = new AtendimentoDTO();
        dto.setId(model.getId());
        dto.setPaciente(model.getPaciente());
        dto.setMedico(model.getMedico());
        dto.setData(model.getData());
        dto.setReceita(model.getReceita() != null ? model.getReceita().getId() : null);

        return dto;
    }

    public static Atendimento toModel(AtendimentoDTO dto) {
        Atendimento model = new Atendimento();
        Receita receita = new Receita();
        receita.setId(dto.getReceita());
        model.setId(dto.getId());
        model.setReceita(receita);
        model.setPaciente(dto.getPaciente());
        model.setMedico(dto.getMedico());
        model.setData(dto.getData());

        return model;
    }
}