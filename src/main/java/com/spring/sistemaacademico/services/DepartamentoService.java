package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Departamento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartamentoService extends CrudService<Departamento, Long> {
    List<Departamento> findByNombre(String nombre);
}