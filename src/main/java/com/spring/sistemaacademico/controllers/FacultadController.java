package com.spring.sistemaacademico.controllers;

import com.spring.sistemaacademico.model.Facultad;
import com.spring.sistemaacademico.services.FacultadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facultades")
@RequiredArgsConstructor
public class FacultadController {

    private final FacultadService facultadService;

    @GetMapping
    public ResponseEntity<List<Facultad>> findAll() throws Exception {
        return ResponseEntity.ok(facultadService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Facultad> findById(@PathVariable Long id) throws Exception {
        return facultadService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Facultad> save(@RequestBody Facultad facultad) throws Exception {
        return ResponseEntity.status(201).body(facultadService.save(facultad));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Facultad> update(@PathVariable Long id, @RequestBody Facultad facultad) throws Exception {
        facultad.setCodigoFacultad(id);
        return ResponseEntity.ok(facultadService.update(facultad));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws Exception {
        facultadService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Facultad>> findByNombre(@PathVariable String nombre) {
        List<Facultad> facultades = facultadService.findByNombre(nombre);
        return facultades.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(facultades);
    }
}

