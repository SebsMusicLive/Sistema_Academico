package com.spring.sistemaacademico.services;


import com.spring.sistemaacademico.model.Semestre;
import org.springframework.stereotype.Service;

@Service
public interface SemestreService extends CrudService<Semestre, Long> {
    /*public List<Semestre> findByNumeroSemestre(int numeroSemestre);

    public List<Semestre> findByFechaInicio(Date fechaInicio);

    public List<Semestre> findByFechaFin(Date fechaFin);*/
}