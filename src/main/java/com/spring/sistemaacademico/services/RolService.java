package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Rol;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface RolService {
    List<Rol> findAll() throws Exception;

    Optional<Rol> findById(Long id) throws Exception;

    Rol save(Rol rol) throws Exception;

    Rol update(Rol rol) throws Exception;

    void deleteById(Long id) throws Exception;

    void deleteAll() throws Exception;

    List<Rol> findByNombre(String nombre) throws Exception;

    List<Rol> findByTipoRol(String tipoRol) throws Exception;

    void asignarRol(Long codigoUsuario, Long codigoRol) throws Exception;
}
