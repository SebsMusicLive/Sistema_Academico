package com.spring.sistemaacademico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistemaAcademico.model.Carrera;

import java.util.List;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Long> {

    public List<Carrera> findByNombreCarrera(String nombreCarrera);

    public List<Carrera> findByDuracion(int duracion);


}
