package com.spring.sistemaacademico.controllers;

import com.spring.sistemaacademico.model.Chat;
import com.spring.sistemaacademico.model.Semestre;
import com.spring.sistemaacademico.services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.spring.sistemaacademico.model.Chat;


import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/chats")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    // ========================== CRUD BÁSICO ==========================

    /**
     * Obtener todos los chats
     */
    @GetMapping
    public List<Chat> getAllChats() throws Exception {
        return chatService.findAll();
    }

    /**
     * Obtener un chat por su ID
     */
    @GetMapping("/{codigoChat}")
    public Semestre getChatById(@PathVariable Long codigoChat) throws Exception {
        return chatService.findById(codigoChat);
    }

    /**
     * Crear un nuevo chat (normal)
     */
    @PostMapping
    public Chat createChat(@RequestBody Chat chat) throws Exception {
        return chatService.save(chat);
    }

    /**
     * Actualizar un chat existente
     */
    @PutMapping("/{codigoChat}")
    public Chat updateChat(@PathVariable Long codigoChat, @RequestBody Chat chat) throws Exception {
        chat.setCodigoChat(codigoChat);
        return chatService.update(chat);
    }

    /**
     * Eliminar un chat por ID
     */
    @DeleteMapping("/{codigoChat}")
    public void deleteChat(@PathVariable Long codigoChat) throws Exception {
        chatService.deleteById(codigoChat);
    }

    /**
     * Eliminar todos los chats
     */
    @DeleteMapping
    public void deleteAllChats() throws Exception {
        chatService.deleteAll();
    }

    // ========================== FUNCIONALIDAD PERSONALIZADA ==========================

    /**
     * Crear un nuevo chat entre dos usuarios (verifica existencia previa)
     */
    @PostMapping("/crear")
    public ResponseEntity<Chat> crearChat(@RequestBody Chat chat) {
        Chat nuevoChat = chatService.crearChat(chat);
        return ResponseEntity.ok(nuevoChat);
    }

    /**
     * Obtener todos los chats en los que participa un usuario
     */
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Chat>> obtenerChatsPorUsuario(@PathVariable Long usuarioId) {
        List<Chat> chats = chatService.obtenerChatsPorUsuario(usuarioId);
        return ResponseEntity.ok(chats);
    }

    /**
     * Buscar chats por fecha de creación (opcional)
     */
    @GetMapping("/buscar/fecha-creacion")
    public List<Chat> getChatsByFechaCreacion(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaCreacion) throws Exception {
        return chatService.findByFechaCreacion(fechaCreacion);
    }
}
