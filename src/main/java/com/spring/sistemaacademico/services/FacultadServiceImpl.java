package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Departamento;
import com.spring.sistemaacademico.model.Facultad;
import com.spring.sistemaacademico.repositories.DepartamentoRepository;
import com.spring.sistemaacademico.repositories.FacultadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FacultadServiceImpl implements FacultadService {

    private final FacultadRepository facultadRepository;
    private final DepartamentoRepository departamentoRepository;

    @Override
    public List<Facultad> findAll() {
        return facultadRepository.findAll();
    }

    @Override
    public Optional<Facultad> findById(Long id) {
        return facultadRepository.findById(id);
    }

    @Override
    public Facultad save(Facultad facultad) {
        return facultadRepository.save(facultad);
    }

    @Override
    public Facultad update(Facultad facultad) throws Exception {
        if (facultad.getCodigoFacultad() == null || !facultadRepository.existsById(facultad.getCodigoFacultad())) {
            throw new Exception("Facultad no encontrada");
        }
        return facultadRepository.save(facultad);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        if (!facultadRepository.existsById(id)) {
            throw new Exception("Facultad no encontrada");
        }
        facultadRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        facultadRepository.deleteAll();
    }

    @Override
    public List<Facultad> findByNombre(String nombre) {
        return facultadRepository.findByNombre(nombre);
    }

    @Override
    public Facultad agregarFacultad(Facultad facultad) {
        return facultadRepository.save(facultad);
    }

    @Override
    public Facultad modificarFacultad(Facultad facultad) throws Exception {
        return update(facultad);
    }

    @Override
    public void eliminarFacultad(Long id) throws Exception {
        deleteById(id);
    }

    @Override
    public void crearDepartamento(Long facultadId, Long departamentoId) throws Exception {
        Facultad facultad = facultadRepository.findById(facultadId)
                .orElseThrow(() -> new Exception("Facultad no encontrada"));

        Departamento departamento = departamentoRepository.findById(departamentoId)
                .orElseThrow(() -> new Exception("Departamento no encontrado"));

        departamento.setFacultad(facultad);
        departamentoRepository.save(departamento);
    }

    @Override
    public String generarReporteEstadistico(Long facultadId) {
        Optional<Facultad> facultadOpt = facultadRepository.findById(facultadId);
        if (facultadOpt.isEmpty()) {
            return "Facultad no encontrada";
        }

        Facultad facultad = facultadOpt.get();
        List<Departamento> departamentos = departamentoRepository.findByFacultad(facultad);

        int totalDepartamentos = departamentos.size();
        int totalDocentes = departamentos.stream().mapToInt(d -> d.getDocentes().size()).sum();

        return String.format("Reporte de la Facultad '%s' (ID: %d):\n- Departamentos: %d\n- Docentes: %d",
                facultad.getNombre(), facultadId, totalDepartamentos, totalDocentes);
    }
}
