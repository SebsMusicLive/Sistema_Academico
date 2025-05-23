package com.spring.sistemaacademico.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemaAcademico.model.Mensaje;
import sistemaAcademico.service.MensajeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mensajes")
@RequiredArgsConstructor
public class MensajeController {

    private final MensajeService mensajeService;

    /**
     * Obtiene la lista de todos los mensajes
     */
    @GetMapping
    public List<Mensaje> getAllMensajes() throws Exception {
        return mensajeService.findAll();
    }

    /**
     * Obtiene un mensaje por su ID
     */
    @GetMapping("/{codigoMensaje}")
    public ResponseEntity<Mensaje> getMensajeById(@PathVariable Long codigoMensaje) throws Exception {
        Optional<Mensaje> mensaje = mensajeService.findById(codigoMensaje);
        return mensaje.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    /**
     * Crea un nuevo mensaje
     */
    @PostMapping
    public Mensaje createMensaje(@RequestBody Mensaje mensaje) throws Exception {
        return mensajeService.save(mensaje);
    }

    /**
     * Actualiza un mensaje existente
     */
    @PutMapping("/{codigoMensaje}")
    public Mensaje updateMensaje(@PathVariable Long codigoMensaje, @RequestBody Mensaje mensaje) throws Exception {
        mensaje.setCodigoMensaje(codigoMensaje);
        return mensajeService.update(mensaje);
    }

    /**
     * Elimina un mensaje por ID
     */
    @DeleteMapping("/{codigoMensaje}")
    public ResponseEntity<Void> deleteMensaje(@PathVariable Long codigoMensaje) throws Exception {
        mensajeService.deleteById(codigoMensaje);
        return ResponseEntity.noContent().build();
    }

    /**
     * Elimina todos los mensajes
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteAllMensajes() throws Exception {
        mensajeService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    /**
     * Buscar mensajes por contenido
     */
    @GetMapping("/buscar/contenido")
    public List<Mensaje> buscarPorContenido(@RequestParam String contenido) throws Exception {
        return mensajeService.findByContenido(contenido);
    }

    /**
     * Buscar mensajes por chat
     */
    @GetMapping("/buscar/chat")
    public List<Mensaje> buscarPorChat(@RequestParam Long idChat) throws Exception {
        return mensajeService.findByChatId(idChat);
    }

    /**
     * Obtener todos los mensajes de un chat específico (vía path variable)
     */
    @GetMapping("/chat/{chatId}")
    public List<Mensaje> getMensajesPorChat(@PathVariable Long chatId) throws Exception {
        return mensajeService.obtenerMensajesPorChat(chatId);
    }

    /**
     * Enviar un nuevo mensaje
     */
    @PostMapping("/enviar")
    public ResponseEntity<Mensaje> enviarMensaje(@RequestBody Mensaje mensaje) throws Exception {
        Mensaje enviado = mensajeService.enviarMensaje(mensaje);
        return ResponseEntity.ok(enviado);
    }

    /**
     * Eliminar un mensaje por su ID (otra variante)
     */
    @DeleteMapping("/eliminar/{mensajeId}")
    public ResponseEntity<Void> eliminarMensaje(@PathVariable Long mensajeId) throws Exception {
        mensajeService.eliminarMensaje(mensajeId);
        return ResponseEntity.noContent().build();
    }
}
