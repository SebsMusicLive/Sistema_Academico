package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Notificacion;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface NotificacionService extends CrudService<Notificacion, Long> {

    List<Notificacion> findByMensaje(String mensaje);

    List<Notificacion> findByFechaEnvio(LocalDateTime desde, LocalDateTime hasta);

    List<Notificacion> findByTipo(String tipo);

    List<Notificacion> findByLeido(boolean leido);

    List<Notificacion> findByReceptor(Long codigoUsuario);

    @Scheduled(fixedRate = 86400000)
    void programarNotificacionAutomatica();

    void enviarNotificacion(); // opcional

    void marcarComoLeida(); // opcional
}
