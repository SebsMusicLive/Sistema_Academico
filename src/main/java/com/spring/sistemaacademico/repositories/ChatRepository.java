package com.spring.sistemaacademico.repositories;

import com.spring.sistemaacademico.model.Chat;
import com.spring.sistemaacademico.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    // Buscar por fecha de creación exacta
    List<Chat> findByFechaCreacion(Date fechaCreacion);

    // ✅ Buscar todos los chats donde el usuario participe como usuario1 o usuario2
    @Query("SELECT c FROM Chat c WHERE c.codigoUsuario1.codigoUsuario = :usuarioId OR c.codigoUsuario2.codigoUsuario = :usuarioId")
    List<Chat> findByUsuarioParticipante(@Param("usuarioId") Long usuarioId);

    // Buscar un chat específico por orden exacto (usuario1 -> usuario2)
    Optional<Chat> findByCodigoUsuario1AndCodigoUsuario2(Usuario u1, Usuario u2);

    // Buscar un chat específico por orden invertido (usuario2 -> usuario1)
    Optional<Chat> findByCodigoUsuario2AndCodigoUsuario1(Usuario u2, Usuario u1);

    // ✅ Buscar un chat entre dos usuarios sin importar el orden
    @Query("SELECT c FROM Chat c WHERE (c.codigoUsuario1 = :usuario1 AND c.codigoUsuario2 = :usuario2) OR (c.codigoUsuario1 = :usuario2 AND c.codigoUsuario2 = :usuario1)")
    Optional<Chat> findByUsuarios(@Param("usuario1") Usuario usuario1, @Param("usuario2") Usuario usuario2);
}