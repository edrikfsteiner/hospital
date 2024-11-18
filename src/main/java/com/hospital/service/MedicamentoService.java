package com.hospital.service;

import com.hospital.dto.MedicamentoDTO;
import com.hospital.mapper.MedicamentoMapper;
import com.hospital.model.Medicamento;
import com.hospital.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoService {
    @Autowired
    private MedicamentoRepository repository;

    public ResponseEntity<?> getAll() {
        List<MedicamentoDTO> medicamentos = repository.findAll().stream().map(MedicamentoMapper::toDTO).toList();

        if (medicamentos.isEmpty()) {
            return new ResponseEntity<>("Não há medicamentos listados", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(medicamentos, HttpStatus.OK);
    }

    public ResponseEntity<?> getById(Long id) {
        Optional<Medicamento> medicamento = repository.findById(id);

        if (medicamento.isEmpty()) {
            return new ResponseEntity<>("Medicamento não encontrado", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(medicamento, HttpStatus.OK);
    }

    public ResponseEntity<?> create(MedicamentoDTO dto) {
        if (dto.nome().isBlank()) {
            return new ResponseEntity<>("Nome inválido", HttpStatus.BAD_REQUEST);
        } else if (dto.dosagem().isBlank()) {
            return new ResponseEntity<>("Dosagem inválida", HttpStatus.BAD_REQUEST);
        }

        Medicamento medicamento = MedicamentoMapper.toModel(dto);
        MedicamentoDTO savedMedicamento = MedicamentoMapper.toDTO(repository.save(medicamento));

        return new ResponseEntity<>(savedMedicamento, HttpStatus.CREATED);
    }

    public ResponseEntity<?> edit(MedicamentoDTO dto) {
        Optional<Medicamento> medicamento = repository.findById(dto.id());

        if (medicamento.isEmpty()) {
            return new ResponseEntity<>("Medicamento não encontrado", HttpStatus.NOT_FOUND);
        } else if (dto.nome().isBlank()) {
            return new ResponseEntity<>("Nome inválido", HttpStatus.BAD_REQUEST);
        } else if (dto.dosagem().isBlank()) {
            return new ResponseEntity<>("Dosagem inválida", HttpStatus.BAD_REQUEST);
        }

        Medicamento model = MedicamentoMapper.toModel(dto);
        MedicamentoDTO savedMedicamento = MedicamentoMapper.toDTO(repository.save(model));

        return new ResponseEntity<>(savedMedicamento, HttpStatus.OK);
    }

    public ResponseEntity<?> delete(Long id) {
        Optional<Medicamento> medicamento = repository.findById(id);

        if (medicamento.isEmpty()) {
            return new ResponseEntity<>("Medicamento não encontrado", HttpStatus.NOT_FOUND);
        }

        repository.deleteById(id);

        return new ResponseEntity<>("Medicamento deletado", HttpStatus.OK);
    }
}