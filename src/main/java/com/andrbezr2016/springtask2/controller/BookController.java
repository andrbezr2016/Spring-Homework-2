package com.andrbezr2016.springtask2.controller;

import com.andrbezr2016.springtask2.exception.ResourceNotFoundException;
import com.andrbezr2016.springtask2.model.Book;
import com.andrbezr2016.springtask2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

    @DeleteMapping("{id}")
    public Map<String, Boolean> deleteBook(@PathVariable Integer id) throws ResourceNotFoundException {
        service.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return response;
    }

    @PatchMapping("{id}")
    public Book updateBook(@PathVariable Integer id, @RequestBody Book newBook) throws ResourceNotFoundException {
        return service.update(id, newBook);
    }

    @PostMapping
    public Book addBook(@RequestBody Book newBook) {
        return service.add(newBook);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> getBook(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(service.get(id));
    }

    @PutMapping("{id}")
    public Book replaceBook(@PathVariable(value = "id") Integer id, @RequestBody Book newBook) throws ResourceNotFoundException {
        return service.replace(id, newBook);
    }

    @GetMapping("titles-and-prices")
    public List<Object[]> getBooksTitlesAndPrices() {
        return service.getTitlesAndPrices();
    }

    @GetMapping("windows-or-20000")
    public List<Object[]> getBooksWhere() {
        return service.getWhere("Windows", 20000);
    }
}
