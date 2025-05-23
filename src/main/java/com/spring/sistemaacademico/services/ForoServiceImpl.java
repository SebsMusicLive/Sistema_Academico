package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Foro;
import com.spring.sistemaacademico.repositories.ForoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ForoServiceImpl implements ForoService{

    private final ForoRepository foroRepository;

    @Override
    public List<Foro> findAll() throws Exception {
        return foroRepository.findAll();
    }

    @Override
    public Optional<Foro> findById(Long id) throws Exception {
        return foroRepository.findById(id);
    }

    @Override
    public Foro save(Foro entity) throws Exception {
        entity.setFechaCreacion(new Date()); // Asignamos la fecha automáticamente
        return foroRepository.save(entity);
    }

    @Override
    public Foro update(Foro entity) throws Exception {
        return foroRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        foroRepository.deleteById(id);
    }

    @Override
    public void deleteAll() throws Exception {
        foroRepository.deleteAll();
    }

    // Métodos personalizados

    @Override
    public List<Foro> findByTitulo(String titulo) throws Exception {
        return foroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    @Override
    public List<Foro> findByDescripcion(String descripcion) throws Exception {
        return foroRepository.findByDescripcionContainingIgnoreCase(descripcion);
    }

    @Override
    public List<Foro> findByFechaCreacion(Date fechaCreacion) throws Exception {
        return foroRepository.findByFechaCreacion(fechaCreacion);
    }
}
