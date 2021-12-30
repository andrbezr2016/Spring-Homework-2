package com.andrbezr2016.springtask2.controller;

import com.andrbezr2016.springtask2.exception.ResourceNotFoundException;
import com.andrbezr2016.springtask2.model.Store;
import com.andrbezr2016.springtask2.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stores")
public class StoreController {

    @Autowired
    private StoreService service;

    @DeleteMapping("{id}")
    public Map<String, Boolean> deleteStore(@PathVariable Integer id) throws ResourceNotFoundException {
        service.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return response;
    }

    @PatchMapping("{id}")
    public Store updateStore(@PathVariable Integer id, @RequestBody Store newStore) throws ResourceNotFoundException {
        return service.update(id, newStore);
    }

    @PostMapping
    public Store addStore(@RequestBody Store newStore) {
        return service.add(newStore);
    }

    @GetMapping
    public List<Store> getAllStores() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Store> getStore(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(service.get(id));
    }

    @PutMapping("{id}")
    public Store replaceStore(@PathVariable(value = "id") Integer id, @RequestBody Store newStore) throws ResourceNotFoundException {
        return service.replace(id, newStore);
    }
}
