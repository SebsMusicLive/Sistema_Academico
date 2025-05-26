package com.spring.sistemaacademico.controllers;

import com.spring.sistemaacademico.model.Mensaje;
import com.spring.sistemaacademico.services.MensajeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mensajes")
@RequiredArgsConstructor
public class MensajeController {

    private final MensajeService mensajeService;

    @GetMapping
    public ResponseEntity<List<Mensaje>> getAll() throws Exception {
        return ResponseEntity.ok(mensajeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mensaje> getById(@PathVariable Long id) throws Exception {
        Optional<Mensaje> mensaje = mensajeService.findById(id);
        return mensaje.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Mensaje> create(@RequestBody Mensaje mensaje) throws Exception {
        Mensaje creado = mensajeService.enviarMensaje(mensaje);
        return ResponseEntity.status(201).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable Long id, @RequestBody Mensaje mensaje) throws Exception {
        Optional<Mensaje> existente = mensajeService.findById(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        mensaje.setCodigoMensaje(id);
        Mensaje actualizado = mensajeService.update(mensaje);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        Optional<Mensaje> existente = mensajeService.findById(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        mensajeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() throws Exception {
        mensajeService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/contenido")
    public ResponseEntity<List<Mensaje>> buscarPorContenido(@RequestParam String contenido) throws Exception {
        return ResponseEntity.ok(mensajeService.findByContenido(contenido));
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<Mensaje>> getMensajesPorChat(@PathVariable Long chatId) throws Exception {
        return ResponseEntity.ok(mensajeService.obtenerMensajesPorChat(chatId));
    }

    @GetMapping("/buscar/emisor")
    public ResponseEntity<List<Mensaje>> buscarPorEmisor(@RequestParam Long idEmisor) throws Exception {
        return ResponseEntity.ok(mensajeService.findByEmisorId(idEmisor));
    }

    @GetMapping("/recibir/{idReceptor}")
    public ResponseEntity<List<Mensaje>> recibirMensajes(@PathVariable Long idReceptor) throws Exception {
        return ResponseEntity.ok(mensajeService.recibirMensajes(idReceptor));
    }
}
