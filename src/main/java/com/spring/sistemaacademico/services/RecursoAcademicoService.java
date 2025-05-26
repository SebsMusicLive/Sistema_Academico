package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.RecursoAcademico;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecursoAcademicoService extends CrudService<RecursoAcademico, Long> {

    List<RecursoAcademico> findByTitulo(String titulo);

    List<RecursoAcademico> findByTipo(String tipo);

    List<RecursoAcademico> findByNombreRecursoAcademico(String nombreRecursoAcademico);

    List<RecursoAcademico> findByDisponible(boolean disponible);

    List<RecursoAcademico> findByUbicacion(String ubicacion);

    List<RecursoAcademico> findByTipoEspacio(String tipoEspacio);

    // Método para agregar un recurso
    RecursoAcademico agregarRecurso(RecursoAcademico recurso);

    // Método para eliminar un recurso
    void eliminarRecurso(Long idRecurso) throws Exception;

    // Método para reservar un recurso
    void reservarRecurso(Long idRecurso) throws Exception;

    // Método para verificar la disponibilidad de un recurso
    boolean verificarDisponibilidad(Long idRecurso);

    // Método para gestionar mantenimiento de un recurso
    void gestionarMantenimiento(Long idRecurso) throws Exception;
}
