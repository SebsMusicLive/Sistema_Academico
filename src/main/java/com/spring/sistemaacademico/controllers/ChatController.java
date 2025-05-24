package com.spring.sistemaacademico.controllers;



import com.spring.sistemaacademico.model.Chat;
import com.spring.sistemaacademico.services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/chats")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    // Obtener todos los chats
    @GetMapping
    public ResponseEntity<List<Chat>> getAllChats() throws Exception {
        return ResponseEntity.ok(chatService.findAll());
    }

    // Obtener un chat por ID
    @GetMapping("/{codigoChat}")
    public ResponseEntity<Chat> getChatById(@PathVariable Long codigoChat) throws Exception {
        return chatService.findById(codigoChat)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo chat
    @PostMapping
    public ResponseEntity<Chat> createChat(@RequestBody Chat chat) throws Exception {
        return ResponseEntity.status(201).body(chatService.save(chat));
    }

    // Actualizar un chat
    @PutMapping("/{codigoChat}")
    public ResponseEntity<Chat> updateChat(@PathVariable Long codigoChat, @RequestBody Chat chat) throws Exception {
        chat.setCodigoChat(codigoChat);
        return ResponseEntity.ok(chatService.update(chat));
    }

    // Eliminar un chat por ID
    @DeleteMapping("/{codigoChat}")
    public ResponseEntity<Void> deleteChat(@PathVariable Long codigoChat) throws Exception {
        chatService.deleteById(codigoChat);
        return ResponseEntity.noContent().build();
    }

    // Eliminar todos los chats
    @DeleteMapping
    public ResponseEntity<Void> deleteAllChats() throws Exception {
        chatService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    // Crear un nuevo chat entre dos usuarios
    @PostMapping("/crear")
    public ResponseEntity<Chat> crearChat(@RequestBody Chat chat) {
        Chat nuevoChat = chatService.crearChat(chat);
        return ResponseEntity.ok(nuevoChat);
    }

    // Obtener todos los chats de un usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Chat>> obtenerChatsPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(chatService.obtenerChatsPorUsuario(usuarioId));
    }

    // Buscar chats por fecha de creación
    @GetMapping("/buscar/fecha-creacion")
    public ResponseEntity<List<Chat>> getChatsByFechaCreacion(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaCreacion) throws Exception {
        return ResponseEntity.ok(chatService.findByFechaCreacion(fechaCreacion));
    }
}
