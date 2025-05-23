package com.spring.sistemaacademico.services;

import org.springframework.stereotype.Service;
import sistemaAcademico.model.Departamento;

import java.util.List;

@Service
public interface DepartamentoService extends CrudService<Departamento, Long>{
    public List<Departamento> findByCodigoDepartamento(String codigoDepartamento);

    public List<Departamento> findByNombre(String nombre);
}
