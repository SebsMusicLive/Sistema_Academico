package com.spring.sistemaacademico.controllers;

import com.spring.sistemaacademico.model.Matricula;
import com.spring.sistemaacademico.services.MatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matriculas")
@RequiredArgsConstructor
public class MatriculaController {

    private final MatriculaService matriculaService;

    @GetMapping
    public ResponseEntity<List<Matricula>> getAll() throws Exception {
        return ResponseEntity.ok(matriculaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> getById(@PathVariable Long id) throws Exception {
        Optional<Matricula> matricula = matriculaService.findById(id);
        return matricula.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Matricula> save(@RequestBody Matricula matricula) throws Exception {
        return ResponseEntity.status(201).body(matriculaService.save(matricula));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matricula> update(@PathVariable Long id, @RequestBody Matricula matricula) throws Exception {
        if (matriculaService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        matricula.setCodigoMatricula(id);
        return ResponseEntity.ok(matriculaService.update(matricula));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        if (matriculaService.findById(id).isPresent()) {
            matriculaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() throws Exception {
        matriculaService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
