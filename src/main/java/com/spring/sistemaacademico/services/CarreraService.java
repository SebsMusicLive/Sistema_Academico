package com.spring.sistemaacademico.services;

import org.springframework.stereotype.Service;
import sistemaAcademico.model.Carrera;

import java.util.List;

@Service
public interface CarreraService extends CrudService<Carrera, Long> {

    public List<Carrera> findByNombreCarrera(String nombreCarrera);

    public List<Carrera> findByDuracion(int duracion);
}
