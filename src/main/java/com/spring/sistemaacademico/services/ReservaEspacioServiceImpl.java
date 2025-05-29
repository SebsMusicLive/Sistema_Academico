package com.spring.sistemaacademico.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.sistemaacademico.model.ReservaEspacio;
import com.spring.sistemaacademico.repositories.ReservaEspacioRepository;

@Service
public class ReservaEspacioServiceImpl implements ReservaEspacioService {

    @Autowired
    private ReservaEspacioRepository reservaEspacioRepository;

    @Override
    public List<ReservaEspacio> findAll() {
        return reservaEspacioRepository.findAll();
    }

    @Override
    public ReservaEspacio findById(Long id) {
        return reservaEspacioRepository.findById(id).orElse(null);
    }

    @Override
    public ReservaEspacio save(ReservaEspacio reservaEspacio) {
        return reservaEspacioRepository.save(reservaEspacio);
    }

    @Override
    public void delete(Long id) {
        reservaEspacioRepository.deleteById(id);
    }

    @Override
    public List<ReservaEspacio> findByUsuarioCodigoUsuario(Long codigoUsuario) {
        return reservaEspacioRepository.findByUsuarioCodigoUsuario(codigoUsuario);
    }
}
