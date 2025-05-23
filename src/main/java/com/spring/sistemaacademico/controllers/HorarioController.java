package com.spring.sistemaacademico.controllers;

import com.spring.sistemaacademico.model.Curso;
import com.spring.sistemaacademico.model.Horario;
import com.spring.sistemaacademico.services.HorarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/horarios")
@RequiredArgsConstructor
public class HorarioController {

    private final HorarioService horarioService;

    /**
     * Obtiene la lista de todos los horarios
     */
    @GetMapping
    public List<Horario> getAllHorarios() throws Exception {
        return horarioService.findAll();
    }

    /**
     * Obtiene un horario por su ID
     */
    @GetMapping("/{codigoHorario}")
    public ResponseEntity<Horario> getHorarioById(@PathVariable Long codigoHorario) throws Exception {
        Optional<Horario> horario = horarioService.findById(codigoHorario);
        return horario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo horario
     */
    @PostMapping
    public ResponseEntity<Horario> createHorario(@RequestBody Horario horario) throws Exception {
        return ResponseEntity.ok(horarioService.save(horario));
    }

    /**
     * Actualiza un horario existente
     */
    @PutMapping("/{codigoHorario}")
    public ResponseEntity<Horario> updateHorario(@PathVariable Long codigoHorario, @RequestBody Horario horario) throws Exception {
        horario.setCodigoHorario(codigoHorario);
        Horario updatedHorario = horarioService.update(horario);
        return ResponseEntity.ok(updatedHorario);
    }

    /**
     * Elimina un horario por ID
     */
    @DeleteMapping("/{codigoHorario}")
    public ResponseEntity<Void> deleteHorario(@PathVariable Long codigoHorario) throws Exception {
        if (horarioService.findById(codigoHorario).isPresent()) {
            horarioService.deleteById(codigoHorario);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Elimina todos los horarios
     */
    @DeleteMapping
    public void deleteAllHorarios() throws Exception {
        horarioService.deleteAll();
    }

    /**
     * Modifica un horario existente con una nueva hora de inicio y fin
     */
    @PutMapping("/{codigoHorario}/modificar")
    public ResponseEntity<Horario> modificarHorario(@PathVariable Long codigoHorario,
                                                    @RequestParam Date nuevaHoraInicio,
                                                    @RequestParam Date nuevaHoraFin) throws Exception {
        Horario horarioModificado = horarioService.modificarHorario(codigoHorario, nuevaHoraInicio, nuevaHoraFin);
        return ResponseEntity.ok(horarioModificado);
    }

    /**
     * Asigna un nuevo horario a un curso
     */
    @PostMapping("/asignar")
    public ResponseEntity<Horario> asignarHorario(@RequestBody Curso curso,
                                                  @RequestParam Date horaInicio,
                                                  @RequestParam Date horaFin,
                                                  @RequestParam String tipoSesion) throws Exception {
        Horario nuevoHorario = horarioService.asignarHorario(curso, horaInicio, horaFin, tipoSesion);
        return ResponseEntity.ok(nuevoHorario);
    }

    /**
     * Verifica si hay disponibilidad de horario para un curso específico
     */
    @GetMapping("/verificarDisponibilidad")
    public ResponseEntity<Boolean> verificarDisponibilidad(@RequestParam Curso curso,
                                                           @RequestParam Date horaInicio,
                                                           @RequestParam Date horaFin) {
        boolean disponible = horarioService.verificarDisponibilidad(curso, horaInicio, horaFin);
        return ResponseEntity.ok(disponible);
    }

    /**
     * Optimiza el uso de recursos (detectando huecos entre sesiones de los cursos)
     */
    @GetMapping("/optimizar")
    public ResponseEntity<List<String>> optimizarRecursos() {
        List<String> recomendaciones = horarioService.optimizarUsoRecursos();
        return ResponseEntity.ok(recomendaciones);
    }

}
