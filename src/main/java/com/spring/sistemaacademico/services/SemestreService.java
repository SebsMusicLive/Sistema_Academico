package com.spring.sistemaacademico.services;

import org.springframework.stereotype.Service;
import sistemaAcademico.model.Semestre;

@Service
public interface SemestreService extends CrudService<Semestre, Long>{
    /*public List<Semestre> findByNumeroSemestre(int numeroSemestre);

    public List<Semestre> findByFechaInicio(Date fechaInicio);

    public List<Semestre> findByFechaFin(Date fechaFin);*/
}
