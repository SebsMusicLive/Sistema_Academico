package com.spring.sistemaacademico.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sistemaAcademico.model.Matricula;
import sistemaAcademico.service.MatriculaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/matriculas")
@RequiredArgsConstructor
public class MatriculaController {

    private final MatriculaService matriculaService;

    /**
     * Obtiene la lista de todas las matrículas
     */
    @GetMapping
    public List<Matricula> getAllMatriculas() throws Exception {
        return matriculaService.findAll();
    }

    /**
     * Obtiene una matrícula por su ID
     */
    @GetMapping("/{codigoMatricula}")
    public Optional<Matricula> getMatriculaById(@PathVariable Long codigoMatricula) throws Exception {
        return matriculaService.findById(codigoMatricula);
    }

    /**
     * Crea una nueva matrícula
     */
    @PostMapping
    public Matricula createMatricula(@RequestBody Matricula matricula) throws Exception {
        return matriculaService.save(matricula);
    }

    /**
     * Actualiza una matrícula existente
     */
    @PutMapping("/{codigoMatricula}")
    public Matricula updateMatricula(@PathVariable Long codigoMatricula, @RequestBody Matricula matricula) throws Exception {
        matricula.setCodigoMatricula(codigoMatricula);
        return matriculaService.update(matricula);
    }

    /**
     * Elimina una matrícula por ID
     */
    @DeleteMapping("/{codigoMatricula}")
    public void deleteMatricula(@PathVariable Long codigoMatricula) throws Exception {
        matriculaService.deleteById(codigoMatricula);
    }

    /**
     * Elimina todas las matrículas
     */
    @DeleteMapping
    public void deleteAllMatriculas() throws Exception {
        matriculaService.deleteAll();
    }

    /*
     * Métodos personalizados futuros:
     * Descomenta e implementa estos métodos en el servicio y repositorio si los necesitas.
     */
    /*
    @GetMapping("/buscar/fecha")
    public List<Matricula> getMatriculasByFecha(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaMatricula) throws Exception {
        return matriculaService.findByFechaMatricula(fechaMatricula);
    }
    */
}
