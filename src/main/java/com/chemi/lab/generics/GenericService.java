package com.chemi.lab.generics;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public abstract class GenericService<T extends GenericEntity <T>> {

    private final GenericRepository<T> repository;

    public GenericService(GenericRepository<T> repository) {
        this.repository = repository;
    }

    public List<T> getPage(){
        return repository.findAll();
    }

    public T get(String id){
        return repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Id not found")
        );
    }

    @Transactional
    public T update(T updated){
        T dbDomain = get(updated.getId());
        dbDomain.update(updated);

        return repository.save(dbDomain);
    }

    @Transactional
    public T create(T newDomain){
        T dbDomain = newDomain.createNewInstance();
        return repository.save(dbDomain);
    }

    @Transactional
    public void delete(String id){
        //check if object with this id exists
        get(id);
        repository.deleteById(id);
    }
}
