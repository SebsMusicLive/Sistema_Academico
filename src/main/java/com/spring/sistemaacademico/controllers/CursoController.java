package com.spring.sistemaacademico.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sistemaAcademico.model.Curso;
import sistemaAcademico.service.CursoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {
    private final CursoService cursoService;

    /**
     * Obtiene todos los cursos
     */
    @GetMapping
    public List<Curso> getAllCursos() throws Exception {
        return cursoService.findAll();
    }

    /**
     * Obtiene un curso por su ID
     */
    @GetMapping("/{codigoCurso}")
    public Optional<Curso> getCursoById(@PathVariable Long codigoCurso) throws Exception {
        return cursoService.findById(codigoCurso);
    }

    /**
     * Crea un nuevo curso
     */
    @PostMapping
    public Curso createCurso(@RequestBody Curso curso) throws Exception {
        return cursoService.save(curso);
    }

    /**
     * Actualiza un curso existente
     */
    @PutMapping("/{codigoCurso}")
    public Curso updateCurso(@PathVariable Long codigoCurso, @RequestBody Curso curso) throws Exception {
        curso.setCodigoCurso(codigoCurso);
        return cursoService.update(curso);
    }

    /**
     * Elimina un curso por ID
     */
    @DeleteMapping("/{codigoCurso}")
    public void deleteCurso(@PathVariable Long codigoCurso) throws Exception {
        cursoService.deleteById(codigoCurso);
    }

    /**
     * Elimina todos los cursos
     */
    @DeleteMapping
    public void deleteAllCursos() throws Exception {
        cursoService.deleteAll();
    }

    // Búsqueda por carrera
    @GetMapping("/buscar/carrera")
    public List<Curso> getCursosByCarrera(@RequestParam Long codigoCarrera) throws Exception {
        return cursoService.findByCodigoCarreraId(codigoCarrera); // Llamar al método correcto
    }

    // Búsqueda por semestre
    @GetMapping("/buscar/semestre")
    public List<Curso> getCursosBySemestre(@RequestParam Long codigoSemestre) throws Exception {
        return cursoService.findByCodigoSemestreId(codigoSemestre); // Llamar al método correcto
    }

    // Búsqueda por docente
    @GetMapping("/buscar/docente")
    public List<Curso> getCursosByDocente(@RequestParam Long codigoDocente) throws Exception {
        return cursoService.findByCodigoDocenteId(codigoDocente); // Llamar al método correcto
    }
}


