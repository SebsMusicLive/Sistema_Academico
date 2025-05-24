package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Departamento;
import com.spring.sistemaacademico.model.Docente;
import com.spring.sistemaacademico.repositories.DepartamentoRepository;
import com.spring.sistemaacademico.repositories.DocenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartamentoServiceImpl implements DepartamentoService {

    private final DepartamentoRepository departamentoRepository;
    private final DocenteRepository docenteRepository;

    @Override
    public List<Departamento> findAll() throws Exception {
        return departamentoRepository.findAll();
    }

    @Override
    public Optional<Departamento> findById(Long id) throws Exception {
        return departamentoRepository.findById(id);
    }

    @Override
    public Departamento save(Departamento departamento) throws Exception {
        return departamentoRepository.save(departamento);
    }

    @Override
    public Departamento update(Departamento departamento) throws Exception {
        if (departamento.getCodigoDepartamento() == null || !departamentoRepository.existsById(departamento.getCodigoDepartamento())) {
            throw new Exception("El departamento no existe o no tiene un ID válido");
        }
        return departamentoRepository.save(departamento);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        departamentoRepository.deleteById(id);
    }

    @Override
    public void deleteAll() throws Exception {
        departamentoRepository.deleteAll();
    }

    @Override
    public List<Departamento> findByNombre(String nombre) {
        return departamentoRepository.findByNombre(nombre);
    }

    // Método extra personalizado (fuera del CrudService)
    public Departamento asignarDocente(Long departamentoId, Long docenteId) throws Exception {
        Departamento departamento = findById(departamentoId)
                .orElseThrow(() -> new Exception("Departamento no encontrado"));

        Docente docente = docenteRepository.findById(docenteId)
                .orElseThrow(() -> new Exception("Docente no encontrado"));

        departamento.getDocentes().add(docente);
        return departamentoRepository.save(departamento);
    }
}