package com.hospital.controller;

import com.hospital.dto.MedicamentoDTO;
import com.hospital.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {
    @Autowired
    MedicamentoService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody MedicamentoDTO dto) {
        return service.create(dto);
    }

    @PutMapping
    public ResponseEntity<?> edit(@RequestBody MedicamentoDTO dto) {
        return service.edit(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}