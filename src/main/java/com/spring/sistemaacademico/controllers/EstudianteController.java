package com.spring.sistemaacademico.controllers;

import com.spring.sistemaacademico.model.Estudiante;
import com.spring.sistemaacademico.services.EstudianteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService estudianteService;

    /**
     * Obtiene la lista de todos los estudiantes
     */
    @GetMapping
    public List<Estudiante> getAllEstudiantes() throws Exception {
        return estudianteService.findAll();
    }

    /**
     * Obtiene un estudiante por su ID
     */
    @GetMapping("/{codigoEstudiante}")
    public Optional<Estudiante> getEstudianteById(@PathVariable Long codigoEstudiante) throws Exception {
        return estudianteService.findById(codigoEstudiante);
    }

    /**
     * Crea un nuevo estudiante
     */
    @PostMapping
    public Estudiante createEstudiante(@Valid @RequestBody Estudiante estudiante) throws Exception {
        return estudianteService.save(estudiante);
    }

    /**
     * Actualiza un estudiante existente
     */
    @PutMapping("/{codigoEstudiante}")
    public Estudiante updateEstudiante(@PathVariable Long codigoEstudiante, @RequestBody Estudiante estudiante) throws Exception {
        estudiante.setCodigoEstudiante(codigoEstudiante); // Asegura que el ID sea el correcto
        return estudianteService.update(estudiante);
    }

    /**
     * Elimina un estudiante por ID
     */
    @DeleteMapping("/{codigoEstudiante}")
    public void deleteEstudiante(@PathVariable Long codigoEstudiante) throws Exception {
        estudianteService.deleteById(codigoEstudiante);
    }

    /**
     * Elimina todos los estudiantes
     */
    @DeleteMapping
    public void deleteAllEstudiantes() throws Exception {
        estudianteService.deleteAll();
    }

    @GetMapping("/buscar/nombre")
    public List<Estudiante> getEstudiantesByNombre(@RequestParam String nombre) throws Exception {
        return estudianteService.findByNombre(nombre);
    }

    @GetMapping("/buscar/correo")
    public List<Estudiante> getEstudiantesByCorreo(@RequestParam String correo) throws Exception {
        return estudianteService.findByCorreo(correo);
    }
}