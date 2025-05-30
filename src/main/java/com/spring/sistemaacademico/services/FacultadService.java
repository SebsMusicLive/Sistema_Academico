package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Facultad;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface FacultadService extends CrudService<Facultad, Long> {
    List<Facultad> findByNombre(String nombre);

    Facultad agregarFacultad(Facultad facultad) throws Exception;

    Facultad modificarFacultad(Facultad facultad) throws Exception;

    void eliminarFacultad(Long id) throws Exception;

    void crearDepartamento(Long facultadId, Long departamentoId) throws Exception;

    String generarReporteEstadistico(Long facultadId);
}

