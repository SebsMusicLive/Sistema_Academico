package com.spring.sistemaacademico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sistemaAcademico.model.Chat;
import sistemaAcademico.model.Usuario;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findByFechaCreacion(Date fechaCreacion);

    List<Chat> findByCodigoUsuario1IdOrCodigoUsuario2Id(Long usuario1Id, Long usuario2Id);

    Optional<Chat> findByCodigoUsuario1AndCodigoUsuario2(Usuario u1, Usuario u2);
    Optional<Chat> findByCodigoUsuario2AndCodigoUsuario1(Usuario u2, Usuario u1);
    @Query("SELECT c FROM Chat c WHERE (c.codigoUsuario1 = :usuario1 AND c.codigoUsuario2 = :usuario2) OR (c.codigoUsuario1 = :usuario2 AND c.codigoUsuario2 = :usuario1)")
    Optional<Chat> findByUsuarios(@Param("usuario1") Usuario usuario1, @Param("usuario2") Usuario usuario2);
}
