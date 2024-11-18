package com.hospital.service;

import com.hospital.dto.AtendimentoDTO;
import com.hospital.mapper.AtendimentoMapper;
import com.hospital.model.Atendimento;
import com.hospital.repository.AtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtendimentoService {
    @Autowired
    private AtendimentoRepository repository;

    public ResponseEntity<?> getAll() {
        List<AtendimentoDTO> atendimentos = repository.findAll().stream().map(AtendimentoMapper::toDTO).toList();

        if (atendimentos.isEmpty()) {
            return new ResponseEntity<>("Não há atendimentos listados", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(atendimentos, HttpStatus.OK);
    }

    public ResponseEntity<?> getById(Long id) {
        Optional<Atendimento> atendimentoOpt = repository.findById(id);

        if (atendimentoOpt.isEmpty()) {
            return new ResponseEntity<>("Atendimento não encontrado", HttpStatus.NOT_FOUND);
        }

        AtendimentoDTO atendimento = AtendimentoMapper.toDTO(atendimentoOpt.get());

        return new ResponseEntity<>(atendimento, HttpStatus.OK);
    }

    public ResponseEntity<?> create(AtendimentoDTO dto) {
        if (dto.paciente().isBlank()) {
            return new ResponseEntity<>("Paciente inválido", HttpStatus.BAD_REQUEST);
        } else if (dto.medico().isBlank()) {
            return new ResponseEntity<>("Médico inválido", HttpStatus.BAD_REQUEST);
        } else if (dto.data().isBlank()) {
            return new ResponseEntity<>("Data inválida", HttpStatus.BAD_REQUEST);
        }

        Atendimento atendimento = AtendimentoMapper.toModel(dto);
        AtendimentoDTO savedAtendimento = AtendimentoMapper.toDTO(repository.save(atendimento));

        return new ResponseEntity<>(savedAtendimento, HttpStatus.CREATED);
    }

    public ResponseEntity<?> edit(AtendimentoDTO dto) {
        Optional<Atendimento> atendimento = repository.findById(dto.id());

        if (atendimento.isEmpty()) {
            return new ResponseEntity<>("Atendimento não encontrado", HttpStatus.NOT_FOUND);
        } else if (dto.paciente().isBlank()) {
            return new ResponseEntity<>("Paciente inválido", HttpStatus.BAD_REQUEST);
        } else if (dto.medico().isBlank()) {
            return new ResponseEntity<>("Médico inválido", HttpStatus.BAD_REQUEST);
        } else if (dto.data().isBlank()) {
            return new ResponseEntity<>("Data inválida", HttpStatus.BAD_REQUEST);
        }

        Atendimento model = AtendimentoMapper.toModel(dto);
        AtendimentoDTO savedAtendimento = AtendimentoMapper.toDTO(repository.save(model));

        return new ResponseEntity<>(savedAtendimento, HttpStatus.OK);
    }

    public ResponseEntity<?> delete(Long id) {
        Optional<Atendimento> atendimento = repository.findById(id);

        if (atendimento.isEmpty()) {
            return new ResponseEntity<>("Atendimento não encontrado", HttpStatus.NOT_FOUND);
        }

        repository.deleteById(id);

        return new ResponseEntity<>("Atendimento deletado", HttpStatus.OK);
    }
}