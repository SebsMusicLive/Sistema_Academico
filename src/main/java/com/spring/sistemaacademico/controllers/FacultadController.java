package com.spring.sistemaacademico.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sistemaAcademico.model.Facultad;
import sistemaAcademico.service.FacultadService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/facultades")
@RequiredArgsConstructor
public class FacultadController {

    private final FacultadService facultadService;

    /**
     * Obtiene la lista de todas las facultades
     */
    @GetMapping
    public List<Facultad> getAllFacultades() throws Exception {
        return facultadService.findAll();
    }

    /**
     * Obtiene una facultad por su ID
     */
    @GetMapping("/{codigoFacultad}")
    public Optional<Facultad> getFacultadById(@PathVariable Long codigoFacultad) throws Exception {
        return facultadService.findById(codigoFacultad);
    }

    /**
     * Crea una nueva facultad
     */
    @PostMapping
    public Facultad createFacultad(@RequestBody Facultad facultad) throws Exception {
        return facultadService.save(facultad);
    }

    /**
     * Actualiza una facultad existente
     */
    @PutMapping("/{codigoFacultad}")
    public Facultad updateFacultad(@PathVariable Long codigoFacultad, @RequestBody Facultad facultad) throws Exception {
        facultad.setCodigoFacultad(codigoFacultad);
        return facultadService.update(facultad);
    }

    /**
     * Elimina una facultad por su ID
     */
    @DeleteMapping("/{codigoFacultad}")
    public void deleteFacultad(@PathVariable Long codigoFacultad) throws Exception {
        facultadService.deleteById(codigoFacultad);
    }

    /**
     * Elimina todas las facultades
     */
    @DeleteMapping
    public void deleteAllFacultades() throws Exception {
        facultadService.deleteAll();
    }

    @GetMapping("/buscar/nombre")
    public List<Facultad> getFacultadesByNombre(@RequestParam String nombre) throws Exception {
        return facultadService.findByNombre(nombre);
    }

}
