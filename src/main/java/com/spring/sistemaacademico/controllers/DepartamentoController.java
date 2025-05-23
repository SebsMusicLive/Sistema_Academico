package com.spring.sistemaacademico.controllers;

import com.spring.sistemaacademico.model.Departamento;
import com.spring.sistemaacademico.services.DepartamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departamentos")
@RequiredArgsConstructor
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    /**
     * Obtiene la lista de todos los departamentos
     */
    @GetMapping
    public List<Departamento> getAllDepartamentos() throws Exception {
        return departamentoService.findAll();
    }

    /**
     * Obtiene un departamento por su ID
     */
    @GetMapping("/{codigoDepartamento}")
    public Optional<Departamento> getDepartamentoById(@PathVariable Long codigoDepartamento) throws Exception {
        return departamentoService.findById(codigoDepartamento);
    }

    /**
     * Crea un nuevo departamento
     */
    @PostMapping
    public Departamento createDepartamento(@RequestBody Departamento departamento) throws Exception {
        return departamentoService.save(departamento);
    }

    /**
     * Actualiza un departamento existente
     */
    @PutMapping("/{codigoDepartamento}")
    public Departamento updateDepartamento(@PathVariable Long codigoDepartamento, @RequestBody Departamento departamento) throws Exception {
        departamento.setCodigoDepartamento(codigoDepartamento);
        return departamentoService.update(departamento);
    }

    /**
     * Elimina un departamento por ID
     */
    @DeleteMapping("/{codigoDepartamento}")
    public void deleteDepartamento(@PathVariable Long codigoDepartamento) throws Exception {
        departamentoService.deleteById(codigoDepartamento);
    }

    /**
     * Elimina todos los departamentos
     */
    @DeleteMapping
    public void deleteAllDepartamentos() throws Exception {
        departamentoService.deleteAll();
    }

    /*
     * Métodos personalizados futuros:
     * Descomenta e implementa estos métodos en el service y repository si los necesitas.
     */

    @GetMapping("/buscar/nombre")
    public List<Departamento> getDepartamentosByNombre(@RequestParam String nombre) throws Exception {
        return departamentoService.findByNombre(nombre);
    }


    /**
     * Busca departamentos por código de departamento
     */
    @GetMapping("/buscar/codigo")
    public List<Departamento> getDepartamentosByCodigo(@RequestParam String codigoDepartamento) throws Exception {
        return departamentoService.findByCodigoDepartamento(codigoDepartamento);
    }

}
