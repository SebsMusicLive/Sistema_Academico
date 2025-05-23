package com.spring.sistemaacademico.repositories;

import com.spring.sistemaacademico.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    //public List<Rol> findByNombre(String nombre);

    //public List<Rol> findByTipoRol(String tipoRol);

    List<Rol> findByNombre(String nombre);

    List<Rol> findByTipoRol(String tipoRol);
}
