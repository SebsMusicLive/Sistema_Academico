package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Rol;
import com.spring.sistemaacademico.model.Usuario;
import com.spring.sistemaacademico.repositories.RolRepository;
import com.spring.sistemaacademico.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    @Override
    public List<Rol> findAll() throws Exception {
        return rolRepository.findAll();
    }

    @Override
    public Optional<Rol> findById(Long id) throws Exception {
        return rolRepository.findById(id);
    }

    @Override
    public Rol save(Rol rol) throws Exception {
        return rolRepository.save(rol);
    }

    @Override
    public Rol update(Rol rol) throws Exception {
        return rolRepository.save(rol);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        rolRepository.deleteById(id);
    }

    @Override
    public void deleteAll() throws Exception {
        rolRepository.deleteAll();
    }

    @Override
    public List<Rol> findByNombre(String nombre) throws Exception {
        return rolRepository.findByNombre(nombre);
    }

    @Override
    public List<Rol> findByTipoRol(String tipoRol) throws Exception {
        return rolRepository.findByTipoRol(tipoRol);
    }

    @Override
    public void asignarRol(Long codigoUsuario, Long codigoRol) throws Exception {
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
}
