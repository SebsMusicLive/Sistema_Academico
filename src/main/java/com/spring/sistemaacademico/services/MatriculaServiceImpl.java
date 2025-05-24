package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Matricula;
import com.spring.sistemaacademico.repositories.MatriculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatriculaServiceImpl implements MatriculaService {

    private final MatriculaRepository matriculaRepository;

    @Override
    public List<Matricula> findAll() {
        return matriculaRepository.findAll();
    }

    @Override
    public Matricula save(Matricula matricula) {
        return matriculaRepository.save(matricula);
    }

    @Override
    public Matricula update(Matricula matricula) throws Exception {
        if (matricula.getCodigoMatricula() == null || !matriculaRepository.existsById(matricula.getCodigoMatricula())) {
            throw new Exception("La matrícula no existe o no tiene ID válido.");
        }
        return matriculaRepository.save(matricula);
    }

    @Override
    public Optional<Matricula> findById(Long id) {
        return matriculaRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        matriculaRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        matriculaRepository.deleteAll();
    }
}
