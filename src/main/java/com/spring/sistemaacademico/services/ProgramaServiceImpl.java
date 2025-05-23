package com.spring.sistemaacademico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistemaAcademico.model.Curso;
import sistemaAcademico.model.Programa;
import sistemaAcademico.model.Semestre;
import sistemaAcademico.repository.CursoRepository;
import sistemaAcademico.repository.ProgramaRepository;

import java.util.List;

@Service
public class ProgramaServiceImpl implements ProgramaService {

    @Autowired
    private ProgramaRepository programaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public List<Programa> findAll() throws Exception {
        return programaRepository.findAll();
    }

    @Override
    public Programa save(Programa programa) throws Exception {
        return programaRepository.save(programa);
    }

    @Override
    public Programa update(Programa programa) throws Exception {
        if (programa.getCodigoPrograma() == null || !programaRepository.existsById(programa.getCodigoPrograma())) {
            throw new Exception("El programa no existe o no tiene un ID válido");
        }
        return programaRepository.save(programa);
    }

    @Override
    public Semestre findById(Long id) throws Exception {
        return programaRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        programaRepository.deleteById(id);
    }

    @Override
    public void deleteAll() throws Exception {
        programaRepository.deleteAll();
    }

    @Override
    public List<Programa> findByNombre(String nombre) {
        return programaRepository.findByNombre(nombre);
    }

    @Override
    public List<Programa> findByDescripcion(String descripcion) {
        return programaRepository.findByDescripcion(descripcion);
    }

    // 👉 Métodos personalizados

    @Override
    public Programa registrarPrograma(Programa programa) throws Exception {
        if (programa == null) {
            throw new Exception("El programa no puede ser nulo");
        }
        return programaRepository.save(programa);
    }

    @Override
    public void eliminarPrograma(Long id) throws Exception {
        if (id == null || !programaRepository.existsById(id)) {
            throw new Exception("Programa no encontrado");
        }
        programaRepository.deleteById(id);
    }

    @Override
    public Programa agregarCurso(Long programaId, Long cursoId) throws Exception {
        Programa programa = programaRepository.findById(programaId)
                .orElseThrow(() -> new Exception("Programa no encontrado"));

        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new Exception("Curso no encontrado"));

        List<Curso> cursos = programa.getCursos();
        if (!cursos.contains(curso)) {
            cursos.add(curso);
        }

        return programaRepository.save(programa);
    }

    @Override
    public Programa actualizarContenidos(Long programaId, String nuevosContenidos) throws Exception {
        Programa programa = programaRepository.findById(programaId)
                .orElseThrow(() -> new Exception("Programa no encontrado"));

        programa.setDescripcion(nuevosContenidos); // Cambiado a 'descripcion' como campo existente

        return programaRepository.save(programa);
    }
}
