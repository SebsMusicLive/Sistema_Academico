package com.spring.sistemaacademico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistemaAcademico.model.Semestre;
import sistemaAcademico.repository.SemestreRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public abstract class SemestreServiceImpl implements SemestreService {

    @Autowired
    private SemestreRepository semestreRepository;

    private Long semestreActivoId; // Suponiendo que se guarda el semestre actual activo

    // Método para definir fechas de inicio y fin del semestre activo
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

    // Método para cerrar el semestre (ej: quitarlo como activo)
    public void cerrarSemestre() throws Exception {
        if (semestreActivoId == null) {
            throw new Exception("No hay semestre activo para cerrar.");
        }
        semestreActivoId = null; // Cierra el semestre (ejemplo de implementación simple)
    }

    // Método para generar un calendario académico en formato String
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

    // Métodos de CrudService

    @Override
    public Semestre save(Semestre semestre) throws Exception {
        Semestre saved = semestreRepository.save(semestre);
        semestreActivoId = saved.getCodigoSemestre(); // Asignar como activo al guardar
        return saved;
    }

    @Override
    public Semestre findById(Long id) throws Exception {
        return semestreRepository.findById(id)
                .orElseThrow(() -> new Exception("Semestre no encontrado"));
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
    public Semestre update(Semestre semestre) throws Exception {
        if (!semestreRepository.existsById(semestre.getCodigoSemestre())) {
            throw new Exception("Semestre no encontrado para actualizar");
        }
        return semestreRepository.save(semestre);
    }
}
