package com.spring.sistemaacademico.repositories;

import com.spring.sistemaacademico.model.Foro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ForoRepository extends JpaRepository<Foro, Long> {


    List<Foro> findByTituloContainingIgnoreCase(String titulo);

    List<Foro> findByDescripcionContainingIgnoreCase(String descripcion);

    List<Foro> findByFechaCreacion(Date fechaCreacion);

}
