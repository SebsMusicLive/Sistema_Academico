package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Curso;
import com.spring.sistemaacademico.model.Horario;
import com.spring.sistemaacademico.repositories.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HorarioServiceImpl implements HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    @Override
    public List<Horario> findAll() {
        return horarioRepository.findAll();
    }

    @Override
    public Optional<Horario> findById(Long id) {
        return horarioRepository.findById(id);
    }

    @Override
    public Horario save(Horario horario) {
        return horarioRepository.save(horario);
    }

    @Override
    public Horario update(Horario horario) throws Exception {
        if (horario.getCodigoHorario() == null || !horarioRepository.existsById(horario.getCodigoHorario())) {
            throw new Exception("El horario no existe o no tiene ID válido.");
        }
        return horarioRepository.save(horario);
    }

    @Override
    public void deleteById(Long id) {
        horarioRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        horarioRepository.deleteAll();
    }

    @Override
    public List<Horario> findByCodigoCurso(Curso codigoCurso) {
        return horarioRepository.findByCodigoCurso(codigoCurso);
    }

    @Override
    public List<Horario> findByHoraInicio(Date horaInicio) {
        return horarioRepository.findByHoraInicio(horaInicio);
    }

    @Override
    public List<Horario> findByHoraFin(Date horaFin) {
        return horarioRepository.findByHoraFin(horaFin);
    }

    @Override
    public List<Horario> findByTipoSesion(String tipoSesion) {
        return horarioRepository.findByTipoSesion(tipoSesion);
    }

    @Override
    public Horario modificarHorario(Long id, Date nuevaHoraInicio, Date nuevaHoraFin) throws Exception {
        Horario horario = horarioRepository.findById(id)
                .orElseThrow(() -> new Exception("Horario no encontrado con ID: " + id));
        horario.setHoraInicio(nuevaHoraInicio);
        horario.setHoraFin(nuevaHoraFin);
        return horarioRepository.save(horario);
    }

    @Override
    public Horario asignarHorario(Curso curso, Date horaInicio, Date horaFin, String tipoSesion) {
        Horario nuevoHorario = new Horario();
        nuevoHorario.setCodigoCurso(curso);
        nuevoHorario.setHoraInicio(horaInicio);
        nuevoHorario.setHoraFin(horaFin);
        nuevoHorario.setTipoSesion(tipoSesion);
        return horarioRepository.save(nuevoHorario);
    }

    @Override
    public boolean verificarDisponibilidad(Curso curso, Date horaInicio, Date horaFin) {
        List<Horario> horariosExistentes = horarioRepository.findByCodigoCurso(curso);
        for (Horario h : horariosExistentes) {
            if (horaInicio.before(h.getHoraFin()) && horaFin.after(h.getHoraInicio())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<String> optimizarUsoRecursos() {
        List<Horario> todos = horarioRepository.findAll();
        List<String> recomendaciones = new ArrayList<>();

        Map<Curso, List<Horario>> horariosPorCurso = todos.stream()
                .collect(Collectors.groupingBy(Horario::getCodigoCurso));

        for (Map.Entry<Curso, List<Horario>> entry : horariosPorCurso.entrySet()) {
            Curso curso = entry.getKey();
            List<Horario> horarios = entry.getValue();
            horarios.sort(Comparator.comparing(Horario::getHoraInicio));

            for (int i = 0; i < horarios.size() - 1; i++) {
                Horario actual = horarios.get(i);
                Horario siguiente = horarios.get(i + 1);

                long diferencia = siguiente.getHoraInicio().getTime() - actual.getHoraFin().getTime();

                if (diferencia > 3600000) {
                    String mensaje = "Curso '" + curso.getNombre() + "' tiene hueco de "
                            + (diferencia / 60000) + " minutos entre sesiones: "
                            + actual.getHoraFin() + " y " + siguiente.getHoraInicio();
                    recomendaciones.add(mensaje);
                }
            }
        }

        if (recomendaciones.isEmpty()) {
            recomendaciones.add("No se detectaron huecos relevantes entre sesiones.");
        }

        return recomendaciones;
    }
}
