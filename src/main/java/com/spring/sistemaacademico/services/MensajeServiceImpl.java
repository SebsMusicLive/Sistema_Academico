package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Chat;
import com.spring.sistemaacademico.model.Mensaje;
import com.spring.sistemaacademico.model.Usuario;
import com.spring.sistemaacademico.repositories.ChatRepository;
import com.spring.sistemaacademico.repositories.MensajeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MensajeServiceImpl implements MensajeService {

    private final MensajeRepository mensajeRepository;
    private final ChatRepository chatRepository;
    private final ChatService chatService;

    @Override
    public List<Mensaje> findAll() throws Exception {
        return mensajeRepository.findAll();
    }

    @Override
    public Optional<Mensaje> findById(Long id) throws Exception {
        return mensajeRepository.findById(id);
    }

    @Override
    public Mensaje save(Mensaje mensaje) throws Exception {
        return mensajeRepository.save(mensaje);
    }

    @Override
    public Mensaje update(Mensaje mensaje) throws Exception {
        return mensajeRepository.save(mensaje);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        mensajeRepository.deleteById(id);
    }

    @Override
    public void deleteAll() throws Exception {
        mensajeRepository.deleteAll();
    }

    @Override
    public Mensaje enviarMensaje(Mensaje mensaje) throws Exception {
        mensaje.setFechaEnvio(new Date());
        mensaje.setLeido(false);

        Usuario emisor = mensaje.getEmisor();
        Usuario receptor = mensaje.getReceptor();

        Optional<Chat> chatExistente = chatService.obtenerChatEntreUsuarios(emisor, receptor);
        if (chatExistente.isPresent()) {
            mensaje.setChat(chatExistente.get());
        } else {
            Chat nuevoChat = new Chat();
            nuevoChat.setCodigoUsuario1(emisor);
            nuevoChat.setCodigoUsuario2(receptor);
            nuevoChat.setFechaCreacion(new Date());
            chatRepository.save(nuevoChat);
            mensaje.setChat(nuevoChat);
        }

        return mensajeRepository.save(mensaje);
    }

    @Override
    public List<Mensaje> recibirMensajes(Long idReceptor) throws Exception {
        List<Mensaje> mensajes = mensajeRepository.findByReceptorId(idReceptor);
        for (Mensaje m : mensajes) {
            if (!m.isLeido()) {
                m.setLeido(true);
                mensajeRepository.save(m);
            }
        }
        return mensajes;
    }

    @Override
    public List<Mensaje> findByContenido(String contenido) throws Exception {
        return mensajeRepository.findByContenidoContainingIgnoreCase(contenido);
    }

    @Override
    public List<Mensaje> findByFechaEnvio(Date fechaEnvio) throws Exception {
        return mensajeRepository.findByFechaEnvio(fechaEnvio);
    }

    @Override
    public List<Mensaje> findByChatId(Long chatId) throws Exception {
        return mensajeRepository.findByChatCodigoChat(chatId);
    }

    @Override
    public List<Mensaje> findByEmisorId(Long emisorId) throws Exception {
        return mensajeRepository.findByEmisorId(emisorId);
    }

    @Override
    public List<Mensaje> obtenerMensajesPorChat(Long chatId) throws Exception {
        return mensajeRepository.findByChatCodigoChat(chatId);
    }
}
