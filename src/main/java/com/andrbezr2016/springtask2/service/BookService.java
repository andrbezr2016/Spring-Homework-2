package com.andrbezr2016.springtask2.service;

import com.andrbezr2016.springtask2.exception.ResourceNotFoundException;
import com.andrbezr2016.springtask2.model.Book;
import com.andrbezr2016.springtask2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public void delete(Integer id) throws ResourceNotFoundException {
        Book book = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found for id: " + id));
        repository.delete(book);
    }

    public Book update(Integer id, Book newBook) throws ResourceNotFoundException {
        Book book = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found for id: " + id));
        if (newBook.getTitle() != null) book.setTitle(newBook.getTitle());
        if (newBook.getPrice() != 0) book.setPrice(newBook.getPrice());
        if (newBook.getStorage() != null) book.setStorage(newBook.getStorage());
        if (newBook.getQuantity() != 0) book.setQuantity(newBook.getQuantity());
        return repository.save(book);
    }

    public Book add(Book newBook) {
        return repository.save(newBook);
    }

    public List<Book> getAll() {
        return repository.findAll();
    }

    public Book get(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found for id: " + id));
    }

    public Book replace(Integer id, Book newBook) throws ResourceNotFoundException {
        Book book = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found for id: " + id));
        book.setTitle(newBook.getTitle());
        book.setPrice(newBook.getPrice());
        book.setStorage(newBook.getStorage());
        book.setQuantity(newBook.getQuantity());
        return repository.save(book);
    }

    public List<Object[]> getTitlesAndPrices() {
        return repository.findTitleAndPrices();
    }

    public List<Object[]> getWhere(String word, Integer price) {
        return repository.findWhere(word, price);
    }
}
