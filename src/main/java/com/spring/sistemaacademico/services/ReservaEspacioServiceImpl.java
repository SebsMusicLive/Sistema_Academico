package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.ReservaEspacio;
import com.spring.sistemaacademico.repositories.ReservaEspacioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservaEspacioServiceImpl implements ReservaEspacioService {
    private final ReservaEspacioRepository reservaEspacioRepository;

    @Override
    public List<ReservaEspacio> findAll() {
        return reservaEspacioRepository.findAll();
    }

    @Override
    public ReservaEspacio save(ReservaEspacio reserva) {
        return reservaEspacioRepository.save(reserva);
    }

    @Override
    public ReservaEspacio update(ReservaEspacio reserva) {
        return reservaEspacioRepository.save(reserva);
    }

    @Override
    public Optional<ReservaEspacio> findById(Long id) {
        return reservaEspacioRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        reservaEspacioRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        reservaEspacioRepository.deleteAll();
    }


    public List<ReservaEspacio> findByEspacioId(Long codigoEspacio) {
        return reservaEspacioRepository.findByEspacioCodigoEspacio(codigoEspacio);
    }

    @Override
    public boolean existeTraslape(Long espacioId, LocalDateTime inicio, LocalDateTime fin) {
        List<ReservaEspacio> reservas = findByEspacioId(espacioId);
        return reservas.stream().anyMatch(r ->
                !r.getFechaInicio().isAfter(fin) && !r.getFechaFin().isBefore(inicio)
        );
    }

    public boolean existeTraslapeExcluyendoId(Long espacioId, LocalDateTime inicio, LocalDateTime fin, Long idAExcluir) {
        List<ReservaEspacio> reservas = findByEspacioId(espacioId);
        return reservas.stream().anyMatch(r ->
                !r.getId().equals(idAExcluir) &&
                        !r.getFechaInicio().isAfter(fin) &&
                        !r.getFechaFin().isBefore(inicio)
        );
    }

    @Override
    public List<ReservaEspacio> findByEspacioCodigoEspacio(Long codigoEspacio) {
        return reservaEspacioRepository.findByEspacioCodigoEspacio(codigoEspacio);
    }

    @Override
    public List<ReservaEspacio> findByUsuario(Long codigoUsuario) {
        return reservaEspacioRepository.findByUsuarioCodigoUsuario(codigoUsuario);
    }

    @Override
    public List<ReservaEspacio> findByEstado(String estado) {
        return reservaEspacioRepository.findByEstado(estado);
    }
}

