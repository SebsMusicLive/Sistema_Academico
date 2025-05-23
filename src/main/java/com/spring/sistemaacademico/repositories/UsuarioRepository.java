package com.spring.sistemaacademico.repositories;

import com.spring.sistemaacademico.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //public List<Usuario> findByPersonaDocumento(String personaDocumento);

    //public List<Usuario> findByNombre(String nombre);

    //public List<Usuario> findByClave(String clave);

    //public List<Usuario> findBySesionActiva(boolean sesionActiva);

    Optional<Usuario> findByPersonaDocumento(String personaDocumento);

    List<Usuario> findByNombre(String nombre);

    List<Usuario> findBySesionActiva(boolean sesionActiva);

    Optional<Usuario> findByNombreAndClave(String nombre, String clave);
}
