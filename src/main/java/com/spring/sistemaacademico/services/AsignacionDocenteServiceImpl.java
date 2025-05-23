package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.AsignacionDocente;
import com.spring.sistemaacademico.model.Curso;
import com.spring.sistemaacademico.model.Docente;
import com.spring.sistemaacademico.model.Horario;
import com.spring.sistemaacademico.repositories.AsignacionDocenteRepository;
import lombok.RequiredArgsConstructor;
import org.jvnet.hk2.annotations.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AsignacionDocenteServiceImpl implements AsignacionDocenteService {

    private final AsignacionDocenteRepository repository;

    @Override
    public List<AsignacionDocente> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<AsignacionDocente> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public AsignacionDocente save(AsignacionDocente entity) {
        return repository.save(entity);
    }

    @Override
    public AsignacionDocente update(AsignacionDocente entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public List<AsignacionDocente> findByFecha(Date fecha) {
        return repository.findByFecha(fecha);
    }

    @Override
    public List<AsignacionDocente> findByCargaHoraria(int cargaHoraria) {
        return repository.findByCargaHoraria(cargaHoraria);
    }
    @Override
    public void asignarCurso(Docente docente, Curso curso) {
        if (verificarDisponibilidad(docente, curso.getHorario())) {
            docente.asignarCurso(curso);
            ajustarCargaHoraria(docente.getCargaHoraria() + curso.getHoras());
        } else {
            throw new DisponibilidadException("El docente no está disponible en el horario del curso.");
        }
    }

    @Override
    public boolean verificarDisponibilidad(Docente docente, Horario horario) {
        return docente.getHorariosDisponibles().contains(horario) && docente.getCargaHoraria() < docente.getMaxHoras();
    }

    @Override
    public void ajustarCargaHoraria(Docente docente, int nuevaCarga) {
        if (nuevaCarga <= docente.getMaxHoras()) {
            docente.setCargaHoraria(nuevaCarga);
        } else {
            throw new CargaExcesivaException("La nueva carga excede el límite permitido.");
        }
    }
}

