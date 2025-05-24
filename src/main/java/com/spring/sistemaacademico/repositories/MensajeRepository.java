package com.spring.sistemaacademico.repositories;

import com.spring.sistemaacademico.model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {

    List<Mensaje> findByContenidoContainingIgnoreCase(String contenido);

    List<Mensaje> findByFechaEnvio(Date fechaEnvio);

    List<Mensaje> findByEmisorId(Long emisorId);

    List<Mensaje> findByReceptorId(Long receptorId);

    List<Mensaje> findByChatCodigoChat(Long chatId);

    List<Mensaje> findByLeidoFalseAndReceptorId(Long receptorId);
}
