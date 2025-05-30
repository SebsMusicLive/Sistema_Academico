package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Notificacion;
import com.spring.sistemaacademico.repositories.NotificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificacionServiceImpl implements NotificacionService {

    private final NotificacionRepository notificacionRepository;

    @Override
    public List<Notificacion> findAll() {
        return notificacionRepository.findAll();
    }

    @Override
    public Optional<Notificacion> findById(Long id) {
        return notificacionRepository.findById(id);
    }

    @Override
    public Notificacion save(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    @Override
    public Notificacion update(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    @Override
    public void deleteById(Long id) {
        notificacionRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        notificacionRepository.deleteAll();
    }

    @Override
    public List<Notificacion> findByMensaje(String mensaje) {
        return notificacionRepository.findByMensajeContainingIgnoreCase(mensaje);
    }

    @Override
    public List<Notificacion> findByFechaEnvio(LocalDateTime desde, LocalDateTime hasta) {
        return notificacionRepository.findByFechaEnvioBetween(desde, hasta);
    }

    @Override
    public List<Notificacion> findByTipo(String tipo) {
        return notificacionRepository.findByTipo(tipo);
    }

    @Override
    public List<Notificacion> findByLeido(boolean leido) {
        return notificacionRepository.findByLeido(leido);
    }

    @Override
    public List<Notificacion> findByReceptor(Long codigoUsuario) {
        return notificacionRepository.findByReceptor_CodigoUsuario(codigoUsuario);
    }

    @Override
    public void programarNotificacionAutomatica() {
        // implementación opcional
    }

    @Override
    public void enviarNotificacion() {
        // implementación opcional
    }

    @Override
    public void marcarComoLeida() {
        // implementación opcional
    }
}