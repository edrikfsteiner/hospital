package com.hospital.service;

import com.hospital.dto.ReceitaDTO;
import com.hospital.mapper.ReceitaMapper;
import com.hospital.model.Atendimento;
import com.hospital.model.Medicamento;
import com.hospital.model.Receita;
import com.hospital.repository.AtendimentoRepository;
import com.hospital.repository.MedicamentoRepository;
import com.hospital.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceitaService {
    @Autowired
    AtendimentoRepository atendimentoRepository;
    @Autowired
    MedicamentoRepository medicamentoRepository;
    @Autowired
    private ReceitaRepository repository;

    public ResponseEntity<?> getAll() {
        List<ReceitaDTO> receitas = repository.findAll().stream().map(ReceitaMapper::toDTO).toList();

        if (receitas.isEmpty()) {
            return new ResponseEntity<>("Não há receitas listadas", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(receitas, HttpStatus.OK);
    }

    public ResponseEntity<?> getById(Long id) {
        Optional<Receita> receitaOpt = repository.findById(id);

        if (receitaOpt.isEmpty()) {
            return new ResponseEntity<>("Receita não encontrada", HttpStatus.NOT_FOUND);
        }

        ReceitaDTO receita = ReceitaMapper.toDTO(receitaOpt.get());

        return new ResponseEntity<>(receita, HttpStatus.OK);
    }

    public ResponseEntity<?> create(ReceitaDTO dto) {
        Optional<Receita> receita = repository.findById(dto.getId());
        Optional<Atendimento> atendimento = atendimentoRepository.findById(dto.getAtendimento());
        List<Long> medicamentosId = dto.getMedicamentos();
        List<Medicamento> medicamentos = medicamentoRepository.findAllById(medicamentosId);

        if (receita.isEmpty()) {
            return new ResponseEntity<>("Receita não encontrada", HttpStatus.NOT_FOUND);
        } else if (atendimento.isEmpty()) {
            return new ResponseEntity<>("Atendimento inválido", HttpStatus.BAD_REQUEST);
        } else if (medicamentos.isEmpty()) {
            return new ResponseEntity<>("Medicamentos inválidos", HttpStatus.BAD_REQUEST);
        } else if(medicamentos.size() != medicamentosId.size()) {
            List<Long> idsInvalidos = medicamentosId.stream()
                    .filter(id -> medicamentos.stream()
                            .noneMatch(medicamento -> medicamento.getId().equals(id)))
                    .toList();
            String mensagem = String.format("Medicamentos inválidos: %s", idsInvalidos);

            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (dto.getInstrucoes().isBlank()) {
            return new ResponseEntity<>("Instruções inválidas", HttpStatus.BAD_REQUEST);
        }

        Receita model = ReceitaMapper.toModel(dto);
        ReceitaDTO savedReceita = ReceitaMapper.toDTO(repository.save(model));

        return new ResponseEntity<>(savedReceita, HttpStatus.CREATED);
    }

    public ResponseEntity<?> edit(ReceitaDTO dto) {
        Optional<Receita> receita = repository.findById(dto.getId());
        Optional<Atendimento> atendimento = atendimentoRepository.findById(dto.getAtendimento());
        List<Medicamento> medicamentos = medicamentoRepository.findAllById(dto.getMedicamentos());

        if (receita.isEmpty()) {
            return new ResponseEntity<>("Receita não encontrada", HttpStatus.NOT_FOUND);
        } else if (atendimento.isEmpty()) {
            return new ResponseEntity<>("Atendimento inválido", HttpStatus.BAD_REQUEST);
        } else if (medicamentos.isEmpty()) {
            return new ResponseEntity<>("Medicamentos inválidos", HttpStatus.BAD_REQUEST);
        } else if (dto.getInstrucoes().isBlank()) {
            return new ResponseEntity<>("Instruções inválidas", HttpStatus.BAD_REQUEST);
        }

        Receita model = ReceitaMapper.toModel(dto);
        ReceitaDTO savedReceita = ReceitaMapper.toDTO(repository.save(model));

        return new ResponseEntity<>(savedReceita, HttpStatus.OK);
    }

    public ResponseEntity<?> delete(Long id) {
        Optional<Receita> receitaOpt = repository.findById(id);

        if (receitaOpt.isEmpty()) {
            return new ResponseEntity<>("Receita não encontrada", HttpStatus.NOT_FOUND);
        }

        Atendimento atendimento = receitaOpt.get().getAtendimento();

        if (atendimento != null) {
            atendimento.setReceita(null);
            atendimentoRepository.save(atendimento);
        }

        repository.deleteById(id);

        return new ResponseEntity<>("Receita deletada", HttpStatus.OK);
    }
}