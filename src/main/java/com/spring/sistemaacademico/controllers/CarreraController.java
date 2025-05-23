package com.spring.sistemaacademico.controllers;

import com.spring.sistemaacademico.model.Carrera;
import com.spring.sistemaacademico.services.CarreraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carreras")
@RequiredArgsConstructor
public class CarreraController {

    private final CarreraService carreraService;

    // Obtener todas las carreras
    @GetMapping
    public ResponseEntity<List<Carrera>> getAll() throws Exception {
        List<Carrera> carreras = carreraService.findAll();
        return ResponseEntity.ok(carreras); // Devuelve la lista con código 200
    }

    // Obtener carrera por ID
    @GetMapping("/{id}")
    public ResponseEntity<Carrera> getById(@PathVariable Long id) throws Exception {
        Optional<Carrera> carrera = carreraService.findById(id);
        return carrera.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // 404 si no se encuentra
    }

    // Guardar nueva carrera
    @PostMapping
    public ResponseEntity<Carrera> save(@RequestBody Carrera carrera) throws Exception {
        Carrera savedCarrera = carreraService.save(carrera);
        return ResponseEntity.status(201).body(savedCarrera); // 201 para creación exitosa
    }

    // Actualizar carrera existente
    @PutMapping("/{id}")
    public ResponseEntity<Carrera> update(@PathVariable Long id, @RequestBody Carrera carreraActualizada) throws Exception {
        Optional<Carrera> existente = carreraService.findById(id);
        if (existente.isPresent()) {
            carreraActualizada.setCodigoCarrera(id); // Se actualiza el ID
            return ResponseEntity.ok(carreraService.update(carreraActualizada)); // 200 si es exitoso
        }
        return ResponseEntity.notFound().build(); // 404 si no se encuentra
    }

    // Eliminar carrera
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        if (carreraService.findById(id).isPresent()) {
            carreraService.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 si se elimina exitosamente
        }
        return ResponseEntity.notFound().build(); // 404 si no se encuentra
    }

    // Buscar carreras por nombre
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Carrera>> getByNombre(@PathVariable String nombre) {
        List<Carrera> carreras = carreraService.findByNombreCarrera(nombre);
        return carreras.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carreras); // 204 si no hay resultados
    }

    // Buscar carreras por duración
    @GetMapping("/duracion/{duracion}")
    public ResponseEntity<List<Carrera>> getByDuracion(@PathVariable int duracion) {
        List<Carrera> carreras = carreraService.findByDuracion(duracion);
        return carreras.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carreras); // 204 si no hay resultados
    }
}
