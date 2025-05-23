package com.spring.sistemaacademico.services;

import org.springframework.stereotype.Service;
import sistemaAcademico.model.Facultad;

import java.util.List;

@Service
public interface FacultadService extends CrudService<Facultad, Long>{
    public List<Facultad> findByNombre(String nombre);

    // Nuevos métodos agregados
    Facultad agregarFacultad(Facultad facultad) throws Exception;

    Facultad modificarFacultad(Facultad facultad) throws Exception;

    void eliminarFacultad(Long id) throws Exception;

    void crearDepartamento(Long facultadId, Long departamentoId) throws Exception;

    String generarReporteEstadistico(Long facultadId); // Devuelve un resumen como String por simplicidad
}
