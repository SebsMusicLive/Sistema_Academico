package com.spring.sistemaacademico.controllers;

import com.spring.sistemaacademico.model.Curso;
import com.spring.sistemaacademico.services.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @GetMapping
    public List<Curso> getAllCursos() throws Exception {
        return cursoService.findAll();
    }

    @GetMapping("/{codigoCurso}")
    public Optional<Curso> getCursoById(@PathVariable Long codigoCurso) throws Exception {
        return cursoService.findById(codigoCurso);
    }

    @PostMapping
    public Curso createCurso(@RequestBody Curso curso) throws Exception {
        return cursoService.save(curso);
    }

    @PutMapping("/{codigoCurso}")
    public Curso updateCurso(@PathVariable Long codigoCurso, @RequestBody Curso curso) throws Exception {
        curso.setCodigoCurso(codigoCurso);
        return cursoService.update(curso);
    }

    @DeleteMapping("/{codigoCurso}")
    public void deleteCurso(@PathVariable Long codigoCurso) throws Exception {
        cursoService.deleteById(codigoCurso);
    }

    @DeleteMapping
    public void deleteAllCursos() throws Exception {
        cursoService.deleteAll();
    }

    @GetMapping("/buscar/carrera")
    public List<Curso> getCursosByCarrera(@RequestParam Long codigoCarrera) throws Exception {
        return cursoService.findByCodigoCarreraId(codigoCarrera);
    }

    @GetMapping("/buscar/semestre")
    public List<Curso> getCursosBySemestre(@RequestParam Long codigoSemestre) throws Exception {
        return cursoService.findByCodigoSemestreId(codigoSemestre);
    }

    @GetMapping("/buscar/docente")
    public List<Curso> getCursosByDocente(@RequestParam Long codigoDocente) throws Exception {
        return cursoService.findByCodigoDocenteId(codigoDocente);
    }
}

