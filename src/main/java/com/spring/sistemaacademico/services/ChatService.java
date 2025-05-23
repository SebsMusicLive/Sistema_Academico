package com.spring.sistemaacademico.services;

import org.springframework.stereotype.Service;
import sistemaAcademico.model.Chat;
import sistemaAcademico.model.Usuario;

import java.util.Date;
import java.util.List;
import java.util.Optional;



@Service
public interface ChatService extends CrudService<Chat, Long> {

    void iniciarChat(Chat chat) throws Exception;

    void cerrarChat(Long id) throws Exception;

    List<Chat> findByFechaCreacion(Date fechaCreacion) throws Exception;

    List<Chat> findChatsByUsuarioId(Long usuarioId) throws Exception;

    Optional<Chat> obtenerChatEntreUsuarios(Usuario u1, Usuario u2);

    Chat crearChat(Chat chat);

    List<Chat> obtenerChatsPorUsuario(Long usuarioId);
}
