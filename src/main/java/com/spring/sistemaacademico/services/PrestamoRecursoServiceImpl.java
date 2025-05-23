package com.spring.sistemaacademico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistemaAcademico.model.PrestamoRecurso;
import sistemaAcademico.model.Semestre;
import sistemaAcademico.repository.PrestamoRecursoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PrestamoRecursoServiceImpl implements PrestamoRecursoService {

    @Autowired
    private PrestamoRecursoRepository prestamoRecursoRepository;

    @Override
    public List<PrestamoRecurso> findAll() throws Exception {
        return prestamoRecursoRepository.findAll();
    }

    @Override
    public PrestamoRecurso save(PrestamoRecurso prestamo) throws Exception {
        return prestamoRecursoRepository.save(prestamo);
    }

    @Override
    public PrestamoRecurso update(PrestamoRecurso prestamo) throws Exception {
        if (prestamo.getCodigoPrestamoRecurso() == null ||
                !prestamoRecursoRepository.existsById(prestamo.getCodigoPrestamoRecurso())) {
            throw new Exception("El préstamo no existe o no tiene ID válido");
        }
        return prestamoRecursoRepository.save(prestamo);
    }

    @Override
    public Semestre findById(Long id) throws Exception {
        return prestamoRecursoRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        prestamoRecursoRepository.deleteById(id);
    }

    @Override
    public void deleteAll() throws Exception {
        prestamoRecursoRepository.deleteAll();
    }

    @Override
    public List<PrestamoRecurso> findByFechaPrestamo(Date fechaPrestamo) {
        return prestamoRecursoRepository.findByFechaPrestamo(fechaPrestamo);
    }

    @Override
    public List<PrestamoRecurso> findByFechaDevolucion(Date fechaDevolucion) {
        return prestamoRecursoRepository.findByFechaDevolucion(fechaDevolucion);
    }

    // Registrar un nuevo préstamo
    public PrestamoRecurso registrarPrestamo(PrestamoRecurso prestamo) throws Exception {
        if (prestamo == null) {
            throw new Exception("El préstamo no puede ser nulo");
        }
        prestamo.setFechaPrestamo(new Date()); // Establece la fecha de préstamo actual
        return prestamoRecursoRepository.save(prestamo);
    }

    // Marcar un recurso como devuelto y actualizar la fecha de devolución
    public PrestamoRecurso devolverRecurso(Long prestamoId) throws Exception {
        Optional<PrestamoRecurso> prestamoOpt = prestamoRecursoRepository.findById(prestamoId);
        if (!prestamoOpt.isPresent()) {
            throw new Exception("Préstamo no encontrado");
        }

        PrestamoRecurso prestamo = prestamoOpt.get();
        prestamo.setFechaDevolucion(new Date()); // Establece la fecha de devolución actual
        return prestamoRecursoRepository.save(prestamo);
    }

    // Extender el plazo de devolución de un recurso
    public PrestamoRecurso extenderPlazo(Long prestamoId, Date nuevaFechaDevolucion) throws Exception {
        Optional<PrestamoRecurso> prestamoOpt = prestamoRecursoRepository.findById(prestamoId);
        if (!prestamoOpt.isPresent()) {
            throw new Exception("Préstamo no encontrado");
        }

        PrestamoRecurso prestamo = prestamoOpt.get();
        prestamo.setFechaDevolucion(nuevaFechaDevolucion); // Establece la nueva fecha de devolución
        return prestamoRecursoRepository.save(prestamo);
    }

    // Generar multa si el recurso no ha sido devuelto en el plazo
    public double generarMulta(Long prestamoId) throws Exception {
        Optional<PrestamoRecurso> prestamoOpt = prestamoRecursoRepository.findById(prestamoId);
        if (!prestamoOpt.isPresent()) {
            throw new Exception("Préstamo no encontrado");
        }

        PrestamoRecurso prestamo = prestamoOpt.get();
        if (prestamo.getFechaDevolucion() == null) {
            throw new Exception("El recurso no ha sido devuelto aún");
        }

        long diferenciaEnMilisegundos = new Date().getTime() - prestamo.getFechaDevolucion().getTime();
        long diferenciaEnDias = diferenciaEnMilisegundos / (1000 * 60 * 60 * 24);

        if (diferenciaEnDias > 0) {
            // Se genera una multa por cada día de retraso (por ejemplo, $5 por día)
            return diferenciaEnDias * 5.0;
        } else {
            return 0.0; // No hay multa si el recurso fue devuelto a tiempo
        }
    }
}
