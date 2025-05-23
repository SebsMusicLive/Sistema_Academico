package com.spring.sistemaacademico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistemaAcademico.model.Rol;

import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    //public List<Rol> findByNombre(String nombre);

    //public List<Rol> findByTipoRol(String tipoRol);

    List<Rol> findByNombre(String nombre);

    List<Rol> findByTipoRol(String tipoRol);
}
