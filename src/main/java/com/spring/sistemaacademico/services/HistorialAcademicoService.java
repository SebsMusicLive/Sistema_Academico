package com.spring.sistemaacademico.services;

import org.springframework.stereotype.Service;
import sistemaAcademico.model.HistorialAcademico;

import java.util.List;
@Service
public interface HistorialAcademicoService extends CrudService<HistorialAcademico, Long> {

    public List<HistorialAcademico> findByPromedioGeneral(float promedioGeneral);
}
