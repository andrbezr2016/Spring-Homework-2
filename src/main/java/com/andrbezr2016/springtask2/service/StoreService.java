package com.andrbezr2016.springtask2.service;

import com.andrbezr2016.springtask2.exception.ResourceNotFoundException;
import com.andrbezr2016.springtask2.model.Store;
import com.andrbezr2016.springtask2.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    @Autowired
    private StoreRepository repository;

    public void delete(Integer id) throws ResourceNotFoundException {
        Store store = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Store not found for id: " + id));
        repository.delete(store);
    }

    public Store update(Integer id, Store newStore) throws ResourceNotFoundException {
        Store store = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Store not found for id: " + id));
        if (newStore.getTitle() != null) store.setTitle(newStore.getTitle());
        if (newStore.getLocation() != null) store.setLocation(newStore.getLocation());
        if (newStore.getCommission() != 0) store.setCommission(newStore.getCommission());
        return repository.save(store);
    }

    public Store add(Store newStore) {
        return repository.save(newStore);
    }

    public List<Store> getAll() {
        return repository.findAll();
    }

    public Store get(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Store not found for id: " + id));
    }

    public Store replace(Integer id, Store newStore) throws ResourceNotFoundException {
        Store store = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Store not found for id: " + id));
        store.setTitle(newStore.getTitle());
        store.setLocation(newStore.getLocation());
        store.setCommission(newStore.getCommission());
        return repository.save(store);
    }
}
