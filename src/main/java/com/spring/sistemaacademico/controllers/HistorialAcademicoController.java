package com.spring.sistemaacademico.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sistemaAcademico.model.HistorialAcademico;
import sistemaAcademico.service.HistorialAcademicoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/historiales")
@RequiredArgsConstructor
public class HistorialAcademicoController {

    private final HistorialAcademicoService historialAcademicoService;

    /**
     * Obtiene la lista de todos los historiales académicos
     */
    @GetMapping
    public List<HistorialAcademico> getAllHistoriales() throws Exception {
        return historialAcademicoService.findAll();
    }

    /**
     * Obtiene un historial académico por su ID
     */
    @GetMapping("/{codigoHistorialAcademico}")
    public Optional<HistorialAcademico> getHistorialById(@PathVariable Long codigoHistorialAcademico) throws Exception {
        return historialAcademicoService.findById(codigoHistorialAcademico);
    }

    /**
     * Crea un nuevo historial académico
     */
    @PostMapping
    public HistorialAcademico createHistorial(@RequestBody HistorialAcademico historialAcademico) throws Exception {
        return historialAcademicoService.save(historialAcademico);
    }

    /**
     * Actualiza un historial académico existente
     */
    @PutMapping("/{codigoHistorialAcademico}")
    public HistorialAcademico updateHistorial(@PathVariable Long codigoHistorialAcademico, @RequestBody HistorialAcademico historialAcademico) throws Exception {
        historialAcademico.setCodigoHistorialAcademico(codigoHistorialAcademico);
        return historialAcademicoService.update(historialAcademico);
    }

    /**
     * Elimina un historial académico por ID
     */
    @DeleteMapping("/{codigoHistorialAcademico}")
    public void deleteHistorial(@PathVariable Long codigoHistorialAcademico) throws Exception {
        historialAcademicoService.deleteById(codigoHistorialAcademico);
    }

    /**
     * Elimina todos los historiales académicos
     */
    @DeleteMapping
    public void deleteAllHistoriales() throws Exception {
        historialAcademicoService.deleteAll();
    }


    @GetMapping("/buscar/promedio")
    public List<HistorialAcademico> getHistorialesByPromedioGeneral(@RequestParam float promedioGeneral) throws Exception {
        return historialAcademicoService.findByPromedioGeneral(promedioGeneral);
    }

}
