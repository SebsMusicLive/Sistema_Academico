package com.spring.sistemaacademico.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import sistemaAcademico.model.Foro;
import sistemaAcademico.service.ForoService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/foros")
@RequiredArgsConstructor
public class ForoController {

    private final ForoService foroService;

    /** Obtiene la lista de todos los foros */
    @GetMapping
    public List<Foro> getAllForos() throws Exception {
        return foroService.findAll();
    }

    /** Obtiene un foro por su ID */
    @GetMapping("/{codigoForo}")
    public Optional<Foro> getForoById(@PathVariable Long codigoForo) throws Exception {
        return foroService.findById(codigoForo);
    }

    /** Crea un nuevo foro */
    @PostMapping
    public Foro createForo(@RequestBody Foro foro) throws Exception {
        return foroService.save(foro);
    }

    /** Actualiza un foro existente */
    @PutMapping("/{codigoForo}")
    public Foro updateForo(@PathVariable Long codigoForo, @RequestBody Foro foro) throws Exception {
        foro.setCodigoForo(codigoForo);
        return foroService.update(foro);
    }

    /** Elimina un foro por ID */
    @DeleteMapping("/{codigoForo}")
    public void deleteForo(@PathVariable Long codigoForo) throws Exception {
        foroService.deleteById(codigoForo);
    }

    /** Elimina todos los foros */
    @DeleteMapping
    public void deleteAllForos() throws Exception {
        foroService.deleteAll();
    }

    /** Buscar foros por título */
    @GetMapping("/buscar/titulo")
    public List<Foro> getForosByTitulo(@RequestParam String titulo) throws Exception {
        return foroService.findByTitulo(titulo);
    }

    /** Buscar foros por descripción */
    @GetMapping("/buscar/descripcion")
    public List<Foro> getForosByDescripcion(@RequestParam String descripcion) throws Exception {
        return foroService.findByDescripcion(descripcion);
    }

    /** Buscar foros por fecha de creación */
    @GetMapping("/buscar/fecha")
    public List<Foro> getForosByFechaCreacion(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaCreacion
    ) throws Exception {
        return foroService.findByFechaCreacion(fechaCreacion);
    }
}
