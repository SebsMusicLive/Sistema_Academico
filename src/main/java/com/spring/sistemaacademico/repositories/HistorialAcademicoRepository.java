package com.spring.sistemaacademico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistemaAcademico.model.HistorialAcademico;

import java.util.List;

@Repository
public interface HistorialAcademicoRepository extends JpaRepository<HistorialAcademico, Long> {

    public List<HistorialAcademico> findByPromedioGeneral(float promedioGeneral);
}
