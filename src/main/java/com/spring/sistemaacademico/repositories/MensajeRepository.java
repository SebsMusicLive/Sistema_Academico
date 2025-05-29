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

    List<Mensaje> findByEmisorCodigoUsuario(Long emisorId); // ← CORREGIDO

    List<Mensaje> findByReceptorCodigoUsuario(Long receptorId); // ← CORREGIDO

    List<Mensaje> findByChatCodigoChat(Long chatId); // Esto está bien si Chat tiene 'codigoChat' como PK

    List<Mensaje> findByLeidoFalseAndReceptorCodigoUsuario(Long receptorId); // ← CORREGIDO
}