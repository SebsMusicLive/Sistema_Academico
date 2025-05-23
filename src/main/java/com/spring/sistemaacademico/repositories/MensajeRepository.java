package com.spring.sistemaacademico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistemaAcademico.model.Mensaje;

import java.util.Date;
import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {

    //public List<Mensaje> findByContenido(String contenido);

    //public List<Mensaje> findByFechaEnvio(Date fechaEnvio);

    List<Mensaje> findByContenidoContainingIgnoreCase(String contenido);

    List<Mensaje> findByFechaEnvio(Date fechaEnvio);

    List<Mensaje> findByEmisorId(Long emisorId);

    List<Mensaje> findByReceptorId(Long receptorId);

    List<Mensaje> findByChatCodigoChat(Long chatId);

    List<Mensaje> findByLeidoFalseAndReceptorId(Long receptorId);

}
