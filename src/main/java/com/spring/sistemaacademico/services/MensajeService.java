package com.spring.sistemaacademico.services;

import org.springframework.stereotype.Service;
import sistemaAcademico.model.Mensaje;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface MensajeService extends CrudService<Mensaje, Long> {

    Mensaje enviarMensaje(Mensaje mensaje) throws Exception;

    Optional<Mensaje> findById(Long id) throws Exception;

    List<Mensaje> recibirMensajes(Long idReceptor) throws Exception;

    List<Mensaje> findByContenido(String contenido) throws Exception;

    List<Mensaje> findByFechaEnvio(Date fechaEnvio) throws Exception;

    List<Mensaje> findByChatId(Long chatId) throws Exception;

    List<Mensaje> findByEmisorId(Long emisorId) throws Exception;

    List<Mensaje> obtenerMensajesPorChat(Long chatId) throws Exception;

    void eliminarMensaje(Long mensajeId) throws Exception;

}
