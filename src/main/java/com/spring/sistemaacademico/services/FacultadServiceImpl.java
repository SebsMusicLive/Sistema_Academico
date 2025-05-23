package com.spring.sistemaacademico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistemaAcademico.model.Departamento;
import sistemaAcademico.model.Facultad;
import sistemaAcademico.model.Semestre;
import sistemaAcademico.repository.DepartamentoRepository;
import sistemaAcademico.repository.FacultadRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FacultadServiceImpl implements FacultadService {

    @Autowired
    private FacultadRepository facultadRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Override
    public List<Facultad> findAll() throws Exception {
        return facultadRepository.findAll();
    }

    @Override
    public Facultad save(Facultad facultad) throws Exception {
        return facultadRepository.save(facultad);
    }

    @Override
    public Facultad update(Facultad facultad) throws Exception {
        if (facultad.getCodigoFacultad() == null || !facultadRepository.existsById(facultad.getCodigoFacultad())) {
            throw new Exception("La facultad no existe o no tiene un ID válido");
        }
        return facultadRepository.save(facultad);
    }

    @Override
    public Semestre findById(Long id) throws Exception {
        return facultadRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        facultadRepository.deleteById(id);
    }

    @Override
    public void deleteAll() throws Exception {
        facultadRepository.deleteAll();
    }

    @Override
    public List<Facultad> findByNombre(String nombre) {
        return facultadRepository.findByNombre(nombre);
    }

    // Implementación de los nuevos métodos

    @Override
    public Facultad agregarFacultad(Facultad facultad) throws Exception {
        if (facultad == null) {
            throw new Exception("Facultad no puede ser nula");
        }
        return facultadRepository.save(facultad);
    }

    @Override
    public Facultad modificarFacultad(Facultad facultad) throws Exception {
        if (facultad.getCodigoFacultad() == null || !facultadRepository.existsById(facultad.getCodigoFacultad())) {
            throw new Exception("Facultad no encontrada para modificar");
        }
        return facultadRepository.save(facultad);
    }

    @Override
    public void eliminarFacultad(Long id) throws Exception {
        if (!facultadRepository.existsById(id)) {
            throw new Exception("Facultad no encontrada para eliminar");
        }
        facultadRepository.deleteById(id);
    }

    @Override
    public void crearDepartamento(Long facultadId, Long departamentoId) throws Exception {
        // Buscar la facultad por su ID (codigoFacultad)
        Optional<Facultad> facultadOpt = facultadRepository.findById(facultadId);
        if (!facultadOpt.isPresent()) {
            throw new Exception("Facultad no encontrada");
        }
        Facultad facultad = facultadOpt.get();

        // Buscar el departamento por su ID (codigoDepartamento)
        Optional<Departamento> departamentoOpt = departamentoRepository.findById(departamentoId);
        if (!departamentoOpt.isPresent()) {
            throw new Exception("Departamento no encontrado");
        }
        Departamento departamento = departamentoOpt.get();

        // Asignar la facultad al departamento
        departamento.setFacultad(facultad);

        // Guardar el departamento con la facultad asociada
        departamentoRepository.save(departamento);
    }

    @Override
    public String generarReporteEstadistico(Long facultadId) {
        // Obtener facultad por ID
        Optional<Facultad> facultadOpt = facultadRepository.findById(facultadId);
        if (!facultadOpt.isPresent()) {
            return "Facultad no encontrada";
        }

        Facultad facultad = facultadOpt.get();

        // Obtener todos los departamentos de la facultad
        List<Departamento> departamentos = departamentoRepository.findByFacultad(facultad);

        // Calcular estadísticas
        int numDepartamentos = departamentos.size();
        int numDocentes = 0;
        for (Departamento departamento : departamentos) {
            numDocentes += departamento.getDocentes().size();
        }

        // Generar reporte con la información obtenida
        return String.format("Reporte de la facultad %s (ID: %d):\n" +
                        "Número de departamentos: %d\n" +
                        "Número de docentes: %d",
                facultad.getNombre(), facultadId, numDepartamentos, numDocentes);
    }
}
