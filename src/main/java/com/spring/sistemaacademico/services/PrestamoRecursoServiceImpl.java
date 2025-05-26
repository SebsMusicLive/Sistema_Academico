package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.PrestamoRecurso;
import com.spring.sistemaacademico.repositories.PrestamoRecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Optional<PrestamoRecurso> findById(Long id) throws Exception {
        Optional<PrestamoRecurso> prestamo = prestamoRecursoRepository.findById(id);
        if (!prestamo.isPresent()) {
            throw new Exception("Préstamo con id " + id + " no encontrado");
        }
        return prestamo;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        if (!prestamoRecursoRepository.existsById(id)) {
            throw new Exception("No se puede eliminar: préstamo con id " + id + " no existe");
        }
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

    public PrestamoRecurso registrarPrestamo(PrestamoRecurso prestamo) throws Exception {
        if (prestamo == null) {
            throw new Exception("El préstamo no puede ser nulo");
        }
        prestamo.setFechaPrestamo(new Date()); // Fecha actual
        return prestamoRecursoRepository.save(prestamo);
    }

    public PrestamoRecurso devolverRecurso(Long prestamoId) throws Exception {
        PrestamoRecurso prestamo = findById(prestamoId).orElseThrow(() -> new Exception("Préstamo no encontrado"));
        prestamo.setFechaDevolucion(new Date()); // Fecha devolución actual
        return prestamoRecursoRepository.save(prestamo);
    }

    public PrestamoRecurso extenderPlazo(Long prestamoId, Date nuevaFechaDevolucion) throws Exception {
        PrestamoRecurso prestamo = findById(prestamoId).orElseThrow(() -> new Exception("Préstamo no encontrado"));
        prestamo.setFechaDevolucion(nuevaFechaDevolucion);
        return prestamoRecursoRepository.save(prestamo);
    }

    public double generarMulta(Long prestamoId) throws Exception {
        PrestamoRecurso prestamo = findById(prestamoId).orElseThrow(() -> new Exception("Préstamo no encontrado"));

        if (prestamo.getFechaDevolucion() == null) {
            throw new Exception("El recurso no ha sido devuelto aún");
        }

        long diffMillis = new Date().getTime() - prestamo.getFechaDevolucion().getTime();
        long diffDays = diffMillis / (1000 * 60 * 60 * 24);

        if (diffDays > 0) {
            return diffDays * 5.0;  // multa $5 por día
        } else {
            return 0.0;
        }
    }
}
