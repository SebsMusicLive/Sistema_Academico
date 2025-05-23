package com.spring.sistemaacademico.controllers;

import com.spring.sistemaacademico.model.RecursoAcademico;
import com.spring.sistemaacademico.services.RecursoAcademicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recursosAcademicos")
@RequiredArgsConstructor
public class RecursoAcademicoController {

    private final RecursoAcademicoService recursoAcademicoService;

    /**
     * Obtiene la lista de todos los recursos académicos
     */
    @GetMapping
    public List<RecursoAcademico> getAllRecursosAcademicos() throws Exception {
        return recursoAcademicoService.findAll();
    }

    /**
     * Obtiene un recurso académico por su ID
     */
    @GetMapping("/{codigoRecursoAcademico}")
    public Optional<RecursoAcademico> getRecursoAcademicoById(@PathVariable Long codigoRecursoAcademico) throws Exception {
        return recursoAcademicoService.findById(codigoRecursoAcademico);
    }

    /**
     * Crea un nuevo recurso académico
     */
    @PostMapping
    public RecursoAcademico createRecursoAcademico(@RequestBody RecursoAcademico recursoAcademico) throws Exception {
        return recursoAcademicoService.save(recursoAcademico);
    }

    /**
     * Actualiza un recurso académico existente
     */
    @PutMapping("/{codigoRecursoAcademico}")
    public RecursoAcademico updateRecursoAcademico(@PathVariable Long codigoRecursoAcademico, @RequestBody RecursoAcademico recursoAcademico) throws Exception {
        recursoAcademico.setCodigoRecursoAcademico(codigoRecursoAcademico);
        return recursoAcademicoService.update(recursoAcademico);
    }

    /**
     * Elimina un recurso académico por ID
     */
    @DeleteMapping("/{codigoRecursoAcademico}")
    public void deleteRecursoAcademico(@PathVariable Long codigoRecursoAcademico) throws Exception {
        recursoAcademicoService.deleteById(codigoRecursoAcademico);
    }

    /**
     * Elimina todos los recursos académicos
     */
    @DeleteMapping
    public void deleteAllRecursosAcademicos() throws Exception {
        recursoAcademicoService.deleteAll();
    }


    @GetMapping("/buscar/titulo")
    public List<RecursoAcademico> getRecursosByTitulo(@RequestParam String titulo) throws Exception {
        return recursoAcademicoService.findByTitulo(titulo);
    }

    @GetMapping("/buscar/tipo")
    public List<RecursoAcademico> getRecursosByTipo(@RequestParam String tipo) throws Exception {
        return recursoAcademicoService.findByTipo(tipo);
    }

    @GetMapping("/buscar/nombre")
    public List<RecursoAcademico> getRecursosByNombre(@RequestParam String nombreRecursoAcademico) throws Exception {
        return recursoAcademicoService.findByNombreRecursoAcademico(nombreRecursoAcademico);
    }

    @GetMapping("/buscar/disponible")
    public List<RecursoAcademico> getRecursosByDisponibilidad(@RequestParam boolean disponible) throws Exception {
        return recursoAcademicoService.findByDisponible(disponible);
    }

    @GetMapping("/buscar/ubicacion")
    public List<RecursoAcademico> getRecursosByUbicacion(@RequestParam String ubicacion) throws Exception {
        return recursoAcademicoService.findByUbicacion(ubicacion);
    }

    @GetMapping("/buscar/tipoEspacio")
    public List<RecursoAcademico> getRecursosByTipoEspacio(@RequestParam String tipoEspacio) throws Exception {
        return recursoAcademicoService.findByTipoEspacio(tipoEspacio);
    }

}
