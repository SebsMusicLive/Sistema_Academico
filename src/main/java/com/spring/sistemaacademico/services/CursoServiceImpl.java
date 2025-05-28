package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.*;
import com.spring.sistemaacademico.repositories.CursoHistorialRepository;
import com.spring.sistemaacademico.repositories.CursoRepository;
import com.spring.sistemaacademico.repositories.HorarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;
    private final CursoHistorialRepository cursoHistorialRepository;
    private final HorarioRepository horarioRepository;

    @Override
    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    @Override
    public Optional<Curso> findById(Long id) {
        return cursoRepository.findById(id);
    }

    @Override
    public Curso save(Curso entity) {
        return cursoRepository.save(entity);
    }

    @Override
    public Curso update(Curso entity) {
        return cursoRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        cursoRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        cursoRepository.deleteAll();
    }

    @Override
    public List<Curso> findByCodigoSemestreId(Long codigoSemestre) {
        return cursoRepository.findByCodigoSemestre_CodigoSemestre(codigoSemestre); // ✅ CORREGIDO
    }

    @Override
    public List<Curso> findByCodigoDocenteId(Long codigoDocente) {
        return cursoRepository.findByCodigoDocente_CodigoDocente(codigoDocente);
    }

    @Override
    public List<Curso> findByCodigoCarreraId(Long codigoCarrera) {
        return cursoRepository.findByCodigoCarrera_CodigoCarrera(codigoCarrera);
    }

    @Override
    public void inscribirEstudiante(Long idCurso, Estudiante estudiante) {
        cursoRepository.findById(idCurso).ifPresent(curso -> {
            if (curso.getEstudiantes() == null) curso.setEstudiantes(new ArrayList<>());
            curso.getEstudiantes().add(estudiante);
            cursoRepository.save(curso);
        });
    }

    @Override
    public void agregarPrerrequisito(Long idCurso, Curso prerrequisito) {
        cursoRepository.findById(idCurso).ifPresent(curso -> {
            if (curso.getPrerequisitos() == null) curso.setPrerequisitos(new ArrayList<>());
            curso.getPrerequisitos().add(prerrequisito);
            cursoRepository.save(curso);
        });
    }

    @Override
    public boolean validarPrerrequisitos(Long codigoEstudiante, Long idCursoDestino) {
        Curso cursoDestino = cursoRepository.findById(idCursoDestino)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        List<CursoHistorial> historial = cursoHistorialRepository
                .findByHistorialAcademico_Estudiante_CodigoEstudiante(codigoEstudiante);

        List<Long> cursosAprobados = historial.stream()
                .filter(ch -> ch.getEstadoCurso() == EstadoCurso.APROBADO)
                .map(ch -> ch.getCurso().getCodigoCurso())
                .toList();

        return cursoDestino.getPrerequisitos().stream()
                .allMatch(prerrequisito -> cursosAprobados.contains(prerrequisito.getCodigoCurso()));
    }

    @Override
    public void agregarCurso(Curso curso) {
        cursoRepository.save(curso);
    }

    @Override
    public void eliminarCurso(Long idCurso) {
        if (cursoRepository.existsById(idCurso)) {
            cursoRepository.deleteById(idCurso);
        } else {
            throw new RuntimeException("Curso con ID " + idCurso + " no encontrado.");
        }
    }

    @Override
    public boolean validarCupos(Long idCurso) {
        Curso curso = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new RuntimeException("Curso con ID " + idCurso + " no encontrado."));
        return curso.getEstudiantes().size() < curso.getCupoMaximo();
    }

    @Override
    public Horario generarHorario() {
        Curso curso = new Curso(); // Sustituir esto por lógica real
        String tipoSesion = "Teoría";

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        Date horaInicio = calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, 10);
        Date horaFin = calendar.getTime();

        Horario horario = new Horario();
        horario.setCodigoCurso(curso);
        horario.setHoraInicio(horaInicio);
        horario.setHoraFin(horaFin);
        horario.setTipoSesion(tipoSesion);

        return horarioRepository.save(horario);
    }

    @Override
    public boolean verificarDisponibilidad() {
        List<Horario> horarios = horarioRepository.findAll();

        for (int i = 0; i < horarios.size(); i++) {
            for (int j = i + 1; j < horarios.size(); j++) {
                Horario h1 = horarios.get(i), h2 = horarios.get(j);
                if (!h1.getCodigoCurso().equals(h2.getCodigoCurso()) && verificarSolapamiento(h1, h2)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean verificarSolapamiento(Horario h1, Horario h2) {
        return h1.getHoraInicio().before(h2.getHoraFin()) && h1.getHoraFin().after(h2.getHoraInicio());
    }
}