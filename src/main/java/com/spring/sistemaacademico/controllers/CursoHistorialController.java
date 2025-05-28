package com.spring.sistemaacademico.controllers;

import com.spring.sistemaacademico.model.CursoHistorial;
import com.spring.sistemaacademico.services.CursoHistorialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos-historial")
@RequiredArgsConstructor
public class CursoHistorialController {

    private final CursoHistorialService cursoHistorialService;

    /**
     * Obtiene la lista de todos los cursos en historial académico
     */
    @GetMapping
    public List<CursoHistorial> getAllCursoHistorial() throws Exception {
        return cursoHistorialService.findAll();
    }

    /**
     * Obtiene un curso historial por su ID
     */
    @GetMapping("/{codigoCursoHistorial}")
    public Optional<CursoHistorial> getCursoHistorialById(@PathVariable Long codigoCursoHistorial) throws Exception {
        return cursoHistorialService.findById(codigoCursoHistorial);
    }

    /**
     * Crea un nuevo curso en historial académico
     */
    @PostMapping
    public CursoHistorial createCursoHistorial(@RequestBody CursoHistorial cursoHistorial) throws Exception {
        return cursoHistorialService.save(cursoHistorial);
    }

    /**
     * Actualiza un curso en historial académico existente
     */
    @PutMapping("/{codigoCursoHistorial}")
    public CursoHistorial updateCursoHistorial(@PathVariable Long codigoCursoHistorial,
                                               @RequestBody CursoHistorial cursoHistorial) throws Exception {
        cursoHistorial.setCodigoCursoHistorial(codigoCursoHistorial); // ← Corregido
        return cursoHistorialService.update(cursoHistorial);
    }

    /**
     * Elimina un curso en historial académico por ID
     */
    @DeleteMapping("/{codigoCursoHistorial}")
    public void deleteCursoHistorial(@PathVariable Long codigoCursoHistorial) throws Exception {
        cursoHistorialService.deleteById(codigoCursoHistorial);
    }

    /**
     * Elimina todos los cursos en historial académico
     */
    @DeleteMapping
    public void deleteAllCursoHistorial() throws Exception {
        cursoHistorialService.deleteAll();
    }

    /*
     * Métodos de búsqueda adicionales (si decides implementarlos luego)
     */
    @GetMapping("/buscar/calificacion")
    public List<CursoHistorial> getByCalificacionFinal(@RequestParam float calificacionFinal) throws Exception {
        return cursoHistorialService.findByCalificacionFinal(calificacionFinal);
    }
}