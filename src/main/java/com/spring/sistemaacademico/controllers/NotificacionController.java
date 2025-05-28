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

    // CRUD BÁSICO

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

        // Cambiado getUsuario() por getReceptor()
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

    // CONSULTAS AVANZADAS

    @GetMapping("/buscar/mensaje")
    public List<Notificacion> getByMensaje(@RequestParam String mensaje) throws Exception {
        return notificacionService.findByMensaje(mensaje);
    }

    @GetMapping("/buscar/fecha")
    public List<Notificacion> getByFecha(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime desde,
                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime hasta) throws Exception {
        return notificacionService.findByFechaEnvio(desde, hasta);
    }

    @GetMapping("/buscar/tipo")
    public List<Notificacion> getByTipo(@RequestParam String tipo) throws Exception {
        return notificacionService.findByTipo(tipo);
    }

    @GetMapping("/buscar/leido")
    public List<Notificacion> getByEstadoLectura(@RequestParam boolean leido) throws Exception {
        return notificacionService.findByLeido(leido);
    }

    @GetMapping("/usuario/{codigoUsuario}")
    public List<Notificacion> getByUsuarioDestino(@PathVariable Long codigoUsuario) throws Exception {
        // Asegúrate de que tu servicio implemente findByReceptor_CodigoUsuario
        return notificacionService.findByReceptor_CodigoUsuario(codigoUsuario);
    }

    @PatchMapping("/{codigoNotificacion}/leer")
    public Notificacion marcarComoLeido(@PathVariable Long codigoNotificacion) throws Exception {
        Optional<Notificacion> optional = notificacionService.findById(codigoNotificacion);
        if (optional.isPresent()) {
            Notificacion notificacion = optional.get();
            notificacion.setLeido(true);
            notificacion.setFechaLectura(LocalDateTime.now());
            return notificacionService.update(notificacion);
        } else {
            throw new Exception("Notificación no encontrada");
        }
    }
}