package com.spring.sistemaacademico.repositories;

import com.spring.sistemaacademico.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

    // Buscar notificaciones cuyo mensaje contenga texto (ignorando mayúsculas/minúsculas)
    List<Notificacion> findByMensajeContainingIgnoreCase(String mensaje);

    // Buscar notificaciones enviadas en un rango de fechas
    List<Notificacion> findByFechaEnvioBetween(LocalDateTime desde, LocalDateTime hasta);

    // Buscar notificaciones por tipo exacto
    List<Notificacion> findByTipo(String tipo);

    // Buscar notificaciones por estado leído/no leído
    List<Notificacion> findByLeido(boolean leido);

    // Buscar notificaciones recibidas por un usuario específico (por código)
    List<Notificacion> findByReceptor_CodigoUsuario(Long codigoUsuario);

    // Opcional: buscar notificaciones enviadas por un usuario específico (por código)
    List<Notificacion> findByEmisor_CodigoUsuario(Long codigoUsuario);
}