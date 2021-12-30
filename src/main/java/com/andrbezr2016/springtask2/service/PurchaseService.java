package com.andrbezr2016.springtask2.service;

import com.andrbezr2016.springtask2.exception.ResourceNotFoundException;
import com.andrbezr2016.springtask2.model.Purchase;
import com.andrbezr2016.springtask2.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository repository;

    public void delete(Integer id) throws ResourceNotFoundException {
        Purchase purchase = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Purchase not found for id: " + id));
        repository.delete(purchase);
    }

    public Purchase update(Integer id, Purchase newPurchase) throws ResourceNotFoundException {
        Purchase purchase = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Purchase not found for id: " + id));
        if (newPurchase.getDate() != null) purchase.setDate(newPurchase.getDate());
        if (newPurchase.getStore() != 0) purchase.setStore(newPurchase.getStore());
        if (newPurchase.getCustomer() != 0) purchase.setCustomer(newPurchase.getCustomer());
        if (newPurchase.getBook() != 0) purchase.setBook(newPurchase.getBook());
        if (newPurchase.getQuantity() != 0) purchase.setQuantity(newPurchase.getQuantity());
        if (newPurchase.getSum() != 0) purchase.setSum(newPurchase.getSum());
        return repository.save(purchase);
    }

    public Purchase add(Purchase newPurchase) {
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
        purchase.setDate(newPurchase.getDate());
        purchase.setStore(newPurchase.getStore());
        purchase.setCustomer(newPurchase.getCustomer());
        purchase.setBook(newPurchase.getBook());
        purchase.setQuantity(newPurchase.getQuantity());
        purchase.setSum(newPurchase.getSum());
        return repository.save(purchase);
    }
}
