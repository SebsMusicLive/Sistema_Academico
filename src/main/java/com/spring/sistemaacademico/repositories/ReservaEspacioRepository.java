package com.spring.sistemaacademico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sistemaAcademico.model.ReservaEspacio;

import java.util.List;

public interface ReservaEspacioRepository extends JpaRepository<ReservaEspacio, Long> {
    List<ReservaEspacio> findByEspacioCodigoEspacio(Long codigoEspacio);
    List<ReservaEspacio> findByUsuarioCodigoUsuario(Long codigoUsuario);
    List<ReservaEspacio> findByEspacioCodigoEspacioAndEstado(Long codigoEspacio, String estado);
    List<ReservaEspacio> findByEstado(String estado);// Para listar todas las "pendientes", por ejemplo
    List<ReservaEspacio> findByUsuario(Long codigoUsuario); // <-- si usas este también

}
