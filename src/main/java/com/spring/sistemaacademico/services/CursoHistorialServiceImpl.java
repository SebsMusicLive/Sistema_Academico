package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.CursoHistorial;
import com.spring.sistemaacademico.repositories.CursoHistorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CursoHistorialServiceImpl implements CursoHistorialService {

    private final CursoHistorialRepository cursoHistorialRepository;

    @Override
    public List<CursoHistorial> findAll() {
        return cursoHistorialRepository.findAll();
    }

    @Override
    public CursoHistorial save(CursoHistorial entity) {
        return cursoHistorialRepository.save(entity);
    }

    @Override
    public CursoHistorial update(CursoHistorial entity) {
        return cursoHistorialRepository.save(entity);
    }

    @Override
    public Optional<CursoHistorial> findById(Long id) {
        return cursoHistorialRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        cursoHistorialRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        cursoHistorialRepository.deleteAll();
    }

    @Override
    public List<CursoHistorial> findByCalificacionFinal(float calificacionFinal) {
        return cursoHistorialRepository.findByCalificacionFinal(calificacionFinal);
    }

    @Override
    public List<CursoHistorial> findByEstudianteCodigoEstudiante(Long codigoEstudiante) {
        return cursoHistorialRepository.findByHistorialAcademico_Estudiante_CodigoEstudiante(codigoEstudiante);
    }
}
