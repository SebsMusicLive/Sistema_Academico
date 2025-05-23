package com.spring.sistemaacademico.controllers;

import com.spring.sistemaacademico.model.Espacio;
import com.spring.sistemaacademico.services.EspacioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/espacios")
@RequiredArgsConstructor
public class EspacioController {
    private final EspacioService espacioService;

    @GetMapping
    public List<Espacio> getAll() throws Exception {
        return espacioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Espacio> getById(@PathVariable Long id) throws Exception {
        Optional<Espacio> espacio = espacioService.findById(id);
        return espacio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Espacio save(@RequestBody Espacio espacio) throws Exception {
        return espacioService.save(espacio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Espacio> update(@PathVariable Long id, @RequestBody Espacio nuevoEspacio) throws Exception {
        Optional<Espacio> existente = espacioService.findById(id);
        if (existente.isPresent()) {
            nuevoEspacio.setCodigoEspacio(id); // Asignación del ID usando el setter correcto
            return ResponseEntity.ok(espacioService.update(nuevoEspacio));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        if (espacioService.findById(id).isPresent()) {
            espacioService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
