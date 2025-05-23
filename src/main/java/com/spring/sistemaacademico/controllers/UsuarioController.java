package com.spring.sistemaacademico.controllers;

import com.spring.sistemaacademico.model.Usuario;
import com.spring.sistemaacademico.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> getAllUsuarios() throws Exception {
        return usuarioService.findAll();
    }

    @GetMapping("/{codigoUsuario}")
    public Optional<Usuario> getUsuarioById(@PathVariable Long codigoUsuario) throws Exception {
        return usuarioService.findById(codigoUsuario);
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) throws Exception {
        return usuarioService.save(usuario);
    }

    @PutMapping("/{codigoUsuario}")
    public Usuario updateUsuario(@PathVariable Long codigoUsuario, @RequestBody Usuario usuario) throws Exception {
        usuario.setCodigoUsuario(codigoUsuario);
        return usuarioService.update(usuario);
    }

    @DeleteMapping("/{codigoUsuario}")
    public void deleteUsuario(@PathVariable Long codigoUsuario) throws Exception {
        usuarioService.deleteById(codigoUsuario);
    }

    @DeleteMapping
    public void deleteAllUsuarios() throws Exception {
        usuarioService.deleteAll();
    }

    // Buscar por documento
    @GetMapping("/buscar/personaDocumento")
    public Usuario getUsuarioByPersonaDocumento(@RequestParam String personaDocumento) throws Exception {
        return usuarioService.buscarPorDocumento(personaDocumento);
    }

    // Buscar por nombre
    @GetMapping("/buscar/nombre")
    public List<Usuario> getUsuariosByNombre(@RequestParam String nombre) throws Exception {
        return usuarioService.buscarPorNombre(nombre);
    }

    // Buscar por estado de sesión activa
    @GetMapping("/buscar/sesionActiva")
    public List<Usuario> getUsuariosBySesionActiva(@RequestParam boolean sesionActiva) throws Exception {
        return usuarioService.buscarPorSesionActiva(sesionActiva);
    }

    // Login por nombre y clave
    @GetMapping("/login")
    public Usuario login(@RequestParam String nombre, @RequestParam String clave) throws Exception {
        return usuarioService.buscarPorNombreYClave(nombre, clave);
    }
}
