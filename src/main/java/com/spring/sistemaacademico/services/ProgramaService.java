package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Programa;

import java.util.List;

public interface ProgramaService extends CrudService<Programa, Long> {

 List<Programa> findByNombre(String nombre);

 List<Programa> findByDescripcion(String descripcion);

 Programa registrarPrograma(Programa programa) throws Exception;

 void eliminarPrograma(Long id) throws Exception;

 Programa agregarCurso(Long programaId, Long cursoId) throws Exception;

 Programa actualizarContenidos(Long programaId, String nuevosContenidos) throws Exception;
}