package com.spring.sistemaacademico.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sistemaAcademico.model.Semestre;
import sistemaAcademico.service.SemestreService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/semestres")
@RequiredArgsConstructor
public class SemestreController {

    private final SemestreService semestreService;

    /**
     * Obtiene la lista de todos los semestres
     */
    @GetMapping
    public List<Semestre> getAllSemestres() throws Exception {
        return semestreService.findAll();
    }

    /**
     * Obtiene un semestre por su ID
     */
    @GetMapping("/{codigoSemestre}")
    public Optional<Semestre> getSemestreById(@PathVariable Long codigoSemestre) throws Exception {
        return semestreService.findById(codigoSemestre);
    }

    /**
     * Crea un nuevo semestre
     */
    @PostMapping
    public Semestre createSemestre(@RequestBody Semestre semestre) throws Exception {
        return semestreService.save(semestre);
    }

    /**
     * Actualiza un semestre existente
     */
    @PutMapping("/{codigoSemestre}")
    public Semestre updateSemestre(@PathVariable Long codigoSemestre, @RequestBody Semestre semestre) throws Exception {
        semestre.setCodigoSemestre(codigoSemestre);
        return semestreService.update(semestre);
    }

    /**
     * Elimina un semestre por ID
     */
    @DeleteMapping("/{codigoSemestre}")
    public void deleteSemestre(@PathVariable Long codigoSemestre) throws Exception {
        semestreService.deleteById(codigoSemestre);
    }

    /**
     * Elimina todos los semestres
     */
    @DeleteMapping
    public void deleteAllSemestres() throws Exception {
        semestreService.deleteAll();
    }

    /*
    @GetMapping("/buscar/numeroSemestre")
    public List<Semestre> getSemestresByNumeroSemestre(@RequestParam int numeroSemestre) throws Exception {
        return semestreService.findByNumeroSemestre(numeroSemestre);
    }

    @GetMapping("/buscar/fechaInicio")
    public List<Semestre> getSemestresByFechaInicio(@RequestParam Date fechaInicio) throws Exception {
        return semestreService.findByFechaInicio(fechaInicio);
    }

    @GetMapping("/buscar/fechaFin")
    public List<Semestre> getSemestresByFechaFin(@RequestParam Date fechaFin) throws Exception {
        return semestreService.findByFechaFin(fechaFin);
    }
    */
}
