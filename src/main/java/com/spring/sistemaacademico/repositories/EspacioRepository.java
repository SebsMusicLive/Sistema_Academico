package com.spring.sistemaacademico.repositories;

import com.spring.sistemaacademico.model.Espacio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspacioRepository extends JpaRepository<Espacio, Long> {
}