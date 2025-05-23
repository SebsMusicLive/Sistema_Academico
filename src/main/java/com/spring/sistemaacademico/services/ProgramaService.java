package com.spring.sistemaacademico.services;

import org.springframework.stereotype.Service;
import sistemaAcademico.model.Programa;

import java.util.List;

@Service
public interface ProgramaService extends CrudService<Programa, Long>{

   public List<Programa> findByNombre(String nombre);

    public List<Programa> findByDescripcion(String descripcion);

    public Programa registrarPrograma(Programa programa) throws Exception;

    public void eliminarPrograma(Long id) throws Exception;

    public Programa agregarCurso(Long programaId, Long cursoId) throws Exception;

    public Programa actualizarContenidos(Long programaId, String nuevosContenidos) throws Exception;
}
