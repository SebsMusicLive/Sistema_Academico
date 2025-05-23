package com.spring.sistemaacademico.services;

import org.springframework.data.repository.CrudRepository;
import sistemaAcademico.model.Semestre;

import java.util.List;

public class GenericServiceImpl<T, ID> implements CrudService<T, ID>{

    protected CrudRepository<T, ID> getRepository() {
        return null;
    }

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
    public Semestre findById(ID id) throws Exception {
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
