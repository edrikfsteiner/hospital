package com.hospital.controller;

import com.hospital.dto.AtendimentoDTO;
import com.hospital.service.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atendimentos")
public class AtendimentoController {
    @Autowired
    AtendimentoService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AtendimentoDTO dto) {
        return service.create(dto);
    }

    @PutMapping
    public ResponseEntity<?> edit(@RequestBody AtendimentoDTO dto) {
        return service.edit(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}