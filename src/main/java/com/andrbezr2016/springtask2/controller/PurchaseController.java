package com.andrbezr2016.springtask2.controller;

import com.andrbezr2016.springtask2.exception.ResourceNotFoundException;
import com.andrbezr2016.springtask2.model.Purchase;
import com.andrbezr2016.springtask2.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService service;

    @DeleteMapping("{id}")
    public Map<String, Boolean> deletePurchase(@PathVariable Integer id) throws ResourceNotFoundException {
        service.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return response;
    }

    @PatchMapping("{id}")
    public Purchase updatePurchase(@PathVariable Integer id, @RequestBody Purchase newPurchase) throws ResourceNotFoundException {
        return service.update(id, newPurchase);
    }

    @PostMapping
    public Purchase addPurchase(@RequestBody Purchase newPurchase) {
        return service.add(newPurchase);
    }

    @GetMapping
    public List<Purchase> getAllPurchases() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Purchase> getPurchase(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(service.get(id));
    }

    @PutMapping("{id}")
    public Purchase replacePurchase(@PathVariable(value = "id") Integer id, @RequestBody Purchase newPurchase) throws ResourceNotFoundException {
        return service.replace(id, newPurchase);
    }
}
