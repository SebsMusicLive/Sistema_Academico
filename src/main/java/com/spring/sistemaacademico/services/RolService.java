package com.spring.sistemaacademico.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sistemaAcademico.model.Rol;
import sistemaAcademico.model.Usuario;
import sistemaAcademico.repository.RolRepository;
import sistemaAcademico.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public abstract class RolService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public void asignarRol(Long codigoUsuario, Long codigoRol) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(codigoUsuario);
        Optional<Rol> rolOpt = rolRepository.findById(codigoRol);

        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado con código: " + codigoUsuario);
        }
        if (rolOpt.isEmpty()) {
            throw new RuntimeException("Rol no encontrado con código: " + codigoRol);
        }

        Usuario usuario = usuarioOpt.get();
        Rol rol = rolOpt.get();

        usuario.setRol(rol);
        usuarioRepository.save(usuario);
    }

    public abstract List<Rol> findAll() throws Exception;

    public abstract Rol save(Rol entity) throws Exception;

    public abstract Rol update(Rol entity) throws Exception;

    public abstract void deleteById(Long id) throws Exception;

    public abstract void deleteAll() throws Exception;

    public abstract List<Rol> findByNombre(String nombre) throws Exception;

    public abstract List<Rol> findByTipoRol(String tipoRol) throws Exception;
}
