package com.spring.sistemaacademico.controllers;

import com.spring.sistemaacademico.model.Asistencia;
import com.spring.sistemaacademico.services.AsistenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/asistencias")
@RequiredArgsConstructor
public class AsistenciaController {

    private final AsistenciaService asistenciaService;

    @GetMapping
    public ResponseEntity<List<Asistencia>> getAll() throws Exception {
        List<Asistencia> asistencias = asistenciaService.findAll();
        return ResponseEntity.ok(asistencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asistencia> getById(@PathVariable Long id) throws Exception {
        return asistenciaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Asistencia> save(@RequestBody Asistencia asistencia) throws Exception {
        Asistencia savedAsistencia = asistenciaService.save(asistencia);
        return ResponseEntity.status(201).body(savedAsistencia); // Código 201 para creación exitosa
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        asistenciaService.deleteById(id);
        return ResponseEntity.noContent().build(); // Código 204 para éxito sin contenido
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<Asistencia>> getByFecha(@RequestParam("fecha") Date fecha) {
        List<Asistencia> result = asistenciaService.findByFechaAsistencia(fecha);
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }

    @GetMapping("/estado")
    public ResponseEntity<List<Asistencia>> getByEstado(@RequestParam("asistencia") boolean asistencia) {
        List<Asistencia> result = asistenciaService.findByAsistencia(asistencia);
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }


}
