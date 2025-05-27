package com.spring.sistemaacademico.controllers;

import com.spring.sistemaacademico.model.Calificacion;
import com.spring.sistemaacademico.services.CalificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/calificaciones")
@RequiredArgsConstructor
public class CalificacionController {

    private final CalificacionService calificacionService;

    // Obtener todas las calificaciones
    @GetMapping
    public ResponseEntity<List<Calificacion>> getAll() throws Exception {
        List<Calificacion> calificaciones = calificacionService.findAll();
        return ResponseEntity.ok(calificaciones); // Devuelve la lista con código 200
    }

    // Obtener calificación por ID
    @GetMapping("/{id}")
    public ResponseEntity<Calificacion> getById(@PathVariable Long id) throws Exception {
        Optional<Calificacion> calificacion = calificacionService.findById(id);
        return calificacion.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // 404 si no se encuentra
    }

    // Guardar una nueva calificación
    @PostMapping
    public ResponseEntity<Calificacion> save(@RequestBody Calificacion calificacion) throws Exception {
        Calificacion savedCalificacion = calificacionService.save(calificacion);
        return ResponseEntity.status(201).body(savedCalificacion); // 201 para creación exitosa
    }

    // Actualizar una calificación
    @PutMapping("/{id}")
    public ResponseEntity<Calificacion> update(@PathVariable Long id, @RequestBody Calificacion nuevaCalificacion) throws Exception {
        Optional<Calificacion> existente = calificacionService.findById(id);
        if (existente.isPresent()) {
            nuevaCalificacion.setCodigoCalificacion(id); // Se actualiza el ID
            return ResponseEntity.ok(calificacionService.update(nuevaCalificacion)); // 200 si es exitoso
        }
        return ResponseEntity.notFound().build(); // 404 si no se encuentra la calificación
    }

    // Eliminar calificación por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        if (calificacionService.findById(id).isPresent()) {
            calificacionService.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 si se elimina exitosamente
        }
        return ResponseEntity.notFound().build(); // 404 si no se encuentra
    }

    // Buscar calificaciones por nota
    @GetMapping("/nota/{nota}")
    public ResponseEntity<List<Calificacion>> findByNota(@PathVariable float nota) {
        List<Calificacion> calificaciones = calificacionService.findByNota(nota);
        return calificaciones.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(calificaciones); // 204 si no hay resultados
    }

    // Buscar calificaciones por estudiante ID
    @GetMapping("/estudiante/{codigoEstudiante}")
    public ResponseEntity<List<Calificacion>> findByEstudianteCodigoEstudiante(@PathVariable Long codigoEstudiante) {
        List<Calificacion> calificaciones = calificacionService.findByEstudianteCodigoEstudiante(codigoEstudiante);
        return calificaciones.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(calificaciones); // 204 si no hay resultados
    }
}
