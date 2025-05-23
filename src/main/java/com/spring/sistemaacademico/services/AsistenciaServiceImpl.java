package com.spring.sistemaacademico.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sistemaAcademico.model.Asistencia;
import sistemaAcademico.model.Semestre;
import sistemaAcademico.repository.AsistenciaRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AsistenciaServiceImpl implements AsistenciaService{

    // Inyección de dependencias
    private final AsistenciaRepository asistenciaRepository;

    @Override
    public List<Asistencia> findAll() {
        return asistenciaRepository.findAll();
    }

    @Override
    public Semestre findById(Long id) {
        return asistenciaRepository.findById(id);
    }

    @Override
    public Asistencia save(Asistencia asistencia) {
        return asistenciaRepository.save(asistencia);
    }

    @Override
    public Asistencia update(Asistencia asistencia) throws Exception {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        asistenciaRepository.deleteById(id);
    }

    @Override
    public void deleteAll() throws Exception {

    }

    // Métodos personalizados
    @Override
    public List<Asistencia> findByFechaAsistencia(Date fechaAsistencia) {
        return asistenciaRepository.findByFechaAsistencia(fechaAsistencia);
    }

    @Override
    public List<Asistencia> findByAsistencia(boolean asistencia) {
        return asistenciaRepository.findByAsistencia(asistencia);
    }
}
