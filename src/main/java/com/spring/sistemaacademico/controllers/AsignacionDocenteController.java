package com.spring.sistemaacademico.controllers;

import com.spring.sistemaacademico.model.AsignacionDocente;
import com.spring.sistemaacademico.services.AsignacionDocenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asignaciones")
@RequiredArgsConstructor
public class AsignacionDocenteController {

    private final AsignacionDocenteService service;

    /**
     * Obtiene todas las asignaciones de docentes.
     */
    @GetMapping
    public ResponseEntity<List<AsignacionDocente>> findAll() {
        try {
            List<AsignacionDocente> asignaciones = service.findAll();
            return ResponseEntity.ok(asignaciones);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Error interno del servidor
        }
    }

    /**
     * Obtiene una asignación de docente por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AsignacionDocente> findById(@PathVariable Long id) {
        Optional<AsignacionDocente> result = service.findById(id);
        return result.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // Devuelve 404 si no se encuentra
    }

    /**
     * Crea una nueva asignación de docente.
     */
    @PostMapping
    public ResponseEntity<AsignacionDocente> save(@RequestBody AsignacionDocente asignacionDocente) {
        try {
            AsignacionDocente created = service.save(asignacionDocente);
            return ResponseEntity.status(201).body(created); // Devuelve 201 cuando se crea exitosamente
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null); // Si ocurre un error con la creación
        }
    }

    /**
     * Actualiza una asignación de docente existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AsignacionDocente> update(@PathVariable Long id, @RequestBody AsignacionDocente asignacionDocente) {
        Optional<AsignacionDocente> existing = service.findById(id);
        if (existing.isPresent()) {
            asignacionDocente.setCodigoAsignacionDocente(id); // Aseguramos que el ID sea el correcto
            return ResponseEntity.ok(service.update(asignacionDocente)); // Devuelve la asignación actualizada
        }
        return ResponseEntity.notFound().build(); // Si no se encuentra el ID, devolvemos 404
    }

    /**
     * Elimina una asignación de docente por su ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<AsignacionDocente> existing = service.findById(id);
        if (existing.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build(); // Devuelve 204 si se elimina correctamente
        }
        return ResponseEntity.notFound().build(); // Si no se encuentra el ID, devolvemos 404
    }

    /**
     * Filtra asignaciones de docentes por fecha.
     */
    @GetMapping("/fecha")
    public ResponseEntity<List<AsignacionDocente>> findByFecha(@RequestParam Date fecha) {
        List<AsignacionDocente> result = service.findByFecha(fecha);
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result); // 204 si está vacío
    }

    /**
     * Filtra asignaciones de docentes por carga horaria.
     */
    @GetMapping("/carga-horaria/{cargaHoraria}")
    public ResponseEntity<List<AsignacionDocente>> findByCargaHoraria(@PathVariable int cargaHoraria) {
        List<AsignacionDocente> result = service.findByCargaHoraria(cargaHoraria);
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result); // 204 si está vacío
    }
}
