package com.spring.sistemaacademico.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sistemaAcademico.model.Semestre;
import sistemaAcademico.model.Usuario;
import sistemaAcademico.repository.UsuarioRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> findAll() throws Exception {
        return usuarioRepository.findAll();
    }

    @Override
    public Semestre findById(Long id) throws Exception {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario save(Usuario entity) throws Exception {
        return usuarioRepository.save(entity);
    }

    @Override
    public Usuario update(Usuario entity) throws Exception {
        return usuarioRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        usuarioRepository.deleteById(id);
    }

    @Override
    public void deleteAll() throws Exception {
        usuarioRepository.deleteAll();
    }

    // ========== MÉTODOS PERSONALIZADOS ==========

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
        // Aquí puedes implementar lógica para enviar un correo o mensaje con la contraseña temporal
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        // Ejemplo: reiniciar la contraseña con una temporal (en una app real, deberías notificarla)
        usuario.setClave("temporal123"); // ⚠️ Reemplazar con lógica segura
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
