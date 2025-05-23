package com.spring.sistemaacademico.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistemaAcademico.model.RecursoAcademico;
import sistemaAcademico.model.Semestre;
import sistemaAcademico.repository.RecursoAcademicoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecursoAcademicoServiceImpl implements RecursoAcademicoService {

    @Autowired
    private RecursoAcademicoRepository recursoAcademicoRepository;

    @Override
    public RecursoAcademico save(RecursoAcademico entity) {
        return recursoAcademicoRepository.save(entity);
    }

    @Override
    public List<RecursoAcademico> findAll() {
        return recursoAcademicoRepository.findAll();
    }

    @Override
    public Semestre findById(Long id) {
        return recursoAcademicoRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        recursoAcademicoRepository.deleteById(id);
    }

    @Override
    public void deleteAll() throws Exception {
        recursoAcademicoRepository.deleteAll();
    }

    @Override
    public List<RecursoAcademico> findByTitulo(String titulo) {
        return recursoAcademicoRepository.findByTitulo(titulo);
    }

    @Override
    public List<RecursoAcademico> findByTipo(String tipo) {
        return recursoAcademicoRepository.findByTipo(tipo);
    }

    @Override
    public List<RecursoAcademico> findByNombreRecursoAcademico(String nombreRecursoAcademico) {
        return recursoAcademicoRepository.findByNombreRecursoAcademico(nombreRecursoAcademico);
    }

    @Override
    public List<RecursoAcademico> findByDisponible(boolean disponible) {
        return recursoAcademicoRepository.findByDisponible(disponible);
    }

    @Override
    public List<RecursoAcademico> findByUbicacion(String ubicacion) {
        return recursoAcademicoRepository.findByUbicacion(ubicacion);
    }

    @Override
    public List<RecursoAcademico> findByTipoEspacio(String tipoEspacio) {
        return recursoAcademicoRepository.findByTipoEspacio(tipoEspacio);
    }

    @Override
    public RecursoAcademico update(RecursoAcademico recurso) throws Exception {
        if (recurso.getCodigoRecursoAcademico() == null || !recursoAcademicoRepository.existsById(recurso.getCodigoRecursoAcademico())) {
            throw new Exception("Recurso no encontrado para actualizar");
        }
        return recursoAcademicoRepository.save(recurso);
    }

    // Nuevo método: agregarRecurso
    @Override
    public RecursoAcademico agregarRecurso(RecursoAcademico recurso) {
        return recursoAcademicoRepository.save(recurso);
    }

    // Nuevo método: eliminarRecurso
    @Override
    public void eliminarRecurso(Long idRecurso) throws Exception {
        if (!recursoAcademicoRepository.existsById(idRecurso)) {
            throw new Exception("Recurso no encontrado para eliminar");
        }
        recursoAcademicoRepository.deleteById(idRecurso);
    }

    // Nuevo método: reservarRecurso
    @Override
    public void reservarRecurso(Long idRecurso) throws Exception {
        RecursoAcademico recurso = recursoAcademicoRepository.findById(idRecurso)
                .orElseThrow(() -> new Exception("Recurso no encontrado"));

        if (!recurso.isDisponible()) {
            throw new Exception("El recurso no está disponible para reservar");
        }

        recurso.setDisponible(false); // Marcar como no disponible
        recursoAcademicoRepository.save(recurso);
    }

    // Nuevo método: verificarDisponibilidad
    @Override
    public boolean verificarDisponibilidad(Long idRecurso) {
        Optional<RecursoAcademico> recurso = recursoAcademicoRepository.findById(idRecurso);
        return recurso.map(RecursoAcademico::isDisponible).orElse(false);
    }

    // Nuevo método: gestionarMantenimiento
    @Override
    public void gestionarMantenimiento(Long idRecurso) throws Exception {
        RecursoAcademico recurso = recursoAcademicoRepository.findById(idRecurso)
                .orElseThrow(() -> new Exception("Recurso no encontrado"));

        recurso.setDisponible(false); // Marcar como no disponible para mantenimiento
        recursoAcademicoRepository.save(recurso);
    }
}
