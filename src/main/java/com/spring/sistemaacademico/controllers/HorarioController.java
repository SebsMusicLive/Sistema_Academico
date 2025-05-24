package com.spring.sistemaacademico.controllers;

import com.spring.sistemaacademico.model.Curso;
import com.spring.sistemaacademico.model.Horario;
import com.spring.sistemaacademico.services.HorarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/horarios")
@RequiredArgsConstructor
public class HorarioController {

    private final HorarioService horarioService;

    @GetMapping
    public ResponseEntity<List<Horario>> getAll() {
        return ResponseEntity.ok(horarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Horario> getById(@PathVariable Long id) {
        return horarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Horario> save(@RequestBody Horario horario) {
        return ResponseEntity.status(201).body(horarioService.save(horario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Horario horario) {
        return horarioService.findById(id).map(h -> {
            horario.setCodigoHorario(id);
            try {
                return ResponseEntity.ok(horarioService.update(horario));
            } catch (Exception e) {
                return ResponseEntity.internalServerError().build();
            }
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return horarioService.findById(id).map(h -> {
            horarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        horarioService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/modificar")
    public ResponseEntity<Horario> modificarHorario(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date nuevaHoraInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date nuevaHoraFin
    ) {
        try {
            return ResponseEntity.ok(horarioService.modificarHorario(id, nuevaHoraInicio, nuevaHoraFin));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/asignar")
    public ResponseEntity<Horario> asignarHorario(
            @RequestBody Curso curso,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date horaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date horaFin,
            @RequestParam String tipoSesion
    ) {
        return ResponseEntity.status(201).body(horarioService.asignarHorario(curso, horaInicio, horaFin, tipoSesion));
    }

    @GetMapping("/disponibilidad")
    public ResponseEntity<Boolean> verificarDisponibilidad(
            @RequestBody Curso curso,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date horaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date horaFin
    ) {
        return ResponseEntity.ok(horarioService.verificarDisponibilidad(curso, horaInicio, horaFin));
    }

    @GetMapping("/optimizar")
    public ResponseEntity<List<String>> optimizarRecursos() {
        return ResponseEntity.ok(horarioService.optimizarUsoRecursos());
    }
}
