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

    @GetMapping
    public List<Departamento> getAllDepartamentos() throws Exception {
        return departamentoService.findAll();
    }

    @GetMapping("/{codigoDepartamento}")
    public Optional<Departamento> getDepartamentoById(@PathVariable Long codigoDepartamento) throws Exception {
        return departamentoService.findById(codigoDepartamento);
    }

    @PostMapping
    public Departamento createDepartamento(@RequestBody Departamento departamento) throws Exception {
        return departamentoService.save(departamento);
    }

    @PutMapping("/{codigoDepartamento}")
    public Departamento updateDepartamento(@PathVariable Long codigoDepartamento, @RequestBody Departamento departamento) throws Exception {
        departamento.setCodigoDepartamento(codigoDepartamento);
        return departamentoService.update(departamento);
    }

    @DeleteMapping("/{codigoDepartamento}")
    public void deleteDepartamento(@PathVariable Long codigoDepartamento) throws Exception {
        departamentoService.deleteById(codigoDepartamento);
    }

    @DeleteMapping
    public void deleteAllDepartamentos() throws Exception {
        departamentoService.deleteAll();
    }

    @GetMapping("/buscar/nombre")
    public List<Departamento> getDepartamentosByNombre(@RequestParam String nombre) throws Exception {
        return departamentoService.findByNombre(nombre);
    }
}
