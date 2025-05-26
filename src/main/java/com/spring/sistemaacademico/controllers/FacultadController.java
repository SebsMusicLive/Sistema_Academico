package com.spring.sistemaacademico.controllers;

import com.spring.sistemaacademico.model.Facultad;
import com.spring.sistemaacademico.services.FacultadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Optional<Facultad> facultad = facultadService.findById(id);
        return facultad.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Facultad> save(@RequestBody Facultad facultad) throws Exception {
        Facultad saved = facultadService.save(facultad);
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Facultad> update(@PathVariable Long id, @RequestBody Facultad facultad) throws Exception {
        if (facultadService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        facultad.setCodigoFacultad(id);
        Facultad updated = facultadService.update(facultad);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws Exception {
        if (facultadService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        facultadService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Facultad>> findByNombre(@PathVariable String nombre) {
        List<Facultad> facultades = facultadService.findByNombre(nombre);
        return facultades.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(facultades);
    }

    // Agregar una nueva facultad (igual que save pero con método personalizado)
    @PostMapping("/agregar")
    public ResponseEntity<Facultad> agregarFacultad(@RequestBody Facultad facultad) throws Exception {
        Facultad nueva = facultadService.agregarFacultad(facultad);
        return ResponseEntity.status(201).body(nueva);
    }

    // Modificar una facultad existente (requiere validación de existencia)
    @PutMapping("/modificar/{id}")
    public ResponseEntity<Facultad> modificarFacultad(@PathVariable Long id, @RequestBody Facultad facultad) throws Exception {
        if (facultadService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        facultad.setCodigoFacultad(id);
        Facultad modificada = facultadService.modificarFacultad(facultad);
        return ResponseEntity.ok(modificada);
    }

    // Eliminar una facultad utilizando método personalizado
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarFacultad(@PathVariable Long id) throws Exception {
        if (facultadService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        facultadService.eliminarFacultad(id);
        return ResponseEntity.noContent().build();
    }

    // Asociar un departamento existente a una facultad
    @PostMapping("/{facultadId}/departamento/{departamentoId}")
    public ResponseEntity<String> crearDepartamento(
            @PathVariable Long facultadId,
            @PathVariable Long departamentoId) throws Exception {
        facultadService.crearDepartamento(facultadId, departamentoId);
        return ResponseEntity.ok("Departamento asociado a la facultad correctamente.");
    }
}

