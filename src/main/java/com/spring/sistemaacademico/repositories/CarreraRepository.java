package com.spring.sistemaacademico.repositories;

import com.spring.sistemaacademico.model.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Long> {

    public List<Carrera> findByNombreCarrera(String nombreCarrera);

    public List<Carrera> findByDuracion(int duracion);


}
