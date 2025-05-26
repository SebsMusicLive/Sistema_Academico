package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Semestre;
import com.spring.sistemaacademico.repositories.SemestreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SemestreServiceImpl implements SemestreService {

    private final SemestreRepository semestreRepository;

    private Long semestreActivoId; // Id del semestre activo, se puede mejorar con otra estrategia

    @Autowired
    public SemestreServiceImpl(SemestreRepository semestreRepository) {
        this.semestreRepository = semestreRepository;
    }

    public void definirFechasInicioFin(Date fechaInicio, Date fechaFin) throws Exception {
        if (semestreActivoId == null) {
            throw new Exception("No hay semestre activo seleccionado.");
        }

        Semestre semestre = semestreRepository.findById(semestreActivoId)
                .orElseThrow(() -> new Exception("Semestre no encontrado"));

        semestre.setFechaInicio(fechaInicio);
        semestre.setFechaFin(fechaFin);
        semestreRepository.save(semestre);
    }

    public void cerrarSemestre() throws Exception {
        if (semestreActivoId == null) {
            throw new Exception("No hay semestre activo para cerrar.");
        }
        semestreActivoId = null;
    }

    public String generarCalendarioAcademico() throws Exception {
        if (semestreActivoId == null) {
            throw new Exception("No hay semestre activo.");
        }

        Semestre semestre = semestreRepository.findById(semestreActivoId)
                .orElseThrow(() -> new Exception("Semestre no encontrado."));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Calendario Académico\n" +
                "----------------------\n" +
                "Semestre: " + semestre.getNumeroSemestre() + "\n" +
                "Inicio: " + sdf.format(semestre.getFechaInicio()) + "\n" +
                "Fin: " + sdf.format(semestre.getFechaFin()) + "\n";
    }

    @Override
    public Semestre save(Semestre semestre) throws Exception {
        Semestre saved = semestreRepository.save(semestre);
        semestreActivoId = saved.getCodigoSemestre();
        return saved;
    }

    @Override
    public Optional<Semestre> findById(Long id) throws Exception {
        return semestreRepository.findById(id);
    }

    @Override
    public List<Semestre> findAll() throws Exception {
        return semestreRepository.findAll();
    }

    @Override
    public void deleteById(Long id) throws Exception {
        semestreRepository.deleteById(id);
    }

    @Override
    public void deleteAll() throws Exception {
        semestreRepository.deleteAll();
    }

    @Override
    public Semestre update(Semestre semestre) throws Exception {
        if (!semestreRepository.existsById(semestre.getCodigoSemestre())) {
            throw new Exception("Semestre no encontrado para actualizar");
        }
        return semestreRepository.save(semestre);
    }
}
