package com.spring.sistemaacademico.repositories;

import com.spring.sistemaacademico.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

    List<Notificacion> findByMensajeContainingIgnoreCase(String mensaje);

    List<Notificacion> findByFechaEnvioBetween(LocalDateTime desde, LocalDateTime hasta);

    List<Notificacion> findByTipo(String tipo);

    List<Notificacion> findByLeido(boolean leido);

    List<Notificacion> findByCodigoUsuarioDestino(Long codigoUsuarioDestino);
}
