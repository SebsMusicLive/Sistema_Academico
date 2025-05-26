package com.spring.sistemaacademico.controllers;

import com.spring.sistemaacademico.model.Curso;
import com.spring.sistemaacademico.model.Estudiante;
import com.spring.sistemaacademico.services.EstudianteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService estudianteService;

    /**
     * Obtiene la lista de todos los estudiantes
     */
    @GetMapping
    public ResponseEntity<List<Estudiante>> getAllEstudiantes() throws Exception {
        return ResponseEntity.ok(estudianteService.findAll());
    }

    /**
     * Obtiene un estudiante por su ID
     */
    @GetMapping("/{codigoEstudiante}")
    public ResponseEntity<Estudiante> getEstudianteById(@PathVariable Long codigoEstudiante) throws Exception {
        return estudianteService.findById(codigoEstudiante)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo estudiante
     */
    @PostMapping
    public ResponseEntity<Estudiante> createEstudiante(@Valid @RequestBody Estudiante estudiante) throws Exception {
        Estudiante creado = estudianteService.save(estudiante);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    /**
     * Actualiza un estudiante existente
     */
    @PutMapping("/{codigoEstudiante}")
    public ResponseEntity<Estudiante> updateEstudiante(@PathVariable Long codigoEstudiante,
                                                       @Valid @RequestBody Estudiante estudiante) throws Exception {
        if (!estudianteService.findById(codigoEstudiante).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        estudiante.setCodigoEstudiante(codigoEstudiante);
        Estudiante actualizado = estudianteService.update(estudiante);
        return ResponseEntity.ok(actualizado);
    }

    /**
     * Elimina un estudiante por ID
     */
    @DeleteMapping("/{codigoEstudiante}")
    public ResponseEntity<Void> deleteEstudiante(@PathVariable Long codigoEstudiante) throws Exception {
        if (!estudianteService.findById(codigoEstudiante).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        estudianteService.deleteById(codigoEstudiante);
        return ResponseEntity.noContent().build();
    }

    /**
     * Elimina todos los estudiantes
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteAllEstudiantes() throws Exception {
        estudianteService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    /**
     * Busca estudiantes por nombre
     */
    @GetMapping("/buscar/nombre")
    public ResponseEntity<List<Estudiante>> getEstudiantesByNombre(@RequestParam String nombre) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(estudianteService.findByNombre(nombre));
    }

    /**
     * Busca estudiantes por correo
     */
    @GetMapping("/buscar/correo")
    public ResponseEntity<List<Estudiante>> getEstudiantesByCorreo(@RequestParam String correo) throws Exception {
        if (correo == null || correo.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(estudianteService.findByCorreo(correo));
    }
    /**
     * Inscribe a un estudiante en un curso
     */
    @PostMapping("/{id}/inscribir")
    public ResponseEntity<Void> inscribirCurso(@PathVariable Long id, @RequestBody Curso curso) {
        try {
            estudianteService.inscribirCurso(id, curso);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Cancela la inscripción de un estudiante en un curso
     */
    @PostMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelarInscripcion(@PathVariable Long id, @RequestBody Curso curso) {
        try {
            estudianteService.cancelarInscripcion(id, curso);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Consulta el historial académico de un estudiante
     */
    @GetMapping("/{id}/historial")
    public ResponseEntity<List<Curso>> consultarHistorial(@PathVariable Long id) {
        try {
            List<Curso> historial = estudianteService.consultarHistorialAcademico(id);
            return ResponseEntity.ok(historial);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}