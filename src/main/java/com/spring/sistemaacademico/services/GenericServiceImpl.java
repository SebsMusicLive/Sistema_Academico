package com.spring.sistemaacademico.services;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public abstract class GenericServiceImpl<T, ID> implements CrudService<T, ID> {

    protected abstract CrudRepository<T, ID> getRepository();

    @Override
    public List<T> findAll() throws Exception {
        try {
            return (List<T>) getRepository().findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public T save(T t) throws Exception {
        try {
            return getRepository().save(t);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public T update(T t) throws Exception {
        try {
            return getRepository().save(t);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Optional<T> findById(ID id) throws Exception {
        try {
            return getRepository().findById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteById(ID id) throws Exception {
        try {
            getRepository().deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteAll() throws Exception {
        try {
            getRepository().deleteAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
