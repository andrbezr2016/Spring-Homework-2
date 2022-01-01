package com.andrbezr2016.springtask2.service;

import com.andrbezr2016.springtask2.exception.ResourceNotFoundException;
import com.andrbezr2016.springtask2.model.Book;
import com.andrbezr2016.springtask2.model.Customer;
import com.andrbezr2016.springtask2.model.Purchase;
import com.andrbezr2016.springtask2.model.Store;
import com.andrbezr2016.springtask2.repository.BookRepository;
import com.andrbezr2016.springtask2.repository.CustomerRepository;
import com.andrbezr2016.springtask2.repository.PurchaseRepository;
import com.andrbezr2016.springtask2.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository repository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BookRepository bookRepository;

    public void delete(Integer id) throws ResourceNotFoundException {
        Purchase purchase = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Purchase not found for id: " + id));
        Book book = bookRepository.findById(purchase.getBook()).
                orElseThrow(() -> new ResourceNotFoundException("Book not found for id: " + purchase.getBook()));
        book.setQuantity(book.getQuantity() + purchase.getQuantity());
        repository.delete(purchase);
    }

    public Purchase update(Integer id, Purchase newPurchase) throws ResourceNotFoundException {
        Purchase purchase = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Purchase not found for id: " + id));
        if (newPurchase.getDate() == null) newPurchase.setDate(purchase.getDate());
        if (newPurchase.getStore() == 0) newPurchase.setStore(purchase.getStore());
        if (newPurchase.getCustomer() == 0) newPurchase.setCustomer(purchase.getCustomer());
        if (newPurchase.getBook() == 0) newPurchase.setBook(purchase.getBook());
        if (newPurchase.getQuantity() == 0) newPurchase.setQuantity(purchase.getQuantity());
        return updateHelper(newPurchase, purchase);
    }

    public Purchase add(Purchase newPurchase) throws ResourceNotFoundException {
        Book newBook = bookRepository.findById(newPurchase.getBook()).
                orElseThrow(() -> new ResourceNotFoundException("Book not found for id: " + newPurchase.getBook()));
        // Может стать отрицательным
        newBook.setQuantity(newBook.getQuantity() - newPurchase.getQuantity());
        newPurchase.setSum(calculateSum(newBook, newPurchase));
        return repository.save(newPurchase);
    }

    public List<Purchase> getAll() {
        return repository.findAll();
    }

    public Purchase get(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Purchase not found for id: " + id));
    }

    public Purchase replace(Integer id, Purchase newPurchase) throws ResourceNotFoundException {
        Purchase purchase = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Purchase not found for id: " + id));
        return updateHelper(newPurchase, purchase);
    }

    private int calculateSum(Book newBook, Purchase newPurchase) throws ResourceNotFoundException {
        Store store = storeRepository.findById(newPurchase.getStore()).
                orElseThrow(() -> new ResourceNotFoundException("Store not found for id: " + newPurchase.getStore()));
        Customer customer = customerRepository.findById(newPurchase.getCustomer()).
                orElseThrow(() -> new ResourceNotFoundException("Customer not found for id: " + newPurchase.getCustomer()));
        return (int) (newPurchase.getQuantity() * (1 + store.getCommission() / 100d) * (1 - customer.getSale() / 100d) * newBook.getPrice());
    }

    private Purchase updateHelper(Purchase newPurchase, Purchase purchase) throws ResourceNotFoundException {
        Book book = bookRepository.findById(purchase.getBook()).
                orElseThrow(() -> new ResourceNotFoundException("Book not found for id: " + purchase.getBook()));
        Book newBook = book;
        if (purchase.getBook() != newPurchase.getBook()) {
            newBook = bookRepository.findById(newPurchase.getBook()).
                    orElseThrow(() -> new ResourceNotFoundException("Book not found for id: " + newPurchase.getBook()));
            book.setQuantity(book.getQuantity() + purchase.getQuantity());
            // Может стать отрицательным
            newBook.setQuantity(newBook.getQuantity() - newPurchase.getQuantity());
        } else {
            // Может стать отрицательным
            book.setQuantity(book.getQuantity() + (purchase.getQuantity() - newPurchase.getQuantity()));
        }
        purchase.setDate(newPurchase.getDate());
        purchase.setStore(newPurchase.getStore());
        purchase.setCustomer(newPurchase.getCustomer());
        purchase.setBook(newPurchase.getBook());
        purchase.setQuantity(newPurchase.getQuantity());
        purchase.setSum(calculateSum(newBook, newPurchase));
        return repository.save(purchase);
    }
}
