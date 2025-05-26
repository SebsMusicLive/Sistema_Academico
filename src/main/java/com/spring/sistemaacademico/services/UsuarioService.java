package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Usuario;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface UsuarioService extends CrudService<Usuario, Long> {

    //public List<Usuario> findByPersonaDocumento(String personaDocumento);

    //public List<Usuario> findByNombre(String nombre);

    //public List<Usuario> findByClave(String clave);

    //public List<Usuario> findBySesionActiva(boolean sesionActiva);

    void iniciarSesion(Long idUsuario) throws Exception;

    void cerrarSesion(Long idUsuario) throws Exception;

    boolean autenticar(Long idUsuario, String contrasena) throws Exception;

    void cambiarContrasena(Long idUsuario, String nuevaContrasena) throws Exception;

    void recuperarContrasena(Long idUsuario) throws Exception;

    Usuario buscarPorDocumento(String documento) throws Exception;

    List<Usuario> buscarPorNombre(String nombre) throws Exception;

    List<Usuario> buscarPorSesionActiva(boolean estado) throws Exception;

    Usuario buscarPorNombreYClave(String nombre, String clave) throws Exception;
}
