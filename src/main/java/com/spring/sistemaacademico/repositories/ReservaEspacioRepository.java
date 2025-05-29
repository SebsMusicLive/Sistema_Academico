package com.spring.sistemaacademico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.sistemaacademico.model.ReservaEspacio;

@Repository
public interface ReservaEspacioRepository extends JpaRepository<ReservaEspacio, Long> {

    // Línea problemática eliminada:
    // List<ReservaEspacio> findByUsuario(Long codigoUsuario);

    // Correcto: buscar por el códigoUsuario que está dentro de usuario (entidad)
    List<ReservaEspacio> findByUsuarioCodigoUsuario(Long codigoUsuario);

}