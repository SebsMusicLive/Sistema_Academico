package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Semestre;
import com.spring.sistemaacademico.model.Usuario;
import com.spring.sistemaacademico.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> findAll() throws Exception {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findById(Long id) throws Exception {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario save(Usuario entity) throws Exception {
        return usuarioRepository.save(entity);
    }

    @Override
    public Usuario update(Usuario entity) throws Exception {
        if (entity.getCodigoUsuario() == null) {
            throw new Exception("El código del usuario es obligatorio para actualizar");
        }
        // Verifica que exista el usuario antes de actualizar
        Usuario usuarioExistente = usuarioRepository.findById(entity.getCodigoUsuario())
                .orElseThrow(() -> new Exception("Usuario no encontrado para actualizar"));
        // Puedes agregar lógica para actualizar solo ciertos campos si quieres
        return usuarioRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        // Verifica que exista antes de borrar
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuario no encontrado para eliminar"));
        usuarioRepository.deleteById(id);
    }

    @Override
    public void deleteAll() throws Exception {
        usuarioRepository.deleteAll();
    }

    // ======== MÉTODOS PERSONALIZADOS ========

    @Override
    public void iniciarSesion(Long idUsuario) throws Exception {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));
        usuario.setSesionActiva(true);
        usuarioRepository.save(usuario);
    }

    @Override
    public void cerrarSesion(Long idUsuario) throws Exception {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));
        usuario.setSesionActiva(false);
        usuarioRepository.save(usuario);
    }

    @Override
    public boolean autenticar(Long idUsuario, String contrasena) throws Exception {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));
        return usuario.getClave().equals(contrasena);
    }

    @Override
    public void cambiarContrasena(Long idUsuario, String nuevaContrasena) throws Exception {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));
        usuario.setClave(nuevaContrasena);
        usuarioRepository.save(usuario);
    }

    @Override
    public void recuperarContrasena(Long idUsuario) throws Exception {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        // Aquí deberías implementar lógica real segura, esto es solo un ejemplo
        usuario.setClave("temporal123");
        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario buscarPorDocumento(String documento) throws Exception {
        return usuarioRepository.findByPersonaDocumento(documento)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));
    }

    @Override
    public List<Usuario> buscarPorNombre(String nombre) throws Exception {
        return usuarioRepository.findByNombre(nombre);
    }

    @Override
    public List<Usuario> buscarPorSesionActiva(boolean estado) throws Exception {
        return usuarioRepository.findBySesionActiva(estado);
    }

    @Override
    public Usuario buscarPorNombreYClave(String nombre, String clave) throws Exception {
        return usuarioRepository.findByNombreAndClave(nombre, clave)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));
    }
}