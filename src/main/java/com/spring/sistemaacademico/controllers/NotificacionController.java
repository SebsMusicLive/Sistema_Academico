package com.spring.sistemaacademico.controllers;

import com.spring.sistemaacademico.model.Notificacion;
import com.spring.sistemaacademico.services.NotificacionEmailService;
import com.spring.sistemaacademico.services.NotificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {

    private final NotificacionService notificacionService;
    private final NotificacionEmailService notificacionEmailService;

    @GetMapping
    public List<Notificacion> getAllNotificaciones() throws Exception {
        return notificacionService.findAll();
    }

    @GetMapping("/{codigoNotificacion}")
    public Optional<Notificacion> getNotificacionById(@PathVariable Long codigoNotificacion) throws Exception {
        return notificacionService.findById(codigoNotificacion);
    }

    @PostMapping
    public Notificacion createNotificacion(@RequestBody Notificacion notificacion) throws Exception {
        Notificacion nuevaNotificacion = notificacionService.save(notificacion);

        String correoDestino = nuevaNotificacion.getReceptor().getCorreo();
        notificacionEmailService.enviarNotificacion(
                correoDestino,
                "Nueva Notificación",
                nuevaNotificacion.getMensaje()
        );

        return nuevaNotificacion;
    }

    @PutMapping("/{codigoNotificacion}")
    public Notificacion updateNotificacion(@PathVariable Long codigoNotificacion,
                                           @RequestBody Notificacion notificacion) throws Exception {
        notificacion.setCodigoNotificacion(codigoNotificacion);
        return notificacionService.update(notificacion);
    }

    @DeleteMapping("/{codigoNotificacion}")
    public void deleteNotificacion(@PathVariable Long codigoNotificacion) throws Exception {
        notificacionService.deleteById(codigoNotificacion);
    }

    @DeleteMapping
    public void deleteAllNotificaciones() throws Exception {
        notificacionService.deleteAll();
    }

    // CONSULTAS PERSONALIZADAS

    @GetMapping("/buscar/mensaje")
    public List<Notificacion> getByMensaje(@RequestParam String mensaje) {
        return notificacionService.findByMensaje(mensaje);
    }

    @GetMapping("/buscar/fecha")
    public List<Notificacion> getByFecha(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime desde,
                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime hasta) {
        return notificacionService.findByFechaEnvio(desde, hasta);
    }

    @GetMapping("/buscar/tipo")
    public List<Notificacion> getByTipo(@RequestParam String tipo) {
        return notificacionService.findByTipo(tipo);
    }

    @GetMapping("/buscar/leido")
    public List<Notificacion> getByEstadoLectura(@RequestParam boolean leido) {
        return notificacionService.findByLeido(leido);
    }

    @GetMapping("/usuario/{codigoUsuario}")
    public List<Notificacion> getByUsuarioDestino(@PathVariable Long codigoUsuario) {
        return notificacionService.findByReceptor(codigoUsuario);
    }

    @PatchMapping("/{codigoNotificacion}/leer")
    public Notificacion marcarComoLeido(@PathVariable Long codigoNotificacion) throws Exception {
        Optional<Notificacion> optional = notificacionService.findById(codigoNotificacion);
        if (optional.isEmpty()) {
            throw new Exception("Notificación no encontrada");
        }
        Notificacion notificacion = optional.get();
        notificacion.setLeido(true);
        notificacion.setFechaLectura(LocalDateTime.now());
        return notificacionService.update(notificacion);
    }
}