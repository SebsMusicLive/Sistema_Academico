package com.spring.sistemaacademico.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sistemaAcademico.model.*;
import sistemaAcademico.repository.CursoHistorialRepository;
import sistemaAcademico.repository.CursoRepository;
import sistemaAcademico.repository.HorarioRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    private final CursoHistorialRepository cursoHistorialRepository;


    private HorarioRepository horarioRepository;

    @Override
    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    @Override
    public Semestre findById(Long id) {
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
        return cursoRepository.findByCodigoSemestre_Id(codigoSemestre);
    }

    @Override
    public List<Curso> findByCodigoDocenteId(Long codigoDocente) {
        return cursoRepository.findByCodigoDocente_CodigoDocente(codigoDocente);
    }

    @Override
    public void inscribirEstudiante(Long idCurso, Estudiante estudiante) {
        Optional<Curso> cursoOpt = cursoRepository.findById(idCurso);
        if (cursoOpt.isPresent()) {
            Curso curso = cursoOpt.get();
            if (curso.getEstudiantes() == null) {
                curso.setEstudiantes(new ArrayList<>());
            }
            curso.getEstudiantes().add(estudiante);
            cursoRepository.save(curso);
        }
    }

    @Override
    public void agregarPrerrequisito(Long idCurso, Curso prerrequisito) {
        Optional<Curso> cursoOpt = cursoRepository.findById(idCurso);
        if (cursoOpt.isPresent()) {
            Curso curso = cursoOpt.get();

            if (curso.getPrerequisitos() == null) {
                curso.setPrerequisitos(new ArrayList<>());
            }

            curso.getPrerequisitos().add(prerrequisito);
            cursoRepository.save(curso);
        }
    }

    @Override
    public boolean validarPrerrequisitos(Long codigoEstudiante, Long idCursoDestino) {
        Curso cursoDestino = cursoRepository.findById(idCursoDestino)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        // Obtener historial académico del estudiante
        List<CursoHistorial> historial = cursoHistorialRepository
                .findByHistorialAcademico_Estudiante_CodigoEstudiante(codigoEstudiante);

        // Obtener los códigos de los cursos aprobados
        List<Long> cursosAprobados = historial.stream()
                .filter(ch -> ch.getEstadoCurso() == EstadoCurso.APROBADO)
                .map(ch -> ch.getCurso().getCodigoCurso())
                .toList();

        // Validar que todos los prerrequisitos estén aprobados
        for (Curso prerrequisito : cursoDestino.getPrerequisitos()) {
            if (!cursosAprobados.contains(prerrequisito.getCodigoCurso())) {
                return false;
            }
        }

        return true;
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

        // Si usas estudiantes.size():
        return curso.getEstudiantes().size() < curso.getCupoMaximo();

    }

    @Override
    public Horario generarHorario() {
        // Obtener el curso del cual se generará el horario
        // Este ejemplo supone que tienes el curso disponible de alguna forma, por ejemplo, a través de un ID o una consulta
        Curso curso = new Curso(); // Obtén el curso (por ID o alguna otra forma)

        // Definir el tipo de sesión (por ejemplo, "Teoría", "Prácticas", etc.)
        String tipoSesion = "Teoría"; // Esto puede ser dinámico según el curso

        // Usamos Calendar para establecer las horas
        Calendar calendar = Calendar.getInstance();

        // Definir la hora de inicio (ejemplo: 08:00 AM)
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date horaInicio = calendar.getTime();

        // Definir la hora de fin (ejemplo: 10:00 AM)
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        Date horaFin = calendar.getTime();

        // Crear el objeto Horario y asignar los valores obtenidos
        Horario horario = new Horario();
        horario.setCodigoCurso(curso);  // Asignar el curso relacionado
        horario.setHoraInicio(horaInicio);  // Asignar la hora de inicio
        horario.setHoraFin(horaFin);  // Asignar la hora de fin
        horario.setTipoSesion(tipoSesion);  // Asignar el tipo de sesión

        // Guardar el horario en la base de datos si es necesario
        horarioRepository.save(horario);

        // Retornar el objeto Horario creado
        return horario;
    }

    @Override
    public boolean verificarDisponibilidad() {
        // Obtener todos los horarios de cursos
        List<Horario> todosLosHorarios = horarioRepository.findAll(); // Esto puede ser modificado dependiendo de cómo obtienes los cursos o los horarios

        // Comparar cada horario con todos los demás para verificar que no haya cruces
        for (int i = 0; i < todosLosHorarios.size(); i++) {
            for (int j = i + 1; j < todosLosHorarios.size(); j++) {
                Horario horario1 = todosLosHorarios.get(i);
                Horario horario2 = todosLosHorarios.get(j);

                // Verificar si los cursos tienen el mismo día, mismo tipo de sesión y se solapan
                if (horario1.getCodigoCurso().equals(horario2.getCodigoCurso())) {
                    continue; // Si son el mismo curso, no debe compararse consigo mismo
                }

                // Si los horarios se solapan
                if (verificarSolapamiento(horario1, horario2)) {
                    return false;  // Si hay solapamiento, se retorna falso
                }
            }
        }
        return true;  // Si no hay solapamiento, se retorna verdadero
    }

    // Método para verificar si dos horarios se solapan
    private boolean verificarSolapamiento(Horario horario1, Horario horario2) {
        // Verificar si las horas de inicio y fin se solapan
        boolean solapan = horario1.getHoraInicio().before(horario2.getHoraFin()) && horario1.getHoraFin().after(horario2.getHoraInicio());
        return solapan;
    }
    @Override
    public List<Curso> findByCodigoCarreraId(Long codigoCarrera) {
        // Llama al repositorio para obtener los cursos por codigoCarrera
        return cursoRepository.findByCodigoCarrera_Id(codigoCarrera);
    }
}
