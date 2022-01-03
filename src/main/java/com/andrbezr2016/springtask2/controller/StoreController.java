package com.andrbezr2016.springtask2.controller;

import com.andrbezr2016.springtask2.exception.ResourceNotFoundException;
import com.andrbezr2016.springtask2.model.Store;
import com.andrbezr2016.springtask2.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stores")
@Tag(name = "Store Controller")
public class StoreController {

    @Autowired
    private StoreService service;

    @DeleteMapping("{id}")
    @Operation(summary = "Delete store by id")
    public Map<String, Boolean> deleteStore(@PathVariable Integer id) throws ResourceNotFoundException {
        service.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return response;
    }

    @PatchMapping("{id}")
    @Operation(summary = "Update store information by id")
    public Store updateStore(@PathVariable Integer id, @RequestBody Store newStore) throws ResourceNotFoundException {
        return service.update(id, newStore);
    }

    @PostMapping
    @Operation(summary = "Add new store")
    public Store addStore(@RequestBody Store newStore) {
        return service.add(newStore);
    }

    @GetMapping
    @Operation(summary = "Get all stores")
    public List<Store> getAllStores() {
        return service.getAll();
    }

    @GetMapping("{id}")
    @Operation(summary = "Get store by id")
    public ResponseEntity<Store> getStore(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(service.get(id));
    }

    @PutMapping("{id}")
    @Operation(summary = "Replace store by id")
    public Store replaceStore(@PathVariable(value = "id") Integer id, @RequestBody Store newStore) throws ResourceNotFoundException {
        return service.replace(id, newStore);
    }

    @GetMapping("sovetsky")
    @Operation(summary = "Get names of stores from Sovetsky District")
    public List<String> getSovetskyStores() {
        return service.getByLocation("Sovetsky");
    }

    @GetMapping("sormovsky")
    @Operation(summary = "Get names of stores from Sormovsky District")
    public List<String> getSormovskyStores() {
        return service.getByLocation("Sormovsky");
    }

    @GetMapping("customers-sale-between-10-15")
    @Operation(summary = "Get names of stores located in any area, except for Avtozavodsky District, where books were bought by customers with sales between 10% and 15%")
    public List<String> getStoresWhereCustomersWithSaleBetween() {
        return service.getCustomersWithSaleBetween10And15();
    }
}
