package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Departamento;
import com.spring.sistemaacademico.model.Docente;
import com.spring.sistemaacademico.model.Semestre;
import com.spring.sistemaacademico.repositories.DepartamentoRepository;
import com.spring.sistemaacademico.repositories.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private DocenteRepository docenteRepository;  // Inyección del repositorio de Docente

    @Override
    public List<Departamento> findAll() throws Exception {
        return departamentoRepository.findAll();
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
    public Semestre findById(Long id) throws Exception {
        return departamentoRepository.findById(id);
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
    public List<Departamento> findByCodigoDepartamento(String codigoDepartamento) {
        return departamentoRepository.findByCodigoDepartamento(codigoDepartamento);
    }

    @Override
    public List<Departamento> findByNombre(String nombre) {
        return departamentoRepository.findByNombre(nombre);
    }

    // Métodos adicionales para agregar lógica de negocio
    public Departamento agregarDepartamento(Departamento departamento) throws Exception {
        if (departamento == null) {
            throw new Exception("El departamento no puede ser nulo");
        }
        return departamentoRepository.save(departamento);
    }

    public Departamento modificarDepartamento(Long id, Departamento departamento) throws Exception {
        if (departamento == null || id == null || !departamentoRepository.existsById(id)) {
            throw new Exception("Departamento no encontrado");
        }
        departamento.setCodigoDepartamento(id);
        return departamentoRepository.save(departamento);
    }

    public void eliminarDepartamento(Long id) throws Exception {
        if (id == null || !departamentoRepository.existsById(id)) {
            throw new Exception("Departamento no encontrado");
        }
        departamentoRepository.deleteById(id);
    }

    // Método para asignar un docente a un departamento
    public Departamento asignarDocente(Long departamentoId, Long docenteId) throws Exception {
        Optional<Departamento> departamentoOpt = departamentoRepository.findById(departamentoId);
        if (!departamentoOpt.isPresent()) {
            throw new Exception("Departamento no encontrado");
        }

        Optional<Docente> docenteOpt = docenteRepository.findById(docenteId);
        if (!docenteOpt.isPresent()) {
            throw new Exception("Docente no encontrado");
        }

        Departamento departamento = departamentoOpt.get();
        Docente docente = docenteOpt.get();

        // Asignar el docente al departamento
        departamento.getDocentes().add(docente);
        return departamentoRepository.save(departamento);
    }
}

