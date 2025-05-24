package com.spring.sistemaacademico.controllers;

import com.spring.sistemaacademico.model.Foro;
import com.spring.sistemaacademico.services.ForoService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/foros")
@RequiredArgsConstructor
public class ForoController {

    private final ForoService foroService;

    @GetMapping
    public ResponseEntity<List<Foro>> getAll() throws Exception {
        return ResponseEntity.ok(foroService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Foro> getById(@PathVariable Long id) throws Exception {
        return foroService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Foro> save(@RequestBody Foro foro) throws Exception {
        return ResponseEntity.status(201).body(foroService.save(foro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Foro> update(@PathVariable Long id, @RequestBody Foro foroActualizado) throws Exception {
        if (foroService.findById(id).isPresent()) {
            foroActualizado.setCodigoForo(id);
            return ResponseEntity.ok(foroService.update(foroActualizado));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        if (foroService.findById(id).isPresent()) {
            foroService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() throws Exception {
        foroService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Foro>> getByTitulo(@PathVariable String titulo) throws Exception {
        List<Foro> foros = foroService.findByTitulo(titulo);
        return foros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(foros);
    }

    @GetMapping("/descripcion/{descripcion}")
    public ResponseEntity<List<Foro>> getByDescripcion(@PathVariable String descripcion) throws Exception {
        List<Foro> foros = foroService.findByDescripcion(descripcion);
        return foros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(foros);
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<Foro>> getByFechaCreacion(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fecha) throws Exception {
        List<Foro> foros = foroService.findByFechaCreacion(fecha);
        return foros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(foros);
    }
}

