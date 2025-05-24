package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.CursoHistorial;
import com.spring.sistemaacademico.model.EstadoCurso;
import com.spring.sistemaacademico.model.HistorialAcademico;
import com.spring.sistemaacademico.repositories.HistorialAcademicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistorialAcademicoServiceImpl implements HistorialAcademicoService {

    private final HistorialAcademicoRepository historialRepo;

    @Override
    public List<HistorialAcademico> findAll() {
        return historialRepo.findAll();
    }

    @Override
    public Optional<HistorialAcademico> findById(Long id) {
        return historialRepo.findById(id);
    }

    @Override
    public HistorialAcademico save(HistorialAcademico historial) {
        return historialRepo.save(historial);
    }

    @Override
    public HistorialAcademico update(HistorialAcademico historial) throws Exception {
        if (historial.getCodigoHistorialAcademico() == null || !historialRepo.existsById(historial.getCodigoHistorialAcademico())) {
            throw new Exception("Historial no encontrado");
        }
        return historialRepo.save(historial);
    }

    @Override
    public void deleteById(Long id) {
        historialRepo.deleteById(id);
    }

    @Override
    public void deleteAll() {
        historialRepo.deleteAll();
    }

    @Override
    public List<HistorialAcademico> findByPromedioGeneral(float promedioGeneral) {
        return historialRepo.findByPromedioGeneral(promedioGeneral);
    }

    @Override
    public String generarReporteDesempeno(Long idHistorial) throws Exception {
        HistorialAcademico historial = findById(idHistorial)
                .orElseThrow(() -> new Exception("Historial no encontrado"));
        return "Promedio general del estudiante: " + historial.getPromedioGeneral();
    }

    @Override
    public float calcularProyeccionRendimiento(Long idHistorial) throws Exception {
        HistorialAcademico historial = findById(idHistorial)
                .orElseThrow(() -> new Exception("Historial no encontrado"));

        List<CursoHistorial> cursos = historial.getCursoHistorial();
        if (cursos.isEmpty()) return 0.0f;

        float suma = 0;
        for (CursoHistorial ch : cursos) {
            suma += ch.getCalificacionFinal();
        }

        return suma / cursos.size();
    }

    @Override
    public List<CursoHistorial> generarReporteCursosAprobados(Long idHistorial) throws Exception {
        HistorialAcademico historial = findById(idHistorial)
                .orElseThrow(() -> new Exception("Historial no encontrado"));

        return historial.getCursoHistorial().stream()
                .filter(ch -> ch.getCalificacionFinal() >= 3.0f)
                .collect(Collectors.toList());
    }

    @Override
    public List<CursoHistorial> generarReporteCursosEnProceso(Long idHistorial) throws Exception {
        HistorialAcademico historial = findById(idHistorial)
                .orElseThrow(() -> new Exception("Historial no encontrado"));

        return historial.getCursoHistorial().stream()
                .filter(ch -> ch.getEstadoCurso() == EstadoCurso.EN_CURSO)
                .collect(Collectors.toList());
    }

    @Override
    public HistorialAcademico agregarCursoHistorial(Long idHistorial, CursoHistorial nuevoCurso) throws Exception {
        HistorialAcademico historial = findById(idHistorial)
                .orElseThrow(() -> new Exception("Historial no encontrado"));

        nuevoCurso.setHistorialAcademico(historial);
        historial.getCursoHistorial().add(nuevoCurso);
        return historialRepo.save(historial);
    }

    @Override
    public List<CursoHistorial> consultarHistorial(Long idHistorial) throws Exception {
        HistorialAcademico historial = findById(idHistorial)
                .orElseThrow(() -> new Exception("Historial no encontrado"));

        return historial.getCursoHistorial();
    }
}
