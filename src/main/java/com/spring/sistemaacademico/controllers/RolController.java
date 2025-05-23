package com.spring.sistemaacademico.controllers;

import com.spring.sistemaacademico.model.Rol;
import com.spring.sistemaacademico.services.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RolController {

    private final RolService rolService;

    @GetMapping
    public List<Rol> getAllRoles() throws Exception {
        return rolService.findAll();
    }

    @GetMapping("/{codigoRol}")
    public Optional<Rol> getRolById(@PathVariable Long codigoRol) throws Exception {
        return rolService.findById(codigoRol);
    }

    @PostMapping
    public Rol createRol(@RequestBody Rol rol) throws Exception {
        return rolService.save(rol);
    }

    @PutMapping("/{codigoRol}")
    public Rol updateRol(@PathVariable Long codigoRol, @RequestBody Rol rol) throws Exception {
        rol.setCodigoRol(codigoRol);
        return rolService.update(rol);
    }

    @DeleteMapping("/{codigoRol}")
    public void deleteRol(@PathVariable Long codigoRol) throws Exception {
        rolService.deleteById(codigoRol);
    }

    @DeleteMapping
    public void deleteAllRoles() throws Exception {
        rolService.deleteAll();
    }

    @GetMapping("/buscar/nombre")
    public List<Rol> getRolesByNombre(@RequestParam String nombre) throws Exception {
        return rolService.findByNombre(nombre);
    }

    @GetMapping("/buscar/tipoRol")
    public List<Rol> getRolesByTipoRol(@RequestParam String tipoRol) throws Exception {
        return rolService.findByTipoRol(tipoRol);
    }
}
