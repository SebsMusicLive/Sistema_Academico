package com.spring.sistemaacademico.controllers;

import com.spring.sistemaacademico.model.PrestamoRecurso;
import com.spring.sistemaacademico.services.PrestamoRecursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prestamos-recursos")
@RequiredArgsConstructor
public class PrestamoRecursoController {

    private final PrestamoRecursoService prestamoRecursoService;

    /**
     * Obtiene la lista de todos los préstamos de recursos
     */
    @GetMapping
    public List<PrestamoRecurso> getAllPrestamosRecursos() throws Exception {
        return prestamoRecursoService.findAll();
    }

    /**
     * Obtiene un préstamo de recurso por su ID
     */
    @GetMapping("/{codigoPrestamoRecurso}")
    public Optional<PrestamoRecurso> getPrestamoRecursoById(@PathVariable Long codigoPrestamoRecurso) throws Exception {
        return prestamoRecursoService.findById(codigoPrestamoRecurso);
    }

    /**
     * Crea un nuevo préstamo de recurso
     */
    @PostMapping
    public PrestamoRecurso createPrestamoRecurso(@RequestBody PrestamoRecurso prestamoRecurso) throws Exception {
        return prestamoRecursoService.save(prestamoRecurso);
    }

    /**
     * Actualiza un préstamo de recurso existente
     */
    @PutMapping("/{codigoPrestamoRecurso}")
    public PrestamoRecurso updatePrestamoRecurso(@PathVariable Long codigoPrestamoRecurso, @RequestBody PrestamoRecurso prestamoRecurso) throws Exception {
        prestamoRecurso.setCodigoPrestamoRecurso(codigoPrestamoRecurso);
        return prestamoRecursoService.update(prestamoRecurso);
    }

    /**
     * Elimina un préstamo de recurso por ID
     */
    @DeleteMapping("/{codigoPrestamoRecurso}")
    public void deletePrestamoRecurso(@PathVariable Long codigoPrestamoRecurso) throws Exception {
        prestamoRecursoService.deleteById(codigoPrestamoRecurso);
    }

    /**
     * Elimina todos los préstamos de recursos
     */
    @DeleteMapping
    public void deleteAllPrestamosRecursos() throws Exception {
        prestamoRecursoService.deleteAll();
    }

    /*
     * Métodos de búsqueda personalizada
     * Descomenta e implementa si agregas la lógica en el servicio y repositorio
     */

    @GetMapping("/buscar/fecha-prestamo")
    public List<PrestamoRecurso> getByFechaPrestamo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaPrestamo) throws Exception {
        return prestamoRecursoService.findByFechaPrestamo(fechaPrestamo);
    }

    @GetMapping("/buscar/fecha-devolucion")
    public List<PrestamoRecurso> getByFechaDevolucion(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaDevolucion) throws Exception {
        return prestamoRecursoService.findByFechaDevolucion(fechaDevolucion);
    }

}
