package com.spring.sistemaacademico.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemaAcademico.model.ReservaEspacio;
import sistemaAcademico.service.ReservaEspacioService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaEspacioController {
    private final ReservaEspacioService reservaEspacioService;

    @GetMapping
    public List<ReservaEspacio> getAll() throws Exception {
        return reservaEspacioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaEspacio> getById(@PathVariable Long id) throws Exception {
        Optional<ReservaEspacio> reserva = reservaEspacioService.findById(id);
        return reserva.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ReservaEspacio> save(@RequestBody ReservaEspacio reserva) throws Exception {
        // Verificar si existe traslape en el horario antes de guardar
        if (reservaEspacioService.existeTraslape(reserva.getEspacio().getCodigoEspacio(), reserva.getFechaInicio(), reserva.getFechaFin())) {
            return ResponseEntity.status(400).build(); // Hay un traslape
        }
        return ResponseEntity.ok(reservaEspacioService.save(reserva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaEspacio> update(@PathVariable Long id, @RequestBody ReservaEspacio nuevaReserva) throws Exception {
        Optional<ReservaEspacio> existente = reservaEspacioService.findById(id);
        if (existente.isPresent()) {
            nuevaReserva.setId(id); // Se utiliza el setter generado por Lombok con @Data
            if (reservaEspacioService.existeTraslape(nuevaReserva.getEspacio().getCodigoEspacio(), nuevaReserva.getFechaInicio(), nuevaReserva.getFechaFin())) {
                return ResponseEntity.status(400).build(); // Error: traslape detectado
            }
            return ResponseEntity.ok(reservaEspacioService.update(nuevaReserva));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        if (reservaEspacioService.findById(id).isPresent()) {
            reservaEspacioService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/espacio/{espacioId}")
    public List<ReservaEspacio> findByEspacioId(@PathVariable Long espacioId) {
        return reservaEspacioService.findByEspacioCodigoEspacio(espacioId);
    }

    @GetMapping("/traslape")
    public boolean verificarTraslape(
            @RequestParam Long espacioId,
            @RequestParam String inicio,
            @RequestParam String fin) {

        LocalDateTime fechaInicio = LocalDateTime.parse(inicio);
        LocalDateTime fechaFin = LocalDateTime.parse(fin);

        return reservaEspacioService.existeTraslape(espacioId, fechaInicio, fechaFin);
    }

    @GetMapping("/usuario/{codigoUsuario}")
    public List<ReservaEspacio> getByUsuario(@PathVariable Long codigoUsuario) {
        return reservaEspacioService.findByUsuario(codigoUsuario);
    }

    @GetMapping("/estado/{estado}")
    public List<ReservaEspacio> getByEstado(@PathVariable String estado) {
        return reservaEspacioService.findByEstado(estado);
    }

}
