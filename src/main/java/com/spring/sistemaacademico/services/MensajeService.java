package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Mensaje;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public interface MensajeService {

    List<Mensaje> findAll() throws Exception;

    Optional<Mensaje> findById(Long id) throws Exception;

    Mensaje save(Mensaje mensaje) throws Exception;

    Mensaje update(Mensaje mensaje) throws Exception;

    void deleteById(Long id) throws Exception;

    void deleteAll() throws Exception;

    Mensaje enviarMensaje(Mensaje mensaje) throws Exception;

    List<Mensaje> recibirMensajes(Long idReceptor) throws Exception;

    List<Mensaje> findByContenido(String contenido) throws Exception;

    List<Mensaje> findByFechaEnvio(Date fechaEnvio) throws Exception;

    List<Mensaje> findByChatId(Long chatId) throws Exception;

    List<Mensaje> findByEmisorId(Long emisorId) throws Exception;

    List<Mensaje> obtenerMensajesPorChat(Long chatId) throws Exception;
}
