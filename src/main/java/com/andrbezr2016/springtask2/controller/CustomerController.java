package com.andrbezr2016.springtask2.controller;

import com.andrbezr2016.springtask2.exception.ResourceNotFoundException;
import com.andrbezr2016.springtask2.model.Customer;
import com.andrbezr2016.springtask2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @DeleteMapping("{id}")
    public Map<String, Boolean> deleteBook(@PathVariable Integer id) throws ResourceNotFoundException {
        service.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return response;
    }

    @PatchMapping("{id}")
    public Customer updateCustomer(@PathVariable Integer id, @RequestBody Customer newCustomer) throws ResourceNotFoundException {
        return service.update(id, newCustomer);
    }

    @PostMapping
    public Customer addCustomer(@RequestBody Customer newCustomer) {
        return service.add(newCustomer);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(service.get(id));
    }

    @PutMapping("{id}")
    public Customer replaceCustomer(@PathVariable(value = "id") Integer id, @RequestBody Customer newCustomer) throws ResourceNotFoundException {
        return service.replace(id, newCustomer);
    }

    @GetMapping("districts")
    public List<String> getCustomersDistricts() {
        return service.getDistricts();
    }

    @GetMapping("nizhegorodsky")
    public List<Object[]> getNizhegorodskyCustomers() {
        return service.getByResidence("Nizhegorodsky");
    }
}
