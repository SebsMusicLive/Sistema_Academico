package com.spring.sistemaacademico.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sistemaAcademico.model.Notificacion;
import sistemaAcademico.repository.NotificacionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificacionServiceImpl implements NotificacionService{

    private final NotificacionRepository notificacionRepository;

    @Override
    public List<Notificacion> findAll() throws Exception {
        return notificacionRepository.findAll();
    }

    @Override
    public Optional<Notificacion> findById(Long id) throws Exception {
        return notificacionRepository.findById(id);
    }

    @Override
    public Notificacion save(Notificacion notificacion) throws Exception {
        return notificacionRepository.save(notificacion);
    }

    @Override
    public Notificacion update(Notificacion notificacion) throws Exception {
        return notificacionRepository.save(notificacion);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        notificacionRepository.deleteById(id);
    }

    @Override
    public void deleteAll() throws Exception {
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
    public List<Notificacion> findByUsuarioDestino(Long codigoUsuarioDestino) {
        return notificacionRepository.findByCodigoUsuarioDestino(codigoUsuarioDestino);
    }
}
