package com.spring.sistemaacademico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistemaAcademico.model.RecursoAcademico;

import java.util.List;

@Repository
public interface RecursoAcademicoRepository extends JpaRepository<RecursoAcademico, Long> {

    public List<RecursoAcademico> findByTitulo(String titulo);

    public List<RecursoAcademico> findByTipo(String tipo);

    public List<RecursoAcademico> findByNombreRecursoAcademico(String nombreRecursoAcademico);

    public List<RecursoAcademico> findByDisponible(boolean disponible);

    public List<RecursoAcademico> findByUbicacion(String ubicacion);

    public List<RecursoAcademico> findByTipoEspacio(String tipoEspacio);
}
