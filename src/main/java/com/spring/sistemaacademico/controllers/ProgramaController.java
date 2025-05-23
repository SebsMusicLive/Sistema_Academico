package com.spring.sistemaacademico.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sistemaAcademico.model.Programa;
import sistemaAcademico.service.ProgramaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/programas")
@RequiredArgsConstructor
public class ProgramaController {

    private final ProgramaService programaService;

    /**
     * Obtiene la lista de todos los programas
     */
    @GetMapping
    public List<Programa> getAllProgramas() throws Exception {
        return programaService.findAll();
    }

    /**
     * Obtiene un programa por su ID
     */
    @GetMapping("/{codigoPrograma}")
    public Optional<Programa> getProgramaById(@PathVariable Long codigoPrograma) throws Exception {
        return programaService.findById(codigoPrograma);
    }

    /**
     * Crea un nuevo programa
     */
    @PostMapping
    public Programa createPrograma(@RequestBody Programa programa) throws Exception {
        return programaService.save(programa);
    }

    /**
     * Actualiza un programa existente
     */
    @PutMapping("/{codigoPrograma}")
    public Programa updatePrograma(@PathVariable Long codigoPrograma, @RequestBody Programa programa) throws Exception {
        programa.setCodigoPrograma(codigoPrograma);
        return programaService.update(programa);
    }

    /**
     * Elimina un programa por ID
     */
    @DeleteMapping("/{codigoPrograma}")
    public void deletePrograma(@PathVariable Long codigoPrograma) throws Exception {
        programaService.deleteById(codigoPrograma);
    }

    /**
     * Elimina todos los programas
     */
    @DeleteMapping
    public void deleteAllProgramas() throws Exception {
        programaService.deleteAll();
    }


    @GetMapping("/buscar/nombre")
    public List<Programa> getProgramasByNombre(@RequestParam String nombre) throws Exception {
        return programaService.findByNombre(nombre);
    }

    @GetMapping("/buscar/descripcion")
    public List<Programa> getProgramasByDescripcion(@RequestParam String descripcion) throws Exception {
        return programaService.findByDescripcion(descripcion);
    }

}
