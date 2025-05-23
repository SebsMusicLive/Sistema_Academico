package com.spring.sistemaacademico.services;

import org.springframework.stereotype.Service;
import sistemaAcademico.model.RecursoAcademico;

import java.util.List;

@Service
public interface RecursoAcademicoService extends CrudService<RecursoAcademico, Long> {

    public List<RecursoAcademico> findByTitulo(String titulo);

    public List<RecursoAcademico> findByTipo(String tipo);

    public List<RecursoAcademico> findByNombreRecursoAcademico(String nombreRecursoAcademico);

    public List<RecursoAcademico> findByDisponible(boolean disponible);

    public List<RecursoAcademico> findByUbicacion(String ubicacion);

    public List<RecursoAcademico> findByTipoEspacio(String tipoEspacio);

    // Método para agregar un recurso
    public RecursoAcademico agregarRecurso(RecursoAcademico recurso);

    // Método para eliminar un recurso
    public void eliminarRecurso(Long idRecurso) throws Exception;

    // Método para reservar un recurso
    public void reservarRecurso(Long idRecurso) throws Exception;

    // Método para verificar la disponibilidad de un recurso
    public boolean verificarDisponibilidad(Long idRecurso);

    // Método para gestionar mantenimiento de un recurso
    public void gestionarMantenimiento(Long idRecurso) throws Exception;
}
