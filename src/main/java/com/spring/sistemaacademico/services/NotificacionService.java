package com.spring.sistemaacademico.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.spring.sistemaacademico.model.Notificacion;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface NotificacionService extends CrudService<Notificacion, Long> {

    void enviarNotificacion();

    void marcarComoLeida();

    @Scheduled(fixedRate = 86400000)
    void programarNotificacionAutomatica();

    List<Notificacion> findByMensaje(String mensaje);

    List<Notificacion> findByFechaEnvio(LocalDateTime desde, LocalDateTime hasta);

    List<Notificacion> findByTipo(String tipo);

    List<Notificacion> findByLeido(boolean leido);

    List<Notificacion> findByUsuarioDestino(Long codigoUsuarioDestino);
}
