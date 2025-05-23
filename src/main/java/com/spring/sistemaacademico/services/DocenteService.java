package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Docente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DocenteService extends CrudService<Docente, Long>{
   /* public List<Docente> findByTitulo(String titulo);

    public List<Docente> findByEspecializacion(String especializacion);

    public List<Docente> findByCargaHoraria(int cargaHoraria);*/
   List<Docente> findByNombre(String nombre);

    List<Docente> findByCorreo(String correo);

    List<Docente> findByTelefono(String telefono);

}
