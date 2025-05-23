package com.spring.sistemaacademico.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sistemaAcademico.model.Docente;
import sistemaAcademico.service.DocenteService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/docentes")
@RequiredArgsConstructor


public class DocenteController {
    private final DocenteService docenteService;

    /**
     * Obtiene la lista de todos los docentes
     */
    @GetMapping
    public List<Docente> getAllDocentes() throws Exception {
        return docenteService.findAll();
    }

    /**
     * Obtiene un docente por su ID
     */
    @GetMapping("/{codigoDocente}")
    public Optional<Docente> getDocenteById(@PathVariable Long codigoDocente) throws Exception {
        return docenteService.findById(codigoDocente);
    }

    /**
     * Crea un nuevo docente
     */
    @PostMapping
    public Docente createDocente(@RequestBody Docente docente) throws Exception {
        return docenteService.save(docente);
    }

    /**
     * Actualiza un docente existente
     */
    @PutMapping("/{codigoDocente}")
    public Docente updateDocente(@PathVariable Long codigoDocente, @RequestBody Docente docente) throws Exception {
        docente.setCodigoDocente(codigoDocente); // Asegura que el ID sea el correcto
        return docenteService.update(docente);
    }

    /**
     * Elimina un docente por ID
     */
    @DeleteMapping("/{codigoDocente}")
    public void deleteDocente(@PathVariable Long codigoDocente) throws Exception {
        docenteService.deleteById(codigoDocente);
    }

    /**
     * Elimina todos los docentes
     */
    @DeleteMapping
    public void deleteAllDocentes() throws Exception {
        docenteService.deleteAll();
    }

    // Búsqueda por nombre
    @GetMapping("/buscar/nombre")
    public List<Docente> getDocentesByNombre(@RequestParam String nombre) throws Exception {
        return docenteService.findByNombre(nombre);
    }

    // Búsqueda por correo
    @GetMapping("/buscar/correo")
    public List<Docente> getDocentesByCorreo(@RequestParam String correo) throws Exception {
        return docenteService.findByCorreo(correo);
    }

    // Búsqueda por teléfono
    @GetMapping("/buscar/telefono")
    public List<Docente> getDocentesByTelefono(@RequestParam String telefono) throws Exception {
        return docenteService.findByTelefono(telefono);
    }
}
