package com.spring.sistemaacademico.controllers;

import com.spring.sistemaacademico.model.CursoHistorial;
import com.spring.sistemaacademico.model.HistorialAcademico;
import com.spring.sistemaacademico.services.HistorialAcademicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/historiales")
@RequiredArgsConstructor
public class HistorialAcademicoController {

    private final HistorialAcademicoService historialService;

    @GetMapping
    public ResponseEntity<List<HistorialAcademico>> getAll() throws Exception {
        return ResponseEntity.ok(historialService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialAcademico> getById(@PathVariable Long id) throws Exception {
        return historialService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HistorialAcademico> save(@RequestBody HistorialAcademico historial) throws Exception {
        return ResponseEntity.status(201).body(historialService.save(historial));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistorialAcademico> update(@PathVariable Long id, @RequestBody HistorialAcademico historial) throws Exception {
        Optional<HistorialAcademico> existing = historialService.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        historial.setCodigoHistorialAcademico(id);
        return ResponseEntity.ok(historialService.update(historial));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        if (historialService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        historialService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() throws Exception {
        historialService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/promedio/{promedio}")
    public ResponseEntity<List<HistorialAcademico>> getByPromedioGeneral(@PathVariable float promedio) throws Exception {
        List<HistorialAcademico> historiales = historialService.findByPromedioGeneral(promedio);
        return historiales.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(historiales);
    }

    // Funciones personalizadas
    @GetMapping("/{id}/reporte-desempeno")
    public ResponseEntity<String> generarReporteDesempeno(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(historialService.generarReporteDesempeno(id));
    }

    @GetMapping("/{id}/proyeccion-rendimiento")
    public ResponseEntity<Float> calcularProyeccionRendimiento(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(historialService.calcularProyeccionRendimiento(id));
    }

    @GetMapping("/{id}/cursos-aprobados")
    public ResponseEntity<List<CursoHistorial>> getCursosAprobados(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(historialService.generarReporteCursosAprobados(id));
    }

    @GetMapping("/{id}/cursos-en-proceso")
    public ResponseEntity<List<CursoHistorial>> getCursosEnProceso(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(historialService.generarReporteCursosEnProceso(id));
    }

    @PostMapping("/{id}/agregar-curso")
    public ResponseEntity<HistorialAcademico> agregarCurso(@PathVariable Long id, @RequestBody CursoHistorial curso) {
        try {
            return ResponseEntity.ok(historialService.agregarCursoHistorial(id, curso));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/cursos")
    public ResponseEntity<List<CursoHistorial>> consultarHistorial(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(historialService.consultarHistorial(id));
    }
}
