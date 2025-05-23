package com.spring.sistemaacademico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistemaAcademico.model.*;
import sistemaAcademico.repository.HistorialAcademicoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistorialAcademicoServiceImpl implements HistorialAcademicoService {

    @Autowired
    private HistorialAcademicoRepository historialAcademicoRepository;

    @Override
    public List<HistorialAcademico> findAll() throws Exception {
        return historialAcademicoRepository.findAll();
    }

    @Override
    public HistorialAcademico save(HistorialAcademico historial) throws Exception {
        return historialAcademicoRepository.save(historial);
    }

    @Override
    public HistorialAcademico update(HistorialAcademico historial) throws Exception {
        if (historial.getCodigoHistorialAcademico() == null ||
                !historialAcademicoRepository.existsById(historial.getCodigoHistorialAcademico())) {
            throw new Exception("El historial no existe o no tiene ID válido");
        }
        return historialAcademicoRepository.save(historial);
    }

    @Override
    public Semestre findById(Long id) throws Exception {
        return historialAcademicoRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        historialAcademicoRepository.deleteById(id);
    }

    @Override
    public void deleteAll() throws Exception {
        historialAcademicoRepository.deleteAll();
    }

    @Override
    public List<HistorialAcademico> findByPromedioGeneral(float promedioGeneral) {
        return historialAcademicoRepository.findByPromedioGeneral(promedioGeneral);
    }

    // Métodos personalizados

    public String generarReporteDesempeño(Long idHistorial) throws Exception {
        HistorialAcademico historial = findById(idHistorial)
                .orElseThrow(() -> new Exception("Historial no encontrado"));
        return "Promedio general del estudiante: " + historial.getPromedioGeneral();
    }

    public float calcularProyeccionRendimiento(Long idHistorial) throws Exception {
        HistorialAcademico historial = findById(idHistorial)
                .orElseThrow(() -> new Exception("Historial no encontrado"));

        List<CursoHistorial> cursos = historial.getCursoHistorial();
        if (cursos.isEmpty()) {
            return 0.0f;
        }

        float total = 0;
        for (CursoHistorial ch : cursos) {
            total += ch.getCalificacionFinal();
        }

        return total / cursos.size();
    }

    public List<CursoHistorial> generarReporteCursosAprobados(Long idHistorial) throws Exception {
        HistorialAcademico historial = findById(idHistorial)
                .orElseThrow(() -> new Exception("Historial no encontrado"));

        return historial.getCursoHistorial().stream()
                .filter(ch -> ch.getCalificacionFinal() >= 3.0f)
                .collect(Collectors.toList());
    }

    public List<CursoHistorial> generarReporteCursosEnProceso(Long idHistorial) throws Exception {
        HistorialAcademico historial = findById(idHistorial)
                .orElseThrow(() -> new Exception("Historial no encontrado"));

        return historial.getCursoHistorial().stream()
                .filter(ch -> ch.getEstadoCurso() == EstadoCurso.EN_CURSO)
                .collect(Collectors.toList());
    }

    public HistorialAcademico agregarCursoHistorial(Long idHistorial, CursoHistorial nuevoCurso) throws Exception {
        HistorialAcademico historial = findById(idHistorial)
                .orElseThrow(() -> new Exception("Historial no encontrado"));

        nuevoCurso.setHistorialAcademico(historial);
        historial.getCursoHistorial().add(nuevoCurso);

        return historialAcademicoRepository.save(historial);
    }

    public List<CursoHistorial> consultarHistorial(Long idHistorial) throws Exception {
        HistorialAcademico historial = findById(idHistorial)
                .orElseThrow(() -> new Exception("Historial no encontrado"));

        return historial.getCursoHistorial();
    }
}
