package com.spring.sistemaacademico.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CrudService<T, ID> {
    List<T> findAll() throws Exception;

    Optional<T> findById(ID id) throws Exception;

    T save(T t) throws Exception;

    T update(T t) throws Exception;

    void deleteById(ID id) throws Exception;

    void deleteAll() throws Exception;

}
